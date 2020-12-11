package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.Sla;
import com.mycompany.iach7.slatime.entity.SlaTime;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage Service Level Agreements
 */
@Stateless
@Local(SlaManager.class)
public class SlaManagerBean implements SlaManager {
    private static final Logger LOG = Logger.getLogger(SlaManagerBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public SlaManagerBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Sla sla) {
        em.persist(sla);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sla delete(String id) {
        Sla sla = em.find(Sla.class, id);

        if (sla == null) {
            LOG.warn("Sla is null");
        }
        else {
            LOG.debug("Sla is not null");

            em.remove(sla);
            LOG.info("Sla [" + id + "] deleted");
        }

        return sla;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sla> getAll() {
        List<Sla> resultList = em.createNamedQuery("ServiceLevelAgreement.findAll")
                .getResultList();
        LOG.info("Found Slas: " + resultList.size());

        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sla getById(String id) {
        LOG.info("getById [" + id + "]");
        return em.find(Sla.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sla> getSlaByQuery(String id, String descr) {
        Query query = em.createQuery("select s FROM ServiceLevelAgreement s"
                + " WHERE s.id like :id"
                + "   AND s.descr like :descr"
                + " ORDER BY s.id");
        query.setParameter("id", id);
        query.setParameter("descr", descr);

        List<Sla> slaList = query.getResultList();
        LOG.info("Found Slas: " + slaList.size());

        return slaList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<SlaTime> getSlaTimes(String id) {
        return em.find(Sla.class, id).getSlaTimeItems();
    }
}
