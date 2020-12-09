package com.mycompany.iach7.dummy;

import com.mycompany.iach7.dummy.entity.Cart;
import com.mycompany.iach7.dummy.entity.Items;
import javax.ejb.embeddable.EJBContainer;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test Cart Items ManyToOne OneToMany relationship
 */
public class CartItemsManagerBeanNGTest {
    private static EJBContainer container;

    private CartManager cart;
    private ItemsManager items;

    public CartItemsManagerBeanNGTest() {
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
        cart = (CartManager) container.getContext().lookup("java:global/classes/CartManagerBean");
        items = (ItemsManager) container.getContext().lookup("java:global/classes/ItemsManagerBean");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(priority = 0)
    public void testCreateCartItems() {
        System.out.println("testCreateCartItems");

        Cart cart1 = new Cart();
        assertNull(cart1.getId());
        cart.create(cart1);
        assertNotNull(cart1.getId());

        Cart cart2 = new Cart();
        assertNull(cart2.getId());
        cart.create(cart2);
        assertNotNull(cart2.getId());

        assertTrue(cart1.getItems().isEmpty());
        assertTrue(cart2.getItems().isEmpty());

        Items items1 = new Items(cart1);
        assertNull(items1.getId());
        items.create(items1);
        assertNotNull(items1.getId());
        cart1.addItems(items1);

        Items items2 = new Items(cart1);
        assertNull(items2.getId());
        items.create(items2);
        assertNotNull(items2.getId());
        cart1.addItems(items2);

        Items items3 = new Items(cart2);
        assertNull(items3.getId());
        items.create(items3);
        assertNotNull(items3.getId());
        cart2.addItems(items3);

        Items items4 = new Items(cart2);
        assertNull(items4.getId());
        items.create(items4);
        assertNotNull(items4.getId());
        cart2.addItems(items4);

        Items items5 = new Items(cart2);
        assertNull(items5.getId());
        items.create(items5);
        assertNotNull(items5.getId());
        cart2.addItems(items5);

        assertTrue(!cart1.getItems().isEmpty());
        assertTrue(!cart2.getItems().isEmpty());
    }

    @Test(priority = 99)
    public void testDeleteCartItems() {
        System.out.println("testDeleteCartItems");
    }
}
