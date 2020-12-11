package com.mycompany.iach7.slatime;

import com.mycompany.iach7.slatime.entity.Sla;
import com.mycompany.iach7.slatime.entity.SlaTime;
import com.mycompany.iach7.slatime.entity.SlaTimePK;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
public class SlaTimeSlaManagerBeanNGTest {
    private static EJBContainer container;

    private static final String PICKEDUP_ID = "Picked up";
    private static final String WORKAROUND_ID = "Workaround done";
    private static final String PROBLEMFIXED_ID = "Problem fixed";

    private static final SlaTimePK ID_SYMRISE = new SlaTimePK("FEDEX", "SYMRISE");
    private static final SlaTimePK ID_BASF = new SlaTimePK("FEDEX", "BASF");
    private static final SlaTimePK ID_BAYER = new SlaTimePK("FEDEX", "BAYER");
    private static final SlaTimePK INVALID_ID = new SlaTimePK("DPD", "SYMRISE");

    private SlaManager sla;
    private SlaTimeManager slaTime;

    public SlaTimeSlaManagerBeanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        sla = (SlaManager) container.getContext().lookup("java:global/classes/SlaManagerBean");
        slaTime = (SlaTimeManager) container.getContext().lookup("java:global/classes/SlaTimeManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of create method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 0)
    public void testCreate() throws Exception {
        System.out.println("create");

        // Some SLA first
        Sla sla1 = new Sla(PICKEDUP_ID, "Test 1");
        sla.create(sla1);
        Sla result = sla.getById(PICKEDUP_ID);
        assertEquals(result, sla1);

        Sla sla2 = new Sla(WORKAROUND_ID, "Test 2");
        sla.create(sla2);
        result = sla.getById(WORKAROUND_ID);
        assertEquals(result, sla2);

        Sla sla3 = new Sla(PROBLEMFIXED_ID, "Test 3");
        sla.create(sla3);
        result = sla.getById(PROBLEMFIXED_ID);
        assertEquals(result, sla3);

        // Some SlaTimes now
        SlaTime symrSlaT = new SlaTime(ID_SYMRISE, 60, "Test SLA Time");
        symrSlaT.setSla(sla1);
        slaTime.create(symrSlaT);
        SlaTime slatResult = slaTime.getById(ID_SYMRISE);
        assertEquals(slatResult, symrSlaT);

        SlaTime basfSlaT = new SlaTime(ID_BASF, 45, "Test SLA Time");
        basfSlaT.setSla(sla1);
        slaTime.create(basfSlaT);
        slatResult = slaTime.getById(ID_BASF);
        assertEquals(slatResult, basfSlaT);

        SlaTime bayeSlaT = new SlaTime(ID_BAYER, 120, "Test SLA Time");
        bayeSlaT.setSla(sla1);
        slaTime.create(bayeSlaT);
        slatResult = slaTime.getById(ID_BAYER);
        assertEquals(slatResult, bayeSlaT);

        sla1.addSlaTimeItem(symrSlaT);
        sla1.addSlaTimeItem(basfSlaT);
        sla1.addSlaTimeItem(bayeSlaT);
        assertTrue(sla1.getSlaTimeItems().size() == 3);

        sla2.addSlaTimeItem(symrSlaT);
        sla2.addSlaTimeItem(bayeSlaT);
        assertTrue(sla2.getSlaTimeItems().size() == 2);

        sla3.addSlaTimeItem(symrSlaT);
        sla3.addSlaTimeItem(bayeSlaT);
        assertTrue(sla3.getSlaTimeItems().size() == 2);
    }

    /**
     * Test of delete method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(priority = 99, enabled = false)
    public void testDelete() throws Exception {
        System.out.println("delete");

        SlaTime slaTimeResult = slaTime.getById(ID_SYMRISE);
        assertNotNull(slaTimeResult);
        SlaTime oldSlaTime = slaTime.delete(ID_SYMRISE);
        assertFalse(sla.getById(PICKEDUP_ID).getSlaTimeItems().contains(oldSlaTime));

        Sla result = sla.delete(PICKEDUP_ID);
        assertNotNull(result);
        assertEquals(result.getId(), PICKEDUP_ID);

        result = sla.delete(WORKAROUND_ID);
        assertNotNull(result);
        assertEquals(result.getId(), WORKAROUND_ID);

        result = sla.delete(PROBLEMFIXED_ID);
        assertNotNull(result);
        assertEquals(result.getId(), PROBLEMFIXED_ID);

        assertTrue(slaTime.getAll().isEmpty());
    }

    /**
     * Test of getAll method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(enabled = false)
    public void testGetAll() throws Exception {
        System.out.println("getAll");

        List<Sla> result = sla.getAll();
        assertTrue(result.size() == 3);
    }

    /**
     * Test of getById method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(enabled = false)
    public void testGetById() throws Exception {
        System.out.println("getById");

        // First the simple SLA
        Sla result = sla.getById(PICKEDUP_ID);
        assertNotNull(result);
        // Now the related first SlaTime
        List<SlaTime> list = new ArrayList(result.getSlaTimeItems());
        SlaTimePK id = list.get(0).getId();
        assertNotNull(slaTime.getById(id));

        result = sla.getById("UNKNOWN");
        assertNull(result);
    }

    /**
     * Test of getSlaByQuery method, of class SlaManagerBean.
     *
     * @throws java.lang.Exception
     */
    @Test(enabled = false)
    public void testGetSlaByQuery() throws Exception {
        System.out.println("getSlaByQuery");

        List<Sla> result = sla.getSlaByQuery("%", "%");
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
    @Test(enabled = false)
    public void testGetSlaTimes() throws Exception {
        System.out.println("getSlaTimes");

        Set<SlaTime> result = sla.getSlaTimes(PICKEDUP_ID);
        assertTrue(result.isEmpty());
    }
}
