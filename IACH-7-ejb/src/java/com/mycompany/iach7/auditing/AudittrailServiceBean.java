package com.mycompany.iach7.auditing;

import com.mycompany.iach7.api.auditing.AudittrailService;
import com.mycompany.iach7.api.auditing.AudittrailVO;
import com.mycompany.iach7.auditing.entity.Audittrail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 * Handle remote access to audittrail entries
 */
@Stateless
@Remote(com.mycompany.iach7.api.auditing.AudittrailService.class)
public class AudittrailServiceBean implements AudittrailService {
    private static final Logger log = Logger.getLogger(AudittrailServiceBean.class);

    @EJB
    AudittrailManager audittrailLocal;

    /**
     * Default constructor
     */
    public AudittrailServiceBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AudittrailVO> getAll() {
        List<Audittrail> audittrailList = audittrailLocal.getAll();
        List<AudittrailVO> audittrailVOList = new ArrayList<>();
        AudittrailVO audittrailVO;

        for (Audittrail item : audittrailList) {
            audittrailVO = new AudittrailVO(item.getId(), item.getPrincipal(), item.getClazz(), item.getMethod(), item.getDttm());

            audittrailVOList.add(audittrailVO);
        }

        return audittrailVOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AudittrailVO> getByQuery(String principal, String clazz, String method, String dttm) {
        List<Audittrail> audittrailList = audittrailLocal.getByQuery(principal, clazz, method, dttm);
        List<AudittrailVO> audittrailVOList = new ArrayList<>();
        AudittrailVO audittrailVO;

        for (Audittrail item : audittrailList) {
            audittrailVO = new AudittrailVO(item.getId(), item.getPrincipal(), item.getClazz(), item.getMethod(), item.getDttm());

            audittrailVOList.add(audittrailVO);
        }

        return audittrailVOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AudittrailVO> getByQuery(String principal, String clazz, String method, String fromDttm, String toDttm) {
        List<Audittrail> audittrailList = audittrailLocal.getByQuery(principal, clazz, method, fromDttm, toDttm);
        List<AudittrailVO> audittrailVOList = new ArrayList<>();
        AudittrailVO audittrailVO;

        for (Audittrail item : audittrailList) {
            audittrailVO = new AudittrailVO(item.getId(), item.getPrincipal(), item.getClazz(), item.getMethod(), item.getDttm());

            audittrailVOList.add(audittrailVO);
        }

        return audittrailVOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(AudittrailVO aTrailVO) {
        if (aTrailVO == null) {
            log.fatal("Invalid PARAM [aTrailVO is null]");
            return;
        }
        log.info("Save " + aTrailVO.toString());

        Audittrail aTrail = new Audittrail();

        aTrail.setPrincipal(aTrailVO.getPrincipal());
        aTrail.setClazz(aTrailVO.getClazz());
        aTrail.setMethod(aTrailVO.getMethod());
        aTrail.setDttm(aTrailVO.getDttm());

        log.info("Insert " + aTrail.toString());
        audittrailLocal.create(aTrail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(AudittrailVO aTrailVO) {
        if (aTrailVO == null) {
            log.error("Invalid PARAM [aTrailVO == null]");
            return;
        }
        log.info("Delete " + aTrailVO.toString());

        audittrailLocal.delete(aTrailVO.getId());
    }
}
