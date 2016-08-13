package com.mygdx.game.modelItems;

import java.util.HashMap;

/**
 * Created by ilost on 04.08.2016.
 */

public class ModelItemsShopManager
{
    HashMap<String, ModelItemsShop> shops;

    private static ModelItemsShopManager instance;

    public static ModelItemsShopManager get()
    {
        if(instance == null)
        {
            instance = new ModelItemsShopManager();
        }
        return instance;
    }

    public ModelItemsShopManager()
    {
        shops = new HashMap<String, ModelItemsShop>();
    }

    public void AddShop(String name, ModelItemsShop shop)
    {
        shops.put(name, shop);
    }

    public ModelItemsShop GetShop(String name)
    {
        if(shops.containsKey(name))
            return shops.get(name);
        return null;
    }
}