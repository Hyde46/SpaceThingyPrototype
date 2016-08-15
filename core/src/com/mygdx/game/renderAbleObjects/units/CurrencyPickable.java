package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.levels.LevelState;

import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

/**
 * Created by denis on 8/12/16.
 */
public class CurrencyPickable extends PickableItem {

    private int currencyAmount;

    public CurrencyPickable(){
        super();
    }

    public void initialize(int itemID, Vector2 pos, int currencyAmount){
        initializeItem(itemID, pos, 2);
        this.currencyAmount = currencyAmount;
    }

    protected void updateLevelState(LevelState levelState){
        levelState.addCurrency(this.currencyAmount);
    }
}
