package com.mycompany.iach7.tour;

import com.mycompany.iach7.auditing.AuditingInterceptor;
import com.mycompany.iach7.tour.entity.Eventstat;
import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import com.mycompany.iach7.tour.entity.Lapeventtype;
import com.mycompany.iach7.tour.entity.Lapstat;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourevent;
import com.mycompany.iach7.tour.entity.ToureventPK;
import com.mycompany.iach7.tour.entity.Tourstat;
import com.mycompany.iach7.util.dttm.DttmCalc;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage tour events.
 */
@Stateless
@Local(ToureventManager.class)
@Interceptors(AuditingInterceptor.class)
public class ToureventManagerBean implements ToureventManager {
    private static final Logger LOG = Logger.getLogger(ToureventManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Default constructor.
     */
    public ToureventManagerBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent agentStaChange(LapPK id, String user, String nAgentSta) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nAgentSta=[" + nAgentSta + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and agentSta value is different
        if (lap != null && !lap.getAgentSta().equals(nAgentSta)) {
            LOG.debug("Lap exists and agentSta changes [" + lap.getAgentSta() + "-->" + nAgentSta + ']');
            // Change lap
            if (nAgentSta.equals(lap.getCustomerSta())) {
                LOG.info("AgentSTA = CustomerSTA --> change syncSTA");
                lap.setSyncSta(nAgentSta);

                // Create a SyncSTA event entry
                tourevent = new Tourevent(
                        id.getTourId(),
                        id.getLapdest(),
                        DttmMakeHelper.makeDttm17(),
                        Lapeventtype.syncsta,
                        "SyncSTA Change",
                        "-",
                        "-",
                        nAgentSta,
                        "-",
                        "-",
                        "-",
                        "Synchronized Scheduled Time of Arrival changed",
                        Eventstat.created,
                        user
                );
                em.persist(tourevent);
                LOG.info("SyncSTA tourevent created");
            }
            else {
                LOG.info("AgentSTA = CustomerSTA --> change AgentSTA");
                lap.setAgentSta(nAgentSta);

                // Create a AgentSTA event entry
                tourevent = new Tourevent(
                        id.getTourId(),
                        id.getLapdest(),
                        DttmMakeHelper.makeDttm17(),
                        Lapeventtype.ownrsta,
                        "AgentSTA change",
                        nAgentSta,
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "Agents Scheduled Time of Arrival changed",
                        Eventstat.created,
                        user
                );
                em.persist(tourevent);
                LOG.info("AgentSTA tourevent created");
            }
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");
        }
        else {
            LOG.info("Lap does not exist OR no change of AgentSTA");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent customerStaChange(LapPK id, String user, String nCustomerSta) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nCustomerSta=[" + nCustomerSta + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and customerSta value is different
        if (lap != null && !lap.getCustomerSta().equals(nCustomerSta)) {
            LOG.debug("Lap exists and customerSta changes [" + lap.getCustomerSta() + "-->" + nCustomerSta + ']');
            // Change lap
            if (nCustomerSta.equals(lap.getAgentSta())) {
                LOG.info("CustomerSTA = AgentSTA --> change syncSTA");
                lap.setSyncSta(nCustomerSta);

                // Create a SyncSTA event entry
                tourevent = new Tourevent(
                        id.getTourId(),
                        id.getLapdest(),
                        DttmMakeHelper.makeDttm17(),
                        Lapeventtype.syncsta,
                        "SyncSTA Change",
                        "-",
                        "-",
                        nCustomerSta,
                        "-",
                        "-",
                        "-",
                        "Synchronized Scheduled Time of Arrival changed",
                        Eventstat.created,
                        user
                );
                em.persist(tourevent);
                LOG.info("SyncSTA tourevent created");
            }
            else {
                LOG.info("CustomerSTA != AgentSTA --> change CustomerSTA");
                lap.setCustomerSta(nCustomerSta);

                // Create an event entry
                tourevent = new Tourevent(
                        id.getTourId(),
                        id.getLapdest(),
                        DttmMakeHelper.makeDttm17(),
                        Lapeventtype.deststa,
                        "CustomerSTA change",
                        "-",
                        nCustomerSta,
                        "-",
                        "-",
                        "-",
                        "-",
                        "Customers Scheduled Time of Arrival changed",
                        Eventstat.created,
                        user
                );
                em.persist(tourevent);
                LOG.info("CustomerSTA tourevent created");
            }
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");
        }
        else {
            LOG.info("Lap does not exist OR no change of CustomerSTA");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent syncStaChange(LapPK id, String user, String nSyncSta) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nSyncSta=[" + nSyncSta + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and syncSta value is different
        if (lap != null && !lap.getSyncSta().equals(nSyncSta)) {
            LOG.debug("Lap exists and syncSta changes [" + lap.getSyncSta() + "-->" + nSyncSta + ']');
            // Change lap
            lap.setSyncSta(nSyncSta);
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");

            // Create an event entry
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.syncsta,
                    "SyncSTA Change",
                    "-",
                    "-",
                    nSyncSta,
                    "-",
                    "-",
                    "-",
                    "Synchronized Scheduled Time of Arrival changed",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("SyncSTA tourevent created");
        }
        else {
            LOG.info("Lap does not exist OR no change of SyncSTA");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent etaChange(LapPK id, String user, String nEta) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nEta=[" + nEta + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and ETA value is different
        if (lap != null && !lap.getEta().equals(nEta)) {
            LOG.debug("Lap exists and eta changes [" + lap.getEta() + "-->" + nEta + ']');
            // Change lap
            lap.setEta(nEta);
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");

            // Create an event entry
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.actueta,
                    "ETA Change",
                    "-",
                    "-",
                    "-",
                    nEta,
                    "-",
                    "-",
                    "Estimated Time of Arrival changed",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("ETA tourevent created");
        }
        else {
            LOG.info("Lap does not exist OR no change of ETA");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent unloadDurationChange(LapPK id, String user, String nUnldur) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nUnldur=[" + nUnldur + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and gate value is different
        if (lap != null && !lap.getUnldur().equals(nUnldur)) {
            LOG.debug("Lap exists and unldur changes [" + lap.getUnldur() + "-->" + nUnldur + ']');
            // Change lap
            lap.setUnldur(nUnldur);
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");

            // Create an event entry
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.actuunl,
                    "UNLDUR Change",
                    "-",
                    "-",
                    "-",
                    "-",
                    nUnldur,
                    "-",
                    "Unload duration changed",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("UNLDUR tourevent created");
        }
        else {
            LOG.info("Lap does not exist OR no change of UNLDUR");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent gateChange(LapPK id, String user, String nGate) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nGate=[" + nGate + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists and gate value is different
        if (lap != null && !lap.getGate().equals(nGate)) {
            LOG.debug("Lap exists and gate changes [" + lap.getGate() + "-->" + nGate + ']');
            // Change lap
            lap.setGate(nGate);
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");

            // Create an event entry
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.aktugte,
                    "GATE Change",
                    "-",
                    "-",
                    "-",
                    "-",
                    "-",
                    nGate,
                    "Gate changed",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("GATE tourevent created");
        }
        else {
            LOG.info("Lap does not exist OR no change of GATE");
        }
        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent lapArrivalReported(LapPK id, String user) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Update the Lap to finished
        if (lap != null) {
            LOG.debug("Lap exists and arrival reported");
            // Change lap
            lap.setLapstat(Lapstat.finished);
            lap.setUpdtDttm(DttmMakeHelper.makeDttm());
            lap.setUpdtGuiUser(user);
            LOG.debug("Lap changed");
        }

        // Finish the Tour if needed
        Query query = em.createQuery("select x FROM Lap x"
                + " where x.id = :id"
                + " and x.lapstat <> :lapstat");

        query.setParameter("id", id);
        query.setParameter("lapstat", Lapstat.finished);

        // Find a lap in the tour where the hopstat is not finished
        List<Lap> laps = query.getResultList();
        LOG.info("Found Laps: " + laps.size());

        if (laps.isEmpty()) {
            // All finished
            Tour tour = em.find(Tour.class, id.getTourId());

            tour.setTourstat(Tourstat.finished);
            tour.setUpdtGuiUser(user);
            tour.setUpdtDttm(DttmMakeHelper.makeDttm());
        }

        // Create a Tourevent for the arrival
        if (lap != null) {
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.arrival,
                    "ARRIVAL at lap",
                    "-",
                    "-",
                    "-",
                    "-",
                    "-",
                    "-",
                    "Arrival",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("ARRIVAL tourevent created");
        }

        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent setupEta(LapPK id, String user, String nEta) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + "] nEta=[" + nEta + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check if lap exists
        if (lap != null) {
            // Set the corrsponding tour to active if not already done
            Tour tour = em.find(Tour.class, id.getTourId());

            if (tour == null) {
                LOG.fatal("NO Tour for Lap=[" + id.toString() + ']');
                return tourevent;
            }

            if (tour.getTourstat() != Tourstat.active) {
                tour.setTourstat(Tourstat.active);
                tour.setUpdtGuiUser(user);
                tour.setUpdtDttm(DttmMakeHelper.makeDttm());
            }

            // Calculate ETA difference of nEta to old value for updating other
            // lap eta values
            long etaDiffMilli = DttmCalc.dttmDiff14(nEta, lap.getEta());
            LOG.info(nEta + " - " + lap.getEta() + " = " + etaDiffMilli);

            // Create a etaChange event for the lap
            tourevent = etaChange(id, user, nEta);

            // Update following laps eta values
            Query query = em.createQuery("select x FROM Lap x"
                    + " where x.id.tourId = :tourId"
                    + " and x.lapSeqn > :lapSeqn"
                    + " order by x.id.tourId, x.lapSeqn desc");

            query.setParameter("tourId", id.getTourId());
            query.setParameter("lapSeqn", lap.getLapSeqn());

            List<Lap> laps = query.getResultList();
            LOG.info("Found Laps: " + laps.size());

            if (!laps.isEmpty()) {
                String cEta;

                for (Lap item : laps) {
                    // Calc the new eta
                    cEta = DttmCalc.dttmAdd14(item.getEta(), etaDiffMilli);

                    // Create an etaChange event
                    if (etaChange(item.getId(), user, cEta) == null) {
                        LOG.fatal("Creating an ETA change event for Lap=[" + id + "] FAILED");
                    }
                }
            }
        }

        return tourevent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tourevent> getAll() {
        List<Tourevent> toureventList = em.createNamedQuery("Tourevent.findAll").getResultList();
        return toureventList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToureventPK delete(ToureventPK id) {
        Tourevent tourevent = em.find(Tourevent.class, id);

        try {
            if (tourevent == null) {
                LOG.warn("Tourevent is null");

                return null;
            }
            else {
                LOG.debug("Tourevent is not null");

                em.remove(tourevent);
                LOG.info("Tourevent [" + tourevent.getId().toString() + "] deleted");

                return tourevent.getId();
            }
        }
        catch (Exception ex) {
            LOG.error(ex);
            throw new EJBException("Error while deleting Tourevent [" + id.toString() + "]");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tourevent> getByTourIdLapdest(String tourId, String lapdest) {
        List<Tourevent> toureventList = em.createNamedQuery("Tourevent.findByTourIdLapdest")
                .setParameter("tourId", tourId)
                .setParameter("lapdest", lapdest)
                .getResultList();
        return toureventList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tourevent> getByTourId(String tourId) {
        List<Tourevent> toureventList = em.createNamedQuery("Tourevent.findByTourId")
                .setParameter("tourId", tourId)
                .getResultList();
        return toureventList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tourevent eventInit(LapPK id, String user) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + ']');

        Tourevent tourevent = null;
        Lap lap = em.find(Lap.class, id);

        // Check lap exists
        if (lap != null) {
            LOG.debug("Lap exists");
            // Create an event entry
            tourevent = new Tourevent(
                    id.getTourId(),
                    id.getLapdest(),
                    DttmMakeHelper.makeDttm17(),
                    Lapeventtype.hopinit,
                    "Hop init",
                    lap.getAgentSta(),
                    lap.getCustomerSta(),
                    lap.getSyncSta(),
                    lap.getEta(),
                    lap.getUnldur(),
                    lap.getGate(),
                    "Hop initialized",
                    Eventstat.created,
                    user
            );
            em.persist(tourevent);
            LOG.info("Tourevent init");
        }
        else {
            LOG.info("Lap does not exist");
        }
        return tourevent;
    }
}
