package com.mygdx.game.renderAbleObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/13/16.
 */
public abstract class ARenderAbleObject {

    protected Vector2 position;
    protected Vector2 spriteDimension;
    protected int spriteID;
    protected Shape2D collisionHitbox;
    protected Shape2D touchHitbox;
    protected boolean isActive;
    protected boolean isDebug;

    //temporary
    protected Texture sprite;

    protected ARenderAbleObject(){
        position = new Vector2();
        spriteDimension  = new Vector2();
        sprite = null;
        isActive = false;
        isDebug = true;
    }

    //lots of initialize methods will have a different header?
    //public abstract void initialize();

    public abstract void render(SpriteBatch g);
    public abstract void renderHitboxes(ShapeRenderer d);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSpriteDimensions(){
        return spriteDimension;
    }

    public Texture getSprite(){ return sprite; }

    public boolean isActive(){ return isActive; }

    public Shape2D getHitbox(){ return touchHitbox; }

    public Shape2D getCollisionHitbox(){return collisionHitbox; }

    public void setRendered(boolean b){
        this.isActive = b;
    }


}
