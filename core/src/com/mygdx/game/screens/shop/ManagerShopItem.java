package com.mygdx.game.screens.shop;

import com.badlogic.gdx.utils.Array;

/**
 * Created by ilost on 18.09.2016.
 */

// holds all shop relevant information on the items - gettable by id

public class ManagerShopItem
{
    private static Array<ShopItem> items;

    public static ShopItem getShopItem(int id)
    {
        if(items == null) setItems();

        if(id >= items.size || id < 0)
        {
            ShopItem item;

            item = new ShopItem();
            item.setName("NameError");
            item.setDesciption("DescError");
            item.setPrice(10);

            return item;
        }

        return items.get(id);
    }

    private static void setItems()
    {
        items = new Array<ShopItem>();

        ShopItem item;

        item = new ShopItem();
        item.setName("Name1");
        item.setDesciption("Desc1");
        item.setPrice(1);
        items.add(item);

        item = new ShopItem();
        item.setName("Name2");
        item.setDesciption("Desc2");
        item.setPrice(2);
        items.add(item);

        item = new ShopItem();
        item.setName("Name3");
        item.setDesciption("Desc3");
        item.setPrice(3);
        items.add(item);

        item = new ShopItem();
        item.setName("Name4");
        item.setDesciption("Desc4");
        item.setPrice(4);
        items.add(item);

        item = new ShopItem();
        item.setName("Name4");
        item.setDesciption("Desc4");
        item.setPrice(4);
        items.add(item);
    }
}