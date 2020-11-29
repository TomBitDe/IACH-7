package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.LapPK;

/**
 * The lap sequence manager interface.
 */
public interface LapSequenceManager {
    /**
     * Change a tour laps order; move it up
     *
     * @param id   the laps id
     * @param user the user who changed the order
     *
     * @return true if the order change was successful else false
     */
    public boolean moveUp(LapPK id, String user);

    /**
     * Change a tour laps order; move it down
     *
     * @param id   the laps id
     * @param user the user who changed the order
     *
     * @return true if the order change was successful else false
     */
    public boolean moveDown(LapPK id, String user);

    /**
     * Change a tour laps order; make it the first tour lap
     *
     * @param id   the laps id
     * @param user the user who changed the order
     *
     * @return true if the order change was successful else false
     */
    public boolean moveTop(LapPK id, String user);

    /**
     * Change a tour laps order; make it the last tour lap
     *
     * @param id   the laps id
     * @param user the user who changed the order
     *
     * @return true if the order change was successful else false
     */
    public boolean moveBottom(LapPK id, String user);
}
