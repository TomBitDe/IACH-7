package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.ServiceLevelAgreement;
import com.mycompany.iach7.slatime.entity.SlaTime;
import java.util.List;

/**
 * Handle local access for Service Level Agreements
 */
public interface SlaManager {
    /**
     * Create an Sla; make it persistent.
     *
     * @param sla the Sla
     */
    public void create(ServiceLevelAgreement sla);

    /**
     * Delete an Sla by its PK id.
     *
     * @param id the PK id
     *
     * @return the deleted Sla or null if nothing exists for the given id
     */
    public ServiceLevelAgreement delete(String id);

    /**
     * Get a list of all Sla's.
     *
     * @return the list of all Sla's
     */
    public List<ServiceLevelAgreement> getAll();

    /**
     * Get a single Sla by its id.
     *
     * @param id the PK id
     *
     * @return the related Sla
     */
    public ServiceLevelAgreement getById(String id);

    /**
     * Get Sla's by query.
     *
     * @param id    the id of the Sla
     * @param descr the description of the Sla
     *
     * @return the list of matching Sla's
     */
    public List<ServiceLevelAgreement> getSlaByQuery(String id, String descr);

    /**
     * Get all SlaTimes using this Sla.
     *
     * @param id the sla id used by the SlaTimes
     *
     * @return the List of matching SlaTimes
     */
    public List<SlaTime> getSlaTimes(String id);
}
