package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.ScreenShop;

/**
 * Created by ilost on 04.08.2016.
 */

public class ShopButtonSell extends Decoration
{
    private ScreenShop ss;
    private int idItem;

    public void initialize(Vector2 position, int width, int height, int idItem, ScreenShop ss){
        initializePositions(position);
        this.ss = ss;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.idItem = idItem;
        initializeTexture(spriteDimension, 0, "shop-panel-sell-207-100.png");
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }
}