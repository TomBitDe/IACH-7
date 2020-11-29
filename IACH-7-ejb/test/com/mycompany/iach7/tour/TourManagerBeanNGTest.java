package com.mycompany.iach7.tour;

import com.mycompany.iach7.tour.entity.Tour;
import com.mycompany.iach7.tour.entity.Tourstat;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the tour manager
 */
public class TourManagerBeanNGTest {
    private static final String ID = "Tour 1";
    private static final String TOUR_ID = ID;
    private static final String TRUCK_ID = "NE-AP 1234";
    private static final String TRUCKER_ID = "HUGO";
    private static final String AGENT_ID = "UPS";
    private static final String OTHER_ID = "Other Tour";
    private static final String OTHER_TOUR_ID = OTHER_ID;
    private static final String OTHER_TRUCK_ID = "NE-AP 5678";
    private static final String OTHER_TRUCKER_ID = "EGON";
    private static final String OTHER_AGENT_ID = "FEDEX";

    private static EJBContainer container;

    private TourManager tour;

    public TourManagerBeanNGTest() {
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
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAll method, of class TourManagerBean.<br>
     * Depends on what has been done in #see testCreate
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        List<Tour> result = tour.getAll();

        assertTrue(result.size() == 2);
    }

    /**
     * Test of getById method, of class TourManagerBean.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");

        Tour result = tour.getById(ID);

        assertEquals(ID, result.getTourId());
    }

    /**
     * Test of getByTourId method, of class TourManagerBean.
     */
    @Test
    public void testGetByTourId() {
        System.out.println("getByTourId");

        List<Tour> result = tour.getByTourId(TOUR_ID);

        assertEquals(TOUR_ID, result.get(0).getTourId());
    }

    /**
     * Test of getByTruckIdNotFinished method, of class TourManagerBean.<br>
     * Depends on what has been done in #see testCreate
     */
    @Test
    public void testGetByTruckIdNotFinished() {
        System.out.println("getByTruckIdNotFinished");

        List<Tour> result = tour.getByTruckIdNotFinished(TRUCK_ID);

        assertEquals(result.get(0).getTourId(), TOUR_ID);

        result = tour.getByTruckIdNotFinished(OTHER_TRUCK_ID);

        assertTrue(result.isEmpty());
    }

    /**
     * Test of getByQuery method, of class TourManagerBean.
     */
    @Test
    public void testGetByQuery() {
        System.out.println("getByQuery");

        List<Tour> result = tour.getByQuery(OTHER_TOUR_ID, OTHER_TRUCK_ID, OTHER_AGENT_ID);

        assertEquals(result.get(0).getTourId(), OTHER_TOUR_ID);
    }

    /**
     * Test of create method, of class TourManagerBean.
     */
    @Test(priority = 0)
    public void testCreate() {
        System.out.println("create");

        Tour expResult = new Tour(TOUR_ID, Tourstat.created, TRUCK_ID, TRUCKER_ID,
                                  AGENT_ID);

        tour.create(expResult);

        Tour result = tour.getById(TOUR_ID);

        assertEquals(result.getTourId(), expResult.getTourId());

        expResult = new Tour(OTHER_TOUR_ID, Tourstat.finished, OTHER_TRUCK_ID, OTHER_TRUCKER_ID,
                             OTHER_AGENT_ID);

        tour.create(expResult);

        result = tour.getById(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), expResult.getTourId());
    }

    /**
     * Test of delete method, of class TourManagerBean.
     */
    @Test(priority = 99)
    public void testDelete() {
        System.out.println("delete");

        Tour result = tour.delete(TOUR_ID);

        assertEquals(result.getTourId(), TOUR_ID);

        result = tour.delete(OTHER_TOUR_ID);

        assertEquals(result.getTourId(), OTHER_TOUR_ID);
    }

    /**
     * Test of otherTourActiveForTruck method, of class TourManagerBean.<br>
     * Depends on what has been done in #see testCreate
     */
    @Test
    public void testOtherTourActiveForTruck() {
        System.out.println("otherTourActiveForTruck");

        boolean result = tour.otherTourActiveForTruck(TOUR_ID, TRUCK_ID);

        assertFalse(result);
    }
}
