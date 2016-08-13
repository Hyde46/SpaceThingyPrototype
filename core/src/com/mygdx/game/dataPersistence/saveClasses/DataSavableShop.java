package com.mygdx.game.dataPersistence.saveClasses;

import java.util.ArrayList;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableShop extends DataSavable
{
    public ArrayList<ArrayList<Integer>> idsItemsShopOfLevel;
    public ArrayList<ArrayList<Integer>> idsItemsAvailable;

    public DataSavableShop()
    {
        idsItemsShopOfLevel = new ArrayList<ArrayList<Integer>>();
        idsItemsAvailable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < 3; i++)
        {
            idsItemsShopOfLevel.add(new ArrayList<Integer>());
            idsItemsAvailable.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < 4; i++)
        {
            idsItemsShopOfLevel.get(0).add(1);
            idsItemsShopOfLevel.get(1).add(7);
            idsItemsShopOfLevel.get(2).add(8);

            idsItemsAvailable.get(0).add(1);
            idsItemsAvailable.get(1).add(7);
            idsItemsAvailable.get(2).add(8);
        }
    }
}