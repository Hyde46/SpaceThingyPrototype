package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.AUpdateableObject;

/**
 * Created by denis on 5/13/16.
 */
public abstract class Unit extends AUpdateableObject {

    private static int UID = 0;

    protected int unitType;
    /*
    0 - SpaceShip
    1 - Planet
    2 -
    3 -
    4 -
     */

    private int unitID;
    protected Vector2 deltaMovement;
    protected Vector2 targetPosition;
    protected Vector2 prevPosition;

    protected Unit(){
        super();
        unitID = UID++;
        deltaMovement = new Vector2();
        targetPosition = new Vector2();
        prevPosition = new Vector2();
    }

    protected void initializePositions(Vector2 position){

        this.position = position.cpy();
        prevPosition.set(position);
        this.deltaMovement.set(0,0);
    }

    protected void initializeTexture(Vector2 spriteDimensions, int spriteId, String texturePath){
        this.spriteDimension.set(spriteDimensions);
        this.spriteID = spriteId;
    }

    public void render(SpriteBatch g){
        if(!isActive){
            return;
        }
        g.draw(sprite,position.x,position.y);
    }

    public abstract void moveUnit();

    public Shape2D getHitbox(){
        return hitbox;
    }
    public int getUnitID(){
        return unitID;
    }
    public int getUnitType(){ return unitType; }

    public Vector2 getDeltaMovement(){
        return deltaMovement;
    }
}
