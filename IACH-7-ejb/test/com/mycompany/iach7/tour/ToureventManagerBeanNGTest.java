package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourstat;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the tourevent manager
 */
public class ToureventManagerBeanNGTest {
    private static final String OTHER_ID = "Other Tour";
    private static final String OTHER_TOUR_ID = OTHER_ID;
    private static final String OTHER_TRUCK_ID = "NE-AP 5678";
    private static final String OTHER_TRUCKER_ID = "EGON";
    private static final String OTHER_AGENT_ID = "FEDEX";
    private static final String OTHER_CUSTOMER_ID = "SYMRISE";

    private static EJBContainer container;

    private TourManager tour;
    private LapManager lap;

    private ToureventManager tourevent;

    public ToureventManagerBeanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        tour = (TourManager) container.getContext().lookup("java:global/classes/TourManagerBean");
        lap = (LapManager) container.getContext().lookup("java:global/classes/LapManagerBean");

        Tour expResult = new Tour(OTHER_TOUR_ID, Tourstat.finished,
                                  OTHER_TRUCK_ID, OTHER_TRUCKER_ID,
                                  OTHER_AGENT_ID);

        tour.create(expResult);

        Tour result = tour.getById(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), expResult.getTourId());

        // Create the corresponding laps
        // Create a DUS lap
        Lap resultLap = new Lap(OTHER_TOUR_ID, "DUS");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);
        resultLap.setLapSeqn(1);

        lap.create(resultLap);

        Lap expResultLap = lap.getById(OTHER_TOUR_ID, "DUS");

        assertEquals(resultLap, expResultLap);

        // Create a FRA lap
        resultLap = new Lap(OTHER_TOUR_ID, "FRA");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);
        resultLap.setLapSeqn(2);

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "FRA");

        assertEquals(resultLap, expResultLap);

        // Create a BUD lap
        resultLap = new Lap(OTHER_TOUR_ID, "BUD");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);
        resultLap.setLapSeqn(3);

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "BUD");

        assertEquals(resultLap, expResultLap);

        System.out.println(lap.getAll().get(0).getId());
        System.out.println(lap.getAll().get(1).getId());
        System.out.println(lap.getAll().get(2).getId());

        tourevent = (ToureventManager) container.getContext().lookup("java:global/classes/ToureventManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        // Delete the tours and laps
        Tour result = tour.delete(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), OTHER_TOUR_ID);
    }

    /**
     * Test of agentStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testAgentStaChange() {
        System.out.println("agentStaChange");

    }

    /**
     * Test of customerStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testCustomerStaChange() {
        System.out.println("customerStaChange");

    }

    /**
     * Test of syncStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testSyncStaChange() {
        System.out.println("syncStaChange");

    }

    /**
     * Test of etaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testEtaChange() {
        System.out.println("etaChange");

    }

    /**
     * Test of unloadDurationChange method, of class ToureventManagerBean.
     */
    @Test
    public void testUnloadDurationChange() {
        System.out.println("unloadDurationChange");

    }

    /**
     * Test of gateChange method, of class ToureventManagerBean.
     */
    @Test
    public void testGateChange() {
        System.out.println("gateChange");

    }

    /**
     * Test of lapArrivalReported method, of class ToureventManagerBean.
     */
    @Test
    public void testLapArrivalReported() {
        System.out.println("lapArrivalReported");

    }

    /**
     * Test of setupEta method, of class ToureventManagerBean.
     */
    @Test
    public void testSetupEta() {
        System.out.println("setupEta");

    }

    /**
     * Test of getAll method, of class ToureventManagerBean.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

    }

    /**
     * Test of delete method, of class ToureventManagerBean.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");

    }

    /**
     * Test of getByTourIdLapdest method, of class ToureventManagerBean.
     */
    @Test
    public void testGetByTourIdLapdest() {
        System.out.println("getByTourIdLapdest");

    }

    /**
     * Test of getByTourId method, of class ToureventManagerBean.
     */
    @Test
    public void testGetByTourId() {
        System.out.println("getByTourId");

    }

    /**
     * Test of eventInit method, of class ToureventManagerBean.
     */
    @Test
    public void testEventInit() {
        System.out.println("eventInit");

    }
}
