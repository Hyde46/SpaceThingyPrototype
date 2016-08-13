package com.mygdx.game.renderAbleObjects.decorations.uiShop;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;

/**
 * Created by ilost on 04.08.2016.
 */

public class uiButtonBuy extends Decoration
{
    public void initialize(Vector2 position, int width, int height, String pathToTexture, int id){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        //we hold the item slot the button belongs to so that we can change its color
 //       this.id = id;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }



    @Override
    public void renderHitboxes(ShapeRenderer d)
    {

    }
}
