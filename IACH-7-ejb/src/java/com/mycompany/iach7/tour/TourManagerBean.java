package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourstat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage tours.
 */
@Stateless
@Local(TourManager.class)
public class TourManagerBean implements TourManager {
    private static final Logger LOG = Logger.getLogger(TourManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    @EJB
    private LapManager lapManager;

    /**
     * Default constructor.
     */
    public TourManagerBean() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tour> getAll() {
        List<Tour> resultList;

        // Do not use the named query defined in entity Tour because it is not ordered
        // resultList = em.createNamedQuery("Tour.findAll").getResultList();
        resultList = em.createQuery("select x FROM Tour x ORDER BY x.tourId").getResultList();
        LOG.info("Found Tours: " + resultList.size());

        return resultList;
    }

    @Override
    public Tour getById(String tourId) {
        return em.find(Tour.class, tourId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tour> getByTourId(String tourId) {
        Query query = em.createQuery("select x FROM Tour x where x.tourId like :tourId ORDER BY x.tourId");
        query.setParameter("tourId", tourId);

        List<Tour> tours = query.getResultList();
        LOG.info("Found Tours: " + tours.size());

        return tours;
    }

    @Override
    public List<Tour> getByTruckIdNotFinished(String truckId) {
        Query query = em.createQuery("select x FROM Tour x where x.truckId like :truckId AND x.tourstat <> :tourstat");
        query.setParameter("truckId", truckId);
        query.setParameter("tourstat", Tourstat.finished);

        List<Tour> tours = query.getResultList();
        LOG.info("Found Tours: " + tours.size());

        return tours;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tour> getByQuery(String tourId, String truckId, String provider) {
        Query query = em.createQuery("select x FROM Tour x where x.tourId like :tourId and x.truckId like :truckId and x.agent like :agent ORDER BY x.tourId");
        query.setParameter("tourId", tourId);
        query.setParameter("truckId", truckId);
        query.setParameter("agent", provider);

        List<Tour> tours = query.getResultList();
        LOG.info("Found Tours: " + tours.size());

        return tours;
    }

    @Override
    public void create(Tour tour) {
        em.persist(tour);
    }

    @Override
    public Tour delete(String tourId) {
        Tour tour = em.find(Tour.class, tourId);

        if (tour == null) {
            LOG.warn("Tour is null");
        }
        else {
            LOG.debug("Tour is not null");

            List<Lap> tourLaps = lapManager.getByTourId(tourId);
            for (Lap item : tourLaps) {
                lapManager.delete(item.getId().getTourId(), item.getId().getLapdest());
                LOG.info("Lap [" + item.getId().toString() + "] deleted");
            }
            em.remove(tour);
            LOG.info("Tour [" + tourId + "] deleted");
        }

        return tour;
    }

    @Override
    public boolean otherTourActiveForTruck(String tourId, String truckId) {
        boolean ret = false;

        Query query = em.createQuery("select x FROM Tour x where x.tourId <> :tourId AND x.truckId = :truckId AND x.tourstat = :tourstat");
        query.setParameter("tourId", tourId);
        query.setParameter("truckId", truckId);
        query.setParameter("tourstat", Tourstat.active);

        List<Tour> tours = query.getResultList();
        LOG.info("Found other ACTIVE Tours: " + tours.size());

        if (tours.size() > 0) {
            ret = true;
        }

        return ret;
    }
}
