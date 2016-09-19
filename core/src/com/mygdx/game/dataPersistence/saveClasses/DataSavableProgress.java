package com.mygdx.game.dataPersistence.saveClasses;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.dataPersistence.DataPers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableProgress extends DataSavable
{
    // arrays needs to be saved as ArrayList ... so java.io.serializable can handle it

    public ArrayList<Integer> idsItemsPlayer = new ArrayList<Integer>();
    private ArrayList<Integer> idsSkinsPlayer = new ArrayList<Integer>();
    public ArrayList<Integer> preDialogsPlayed;
    public ArrayList<Integer> postDialogsPlayed;
    public int[] hopsPerLevel;
    public boolean[] playableLevel;
    public int credits;
    public int currentLevel;

    public int nthGame = 0;


    public DataSavableProgress()
    {
        idsItemsPlayer = new ArrayList<Integer>();
        idsSkinsPlayer = new ArrayList<Integer>();
        preDialogsPlayed = new ArrayList<Integer>();
        postDialogsPlayed = new ArrayList<Integer>();
        hopsPerLevel = new int[15];
        playableLevel = new boolean[15];
        credits = 0;

        //TODO remove later
        unlockLevels();
        setItems();
    }

    private void unlockLevels()
    {
        for(int i = 0; i < playableLevel.length; i++)
        {
            playableLevel[i] = true;
        }
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