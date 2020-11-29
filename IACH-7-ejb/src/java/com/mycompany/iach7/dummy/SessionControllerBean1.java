package com.mycompany.iach7.dummy;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 * Caller for two injected local EJBs
 */
@Stateless
@LocalBean
public class SessionControllerBean1 {
    private static final Logger LOG = Logger.getLogger(SessionControllerBean1.class);

    // CAUTION: both beans implement the same interface NewSession
    // Without beanName="..." the depoyment fails because of 'there are [2] ejbs in the application with interface'
    @EJB(beanName = "NewSessionBean")
    private NewSession newSession;
    @EJB(beanName = "OtherSessionBean")
    private NewSession otherSession;

    public SessionControllerBean1() {
        super();
        LOG.info("--> SessionControllerBean1");
        LOG.info("<-- SessionControllerBean1");
    }

    public String doControl() {
        LOG.info("--> doControl");

        String first = newSession.businessCall();
        String second = otherSession.businessCall();

        LOG.info("<-- doControl");

        return first + " " + second;
    }
}
