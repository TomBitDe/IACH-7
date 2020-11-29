package com.mycompany.iach7.tour.entity;

/**
 * Lapevent type values.<br>
 * - unknown Default inital value<br>
 * - hopinit Hop initale Werte<br>
 * - aktueta Aktualisiert Ank. am Ziel<br>
 * - aktugte Aktualisiert Gate<br>
 * - actuunl Aktualisiert Unload<br>
 * - arrival Ank. am Ziel<br>
 * - deststa Gewuenschte Ank. am Ziel<br>
 * - ownrsta Gewuenschte Ank. am Ziel<br>
 * - syncsta Abgestimmte Ank. am Ziel<br>
 */
public enum Lapeventtype {
    unknown,
    hopinit,
    actueta,
    aktugte,
    actuunl,
    arrival,
    deststa,
    ownrsta,
    syncsta;
}
