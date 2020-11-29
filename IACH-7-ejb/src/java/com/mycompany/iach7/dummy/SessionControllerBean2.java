package com.mycompany.iach7.dummy;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 * Caller for one injected local EJB
 */
@Stateless
@LocalBean
public class SessionControllerBean2 {
    private static final Logger LOG = Logger.getLogger(SessionControllerBean2.class);

    // CAUTION: two beans implement the same interface NewSession
    //
    @EJB(beanName = "OtherSessionBean")
    private NewSession otherSession;

    public SessionControllerBean2() {
        super();
        LOG.info("--> SessionControllerBean2");
        LOG.info("<-- SessionControllerBean2");
    }

    public String doControl() {
        LOG.info("--> doControl");

        String first = otherSession.businessCall();

        LOG.info("<-- doControl");

        return first;
    }
}
