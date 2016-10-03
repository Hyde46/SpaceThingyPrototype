package com.mygdx.game.dataPersistence.saveClasses;

import com.mygdx.game.Items.ItemManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableProgress extends ADataSavable
{
    // arrays needs to be saved as ArrayList ... so java.io.serializable can handle it

    public ArrayList<Integer> idsItemsPlayer = new ArrayList<Integer>();
    private ArrayList<Integer> idsSkinsPlayer = new ArrayList<Integer>();
    public ArrayList<Integer> preDialogsPlayed;
    public ArrayList<Integer> postDialogsPlayed;
    public int[] hopsPerLevel;
 //   public boolean[] levelsUnlocked;
    public int credits;
 //   public int currentLevel;

    public boolean[][] levelsUnlocked; // [0 level, 1 shop, 2 hangar][instance of type]

    public int nthGame = 0;
    
    public DataSavableProgress()
    {
        idsItemsPlayer = new ArrayList<Integer>();
        idsSkinsPlayer = new ArrayList<Integer>();
        preDialogsPlayed = new ArrayList<Integer>();
        postDialogsPlayed = new ArrayList<Integer>();

        hopsPerLevel = new int[104];

        levelsUnlocked = new boolean[3][100];

        credits = 0;

        //TODO remove later
        unlockLevels();
        setItems();
    }

    public class LevelData implements Serializable
    {
        float time = 0;
        int hops = 0;
        boolean isUnlocked = false;
    }

    private void unlockLevels()
    {
        for(int i = 0; i < levelsUnlocked.length; i++)
            for (int j = 0; j < levelsUnlocked[0].length; j++)
                levelsUnlocked[i][j] = true;
    }

    private void setItems()
    {
        credits = 100;
        idsItemsPlayer.add(ItemManager.ItemNames.TELEPORT.ordinal());
        idsItemsPlayer.add(ItemManager.ItemNames.BREAK.ordinal());
    }

    /**
     * getter for ids of skins
     * @return array of ids
     */
    public ArrayList<Integer> getIdsSkinsPlayer() {
        return idsSkinsPlayer;
    }

    /**
     * add an id to the skins array
     * @param id
     */
    public void addToSkins(int id){
        idsSkinsPlayer.add(id);
    }
}