package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.Sla;
import com.mycompany.iach7.slatime.entity.SlaTime;
import java.util.List;
import java.util.Set;

/**
 * Handle local access for Service Level Agreements
 */
public interface SlaManager {
    /**
     * Create an Sla; make it persistent.
     *
     * @param sla the Sla
     */
    public void create(Sla sla);

    /**
     * Delete an Sla by its PK id.
     *
     * @param id the PK id
     *
     * @return the deleted Sla or null if nothing exists for the given id
     */
    public Sla delete(String id);

    /**
     * Get a list of all Sla's.
     *
     * @return the list of all Sla's
     */
    public List<Sla> getAll();

    /**
     * Get a single Sla by its id.
     *
     * @param id the PK id
     *
     * @return the related Sla
     */
    public Sla getById(String id);

    /**
     * Get Sla's by query.
     *
     * @param id    the id of the Sla
     * @param descr the description of the Sla
     *
     * @return the list of matching Sla's
     */
    public List<Sla> getSlaByQuery(String id, String descr);

    /**
     * Get all SlaTimes using this Sla.
     *
     * @param id the sla id used by the SlaTimes
     *
     * @return the Set of matching SlaTimes
     */
    public Set<SlaTime> getSlaTimes(String id);
}
