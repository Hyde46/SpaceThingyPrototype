package com.mygdx.game.dataPersistence.saveClasses;

import java.util.ArrayList;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class ADataSavableShop extends ADataSavable
{
    public ArrayList<Integer>[] idsItemsShopOfLevel;
    public ArrayList<Integer>[] idsItemsAvailable;

    public ADataSavableShop()
    {
        idsItemsShopOfLevel = (ArrayList<Integer>[])new ArrayList[3];
        idsItemsAvailable = (ArrayList<Integer>[])new ArrayList[3];

        for (int i = 0; i < 3; i++)
        {
            idsItemsShopOfLevel[i] = new ArrayList<Integer>();
            idsItemsAvailable[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < 4; i++)
        {
            idsItemsShopOfLevel[0].add(1);
            idsItemsShopOfLevel[1].add(7);
            idsItemsShopOfLevel[2].add(8);

            idsItemsAvailable[0].add(1);
            idsItemsAvailable[1].add(7);
            idsItemsAvailable[2].add(8);
        }
    }
}