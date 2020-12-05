package com.mycompany.iach7.api.auditing;

import java.util.List;

/**
 * Handle remote access to audittrail entries
 */
public interface AudittrailService {
    /**
     * Get all audittrail entries in a list
     *
     * @return list of all audittrail VO entries
     */
    public List<AudittrailVO> getAll();

    /**
     * Search for audittrail entries
     *
     * @param principal the principal to search for
     * @param clazz     the class to search for
     * @param method    the method to search for
     * @param dttm      the date/time to search for in DTTM format
     *
     * @return a list of matching audittrail VO entries
     */
    public List<AudittrailVO> getByQuery(String principal, String clazz, String method, String dttm);

    /**
     * Search for audittrail entries in a date/time range
     *
     * @param principal the principal to search for
     * @param clazz     the class to search for
     * @param method    the method to search for
     * @param fromDttm  the start date/time in DTTM format
     * @param toDttm    the end datte/time in DTTM format
     *
     * @return a list of matching audittrail VO entries
     */
    public List<AudittrailVO> getByQuery(String principal, String clazz, String method, String fromDttm, String toDttm);

    /**
     * Save an audittrail entry
     *
     * @param aTrailVO the audittrail VO entry
     */
    public void save(AudittrailVO aTrailVO);

    /**
     * Delete an audittrail entry by its VO
     *
     * @param aTrailVO the VO of the entry to delete
     */
    public void delete(AudittrailVO aTrailVO);
}
