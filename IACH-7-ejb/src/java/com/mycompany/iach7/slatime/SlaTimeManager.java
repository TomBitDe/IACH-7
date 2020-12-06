package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.SlaTime;
import com.mycompany.iach7.slatime.entity.SlaTimePK;
import java.util.List;
import javax.ejb.Local;

/**
 * Handle local access for Service Level Agreement Times
 */
@Local
public interface SlaTimeManager {
    /**
     * Get a list of all SlaTimes.
     *
     * @return the list of all SLA Times
     */
    public List<SlaTime> getAll();

    /**
     * Get a list of all SlaTimes where their provider is like the provider pattern.
     *
     * @param provider the provider pattern of the SLA Time
     *
     * @return the list of matching SLA Times
     */
    public List<SlaTime> getSlaTimesByProvider(String provider);

    /**
     * Get a list of all SlaTimes where their customer is like the customer pattern.
     *
     * @param customer the customer pattern
     *
     * @return the list of matching SLA Times
     */
    public List<SlaTime> getSlaTimesByCustomer(String customer);

    /**
     * Get SlaTimes by query.
     *
     * @param provider the providers name of the SLA Time
     * @param customer the customer of the provider
     *
     * @return the list of matching SLA Times
     */
    public List<SlaTime> getSlaTimesByQuery(String provider, String customer);

    /**
     * Get a single SlaTime by its id.
     *
     * @param pk the PK id
     *
     * @return the related SLA Time
     */
    public SlaTime getById(SlaTimePK pk);

    /**
     * Delete an SlaTime by its PK id.
     *
     * @param id the PK id
     *
     * @return the deleted SlaTime or null if nothing exists for the given id
     */
    public SlaTime delete(SlaTimePK id);

    /**
     * Create an SlaTime; make it persistent.
     *
     * @param slaTime the SLA Time
     */
    public void create(SlaTime slaTime);
}
