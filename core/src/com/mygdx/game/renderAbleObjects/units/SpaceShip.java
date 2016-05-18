package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by denis on 5/13/16.
 */
public class SpaceShip extends MoveableUnit {
    /*
    Players space ship for now
     */
    private boolean isInOrbit;
    private float currentOrbitRadius;
    private Vector2 connectedPlanetPos;

    private float rotationSpeed;
    private int rotationDirection;
    /*
    -1 if not connected
     */
    private int connectedPlanetID;

    private Vector2 resetPos;
    private float resetRadius;
    private Vector2 resetPlanetPos;
    private int resetConnectedPlanetID;

    public SpaceShip(Vector2 pos, Vector2 spriteDimension, int connectedPlanetID, float currentOrbitRadius, Vector2 connectedPlanetPos){
        super(pos,spriteDimension,0);
        this.connectedPlanetID = connectedPlanetID;
        isInOrbit = true;
        this.currentOrbitRadius = currentOrbitRadius;
        this.connectedPlanetPos = new Vector2(connectedPlanetPos.cpy());

        rotationSpeed = 1.5f;
        rotationDirection = 1;

        hitbox = new Circle(pos.x,pos.y,20f);
        targetHitbox = new Circle(pos.x,pos.y,20f);

        resetPos = new Vector2(pos.cpy());
        resetPlanetPos = new Vector2((connectedPlanetPos.cpy()));
        resetRadius = currentOrbitRadius;
        resetConnectedPlanetID = connectedPlanetID;

        deltaMovement = new Vector2();
    }

    public int getConnectedPlanetID(){
        return connectedPlanetID;
    }

    public void launch(){
        if(!isInOrbit){
            return;
        }
        currentOrbitRadius = 0f;

        Vector2 b = position.cpy();
        Vector2 vecToPlanet = b.sub(connectedPlanetPos);
        vecToPlanet = vecToPlanet.nor();
        deltaMovement = vecToPlanet.cpy();
        deltaMovement.scl(7f);
        deltaMovement.set(-deltaMovement.y,deltaMovement.x);
        deltaMovement.scl(rotationDirection);
        isInOrbit = false;

    }

    public void enterOrbit(int connectedPlanetID, float connectedPlanetRadius, Vector2 connectedPlanetPos){
        if(isInOrbit || connectedPlanetID == this.connectedPlanetID){
            return;
        }
        this.connectedPlanetID = connectedPlanetID;
        this.currentOrbitRadius = connectedPlanetRadius;
        this.connectedPlanetPos.set(connectedPlanetPos);
        isInOrbit = true;

        int i = connectedPlanetPos.y > position.y ? 1:-1;

        if(position.x > prevPosition.x )
            rotationDirection = i * 1;
        else
            rotationDirection = i * -1;

    }

    public void update(float delta){
        if(!isInOrbit) {
            targetPosition.add(deltaMovement);
            targetHitbox.set(targetPosition,20f);
        }else{
            targetPosition = SpaceMath.rotatePoint(position,connectedPlanetPos,rotationSpeed,rotationDirection);
            targetHitbox.set(targetPosition,20f);
        }

    }

    public void renderHitboxes(ShapeRenderer sr){
        sr.circle(position.x,position.y,20f);
    }

    //for now
    @Override
    public void reset(){

        this.position.set(resetPos.cpy());
        this.connectedPlanetPos.set(resetPlanetPos);
        this.connectedPlanetID = resetConnectedPlanetID;
        this.currentOrbitRadius = resetRadius;
        isCollided = false;
        isInOrbit = true;
    }

    //@Override
    public int getOrbitRadius(){return 0;}
    @Override
    public int getPlanetId(){ return 0; }

    public boolean isInOrbit(){ return isInOrbit; }
}
