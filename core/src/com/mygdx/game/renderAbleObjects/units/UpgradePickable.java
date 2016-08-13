package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.levels.LevelState;

/**
 * Created by denis on 8/12/16.
 */
public class UpgradePickable extends PickableItem {


    public UpgradePickable(){
        super();
    }

    public void initialize(int itemID, Vector2 pos){
        initializeItem(itemID, pos, 1);
    }
    protected void updateLevelState(LevelState levelState){

    }
}
