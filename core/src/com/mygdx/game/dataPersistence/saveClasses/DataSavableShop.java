package com.mygdx.game.dataPersistence.saveClasses;

import java.util.ArrayList;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableShop extends ADataSavable
{
    public ArrayList<Integer>[] idsItemsShopOfLevel;
    public ArrayList<Integer>[] idsItemsAvailable;

    public DataSavableShop()
    {
        idsItemsShopOfLevel = (ArrayList<Integer>[])new ArrayList[3];
        idsItemsAvailable = (ArrayList<Integer>[])new ArrayList[3];

        for (int i = 0; i < 3; i++)
        {
            idsItemsShopOfLevel[i] = new ArrayList<Integer>();
            idsItemsAvailable[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < 3; i++)
        {
//            idsItemsShopOfLevel[i].add(1);
//            idsItemsShopOfLevel[i].add(2);
//            idsItemsShopOfLevel[i].add(3);
//            idsItemsShopOfLevel[i].add(7);
//            idsItemsShopOfLevel[i].add(14);
//            idsItemsShopOfLevel[i].add(17);
//
//            idsItemsAvailable[i].add(1);
//            idsItemsAvailable[i].add(2);
//            idsItemsAvailable[i].add(3);
//            idsItemsAvailable[i].add(7);
//            idsItemsAvailable[i].add(14);
//            idsItemsAvailable[i].add(17);

            idsItemsShopOfLevel[i].add(0);
            idsItemsShopOfLevel[i].add(1);
            idsItemsShopOfLevel[i].add(2);
            idsItemsShopOfLevel[i].add(3);
            idsItemsShopOfLevel[i].add(4);
            idsItemsShopOfLevel[i].add(5);

            idsItemsAvailable[i].add(0);
            idsItemsAvailable[i].add(1);
            idsItemsAvailable[i].add(2);
            idsItemsAvailable[i].add(3);
            idsItemsAvailable[i].add(4);
            idsItemsAvailable[i].add(5);
        }
    }
}