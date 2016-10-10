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

    private int collectedVisualItem;

    private int hops;

    private int currentLevel;

    private String levelName;

    private boolean hasWon;

    public void resetState(){
        currencyCollected = 0;
        hops = 0;
        itemIdsCollected.clear();
        collectedVisualItem = -1;
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

    public int getCurrentLevel(){ return currentLevel; }

    public void setCollectedVisualItem(int i){
        collectedVisualItem = i;
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
        if(DataPers.dataP().hopsPerLevel[currentLevel] > hops ){
            DataPers.dataP().hopsPerLevel[currentLevel] = hops;
        }
        DataPers.dataP().addToSkins(collectedVisualItem);
        DataPers.saveP();
    }

    public void setLevelName(String lN){
        this.levelName = lN;
    }

    public String getLevelName(){
        return levelName;
    }

    public void setWon(boolean b){ this.hasWon = b;}
    public boolean getHasWon(){ return hasWon;}

    public void setCurrencyToZero(){
        currencyCollected = 0;
    }
}
