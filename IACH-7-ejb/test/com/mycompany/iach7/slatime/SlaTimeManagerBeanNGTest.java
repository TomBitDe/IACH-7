package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.SlaTime;
import com.mycompany.iach7.slatime.entity.SlaTimePK;
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
 *
 */
public class SlaTimeManagerBeanNGTest {
    private static EJBContainer container;

    private static final SlaTimePK ID_SYMRISE = new SlaTimePK("FEDEX", "SYMRISE");
    private static final SlaTimePK ID_BASF = new SlaTimePK("FEDEX", "BASF");
    private static final SlaTimePK ID_BAYER = new SlaTimePK("FEDEX", "BAYER");
    private static final SlaTimePK INVALID_ID = new SlaTimePK("DPD", "SYMRISE");

    private SlaTimeManager slaTime;

    public SlaTimeManagerBeanNGTest() {
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
        slaTime = (SlaTimeManager) container.getContext().lookup("java:global/classes/SlaTimeManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAll method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");

        List<SlaTime> result = slaTime.getAll();
        assertTrue(result.size() == 3);
    }

    /**
     * Test of getSlaTimesByProvider method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSlaTimesByProvider() throws Exception {
        System.out.println("getSlaTimesByProvider");

        List<SlaTime> result = slaTime.getSlaTimesByProvider("FEDEX");
        assertTrue(result.size() == 3);

        result = slaTime.getSlaTimesByProvider("UNKNOWN");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getSlaTimesByCustomer method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSlaTimesByCustomer() throws Exception {
        System.out.println("getSlaTimesByCustomer");

        List<SlaTime> result = slaTime.getSlaTimesByCustomer("SYMRISE");
        assertTrue(result.size() == 1);

        result = slaTime.getSlaTimesByCustomer("UNKNOWN");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getById method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");

        SlaTime result = slaTime.getById(ID_SYMRISE);
        assertNotNull(result);

        result = slaTime.getById(INVALID_ID);
        assertNull(result);
    }

    /**
     * Test of create method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 0)
    public void testCreate() throws Exception {
        System.out.println("create");

        SlaTime expResult = new SlaTime(ID_SYMRISE, DttmMakeHelper.makeDttm(), "Test SLA Time");

        slaTime.create(expResult);

        SlaTime result = slaTime.getById(ID_SYMRISE);
        assertEquals(result, expResult);

        expResult = new SlaTime(ID_BASF, DttmMakeHelper.makeDttm(), "Test SLA Time");
        slaTime.create(expResult);
        result = slaTime.getById(ID_BASF);
        assertEquals(result, expResult);

        expResult = new SlaTime(ID_BAYER, DttmMakeHelper.makeDttm(), "Test SLA Time");
        slaTime.create(expResult);
        result = slaTime.getById(ID_BAYER);
        assertEquals(result, expResult);
    }

    /**
     * Test of delete method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 99)
    public void testDelete() throws Exception {
        System.out.println("delete");

        SlaTime result = slaTime.delete(ID_SYMRISE);

        assertNotNull(result);
        assertEquals(result.getId(), ID_SYMRISE);

        result = slaTime.delete(ID_SYMRISE);

        assertNull(result);
    }

    /**
     * Test of getSlaTimesByQuery method, of class SlaTimeManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSlaTimesByQuery() throws Exception {
        System.out.println("getSlaTimesByQuery");

        List<SlaTime> result = slaTime.getSlaTimesByQuery("FEDEX", "%");
        assertTrue(result.size() == 3);

        result = slaTime.getSlaTimesByQuery("FEDEX", "BA%");
        assertTrue(result.size() == 2);

        result = slaTime.getSlaTimesByQuery("DPD", "%");
        assertTrue(result.isEmpty());

        result = slaTime.getSlaTimesByQuery("%", "BAY%");
        assertTrue(result.size() == 1);

        result = slaTime.getSlaTimesByQuery("%", "%");
        assertTrue(result.size() == 3);
    }
}
