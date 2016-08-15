package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.levels.LevelState;

/**
 * Created by denis on 8/12/16.
 */
public class VisualPickable extends PickableItem{


    public VisualPickable(){
        super();
    }

    public void initialize(int itemID, Vector2 pos){
        initializeItem(itemID, pos, 0);
    }

    protected void updateLevelState(LevelState levelState){

    }
}
