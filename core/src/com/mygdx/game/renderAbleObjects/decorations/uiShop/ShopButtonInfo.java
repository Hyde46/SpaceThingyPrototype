package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.ItemScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by ilost on 04.08.2016.
 */

public class ShopButtonInfo extends Decoration implements IInputHandler
{
    private int idItem;
    private int idReturn;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int idItem, int idReturn){
        initializePositions(position);
        this.idReturn = idReturn;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.idItem = idItem;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td)
    {
        MyGdxGame.game.openScreen(new ItemScreen(idItem, idReturn));
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