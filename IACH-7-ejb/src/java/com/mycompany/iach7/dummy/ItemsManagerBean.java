package com.mycompany.iach7.dummy;

import com.mycompany.iach7.dummy.entity.Items;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 */
@Stateless
@Local(ItemsManager.class)
public class ItemsManagerBean extends AbstractManager<Items> implements ItemsManager {
    @PersistenceContext(unitName = "IACH-7-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsManagerBean() {
        super(Items.class);
    }

}
