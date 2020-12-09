package com.mycompany.iach7.dummy;

import com.mycompany.iach7.dummy.entity.Cart;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 */
@Stateless
@Local(CartManager.class)
public class CartManagerBean extends AbstractManager<Cart> implements CartManager {
    @PersistenceContext(unitName = "IACH-7-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartManagerBean() {
        super(Cart.class);
    }

}
