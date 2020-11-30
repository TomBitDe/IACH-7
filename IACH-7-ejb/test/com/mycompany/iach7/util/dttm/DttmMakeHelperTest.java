package com.mycompany.iach7.util.dttm;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import junit.framework.TestCase;

/**
 * Test the DttmMakeHelper in a unit test environment
 */
public class DttmMakeHelperTest extends TestCase {
    /**
     * Construct the DttmMakeHelper test.
     *
     * @param testName the name of the test
     */
    public DttmMakeHelperTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of makeDttm method of class DttmMakeHelper.
     */
    public void testMakeDttmDate() {
        Date date = null;
        String expResult = "20121005134217";
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2012, 9, 5, 13, 42, 17);
        String result = DttmMakeHelper.makeDttm(cal.getTime());
        assertEquals(expResult, result);
    }

    /**
     * Test of makeDttm method of class DttmMakeHelper.
     */
    public void testMakeDttmNoArgs() {
        String result = DttmMakeHelper.makeDttm();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.length() == 14);
    }

    /**
     * Test of makeDttm16 method of class DttmMakeHelper.
     */
    public void testMakeDttm16Date() {
        Date date = null;
        String expResult = "2012100513421793";
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2012, 9, 5, 13, 42, 17);
        cal.set(Calendar.MILLISECOND, 930);
        String result = DttmMakeHelper.makeDttm16(cal.getTime());
        assertEquals(expResult, result);
    }

    /**
     * Test of makeDttm16 method of class DttmMakeHelper.
     */
    public void testMakeDttm16NoArgs() {
        String result = DttmMakeHelper.makeDttm16();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.length() == 16);
    }

    /**
     * Test of makeDttm16 method of class DttmMakeHelper.
     */
    public void testMakeDttm17Date() {
        Date date = null;
        String expResult = "20121005134217938";
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2012, 9, 5, 13, 42, 17);
        cal.set(Calendar.MILLISECOND, 938);
        String result = DttmMakeHelper.makeDttm17(cal.getTime());
        assertEquals(expResult, result);
    }

    /**
     * Test of makeDttm17 method of class DttmMakeHelper.
     */
    public void testMakeDttm17NoArgs() {
        String result = DttmMakeHelper.makeDttm17();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.length() == 17);
    }
}
