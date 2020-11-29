package com.mycompany.iach7.truck;

import com.mycompany.iach7.truck.entity.Truck;
import com.mycompany.iach7.truck.entity.Truckload;
import com.mycompany.iach7.truck.entity.Trucktype;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the truck manager
 */
public class TruckManagerBeanNGTest {
    private static final String ID = "NE-AP 1234";
    private static final String TRUCK_ID = ID;

    private static EJBContainer container;

    private TruckManager truck;

    public TruckManagerBeanNGTest() {
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
        truck = (TruckManager) container.getContext().lookup("java:global/classes/TruckManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAll method, of class TruckManager.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        List<Truck> result = truck.getAll();

        assertTrue(result.size() == 1);
    }

    /**
     * Test of getById method, of class TruckManager.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");

        Truck result = truck.getById(ID);

        assertEquals(ID, result.getTruckId());
    }

    /**
     * Test of getByTruckId method, of class TruckManager.
     */
    @Test
    public void testGetByTruckId() {
        System.out.println("getByTruckId");

        List<Truck> result = truck.getByTruckId(TRUCK_ID);

        assertEquals(TRUCK_ID, result.get(0).getTruckId());
    }

    /**
     * Test of create method, of class TruckManager.
     */
    @Test(priority = 0)
    public void testCreate() {
        System.out.println("create");

        Truck expResult = new Truck();
        expResult.setTruckId(TRUCK_ID);
        expResult.setComment("Test Truck 1");
        expResult.setLoad(Truckload.unknown);
        expResult.setType(Trucktype.small);
        expResult.setUpdtGuiUser("Test");

        truck.create(expResult);

        Truck result = truck.getById(expResult.getTruckId());

        assertEquals(result, expResult);
    }

    /**
     * Test of delete method, of class TruckManager.
     */
    @Test(priority = 99)
    public void testDelete() {
        System.out.println("delete");

        Truck expResult = truck.delete(TRUCK_ID);

        assertEquals(TRUCK_ID, expResult.getTruckId());
        assertEquals("Test", expResult.getUpdtGuiUser());
    }

    /**
     * Test of getAllAvailable method, of class TruckManager.
     */
    @Test
    public void testGetAllAvailable() {
    }
}
