package com.mycompany.iach7.dummy;

import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test controller bean 2
 */
public class ControllerBean2NGTest {

    public ControllerBean2NGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of doControl method, of class SessionControllerBean2.
     */
    @Test
    public void testDoControl() throws Exception {
        System.out.println("doControl");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionControllerBean2 instance = (SessionControllerBean2) container.getContext().lookup("java:global/classes/SessionControllerBean2");
        assertEquals(instance.doControl(), "OtherSessionBean");
        container.close();
    }

}
