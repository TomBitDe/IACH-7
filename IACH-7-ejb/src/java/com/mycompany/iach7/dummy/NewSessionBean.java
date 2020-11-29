package com.mycompany.iach7.dummy;

import javax.ejb.Local;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 */
@Stateless
@Local(NewSession.class)
public class NewSessionBean implements NewSession {
    private static final Logger LOG = Logger.getLogger(NewSessionBean.class);
    
    public NewSessionBean() {
        super();
        LOG.info("--> NewSessionBean");
        LOG.info("<-- NewSessionBean");
    }
    
    @Override
    public String businessCall() {
        LOG.info("--> businessCall");
        LOG.info("<-- businessCall");
        return this.getClass().getSimpleName();
    }
}
