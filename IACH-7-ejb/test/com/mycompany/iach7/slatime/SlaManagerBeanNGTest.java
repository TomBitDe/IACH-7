package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.ServiceLevelAgreement;
import com.mycompany.iach7.slatime.entity.SlaTime;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class SlaManagerBeanNGTest {
    private static EJBContainer container;

    private static final String PICKEDUP_ID = "Picked up";
    private static final String WORKAROUND_ID = "Workaround done";
    private static final String PROBLEMFIXED_ID = "Problem fixed";

    private SlaManager sla;

    public SlaManagerBeanNGTest() {
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
        sla = (SlaManager) container.getContext().lookup("java:global/classes/SlaManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of createSla method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 0)
    public void testCreate() throws Exception {
        System.out.println("create");

        ServiceLevelAgreement expResult = new ServiceLevelAgreement(PICKEDUP_ID, "Test 1");
        sla.create(expResult);
        ServiceLevelAgreement result = sla.getById(PICKEDUP_ID);
        assertEquals(result, expResult);

        expResult = new ServiceLevelAgreement(WORKAROUND_ID, "Test 2");
        sla.create(expResult);
        result = sla.getById(WORKAROUND_ID);
        assertEquals(result, expResult);

        expResult = new ServiceLevelAgreement(PROBLEMFIXED_ID, "Test 3");
        sla.create(expResult);
        result = sla.getById(PROBLEMFIXED_ID);
        assertEquals(result, expResult);
    }

    /**
     * Test of delete method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 99)
    public void testDelete() throws Exception {
        System.out.println("delete");

        ServiceLevelAgreement result = sla.delete(PICKEDUP_ID);
        assertNotNull(result);
        assertEquals(result.getId(), PICKEDUP_ID);

        result = sla.delete(WORKAROUND_ID);
        assertNotNull(result);
        assertEquals(result.getId(), WORKAROUND_ID);

        result = sla.delete(PROBLEMFIXED_ID);
        assertNotNull(result);
        assertEquals(result.getId(), PROBLEMFIXED_ID);
    }

    /**
     * Test of getAll method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");

        List<ServiceLevelAgreement> result = sla.getAll();
        assertTrue(result.size() == 3);
    }

    /**
     * Test of getById method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");

        ServiceLevelAgreement result = sla.getById(PICKEDUP_ID);
        assertNotNull(result);

        result = sla.getById("UNKNOWN");
        assertNull(result);
    }

    /**
     * Test of getSlaByQuery method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSlaByQuery() throws Exception {
        System.out.println("getSlaByQuery");

        List<ServiceLevelAgreement> result = sla.getSlaByQuery("%", "%");
        assertTrue(result.size() == 3);

        result = sla.getSlaByQuery("%", "Test%");
        assertTrue(result.size() == 3);

        result = sla.getSlaByQuery(WORKAROUND_ID, "%");
        assertTrue(result.size() == 1);

        result = sla.getSlaByQuery("%", "%2");
        assertTrue(result.size() == 1);

        result = sla.getSlaByQuery("P%", "%");
        assertTrue(result.size() == 2);
    }

    /**
     * Test of getSlaTimes method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSlaTimes() throws Exception {
        System.out.println("getSlaTimes");

        List<SlaTime> result = sla.getSlaTimes(PICKEDUP_ID);
        assertTrue(result.isEmpty());
    }
}
