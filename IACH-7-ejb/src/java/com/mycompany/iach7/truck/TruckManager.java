package com.mycompany.iach7.truck;

import com.mycompany.iach7.truck.entity.Truck;
import java.util.List;

/**
 * The truck manager interface.
 */
public interface TruckManager {
    /**
     * Get a list of all trucks.
     *
     * @return all trucks ordered by truckId
     */
    public List<Truck> getAll();

    /**
     * Get a single truck by its id.
     *
     * @param id the trucks id
     *
     * @return the truck or null if a truck does not exist
     */
    public Truck getById(String id);

    /**
     * Get a list of trucks by their truckId.
     *
     * @param truckId the truckId to search for
     *
     * @return the matching trucks
     */
    public List<Truck> getByTruckId(String truckId);

    /**
     * Create a truck; make it persistent.
     *
     * @param truck the truck to persist
     */
    public void create(Truck truck);

    /**
     * Delete a truck by its truckId.
     *
     * @param truckId the truckId
     *
     * @return the deleted truck
     */
    public Truck delete(String truckId);

    /**
     * Get a list of all available trucks.<br>
     * A truck is available if it has no open tour. All assigned tours are finished or no tour is assigned.
     *
     * @return the available trucks
     */
    public List<Truck> getAllAvailable();
}
