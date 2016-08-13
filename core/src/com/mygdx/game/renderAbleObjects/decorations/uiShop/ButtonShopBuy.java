package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.ItemScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.ScreenShop;

import java.util.ArrayList;

/**
 * Created by ilost on 04.08.2016.
 */

public class ButtonShopBuy extends Decoration implements IInputHandler
{
    private ScreenShop ss;
    private int levelShop;
    private int idItem;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int levelShop, int idItem, ScreenShop ss){
        initializePositions(position);
        this.ss = ss;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.levelShop = levelShop;
        this.idItem = idItem;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td)
    {
        int price = 20;
        int creditsOwned = DataPers.dataP().credits;

        if(creditsOwned >= price)
        {
            DataPers.dataP().credits -= price;
            DataPers.dataP().idsItemsPlayer.add(idItem);
            DataPers.saveP();

            DataPers.dataS().idsItemsAvailable.get(levelShop).remove(new Integer(idItem));
            DataPers.saveS();

            ss.buildShop();
        }
        else
        {
            // no money
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