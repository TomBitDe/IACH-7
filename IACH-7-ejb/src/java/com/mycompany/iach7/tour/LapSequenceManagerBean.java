package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Manage tour lap sequences.
 */
@Stateless
@Local(LapSequenceManager.class)
public class LapSequenceManagerBean implements LapSequenceManager {
    private static final Logger LOG = Logger.getLogger(LapSequenceManagerBean.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean moveUp(LapPK id, String user) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + ']');

        Lap lap = em.find(Lap.class, id);

        if (lap == null) {
            return false;
        }
        else {
            LOG.debug("Lap exists now try moveUp");

            Query query = em.createQuery("select x FROM Lap x"
                    + " where x.id.tourId = :tourId"
                    + " and x.lapSeqn < :lapSeqn"
                    + " order by x.id.tourId, x.lapSeqn desc");

            query.setParameter("tourId", id.getTourId());
            query.setParameter("lapSeqn", lap.getLapSeqn());

            // Find a lap in the tour where before the actual hop
            List<Lap> beforehops = query.getResultList();
            LOG.info("Found Laps: " + beforehops.size());

            if (beforehops.isEmpty()) {
                return false;
            }
            else {
                // Hop before exists; switch data now
                Lap beforehop = beforehops.get(0);

                int tmpHopseqn = lap.getLapSeqn();
                lap.setLapSeqn(beforehop.getLapSeqn());
                beforehop.setLapSeqn(tmpHopseqn);

                String tmpEta = lap.getEta();
                lap.setEta(beforehop.getEta());
                beforehop.setEta(tmpEta);

                lap.setUpdtGuiUser(user);
                lap.setUpdtDttm(DttmMakeHelper.makeDttm());

                beforehop.setUpdtGuiUser(user);
                beforehop.setUpdtDttm(DttmMakeHelper.makeDttm());

                return true;
            }
        }
    }

    @Override
    public boolean moveDown(LapPK id, String user) {
        LOG.info("id=[" + id.toString() + "] user=[" + user + ']');

        Lap lap = em.find(Lap.class, id);

        if (lap == null) {
            return false;
        }
        else {
            LOG.debug("Lap exists now try moveDown");

            Query query = em.createQuery("select x FROM Lap x"
                    + " where x.id.tourId = :tourId"
                    + " and x.lapSeqn > :lapSeqn"
                    + " order by x.id.tourId, x.lapSeqn asc");

            query.setParameter("tourId", id.getTourId());
            query.setParameter("lapSeqn", lap.getLapSeqn());

            // Find a lap in the tour where after the actual hop
            List<Lap> afterhops = query.getResultList();
            LOG.info("Found Laps: " + afterhops.size());

            if (afterhops.isEmpty()) {
                return false;
            }
            else {
                // Lap after exists; switch data now
                Lap afterhop = afterhops.get(0);

                int tmpHopseqn = lap.getLapSeqn();
                lap.setLapSeqn(afterhop.getLapSeqn());
                afterhop.setLapSeqn(tmpHopseqn);

                String tmpEta = lap.getEta();
                lap.setEta(afterhop.getEta());
                afterhop.setEta(tmpEta);

                lap.setUpdtGuiUser(user);
                lap.setUpdtDttm(DttmMakeHelper.makeDttm());

                afterhop.setUpdtGuiUser(user);
                afterhop.setUpdtDttm(DttmMakeHelper.makeDttm());

                return true;
            }
        }
    }

    @Override
    public boolean moveTop(LapPK id, String user) {
        boolean ret;

        for (;;) {
            ret = moveUp(id, user);
            if (ret == false) {
                break;
            }
        }

        return ret;
    }

    @Override
    public boolean moveBottom(LapPK id, String user) {
        boolean ret;

        for (;;) {
            ret = moveDown(id, user);
            if (ret == false) {
                break;
            }
        }

        return ret;
    }

}
