package com.mygdx.game.renderAbleObjects.decorations.uiItemDisplay;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.ScreenShop;

/**
 * Created by Vali on 22.07.2016.
 */
public class ButtonItemDisplayReturn extends Decoration implements IInputHandler
{
    private int idReturn;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int idReturn){
        initializePositions(position);
        this.idReturn = idReturn;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td)
    {
        System.out.println("touch return");
        MyGdxGame.game.openScreen(new ScreenShop(idReturn));

//        switch (idReturn)
//        {
//            case 4:
//            {
//                MyGdxGame.game.openScreen(new HangarScreen());
//                break;
//            }
//            case 1:
//            case 2:
//            case 1:
//            {
//                MyGdxGame.game.openScreen(new ScreenShop(idReturn));
//                break;
//            }
//            default: break;
//        }
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