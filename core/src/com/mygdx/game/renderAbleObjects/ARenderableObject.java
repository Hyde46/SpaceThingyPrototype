package com.mygdx.game.renderAbleObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputListener;

/**
 * Created by denis on 5/13/16.
 */
public abstract class ARenderableObject
{
    protected Vector2 position;
    protected Vector2 spriteDimension;
    protected int spriteID;
    protected Shape2D collisionHitbox;
    protected Shape2D touchHitbox;
    protected boolean isActive;
    protected boolean isDebug;

    protected float currentRotDrawingAngle;

    protected boolean isUI;

    //temporary
    protected Texture tex;
    protected Sprite sprite;

    protected ARenderableObject()
    {position = new Vector2();
        spriteDimension  = new Vector2();
        tex = null;
        isActive = false;
        isDebug = true;
        currentRotDrawingAngle = 0;
        isUI = false;
    }

    //lots of initialize methods will have a different header?
    //public abstract void initialize();

    public void setUI(boolean UI) {
        isUI = UI;
    }

    public abstract void renderHitboxes(ShapeRenderer d);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSpriteDimensions(){
        return spriteDimension;
    }

    public Texture getTex(){ return tex; }

    public boolean isActive(){ return isActive; }

    public Shape2D getHitbox(){ return touchHitbox; }

    public Shape2D getCollisionHitbox(){return collisionHitbox; }

    public void setRendered(boolean b){
        this.isActive = b;
    }

    public float getCurrentRotDrawingAngle(){ return currentRotDrawingAngle;}

    public void render(SpriteBatch g){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(g);
        //g.draw(tex,position.x-spriteDimension.x/2,position.y-spriteDimension.y/2,spriteDimension.x,spriteDimension.y);
    }

    protected void initializeTexture(Vector2 spriteDimensions, int spriteId, String texturePath){

        this.spriteID = spriteId;
        if(texturePath != null) {
            tex = new Texture(Gdx.files.internal(texturePath));
            sprite = new Sprite(tex,(int)spriteDimensions.x,(int)spriteDimensions.y);
            sprite.setX(position.x);
            sprite.setY(position.y);
            isActive = true;
            this.spriteDimension.set(spriteDimensions);
        }
    }

    protected void initializePositions(Vector2 position){
        this.position = position.cpy();
    }

    public boolean isUI(){
        return isUI;
    }

    public Sprite getSprite(){ return sprite; }

    public void dispose()
    {
        // problem ... sprite texture ist bei mir öfters null
        // vll direkt die tex clearen?

        if(tex != null)
        {
            //sprite.getTexture().dispose();
            tex.dispose();
            tex = null;
            sprite = null;
        }
    }

    // listener mechanic
    private InputListener listener;

    public void setListener(InputListener listener)
    {
        this.listener = listener;
    }
    public boolean hasListener()
    {
        return this.listener != null;
    }
    public InputListener getListener() {
        return listener;
    }
}