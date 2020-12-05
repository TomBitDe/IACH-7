package com.mycompany.iach7.auditing;

import com.mycompany.iach7.auditing.entity.Audittrail;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * Handle audittrail entries
 */
@Stateless
@Local(AudittrailManager.class)
public class AudittrailManagerBean implements AudittrailManager {
    private static final Logger log = Logger.getLogger(AudittrailManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Default constructor
     */
    public AudittrailManagerBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Audittrail> getAll() {
        List<Audittrail> audittrailList = em.createNamedQuery("Audittrail.findAll").getResultList();
        return audittrailList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Audittrail getById(long id) {
        return em.find(Audittrail.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Audittrail> getByQuery(String principal, String clazz, String method, String dttm) {
        List<Audittrail> audittrailList = em.createNamedQuery("Audittrail.findByQuery")
                .setParameter("principal", principal)
                .setParameter("clazz", clazz)
                .setParameter("method", method)
                .setParameter("dttm", dttm)
                .getResultList();
        return audittrailList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Audittrail> getByQuery(String principal, String clazz, String method, String fromDttm, String toDttm) {
        List<Audittrail> audittrailList = em.createNamedQuery("Audittrail.findByBetweenQuery")
                .setParameter("principal", principal)
                .setParameter("clazz", clazz)
                .setParameter("method", method)
                .setParameter("fromDttm", fromDttm)
                .setParameter("toDttm", toDttm)
                .getResultList();
        return audittrailList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Audittrail aTrail) {
        em.persist(aTrail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        Audittrail aTrail = em.find(Audittrail.class, id);

        if (aTrail == null) {
            log.warn("Audittrail is null");
        }
        else {
            log.debug("Audittrail is not null");
        }

        em.remove(aTrail);
        log.info("Audittrail [" + id + "] deleted");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBetween(String from, String upto) {
        log.info("Start DeleteBetween");
        em.createNamedQuery("Audittrail.deleteBetween")
                .setParameter("fromDttm", from)
                .setParameter("toDttm", upto).executeUpdate();
        log.info("DeleteBetween finished");
    }
}
