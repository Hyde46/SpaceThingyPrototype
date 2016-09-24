package com.mygdx.game.modelItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Items.Item;

/**
 * Created by ilost on 04.08.2016.
 */

public class ModelItemsPlayer
{
    Array<Item> items;

    private static ModelItemsPlayer instance;

    public static ModelItemsPlayer get()
    {
        if(instance == null)
        {
            instance = new ModelItemsPlayer();
        }
        return instance;
    }

    public Array<Item> getItems()
    {
        return items;
    }

    public ModelItemsPlayer()
    {
        items = new Array<Item>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        if(items.contains(item, true)) items.removeValue(item, true);
    }
}
