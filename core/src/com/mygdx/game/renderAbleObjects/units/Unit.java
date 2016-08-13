package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    2 - Pickable Item
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

    protected void initializePositions(Vector2 position,Vector2 deltaMovement){

        this.position = position.cpy();
        targetPosition = position.cpy();
        prevPosition.set(position);
        this.deltaMovement.set(0,0);
        this.deltaMovement = deltaMovement.cpy();
    }

    @Override
    protected void initializeTexture(Vector2 spriteDimensions, int spriteId, String texturePath){

        this.spriteID = spriteId;
        if(texturePath != null) {
            tex = new Texture(Gdx.files.internal(texturePath));
            sprite = new Sprite(tex,(int)spriteDimensions.x,(int)spriteDimensions.y);
            sprite.setCenter((int)(spriteDimensions.x/2),(int)(spriteDimensions.y/2));
            sprite.setX(position.x-spriteDimensions.x/2);
            sprite.setY(position.y-spriteDimensions.y/2);
            isActive = true;
            this.spriteDimension.set(spriteDimensions);
        }
    }

    public abstract void moveUnit();

    public int getUnitID(){
        return unitID;
    }
    public int getUnitType(){ return unitType; }

    public Vector2 getDeltaMovement(){
        return deltaMovement;
    }
    public Vector2 getTargetPosition(){ return targetPosition;}

    public Vector2 getPrevPosition(){ return prevPosition; }
}
