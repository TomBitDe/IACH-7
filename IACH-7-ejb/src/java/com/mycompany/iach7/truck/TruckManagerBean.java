package com.mycompany.iach7.truck;

import com.mycompany.iach7.tour.TourManager;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.truck.entity.Truck;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage trucks.
 */
@Stateless
@Local(TruckManager.class)
public class TruckManagerBean implements TruckManager {
    private static final Logger LOG = Logger.getLogger(TruckManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    @EJB
    TourManager tourMngr;

    /**
     * Default constructor.
     */
    public TruckManagerBean() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Truck> getAll() {
        List<Truck> resultList = em.createQuery("select x FROM Truck x ORDER BY x.truckId").getResultList();
        LOG.info("Found Trucks: " + resultList.size());

        return resultList;
    }

    @Override
    public Truck getById(String id) {
        return em.find(Truck.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Truck> getByTruckId(String truckId) {
        Query query = em.createQuery("select x FROM Truck x where x.truckId like :truckId ORDER BY x.truckId");
        query.setParameter("truckId", truckId);

        List<Truck> trucks = query.getResultList();
        LOG.info("Found Trucks: " + trucks.size());

        return trucks;
    }

    @Override
    public void create(Truck truck) {
        em.persist(truck);
    }

    @Override
    public Truck delete(String truckId) {
        Truck truck = em.find(Truck.class, truckId);

        if (truck == null) {
            LOG.warn("Truck is null");
        }
        else {
            LOG.debug("Truck is not null");

            em.remove(truck);
            LOG.info("Truck [" + truckId + "] deleted");
        }

        return truck;
    }

    @Override
    public List<Truck> getAllAvailable() {
        List<Truck> avail = new ArrayList();

        List<Truck> all = getAll();

        for (Truck truck : all) {
            List<Tour> tours = tourMngr.getByTruckIdNotFinished(truck.getTruckId());
            if (tours.isEmpty()) {
                avail.add(truck);
            }
        }
        LOG.info("Found available Trucks: " + avail.size());

        return avail;
    }
}
