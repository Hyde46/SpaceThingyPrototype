package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by denis on 5/13/16.
 */
public class SpaceShip extends Unit {

    private float currentOrbitRadius;
    private Planet connectedPlanet;

    private float rotationSpeed;
    private int rotationDirection;

    private Circle targetHitbox;

    public SpaceShip(){
        super();

        deltaMovement = new Vector2();
    }

    public void initialize(Vector2 position,Planet connectedPlanet, float currentOrbitRadius, Vector2 spriteDimensions, String texturePath, int spriteId){
        unitType = 0;
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = currentOrbitRadius;

        rotationSpeed = 1.5f;
        rotationDirection = 1;

        hitbox = new Circle(position.x,position.y,20f);
        targetHitbox = new Circle(position.x,position.y,20f);

        initializePositions(position);
        initializeTexture(spriteDimensions, spriteId, texturePath);

    }

    public void launch(){
        if(!isInOrbit()){
            return;
        }
        currentOrbitRadius = 0f;

        Vector2 b = position.cpy();
        Vector2 vecToPlanet = b.sub(connectedPlanet.getPosition());
        vecToPlanet = vecToPlanet.nor();
        deltaMovement = vecToPlanet.cpy();
        deltaMovement.scl(7f);
        deltaMovement.set(-deltaMovement.y,deltaMovement.x);
        deltaMovement.scl(rotationDirection);
        connectedPlanet = null;
    }

    public void enterOrbit(Planet connectedPlanet, float orbitRadius){
        //TODO: dont connect to the last connected planet. may lead to problems
        if(isInOrbit()){
            return;
        }
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = orbitRadius;

        int i = connectedPlanet.getPosition().y > position.y ? 1:-1;

        if(position.x > prevPosition.x )
            rotationDirection = i * 1;
        else
            rotationDirection = i * -1;

    }

    public void update(float delta){
        if(connectedPlanet == null) {
            targetPosition.add(deltaMovement);
            targetHitbox.set(targetPosition,20f);
        }else{
            targetPosition = SpaceMath.rotatePoint(position,connectedPlanet.getPosition(),rotationSpeed,rotationDirection);
            targetHitbox.set(targetPosition,20f);
        }

    }

    public void renderHitboxes(ShapeRenderer sr){
        sr.circle(position.x,position.y,20f);
    }

    public boolean isInOrbit(){ return connectedPlanet != null; }

    public Planet getConnectedPlanet(){
        return connectedPlanet;
    }

    @Override
    public void moveUnit(){
        position.set(targetPosition);
        ((Circle)hitbox).set(targetHitbox);
    }

}
