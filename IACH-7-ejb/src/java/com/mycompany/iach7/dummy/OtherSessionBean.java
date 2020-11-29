package com.mycompany.iach7.dummy;

import javax.ejb.Local;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 */
@Stateless
@Local(NewSession.class)
public class OtherSessionBean implements NewSession {
    private static final Logger LOG = Logger.getLogger(OtherSessionBean.class);
    
    public OtherSessionBean() {
        super();
        LOG.info("--> OtherSessionBean");
        LOG.info("<-- OtherSessionBean");
    }
    
    @Override
    public String businessCall() {
        LOG.info("--> businessCall");
        LOG.info("<-- businessCall");
        return this.getClass().getSimpleName();
    }
}
