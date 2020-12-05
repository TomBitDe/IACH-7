package com.mycompany.iach7.auditing;

import com.mycompany.iach7.auditing.entity.Audittrail;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Logger;

/**
 * Auditing interceptor to save relevant data in an Audittrail on method call.
 */
public class AuditingInterceptor {
    private static final Logger log = Logger.getLogger(AuditingInterceptor.class);

    // SessionContext is needed to get the user
    @Resource
    private SessionContext sctx;

    // Use the bean to handle auditing activities
    @EJB
    private AudittrailManager auditingInterceptorLocal;

    /**
     * On method call create an audittrail entry.
     *
     * @param context the invocation context; needed to get class/method information
     *
     * @return context to proceed the next method in the chain
     *
     * @throws Exception any exception
     */
    @AroundInvoke
    public Object onMethodCall(InvocationContext context) throws Exception {
        // Tell what the interceptor has for information on method call
        log.info("Intercepted class/method=[" + context.getMethod().getDeclaringClass().getName()
                + "/" + context.getMethod().getName()
                + "] called from principal=["
                + sctx.getCallerPrincipal().getName()
                + ']');

        // Create a new audit trail entry
        Audittrail aTrail = new Audittrail(sctx.getCallerPrincipal().getName(),
                                           context.getMethod().getDeclaringClass().getName(),
                                           context.getMethod().getName(),
                                           DttmMakeHelper.makeDttm17());

        // Persist the audittrail item
        auditingInterceptorLocal.create(aTrail);

        return context.proceed();
    }
}
