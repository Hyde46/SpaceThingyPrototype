package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.ScreenShop;

/**
 * Created by ilost on 04.08.2016.
 */

public class ShopTabSell extends ARenderableObject implements IInputHandler
{
    private ScreenShop ss;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, ScreenShop ss){
        initializePositions(position);
        this.ss = ss;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d)
    {

    }

    @Override
    public void OnTouch(TouchData td) {
        // TODO
        System.out.println("pressed sell tab");
        ss.toggleBuyMode();
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