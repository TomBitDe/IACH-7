/**
 * The business objects related to SlaTimes.<br>
 * <p>
 * <strong>Technical issues</strong><br>
 * Also containing the special types (enums) associated with the business objects. All business objects are loosley
 * connected. The connection is done via the business logic.<br>
 * <p>
 * Here the ManyToOne and OneToMany dependency between ServiceLevelAgreement is introduced. See the description below.
 * <p>
 * A ServiceLevelAgreement (SLA) is the contract type.<br>
 * A SlaTime is the time to fullfil a contract type<br>
 * One SLA is assigned to 0..N SlaTime. An SlaTime has 0 or 1 SLA. This is defined as @OneToMany and @ManyToOne
 * dependency in the Entities.
 * <p>
 * <strong>CAUTION</strong>: <strong>under construction</strong>.
 */
package com.mycompany.iach7.slatime.entity;
