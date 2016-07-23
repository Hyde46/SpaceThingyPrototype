package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Vali on 10.07.2016.
 */
public class Slot extends Decoration {


    public void initialize(Vector2 position, int width, int height, String pathToTexture){
        this.position = position.cpy();
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
    }
    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }
}
