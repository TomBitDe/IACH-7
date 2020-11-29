package com.mycompany.iach7.dummy;

import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test controller bean 1
 */
public class ControllerBean1NGTest {

    public ControllerBean1NGTest() {
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
     * Test of doControl method, of class SessionControllerBean1.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDoControl() throws Exception {
        System.out.println("doControl");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionControllerBean1 instance = (SessionControllerBean1) container.getContext().lookup("java:global/classes/SessionControllerBean1");
        assertEquals(instance.doControl(), "NewSessionBean OtherSessionBean");
        container.close();
    }

}
