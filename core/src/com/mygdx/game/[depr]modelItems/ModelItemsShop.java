package com.mygdx.game.modelItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Items.Item;

/**
 * Created by ilost on 04.08.2016.
 */

public class ModelItemsShop
{
    Array<Item> items;

    public ModelItemsShop()
    {
        items = new Array<Item>();
    }

    public Array<Item> getITems()
    {
        return items;
    }

    // add items to the display

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        if(items.contains(item, true)) items.removeValue(item, true);
    }

    // actions

    public void info(int idxItem)
    {
        if(items.size > idxItem)
        {
            Item itemWanted = items.get(idxItem);


        }
    }

    public void sell(Item item)
    {
        // modelitemsplayer remove item
        // player gain currency = item.sellvalue
    }

    public void buy(int idxItem)
    {
        if(items.size > idxItem)
        {
            Item itemWanted = items.get(idxItem);

            // check player resources
            if(true)
            {
                // player lose money
                ModelItemsPlayer.get().addItem(itemWanted);
                removeItem(itemWanted);
            }

        }
    }
}
