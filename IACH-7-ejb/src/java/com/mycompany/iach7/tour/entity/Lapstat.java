package com.mycompany.iach7.tour.entity;

/**
 * Tour Lap states.<br>
 * - unknown not created, active or finished<br>
 * - created hop is created or planned<br>
 * - actived hop is active<br>
 * - finished hop is finished<br>
 */
public enum Lapstat {
    unknown,
    created,
    active,
    finished;
}
