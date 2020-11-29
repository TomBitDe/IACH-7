package com.mycompany.iach7.util.dttm;

import junit.framework.TestCase;

/**
 * Test the DttmCalc in a unit test environment
 */
public class DttmCalcTest extends TestCase {
    /**
     * Construct the DttmCalc test.
     *
     * @param testName the tests name
     */
    public DttmCalcTest(String testName) {
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
     * Test of dttmDiff14 method of class DttmCalc.
     */
    public void testDttmDiff14() {
        String val1 = "20140205113505";
        String val2 = "20140205113507";
        long expResult = -2000L;
        long result = DttmCalc.dttmDiff14(val1, val2);
        assertEquals(expResult, result);
    }

    /**
     * Test of dttmAdd14 method of class DttmCalc.
     */
    public void testDttmAdd14() {
        String val1 = "20140205113505";
        long milli = 5000L;
        String expResult = "20140205113510";
        String result = DttmCalc.dttmAdd14(val1, milli);
        assertEquals(expResult, result);
    }
}
