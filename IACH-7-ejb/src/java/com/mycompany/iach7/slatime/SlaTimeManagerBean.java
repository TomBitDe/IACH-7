package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.SlaTime;
import com.mycompany.iach7.slatime.entity.SlaTimePK;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage Service Level Agreement Times
 */
@Stateless
@Local(SlaTimeManager.class)
public class SlaTimeManagerBean implements SlaTimeManager {
    private static final Logger LOG = Logger.getLogger(SlaTimeManagerBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     * Default constructor.
     */
    public SlaTimeManagerBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SlaTime> getAll() {
        List<SlaTime> resultList = em.createQuery("select s FROM SlaTime s ORDER BY s.id.provider, s.id.customer").getResultList();
        LOG.info("Found SlaTimes: " + resultList.size());

        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SlaTime> getSlaTimesByProvider(String provider) {
        Query query = em.createQuery("select s FROM SlaTime s WHERE s.id.provider like :provider ORDER BY s.id.provider");
        query.setParameter("provider", provider);

        List<SlaTime> slaTimes = query.getResultList();
        LOG.info("Found SlaTimes: " + slaTimes.size());

        return slaTimes;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SlaTime> getSlaTimesByCustomer(String customer) {
        Query query = em.createQuery("select s FROM SlaTime s WHERE s.id.customer like :customer ORDER BY s.id.customer");
        query.setParameter("customer", customer);

        List<SlaTime> slaTimes = query.getResultList();
        LOG.info("Found SlaTimes: " + slaTimes.size());

        return slaTimes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SlaTime getById(SlaTimePK pk) {
        LOG.info("getById [" + pk + "]");
        return em.find(SlaTime.class, pk);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(SlaTime slaTime) {
        em.persist(slaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SlaTime delete(SlaTimePK id) {
        SlaTime slaTime = em.find(SlaTime.class, id);

        if (slaTime == null) {
            LOG.warn("SlaTime is null");
        }
        else {
            LOG.debug("SlaTime is not null");

            em.remove(slaTime);
            LOG.info("SlaTime [" + id + "] deleted");
        }

        return slaTime;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SlaTime> getSlaTimesByQuery(String provider, String customer) {
        Query query = em.createQuery("select s FROM SlaTime s"
                + " WHERE s.id.provider like :provider"
                + "   AND s.id.customer like :customer"
                + " ORDER BY s.id.provider, s.id.customer");
        query.setParameter("provider", provider);
        query.setParameter("customer", customer);

        List<SlaTime> slaTimes = query.getResultList();
        LOG.info("Found SlaTimes: " + slaTimes.size());

        return slaTimes;
    }
}
