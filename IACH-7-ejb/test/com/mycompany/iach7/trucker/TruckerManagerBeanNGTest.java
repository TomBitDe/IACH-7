package com.mycompany.iach7.trucker;

import com.mycompany.iach7.trucker.entity.Trucker;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the trucker manager
 */
public class TruckerManagerBeanNGTest {
    private static final String ID = "Hugo";
    private static final String TRUCKER_ID = ID;

    private static EJBContainer container;

    private TruckerManager trucker;

    public TruckerManagerBeanNGTest() {
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
        trucker = (TruckerManager) container.getContext().lookup("java:global/classes/TruckerManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAll method, of class TruckerManager.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        List<Trucker> result = trucker.getAll();

        assertTrue(result.size() == 1);
    }

    /**
     * Test of getById method, of class TruckerManager.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");

        Trucker result = trucker.getById(ID);

        assertEquals(ID, result.getTruckerId());
    }

    /**
     * Test of getByTruckerId method, of class TruckerManager.
     */
    @Test
    public void testGetByTruckerId() {
        System.out.println("getByTruckerId");

        List<Trucker> result = trucker.getByTruckerId(TRUCKER_ID);

        assertEquals(TRUCKER_ID, result.get(0).getTruckerId());
    }

    /**
     * Test of create method, of class TruckerManager.
     */
    @Test(priority = 0)
    public void testCreate() {
        System.out.println("create");

        Trucker expResult = new Trucker();
        expResult.setTruckerId(TRUCKER_ID);
        expResult.setUpdtDttm(DttmMakeHelper.makeDttm());
        expResult.setUpdtGuiUser("Test");

        trucker.create(expResult);

        Trucker result = trucker.getById(TRUCKER_ID);

        assertEquals(result, expResult);
    }

    /**
     * Test of delete method, of class TruckerManager.
     */
    @Test(priority = 99)
    public void testDelete() {
        System.out.println("delete");

        Trucker expResult = trucker.delete(ID);

        assertEquals(ID, expResult.getTruckerId());
        assertEquals("Test", expResult.getUpdtGuiUser());
    }

    /**
     * Test of getAllAvailable method, of class TruckerManager.
     */
    @Test
    public void testGetAllAvailable() {
        System.out.println("getAllAvailable not done!");
    }
}
