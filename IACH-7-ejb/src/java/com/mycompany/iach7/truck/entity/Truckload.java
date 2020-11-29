package com.mycompany.iach7.truck.entity;

/**
 * How a truck is loaded and can be unloaded.<br>
 * - unknown its not clear how the truck can be loaded / unloaded<br>
 * - side loaded from side<br>
 * - back loaded from the back<br>
 */
public enum Truckload {
    unknown,
    side,
    back;
}
