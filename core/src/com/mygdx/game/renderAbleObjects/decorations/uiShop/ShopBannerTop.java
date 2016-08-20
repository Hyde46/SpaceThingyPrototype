package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;

/**
 * Created by ilost on 04.08.2016.
 */

public class ShopBannerTop extends Decoration
{
    public void initialize(Vector2 position, int width, int height)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, "shop-banner-900-400.png");
    }

    @Override
    public void renderHitboxes(ShapeRenderer d)
    {

    }
}