package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 17.08.2016.
 */
public class LevelInfo extends ARenderableObject implements IInputHandler {

    private int levelId;
    private boolean isExpanded;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int levelId)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.levelId = levelId;
        this.isExpanded = false;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }


    @Override
    public void OnTouch(TouchData td){
        if(isExpanded){
            initialize(position, 300, 100, "level_info.png", levelId);
            isExpanded = false;
        }else{
            initialize(position, 300, 500, "level_info_expanded.png", levelId);
            isExpanded = true;
        }
    }

    @Override
    public void OnRelease(TouchData td) {

    }

    @Override
    public void OnDrag(TouchData td) {

    }

    @Override
    public void OnHold(TouchData td) {

    }

    @Override
    public void OnSwipe(TouchData td) {

    }

}
