package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.ScreenShop;

/**
 * Created by ilost on 04.08.2016.
 */

public class ShopTabBack extends Decoration implements IInputHandler
{
    public void initialize(Vector2 position, int width, int height){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
 //       this.setUI(true);
        initializeTexture(spriteDimension, 0, "shop-tab-back-225-150.png");
    }

    @Override
    public void renderHitboxes(ShapeRenderer d)
    {

    }

    @Override
    public void OnTouch(TouchData td)
    {
        MyGdxGame.game.openScreen(new MainMenuScreen());
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
