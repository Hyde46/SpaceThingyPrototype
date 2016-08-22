package com.mygdx.game.managers.levels;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.dataPersistence.DataPers;

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

    private int currentLevel;

    public void resetState(){
        currencyCollected = 0;
        hops = 0;
        itemIdsCollected.clear();
    }

    public void setCurrentLevel(int level){
        this.currentLevel = level;
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
        DataPers.dataP().credits += currencyCollected;
        for(Integer i : itemIdsCollected){
            if(!DataPers.dataP().idsItemsPlayer.contains(i)){
                DataPers.dataP().idsItemsPlayer.add(i);
            }
        }
        if(DataPers.dataP().hopsPerLevel.get(currentLevel) > hops ){
            DataPers.dataP().hopsPerLevel.put(currentLevel,hops);
        }
        DataPers.saveP();
    }
}
