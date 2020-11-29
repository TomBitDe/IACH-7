package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Lap;
import com.mycompany.iach7.tour.entity.LapPK;
import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourstat;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the lap manager
 */
public class LapManagerBeanNGTest {
    private static final String ID = "Tour 1";
    private static final String TOUR_ID = ID;
    private static final String TRUCK_ID = "NE-AP 1234";
    private static final String TRUCKER_ID = "HUGO";
    private static final String AGENT_ID = "UPS";
    private static final String CUSTOMER_ID = "IBM";
    private static final String OTHER_ID = "Other Tour";
    private static final String OTHER_TOUR_ID = OTHER_ID;
    private static final String OTHER_TRUCK_ID = "NE-AP 5678";
    private static final String OTHER_TRUCKER_ID = "EGON";
    private static final String OTHER_AGENT_ID = "FEDEX";
    private static final String OTHER_CUSTOMER_ID = "SYMRISE";

    private static EJBContainer container;

    private TourManager tour;
    private LapManager lap;

    public LapManagerBeanNGTest() {
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
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAll method, of class LapManagerBean.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        List<Lap> result = lap.getAll();

        assertTrue(result.size() == 3);
    }

    /**
     * Test of getById method, of class LapManagerBean.
     */
    @Test
    public void testGetById_LapPK() {
        System.out.println("getById");

        LapPK pk = new LapPK(TOUR_ID, "DUS");

        Lap result = lap.getById(pk);

        assertEquals(result.getId(), pk);
    }

    /**
     * Test of getById method, of class LapManagerBean.
     */
    @Test
    public void testGetById_String_String() {
        System.out.println("getById");

        Lap result = lap.getById(TOUR_ID, "DUS");

        assertEquals(result.getId().getTourId(), TOUR_ID);
        assertEquals(result.getId().getLapdest(), "DUS");
    }

    /**
     * Test of getByTourId method, of class LapManagerBean.
     */
    @Test
    public void testGetByTourId() {
        System.out.println("getByTourId");

        List<Lap> result = lap.getByTourId(TOUR_ID);

        assertTrue(result.size() == 1);

        result = lap.getByTourId(OTHER_TOUR_ID);

        assertTrue(result.size() == 2);
    }

    /**
     * Test of getByTourIdLapdest method, of class LapManagerBean.
     */
    @Test
    public void testGetByTourIdLapdest() {
        System.out.println("getByTourIdLapdest");

        List<Lap> result = lap.getByTourIdLapdest(OTHER_TOUR_ID, "FRA");

        assertTrue(result.size() == 1);

        result = lap.getByTourIdLapdest(OTHER_TOUR_ID, "BUD");

        assertTrue(result.isEmpty());
    }

    /**
     * Test of getByQuery method, of class LapManagerBean.
     */
    @Test
    public void testGetByQuery() {
        System.out.println("getByQuery");

        List<Lap> result = lap.getByQuery(TOUR_ID, "DUS", CUSTOMER_ID);

        assertTrue(result.size() == 1);

        result = lap.getByQuery(TOUR_ID, "DUS", "%");

        assertTrue(result.size() == 1);

        result = lap.getByQuery(TOUR_ID, "%", "%");

        assertTrue(result.size() == 1);

        result = lap.getByQuery("%", "%", "%");

        assertTrue(result.size() == 3);

        result = lap.getByQuery("%", "DUS", OTHER_CUSTOMER_ID);

        assertTrue(result.size() == 1);

        result = lap.getByQuery("%", "DUS", "%");

        assertTrue(result.size() == 2);

        result = lap.getByQuery(TOUR_ID, "%", CUSTOMER_ID);

        assertTrue(result.size() == 1);

        result = lap.getByQuery("%", "%", "DHL");

        assertTrue(result.isEmpty());
    }

    /**
     * Test of create method, of class LapManagerBean.
     */
    @Test(priority = 0)
    public void testCreate() {
        System.out.println("create");

        // Create two tours first
        Tour expResult = new Tour(TOUR_ID, Tourstat.created, TRUCK_ID,
                                  TRUCKER_ID, AGENT_ID);

        tour.create(expResult);

        Tour result = tour.getById(TOUR_ID);

        assertEquals(result.getTourId(), expResult.getTourId());

        expResult = new Tour(OTHER_TOUR_ID, Tourstat.finished,
                             OTHER_TRUCK_ID, OTHER_TRUCKER_ID,
                             OTHER_AGENT_ID);

        tour.create(expResult);

        result = tour.getById(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), expResult.getTourId());

        // Create the corresponding laps
        Lap resultLap = new Lap(TOUR_ID, "DUS");
        resultLap.setCustomer(CUSTOMER_ID);

        lap.create(resultLap);

        Lap expResultLap = lap.getById(TOUR_ID, "DUS");

        assertEquals(resultLap, expResultLap);

        // Create a DUS lap
        resultLap = new Lap(OTHER_TOUR_ID, "DUS");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "DUS");

        assertEquals(resultLap, expResultLap);

        // Create a FRA lap
        resultLap = new Lap(OTHER_TOUR_ID, "FRA");
        resultLap.setCustomer(OTHER_CUSTOMER_ID);

        lap.create(resultLap);

        expResultLap = lap.getById(OTHER_TOUR_ID, "FRA");

        assertEquals(resultLap, expResultLap);
    }

    /**
     * Test of delete method, of class LapManagerBean.
     */
    @Test(priority = 99)
    public void testDelete() {
        System.out.println("delete");

        // Delete a lap
        Lap lapResult = lap.delete(TOUR_ID, "DUS");

        assertEquals(lapResult.getId().getTourId(), TOUR_ID);
        assertEquals(lapResult.getId().getLapdest(), "DUS");

        // This lap does not exist
        lapResult = lap.delete(OTHER_TOUR_ID, "BUD");

        assertEquals(lapResult, null);

        // Delete the tours
        Tour result = tour.delete(TOUR_ID);

        assertEquals(result.getTourId(), TOUR_ID);

        result = tour.delete(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), OTHER_TOUR_ID);
    }
}
