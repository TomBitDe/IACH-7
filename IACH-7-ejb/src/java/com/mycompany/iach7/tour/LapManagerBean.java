package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage tour laps.
 */
@Stateless
@Local(LapManager.class)
public class LapManagerBean implements LapManager {
    private static final Logger LOG = Logger.getLogger(LapManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Default constructor.
     */
    public LapManagerBean() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Lap> getAll() {
        List<Lap> resultList = em.createQuery("select x FROM Lap x ORDER BY x.id.tourId, x.lapSeqn").getResultList();
        LOG.info("Found Laps: " + resultList.size());

        return resultList;
    }

    @Override
    public Lap getById(LapPK pk) {
        return em.find(Lap.class, pk);
    }

    @Override
    public Lap getById(String tourId, String lapdest) {
        LapPK tourhopPK = new LapPK(tourId, lapdest);
        return em.find(Lap.class, tourhopPK);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Lap> getByTourId(String tourId) {
        Query query = em.createQuery("select x FROM Lap x where x.id.tourId like :tourId ORDER BY x.id.tourId, x.lapSeqn");
        query.setParameter("tourId", tourId);

        List<Lap> tourlaps = query.getResultList();
        LOG.info("Found Laps: " + tourlaps.size());

        return tourlaps;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Lap> getByTourIdLapdest(String tourId, String lapdest) {
        Query query;
        query = em.createQuery("select x FROM Lap x where x.id.tourId like :tourId and x.id.lapdest like :lapdest ORDER BY x.id.tourId, x.lapSeqn");
        query.setParameter("tourId", tourId);
        query.setParameter("lapdest", lapdest);

        List<Lap> tourlaps = query.getResultList();
        LOG.info("Found Laps: " + tourlaps.size());

        return tourlaps;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Lap> getByQuery(String tourId, String lapdest, String customer) {
        Query query = em.createQuery("select x FROM Lap x"
                + " WHERE x.id.tourId like :tourId"
                + "   AND x.id.lapdest like :lapdest"
                + "   AND x.customer like :customer"
                + " ORDER BY x.id.tourId, x.lapSeqn");
        query.setParameter("tourId", tourId);
        query.setParameter("lapdest", lapdest);
        query.setParameter("customer", customer);

        List<Lap> tourlaps = query.getResultList();
        LOG.info("Found Laps: " + tourlaps.size());

        return tourlaps;
    }

    @Override
    public void create(Lap tourlap) {
        em.persist(tourlap);
    }

    @Override
    public Lap delete(String tourId, String lapdest) {
        LapPK pk = new LapPK(tourId, lapdest);
        Lap lap = em.find(Lap.class, pk);

        if (lap == null) {
            LOG.warn("Lap is null");
        }
        else {
            LOG.debug("Lap is not null");

            em.remove(lap);
            LOG.info("Lap [" + tourId + "," + lapdest + "] deleted");
        }

        return lap;
    }
}
