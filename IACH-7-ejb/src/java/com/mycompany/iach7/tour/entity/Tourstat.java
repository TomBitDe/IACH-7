package com.mycompany.iach7.tour.entity;

/**
 * A tour can be in different states.<br>
 * - unknown not in state created, active or finished<br>
 * - created tour is just created or planned<br>
 * - actived tour has been activated<br>
 * - finished tour is done<br>
 */
public enum Tourstat {
    unknown,
    created,
    active,
    finished;
}
