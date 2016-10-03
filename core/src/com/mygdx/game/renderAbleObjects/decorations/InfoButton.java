package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.ItemScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 12.07.2016.
 */
public class InfoButton extends Decoration implements IInputHandler
{
    private int orderId;
    private int itemId;
    private int idReturn;
    private int levelidReturn;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int orderId, int itemId, int idReturn,int leveidReturn)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        //we hold the item slot the button belongs to so that we can change its color
        this.orderId = orderId;
        this.itemId = itemId;
        this.idReturn = idReturn;
        initializeTexture(spriteDimension, 0, pathToTexture);
        this.levelidReturn = leveidReturn;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td)
    {
        HangarScreen screen = (HangarScreen) MyGdxGame.game.current;
        if(!screen.getShowPopUp()) {
            MyGdxGame.game.openScreen(new ItemScreen(itemId, idReturn,levelidReturn,true));
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
