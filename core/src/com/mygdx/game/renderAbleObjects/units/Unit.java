package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.RenderAbleObject;

/**
 * Created by denis on 5/13/16.
 */
public abstract class Unit extends RenderAbleObject {

    private static int UID = 0;

    protected boolean isRendered;
    protected boolean isDeletable;
    protected Circle hitbox;

    private int unitID;

    protected Unit(Vector2 pos,Vector2 spriteDimension, int spriteID){
        super(pos,spriteDimension,spriteID);
        isRendered = false;
        isDeletable = false;
        unitID = UID++;

    }

    public abstract void update(float delta);

    public void render(SpriteBatch g){
        if(!isRendered || isDeletable){
            return;
        }
        //g.draw(whatever)
    }


    public void setRendered(boolean b){
        this.isRendered = b;
    }
    public boolean getIsDeleteable(){
        return isDeletable;
    }
    public Shape2D getHitbox(){
        return hitbox;
    }
    public int getUnitID(){
        return unitID;
    }
}
