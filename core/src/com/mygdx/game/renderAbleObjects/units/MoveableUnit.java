package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

/**
 * Created by denis on 5/13/16.
 */
public abstract class MoveableUnit extends Unit{

    protected Vector2 deltaMovement;
    protected Vector2 targetPosition;

    protected Circle targetHitbox;

    protected boolean isCollided;

    protected Vector2 prevPosition;

    protected MoveableUnit(Vector2 pos, Vector2 spriteDimension, int spriteID){
        super(pos,spriteDimension,spriteID);
        this.deltaMovement = new Vector2(0,0);
        targetPosition = new Vector2(pos.x,pos.y);
        isCollided = false;
        prevPosition = new Vector2(pos);
    }

    public void moveUnit(){
        prevPosition.set(position);
        this.position.set(targetPosition);
        this.hitbox.set(targetHitbox);
    }

    public Vector2 getDeltaMovement(){
        return deltaMovement;
    }

    public Vector2 getTargetPosition(){
        return targetPosition;
    }

    public Circle getTargetHitbox(){ return targetHitbox ; }

    public void collide(){ this.isCollided = true; }

    public boolean isCollided(){ return isCollided; }

    public abstract void reset();

    //TODO sollte man besser lösen
    //public abstract int getOrbitRadius();
    public abstract int getPlanetId();
}
