package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import java.util.List;

/**
 * The lap manager interface.
 */
public interface LapManager {
    /**
     * Get a list of all tour laps.
     *
     * @return a List of all tour laps
     */
    public List<Lap> getAll();

    /**
     * Get a single tour lap by its PK.
     *
     * @param pk the primary key value
     *
     * @return the requested tour lap
     */
    public Lap getById(LapPK pk);

    /**
     * Get a single tour lap by its PK.
     *
     * @param tourId  the tour ID
     * @param lapdest the lap destination
     *
     * @return the requested tour lap
     */
    public Lap getById(String tourId, String lapdest);

    /**
     * Get a list of tour laps by their tourId.
     *
     * @param tourId the tour ID
     *
     * @return a List of matching tour laps
     */
    public List<Lap> getByTourId(String tourId);

    /**
     * Get a list of tourlaps by their tourId and lapdest.
     *
     * @param tourId  the tour ID
     * @param lapdest the tour lap destination
     *
     * @return a List of matching tour laps
     */
    public List<Lap> getByTourIdLapdest(String tourId, String lapdest);

    /**
     * Get a list of tourlaps by their tourId, lapdest and customer.
     *
     * @param tourId   the tour ID
     * @param lapdest  the tour lap destination
     * @param customer the customer
     *
     * @return a List of matching tour laps
     */
    public List<Lap> getByQuery(String tourId, String lapdest, String customer);

    /**
     * Create a tourlap; make it persistent.
     *
     * @param tourlap the tour lap
     */
    public void create(Lap tourlap);

    /**
     * Delete a tourlap by its tourId and lapdest.
     *
     * @param tourId  the tour ID
     * @param lapdest the tour lap destination
     *
     * @return the deleted lap
     */
    public Lap delete(String tourId, String lapdest);
}
