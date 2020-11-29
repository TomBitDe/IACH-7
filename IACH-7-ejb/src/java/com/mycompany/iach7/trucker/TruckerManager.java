package com.mycompany.iach7.trucker;

import com.mycompany.iach7.trucker.entity.Trucker;
import java.util.List;

/**
 * The truck manager interface.
 */
public interface TruckerManager {
    /**
     * Get a list of all truckers.
     *
     * @return all truckers ordered by truckerId
     */
    public List<Trucker> getAll();

    /**
     * Get a single trucker by its id.
     *
     * @param id the trucker id
     *
     * @return the trucker or null if a trucker does not exist
     */
    public Trucker getById(String id);

    /**
     * Get a list of truckers by their truckerId.
     *
     * @param truckerId the truckerId to search for
     *
     * @return the matching truckers
     */
    public List<Trucker> getByTruckerId(String truckerId);

    /**
     * Create a trucker; make it persistent.
     *
     * @param trucker the truck to persist
     */
    public void create(Trucker trucker);

    /**
     * Delete a trucker by its truckerId.
     *
     * @param truckerId the truckerId
     *
     * @return the deleted trucker
     */
    public Trucker delete(String truckerId);

    /**
     * Get a list of all available truckers.<br>
     * A trucker is available if it has no open tour. All assigned tours are finished or no tour is assigned.
     *
     * @return the available trucks
     */
    public List<Trucker> getAllAvailable();
}
