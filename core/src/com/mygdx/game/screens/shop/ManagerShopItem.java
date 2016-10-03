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
        item.setName("Booster");
        item.setDesciption("Speeds up your ship");
        item.setPrice(350);
        items.add(item);

        item = new ShopItem();
        item.setName("Break");
        item.setDesciption("Slows down your ship");
        item.setPrice(400);
        items.add(item);

        item = new ShopItem();
        item.setName("Phaseout");
        item.setDesciption("Phases your ship out for a short time. Making it invinsible for the duration.");
        item.setPrice(950);
        items.add(item);

        item = new ShopItem();
        item.setName("ItemPicker");
        item.setDesciption("Picks up any item in a radius after activation");
        item.setPrice(250);
        items.add(item);

        item = new ShopItem();
        item.setName("ItemHelper");
        item.setDesciption("After activation gives you the possibility to pick up Items in a radius by touching them");
        item.setPrice(250);
        items.add(item);

        item = new ShopItem();
        item.setName("RTeleport");
        item.setDesciption("Teleports your ship to a given destination with some random offset");
        item.setPrice(1200);
        items.add(item);
    }
}