package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.LapPK;
import com.mycompany.iach7.tour.entity.Tourevent;
import com.mycompany.iach7.tour.entity.ToureventPK;
import java.util.List;

/**
 * Handle access for tour events.<br>
 * Whenever a specified Lap attribute changes, a Tourevent is created.
 */
public interface ToureventManager {
    /**
     * Create an initial event entry.
     *
     * @param id   the tour lap id
     * @param user the user who triggered the event
     *
     * @return the created Tourevent or null
     */
    public Tourevent eventInit(LapPK id, String user);

    /**
     * The Providers Scheduled Time of Arrival changed. Update the Tour Lap and insert a new tourevent entry for the
     * changed agentSTA value. If the agentSTA equals the customerSTA update the tour laps syncSta and insert a SyncSTA
     * tourevent
     *
     * @param id        the tour lap id
     * @param user      the user who changed the value
     * @param nAgentSta the new agents scheduled time of arrival value
     *
     * @return the created Tourevent or null
     */
    public Tourevent agentStaChange(LapPK id, String user, String nAgentSta);

    /**
     * The Customers Scheduled Time of Arrival changed. Update the Tour Lap and insert a new tourevent entry for the
     * changed customerSTA value. If the customerSTA equals the agentSTA update the tour laps syncSta and insert a
     * SyncSTA tourevent
     *
     * @param id           the tour lap id
     * @param user         the user who changed the value
     * @param nCustomerSta the new customers scheduled time of arrival value
     *
     * @return the created Tourevent or null
     */
    public Tourevent customerStaChange(LapPK id, String user, String nCustomerSta);

    /**
     * The synchronized Scheduled Time of Arrival changed. Update the Tour Lap and insert a new tourevent entry for the
     * changed syncSTA value
     *
     * @param id       the tour lap id
     * @param user     the user who changed the value
     * @param nSyncSta the new synchronized scheduled time of arrival value
     *
     * @return the created Tourevent or null
     */
    public Tourevent syncStaChange(LapPK id, String user, String nSyncSta);

    /**
     * The Estimated Time of Arrival changed. Update the Tour Lap and insert a new tourevent entry for the changed eta
     * value
     *
     * @param id   the tour lap id
     * @param user the user who changed the value
     * @param nEta the new estimated time of arrival value
     *
     * @return the created Tourevent or null
     */
    public Tourevent etaChange(LapPK id, String user, String nEta);

    /**
     * The unload duration changed. Update the Tour Lap and insert a new tourevent entry for the changed unload duration
     * value
     *
     * @param id      the tour lap id
     * @param user    the user who changed the value
     * @param nUnldur the new unload duration value
     *
     * @return the created Tourevent or null
     */
    public Tourevent unloadDurationChange(LapPK id, String user, String nUnldur);

    /**
     * The gate changed. Update the Tour Lap and insert a new tourevent entry for the changed gate value
     *
     * @param id    the tour lap id
     * @param user  the user who changed the value
     * @param nGate the new gate value
     *
     * @return the created Tourevent or null
     */
    public Tourevent gateChange(LapPK id, String user, String nGate);

    /**
     * Arrival at tour lap destination is reported. Update the Tour Lap to finished. If all Tour Laps are finished now
     * set the corresponding Tour also to finished. Insert a new tourevent entry for the arrival.
     *
     * @param id   the tour lap id
     * @param user the user who changed the value
     *
     * @return the created Tourevent or null
     */
    public Tourevent lapArrivalReported(LapPK id, String user);

    /**
     * Change the ETA of a tour lap and modify the ETA of all following laps accordingly. Insert the needed events
     *
     * @param id   the tour lap id
     * @param user the user who changed the ETA
     * @param nEta the new ETA value
     *
     * @return the modified Tourevent or null
     */
    public Tourevent setupEta(LapPK id, String user, String nEta);

    /**
     * Get all tour events
     *
     * @return the list of all events
     */
    public List<Tourevent> getAll();

    /**
     * Get all tour events filtered by the tourId and lap destination
     *
     * @param tourId  the tourId filter
     * @param lapdest the lap destination
     *
     * @return all matching tour events in a list
     */
    public List<Tourevent> getByTourIdLapdest(String tourId, String lapdest);

    /**
     * Delete a tour event by its Primary Key
     *
     * @param id the key
     */
    public void delete(ToureventPK id);

    /**
     * Get all tour events filtered by the tourId
     *
     * @param tourId the tourId filter
     *
     * @return all matching tour events in a list
     */
    public List<Tourevent> getByTourId(String tourId);
}
