package com.mycompany.iach7.slatime;

import com.mycompany.iach7.api.slatime.SlaTimeService;
import com.mycompany.iach7.api.slatime.SlaTimeVO;
import com.mycompany.iach7.slatime.entity.SlaTime;
import com.mycompany.iach7.slatime.entity.SlaTimePK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 * Handle remote access for Service Level Agreement Times
 */
@Stateless
@Remote(com.mycompany.iach7.api.slatime.SlaTimeService.class)
public class SlaTimeServiceBean implements SlaTimeService {
    private static final Logger log = Logger.getLogger(SlaTimeServiceBean.class.getName());

    @EJB
    private SlaTimeManager slaTimeManager;

    /**
     * Default constructor
     */
    public SlaTimeServiceBean() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(SlaTimeVO slaTimeVO) {
        if (slaTimeVO == null) {
            log.fatal("Invalid PARAM [slaTimeVO is null]");
            return;
        }
        if (slaTimeVO.getProvider().isEmpty() || slaTimeVO.getCustomer().isEmpty()) {
            log.fatal("Invalid PARAM [" + slaTimeVO.toString() + "]");
            return;
        }
        log.info("Save or update " + slaTimeVO.toString());

        SlaTime slaTime;
        if ((slaTime = slaTimeManager.getById(new SlaTimePK(slaTimeVO.getProvider(), slaTimeVO.getCustomer()))) != null) {
            log.info("SlaTime " + slaTime.getId() + " exists");
            slaTime.setSlaTime(slaTimeVO.getSlaTime());
            slaTime.setComment(slaTimeVO.getComment());
            slaTime.setUpdtGuiUser(slaTimeVO.getUpdtGuiUser());
            slaTime.setUpdtDttm(slaTimeVO.getUpdtDttm());
        }
        else {
            slaTime = new SlaTime(
                    new SlaTimePK(slaTimeVO.getProvider(), slaTimeVO.getCustomer()),
                    slaTimeVO.getSlaTime(),
                    slaTimeVO.getComment(),
                    slaTimeVO.getUpdtDttm(),
                    slaTimeVO.getUpdtGuiUser()
            );

            log.info("Insert " + slaTime.toString());
            slaTimeManager.create(slaTime);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(SlaTimeVO slaTimeVO) {
        if (slaTimeVO == null) {
            log.fatal("Invalid PARAM [slaTimeVO is null]");
            return;
        }
        if (slaTimeVO.getProvider().isEmpty() || slaTimeVO.getCustomer().isEmpty()) {
            log.fatal("Invalid PARAM [" + slaTimeVO.toString() + "]");
            return;
        }
        log.info("Delete " + slaTimeVO.toString());

        slaTimeManager.delete(new SlaTimePK(slaTimeVO.getProvider(), slaTimeVO.getCustomer()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(SlaTimeVO slaTimeVO) {
        return slaTimeManager.getById(new SlaTimePK(slaTimeVO.getProvider(), slaTimeVO.getCustomer())) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SlaTimeVO> getAll() {
        List<SlaTime> slaTimeList = slaTimeManager.getAll();
        List<SlaTimeVO> slaTimeVOList = new ArrayList<>();
        SlaTimeVO slaTimeVO;

        for (SlaTime item : slaTimeList) {
            slaTimeVO = new SlaTimeVO(item.getId().getProvider(), item.getId().getCustomer(), item.getSlaTime(), item.getComment(), item.getUpdtDttm(), item.getUpdtGuiUser());
            slaTimeVOList.add(slaTimeVO);
        }

        return slaTimeVOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SlaTimeVO> getAllByQuery(String provider, String customer) {
        List<SlaTime> slaTimeList = slaTimeManager.getSlaTimesByQuery(provider, customer);
        List<SlaTimeVO> slaTimeVOList = new ArrayList<>();
        SlaTimeVO slaTimeVO;

        for (SlaTime item : slaTimeList) {
            slaTimeVO = new SlaTimeVO(item.getId().getProvider(), item.getId().getCustomer(), item.getSlaTime(), item.getComment(), item.getUpdtDttm(), item.getUpdtGuiUser());
            slaTimeVOList.add(slaTimeVO);
        }

        return slaTimeVOList;
    }
}
