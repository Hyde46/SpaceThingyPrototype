package com.mygdx.game.Player;

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
        if(!items[slot].inCooldown)
        {
            items[slot].activate();
        }
    }
}
