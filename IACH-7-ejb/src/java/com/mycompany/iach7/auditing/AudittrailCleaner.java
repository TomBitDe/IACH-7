package com.mycompany.iach7.auditing;

import com.mycompany.iach7.api.auditing.DtIntervalMessage;
import com.mycompany.iach7.api.auditing.KickMessage;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;

/**
 * Cleaner for Audittrail entries
 */
@MessageDriven(mappedName = "jms/queue/AudittrailCleanerQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "AcknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "DestinationType", propertyValue = "javax.jms.Queue")
})
public class AudittrailCleaner implements MessageListener {
    private static final Logger LOG = Logger.getLogger(AudittrailCleaner.class);

    @Resource
    private MessageDrivenContext mdc;

    @EJB
    private AudittrailManager atl;

    public AudittrailCleaner() {
        super();
    }

    /**
     * Start cleanup actions depending on the message.<br>
     * - TextMessage contains two params from date/time and to date/time in DTTM format: delete in the given date/time
     * range<br>
     * - ObjectMessage of type DtIntervalMessage: delete in the given date/time range<br>
     * - ObjectMessage of type KickMessage: do nothing<br>
     *
     * @param message the message to process
     */
    @Override
    public void onMessage(Message message) {
        LOG.info("Message received");

        try {
            if (message instanceof TextMessage) {
                LOG.debug("Process TextMessage");
                TextMessage tMsg = (TextMessage) message;
                LOG.debug("Message content is: " + tMsg.getText());
                String[] param = tMsg.getText().split(";");

                if (param[0] != null && param[1] != null && !param[0].isEmpty() && !param[1].isEmpty()) {
                    LOG.debug("DELETE from " + param[0] + " to " + param[1]);
                    atl.deleteBetween(param[0], param[1]);
                }
                else {
                    LOG.warn("Message has wrong content");
                }
            }
            else if (message instanceof ObjectMessage) {
                LOG.debug("Process ObjectMessage");

                if (((ObjectMessage) message).getObject() instanceof DtIntervalMessage) {
                    DtIntervalMessage dtiMsg = (DtIntervalMessage) ((ObjectMessage) message).getObject();

                    if (!dtiMsg.getFrom().isEmpty() && !dtiMsg.getTo().isEmpty()) {
                        LOG.debug("DELETE from " + dtiMsg.getFrom() + " to " + dtiMsg.getTo());
                        atl.deleteBetween(dtiMsg.getFrom(), dtiMsg.getTo());
                    }
                    else {
                        LOG.warn("Message has wrong content");
                    }
                }
                else if (((ObjectMessage) message).getObject() instanceof KickMessage) {
                    LOG.info("Kick message does nothing ;-)");
                }
                else {
                    LOG.warn("Message of wrong class:" + ((ObjectMessage) message).getObject().getClass().getName());
                }
            }
            if (message != null) {
                LOG.error("Message of wrong type: " + message.getClass().getName());
            }
            else {
                LOG.error("message == null");
            }
        }
        catch (JMSException jmse) {
            LOG.fatal("", jmse);
            mdc.setRollbackOnly();
        }

        LOG.info("Message processed");
    }
}
