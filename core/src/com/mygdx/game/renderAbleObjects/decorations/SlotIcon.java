package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 17.08.2016.
 */
public class SlotIcon extends Decoration implements IInputHandler{

    private int itemId;

    public void initialize(Vector2 position, int width, int height, String pathToTexture)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
        itemId = 0;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * getter for id of item, used in Hangarscreen
     * @return id
     */
    public int getItemId(){
        return  itemId;
    }



    @Override
    public void OnTouch(TouchData td){
        HangarScreen screen = (HangarScreen) MyGdxGame.game.current;
        itemId = screen.getCurrentItemId();
        screen.setShowPopUp(false);
        screen.saveSettings();
        DataPers.saveH();
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


