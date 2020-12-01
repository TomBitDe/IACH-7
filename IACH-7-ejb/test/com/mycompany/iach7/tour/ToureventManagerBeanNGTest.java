package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Eventstat;
import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import com.mycompany.iach7.tour.entity.Lapeventtype;
import com.mycompany.iach7.tour.entity.Prio;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourevent;
import com.mycompany.iach7.tour.entity.ToureventPK;
import com.mycompany.iach7.tour.entity.Tourstat;
import com.mycompany.iach7.util.dttm.DttmCalc;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
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
    private static final String TSTU = "Test";

    private static EJBContainer container;

    static private TourManager tour;
    static private LapManager lap;

    static private ToureventManager tourevent;

    public ToureventManagerBeanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();

        tour = (TourManager) container.getContext().lookup("java:global/classes/TourManagerBean");
        lap = (LapManager) container.getContext().lookup("java:global/classes/LapManagerBean");

        // Start creating some test data
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
        resultLap.setEta(DttmMakeHelper.makeDttm());
        resultLap.setGate("A25");
        resultLap.setUnldur("60000");
        resultLap.setLapPrio(Prio.low);
        resultLap.setAgentSta(DttmMakeHelper.makeDttm());
        resultLap.setSyncSta(DttmMakeHelper.makeDttm());

        lap.create(resultLap);

        Lap expResultLap = lap.getById(OTHER_TOUR_ID, "DUS");

        assertEquals(resultLap, expResultLap);

        // Create a FRA lap
        resultLap = new Lap(OTHER_TOUR_ID, "FRA");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);
        resultLap.setLapSeqn(2);
        resultLap.setEta(DttmCalc.dttmAdd14(DttmMakeHelper.makeDttm(), 600000));
        resultLap.setGate("B4");
        resultLap.setUnldur("80000");
        resultLap.setLapPrio(Prio.low);
        resultLap.setAgentSta(DttmMakeHelper.makeDttm());
        resultLap.setSyncSta(DttmMakeHelper.makeDttm());

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "FRA");

        assertEquals(resultLap, expResultLap);

        // Create a BUD lap
        resultLap = new Lap(OTHER_TOUR_ID, "BUD");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);
        resultLap.setLapSeqn(3);
        resultLap.setEta(DttmCalc.dttmAdd14(DttmMakeHelper.makeDttm(), 1200000));
        resultLap.setGate("A12");
        resultLap.setUnldur("45000");
        resultLap.setLapPrio(Prio.normal);
        resultLap.setAgentSta(DttmMakeHelper.makeDttm());
        resultLap.setSyncSta(DttmMakeHelper.makeDttm());

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "BUD");

        assertEquals(resultLap, expResultLap);

        System.out.println(lap.getAll().get(0).getId());
        System.out.println(lap.getAll().get(1).getId());
        System.out.println(lap.getAll().get(2).getId());

        tourevent = (ToureventManager) container.getContext().lookup("java:global/classes/ToureventManagerBean");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Delete the tours and laps
        Tour result = tour.delete(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), OTHER_TOUR_ID);

        container.close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of agentStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testAgentStaChange() {
        System.out.println("agentStaChange");

        // Really change the Agent STA
        Tourevent result = tourevent.agentStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                    TSTU,
                                                    DttmCalc.dttmAdd14(DttmMakeHelper.makeDttm(), 60000));
        assertNotNull(result);

        // Don't change the Agent STA
        String agentSta = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getAgentSta();

        result = tourevent.agentStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                          TSTU,
                                          agentSta);
        assertNull(result);
    }

    /**
     * Test of customerStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testCustomerStaChange() {
        System.out.println("customerStaChange");

        // The Customer STA has not been set before (#see setUpClass)
        Tourevent result = tourevent.customerStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                       TSTU,
                                                       DttmMakeHelper.makeDttm());
        assertNotNull(result);

        // Don't change the Customer STA
        String customerSta = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getCustomerSta();

        result = tourevent.customerStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                             TSTU,
                                             customerSta);
        assertNull(result);
    }

    /**
     * Test of syncStaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testSyncStaChange() {
        System.out.println("syncStaChange");

        Tourevent result = tourevent.syncStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                   TSTU,
                                                   DttmMakeHelper.makeDttm());
        assertNotNull(result);

        // Don't change the Sync STA
        String syncSta = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getSyncSta();

        result = tourevent.syncStaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                         TSTU,
                                         syncSta);
        assertNull(result);
    }

    /**
     * Test of etaChange method, of class ToureventManagerBean.
     */
    @Test
    public void testEtaChange() {
        System.out.println("etaChange");

        Tourevent result = tourevent.etaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                               TSTU,
                                               DttmCalc.dttmAdd14(DttmMakeHelper.makeDttm(), 600000));
        assertNotNull(result);

        // Don't change the ETA
        String eta = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getEta();

        result = tourevent.etaChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                     TSTU,
                                     eta);
        assertNull(result);
    }

    /**
     * Test of unloadDurationChange method, of class ToureventManagerBean.
     */
    @Test
    public void testUnloadDurationChange() {
        System.out.println("unloadDurationChange");

        Tourevent result = tourevent.unloadDurationChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                          TSTU, "90");
        assertNotNull(result);

        // Don't change the unload duration
        String unlDur = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getUnldur();

        result = tourevent.unloadDurationChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                TSTU,
                                                unlDur);
        assertNull(result);
    }

    /**
     * Test of gateChange method, of class ToureventManagerBean.
     */
    @Test
    public void testGateChange() {
        System.out.println("gateChange");

        Tourevent result = tourevent.gateChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                TSTU, "B17");
        assertNotNull(result);

        // Don't change the unload duration
        String gate = lap.getById(new LapPK(OTHER_TOUR_ID, "DUS")).getGate();

        result = tourevent.gateChange(new LapPK(OTHER_TOUR_ID, "DUS"),
                                      TSTU,
                                      gate);
        assertNull(result);
    }

    /**
     * Test of lapArrivalReported method, of class ToureventManagerBean.
     */
    @Test
    public void testLapArrivalReported() {
        System.out.println("lapArrivalReported");

        Tourevent result = tourevent.lapArrivalReported(new LapPK(OTHER_TOUR_ID, "DUS"),
                                                        TSTU);
        assertNotNull(result);
    }

    /**
     * Test of setupEta method, of class ToureventManagerBean.
     */
    @Test(priority = 1)
    public void testSetupEta() {
        System.out.println("setupEta");

        Tourevent result = tourevent.setupEta(new LapPK(OTHER_TOUR_ID, "DUS"),
                                              TSTU,
                                              DttmMakeHelper.makeDttm());
        assertNotNull(result);
    }

    /**
     * Test of getAll method, of class ToureventManagerBean.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        // Since delete has not been called (priority = 99)
        // some objects should be in the database
        List<Tourevent> result = tourevent.getAll();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of delete method, of class ToureventManagerBean.
     */
    @Test(priority = 99)
    public void testDelete() {
        System.out.println("delete");

        List<Tourevent> dusEvents = tourevent.getByTourIdLapdest(OTHER_ID, "DUS");
        for (Tourevent event : dusEvents) {
            ToureventPK toureventPk = tourevent.delete(event.getId());
            // The delete returns a value not null here
            assertNotNull(toureventPk);
        }
        // Check if all is deleted for OTHER_ID and DUS
        assertTrue(tourevent.getByTourIdLapdest(OTHER_ID, "DUS").isEmpty());

        // The delete returns null here because of the evDttm
        assertNull(tourevent.delete(new ToureventPK(OTHER_TOUR_ID, "BUD", "25000329115920341")));

    }

    /**
     * Test of getByTourIdLapdest method, of class ToureventManagerBean.
     */
    @Test
    public void testGetByTourIdLapdest() {
        System.out.println("getByTourIdLapdest");

        // check if only the requested TourId and Lapdest is in the results
        List<Tourevent> results = tourevent.getByTourIdLapdest(OTHER_ID, "BUD");
        for (Tourevent event : results) {
            assertTrue(event.getId().getTourId().equals(OTHER_TOUR_ID));
            assertTrue(event.getId().getLapdest().equals("BUD"));
        }
    }

    /**
     * Test of getByTourId method, of class ToureventManagerBean.
     */
    @Test
    public void testGetByTourId() {
        System.out.println("getByTourId");

        // check if only the requested TourId is in the results
        List<Tourevent> results = tourevent.getByTourId(OTHER_TOUR_ID);
        results.forEach(event -> {
            assertTrue(event.getId().getTourId().equals(OTHER_TOUR_ID));
        });
    }

    /**
     * Test of eventInit method, of class ToureventManagerBean.
     */
    @Test(priority = 0)
    public void testEventInit() {
        System.out.println("eventInit");

        Tourevent result = tourevent.eventInit(new LapPK(OTHER_TOUR_ID, "DUS"), TSTU);
        assertNotNull(result);
        assertEquals(result.getId().getTourId(), OTHER_TOUR_ID);
        assertEquals(result.getId().getLapdest(), "DUS");
        assertEquals(result.getEventstat(), Eventstat.created);
        assertEquals(result.getLapeventtype(), Lapeventtype.hopinit);

        result = tourevent.eventInit(new LapPK(OTHER_TOUR_ID, "FRA"), TSTU);
        assertNotNull(result);
        assertEquals(result.getId().getTourId(), OTHER_TOUR_ID);
        assertEquals(result.getId().getLapdest(), "FRA");
        assertEquals(result.getEventstat(), Eventstat.created);
        assertEquals(result.getLapeventtype(), Lapeventtype.hopinit);

        result = tourevent.eventInit(new LapPK(OTHER_TOUR_ID, "BUD"), TSTU);
        assertNotNull(result);
        assertEquals(result.getId().getTourId(), OTHER_TOUR_ID);
        assertEquals(result.getId().getLapdest(), "BUD");
        assertEquals(result.getEventstat(), Eventstat.created);
        assertEquals(result.getLapeventtype(), Lapeventtype.hopinit);

        result = tourevent.eventInit(new LapPK(OTHER_TOUR_ID, "DXB"), TSTU);
        assertNull(result);
    }
}
