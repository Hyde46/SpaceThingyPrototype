package com.mygdx.game.dataPersistence.saveClasses;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableProgress extends DataSavable
{
    // arrays needs to be saved as ArrayList ... so java.io.serializable can handle it

    public ArrayList<Integer> idsItemsPlayer;
    public int credits;

    public int nthGame = 0;


    public DataSavableProgress()
    {
        idsItemsPlayer = new ArrayList<Integer>();
        credits = 1000;
    }


}