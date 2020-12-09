package com.mycompany.iach7.dummy;

import com.mycompany.iach7.dummy.entity.Items;
import java.util.List;

/**
 *
 */
public interface ItemsManager {
    void create(Items items);

    void edit(Items items);

    void remove(Items items);

    Items find(Object id);

    List<Items> findAll();

    List<Items> findRange(int[] range);

    int count();

}
