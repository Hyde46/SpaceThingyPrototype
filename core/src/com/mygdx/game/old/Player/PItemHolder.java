package com.mygdx.game.old.Player;

import com.mygdx.game.Items.Item;

/**
 * Created by Mechandrius on 19.06.2016.
 */
public class PItemHolder
{
    Item[] items;

    public PItemHolder(Item slot0, Item slot1)
    {
        items = new Item[2];
    }

    public void add(Item item, int slot)
    {
        items[slot] = item;
    }

    public Item remove(int slot)
    {
        Item item = items[slot];
        items[slot] = null;
        return item;
    }

    public void activate(int slot)
    {
        if(items[slot].stateItem == Item.StateItem.READY)
        {
            items[slot].activate();
        }
        if(items[slot+1 % 2].stateItem == Item.StateItem.ACTIVATED)
        {
            items[slot+1 % 2].deactivate();
        }
    }

    public void update(float delta)
    {
        for(int i = 0; i < items.length; i++) items[i].update(delta);
    }
}
