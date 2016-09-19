package com.mygdx.game.renderAbleObjects.decorations.uiItemDisplay;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.shop.ScreenShop;

/**
 * Created by Vali on 22.07.2016.
 */
public class ItemDisplayButtonReturn extends Decoration
{
    public void initialize(Vector2 position, int width, int height, String pathToTexture){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }
}