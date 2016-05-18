package com.mygdx.game.renderAbleObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/13/16.
 */
public abstract class RenderAbleObject {

    protected Vector2 position;
    protected Vector2 spriteDimension;
    protected int spriteID;

    protected RenderAbleObject(Vector2 pos,Vector2 spriteDimension, int spriteID){
        this.position = new Vector2(pos);
        this.spriteID = spriteID;
        this.spriteDimension  = new Vector2(spriteDimension);
    }

    public abstract void render(SpriteBatch g);
    public abstract void renderHitboxes(ShapeRenderer d);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSpriteDimensions(){
        return spriteDimension;
    }

    public void setNewPosition(Vector2 position){
        this.position.set(position);
    }

}
