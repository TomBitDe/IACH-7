package com.mycompany.iach7.auditing;

import com.mycompany.iach7.auditing.entity.Audittrail;
import java.util.List;
import javax.ejb.Local;

/**
 * Handle audittrail local access
 */
@Local
public interface AudittrailManager {
    /**
     * Get all audittrail entries in a list
     *
     * @return list of all audittrail entries
     */
    public List<Audittrail> getAll();

    /**
     * Get a single auttrail entry by its id
     *
     * @param id the id of the requested audittrail
     *
     * @return the audittrail entry
     */
    public Audittrail getById(long id);

    /**
     * Search for audittrail entries
     *
     * @param principal the principal to search for
     * @param clazz     the class to search for
     * @param method    the method to search for
     * @param dttm      the date/time to search for in DTTM format
     *
     * @return a list of matching audittrail entries
     */
    public List<Audittrail> getByQuery(String principal, String clazz, String method, String dttm);

    /**
     * Search for audittrail entries in a date/time range
     *
     * @param principal the principal to search for
     * @param clazz     the class to search for
     * @param method    the method to search for
     * @param fromDttm  the start date/time in DTTM format
     * @param toDttm    the end datte/time in DTTM format
     *
     * @return a list of matching audittrail entries
     */
    public List<Audittrail> getByQuery(String principal, String clazz, String method, String fromDttm, String toDttm);

    /**
     * Create an audittrail entry
     *
     * @param aTrail the audittrail entry
     */
    public void create(Audittrail aTrail);

    /**
     * Delete an audittrail entry by its id
     *
     * @param id the id of the entry to delete
     *
     * @return the deleted Audittrail or null if there was no Audittrail for the given id
     */
    public Audittrail delete(long id);

    /**
     * Delete audittrail entry in a date/time range
     *
     * @param from the start date/time in DTTM format
     * @param upto the end date/time in DTTM format
     */
    public void deleteBetween(String from, String upto);
}
