package com.mygdx.game.managers.levels;

import com.badlogic.gdx.utils.Array;

/**
 * Created by denis on 8/12/16.
 *
 * safes the stats on a single level
 * if the level has been finished succesfully,
 * the stats will be safed
 */
public class LevelState {

    private int currencyCollected;

    private Array<Integer> itemIdsCollected = new Array<Integer>();

    private int hops;


    public void resetState(){
        currencyCollected = 0;
        hops = -1;
        itemIdsCollected.clear();
    }

    public void addCurrency(int amount){
        currencyCollected += amount;
    }

    public int getCurrency(){
        return currencyCollected;
    }

    public void hop(){
        hops += 1;
    }

    public int getHops(){
        return hops;
    }

    public void addCollectedItemId(int itemId){
        itemIdsCollected.add(itemId);
    }

    public Array<Integer> getCollectedItemIds(){
        return itemIdsCollected;
    }

    public void safeState(){

    }
}
