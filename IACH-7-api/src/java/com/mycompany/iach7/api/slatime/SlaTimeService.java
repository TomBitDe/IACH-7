package com.mycompany.iach7.api.slatime;

import java.util.List;

/**
 * Handle remote access for SLA Times
 */
public interface SlaTimeService {
    /**
     * Save an SLA Time if it is not existing else update the entry
     *
     * @param slaTimeVO the SLA Time VO
     */
    public void saveOrUpdate(SlaTimeVO slaTimeVO);

    /**
     * Delete the SLA Time entry
     *
     * @param slaTimeVO the SLA Time VO to delete
     */
    public void delete(SlaTimeVO slaTimeVO);

    /**
     * Check if an SLA Time already exists as entry
     *
     * @param slaTimeVO the SLA Time VO to check
     *
     * @return true if it exists else false
     */
    public boolean exists(SlaTimeVO slaTimeVO);

    /**
     * Return a list of all SLA Times
     *
     * @return the list of all SLA Time VO's
     */
    public List<SlaTimeVO> getAll();

    /**
     * Return a list of all SLA Times matching the query parameters
     *
     * @param provider the provider to search for
     * @param customer the customer to search for
     *
     * @return the list of matching SLA Time VO's
     */
    public List<SlaTimeVO> getAllByQuery(String provider, String customer);
}
