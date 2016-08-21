package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.ARenderableObject;

/**
 * Created by ilost on 20.08.2016.
 */

public class GenericElement extends ARenderableObject
{
    public void initialize(Vector2 position, int width, int height, String pathTexture)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d)
    {

    }
}