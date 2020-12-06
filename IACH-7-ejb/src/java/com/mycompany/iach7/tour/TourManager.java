package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Tour;
import java.util.List;

/**
 * The tour manager interface.
 */
public interface TourManager {
    /**
     * Get a list of all tours.
     *
     * @return all tours ordered by tourId
     */
    public List<Tour> getAll();

    /**
     * Get a single tour by its id.
     *
     * @param tourId the id to search for
     *
     * @return the tour found
     */
    public Tour getById(String tourId);

    /**
     * Get a list of tours by their tourId.
     *
     * @param tourId the tourId pattern e.g. '123%'
     *
     * @return a list of matching tours
     */
    public List<Tour> getByTourId(String tourId);

    /**
     * Get a list of tours that are not finished by their truckId.
     *
     * @param truckId truckId pattern e.g. 'Ab1%'
     *
     * @return a list of not finished matching tours
     */
    public List<Tour> getByTruckIdNotFinished(String truckId);

    /**
     * Get a list of tours by their tourId, truckId and provider (agent).
     *
     * @param tourId   the tourId pattern
     * @param truckId  the truckId pattern
     * @param provider the provider pattern
     *
     * @return a list of matching tours
     */
    public List<Tour> getByQuery(String tourId, String truckId, String provider);

    /**
     * Check if an other tour is active except the given tour for a truck.
     *
     * @param tourId  the current tourId
     * @param truckId the truckId
     *
     * @return true if an active tour for the truck exists; otherwise false
     */
    public boolean otherTourActiveForTruck(String tourId, String truckId);

    /**
     * Create a tour; make it persistent.
     *
     * @param tour the tour
     */
    public void create(Tour tour);

    /**
     * Delete a tour by its tourId.
     *
     * @param tourId the tourId
     *
     * @return the deleted Tour or null if there was no Tour for the given tourId
     */
    public Tour delete(String tourId);
}
