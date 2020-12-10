package com.mycompany.iach7.dummy;

import com.mycompany.iach7.dummy.entity.Cart;
import java.util.List;

/**
 *
 */
public interface CartManager {
    void create(Cart cart);

    void edit(Cart cart);

    Cart remove(Cart cart);

    Cart find(Object id);

    List<Cart> findAll();

    List<Cart> findRange(int[] range);

    int count();

}
