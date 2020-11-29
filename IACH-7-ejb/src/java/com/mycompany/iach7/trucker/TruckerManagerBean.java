package com.mycompany.iach7.trucker;

import com.mycompany.iach7.trucker.entity.Trucker;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 * Manage truckers.
 */
@Stateless
@Local(TruckerManager.class)
public class TruckerManagerBean implements TruckerManager {
    private static final Logger LOG = Logger.getLogger(TruckerManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Default constructor.
     */
    public TruckerManagerBean() {
        super();
    }

    @Override
    public List<Trucker> getAll() {
        List<Trucker> truckers;

        truckers = em.createNamedQuery("Trucker.findAll", Trucker.class).getResultList();
        LOG.info("Found Truckers: " + truckers.size());

        return truckers;
    }

    @Override
    public Trucker getById(String id) {
        return em.find(Trucker.class, id);
    }

    @Override
    public List<Trucker> getByTruckerId(String truckerId) {
        List<Trucker> truckers;

        TypedQuery<Trucker> query = em.createNamedQuery("Trucker.findLikeTruckerId", Trucker.class);
        query.setParameter("truckerId", truckerId);
        truckers = query.getResultList();
        LOG.info("Found Truckers: " + truckers.size());

        return truckers;
    }

    @Override
    public void create(Trucker trucker) {
        em.persist(trucker);
    }

    @Override
    public Trucker delete(String truckerId) {
        Trucker trucker = em.find(Trucker.class, truckerId);

        if (trucker == null) {
            LOG.warn("Trucker is null");
        }
        else {
            LOG.debug("Trucker is not null");

            em.remove(trucker);
            LOG.info("Trucker [" + truckerId + "] deleted");
        }

        return trucker;
    }

    @Override
    public List<Trucker> getAllAvailable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
