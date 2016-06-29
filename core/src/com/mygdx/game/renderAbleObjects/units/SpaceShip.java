package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.Animation;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by denis on 5/13/16.
 */
public class SpaceShip extends Unit {

    private float currentOrbitRadius;
    private Planet connectedPlanet;
    private int lastConnectedPlanetId;

    private float rotationSpeed;
    private int rotationDirection;

    private Circle targetHitbox;

    private boolean isCollided;
    private boolean isLost;

    private boolean hasReachedGoal;

    private Animation deathAnimation;

    public SpaceShip(){
        super();
        unitType = 0;
        deltaMovement = new Vector2();
    }

    public void initialize(Vector2 position,Vector2 deltaMovement,Planet connectedPlanet, float currentOrbitRadius, Vector2 spriteDimensions, String texturePath, int spriteId){
//        unitType = 0;
        isCollided = false;
        isLost = false;
        hasReachedGoal = false;
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = currentOrbitRadius;

        if(connectedPlanet != null){
            lastConnectedPlanetId = connectedPlanet.getUnitID();
            connectedPlanet.connectSpaceShip(this);
        }else{
            lastConnectedPlanetId = -1;
        }


        rotationSpeed = deltaMovement.len() / currentOrbitRadius;
        rotationSpeed = rotationSpeed*180.0f/3.141592653f;
        rotationDirection = 1;

        collisionHitbox = new Circle(position.x,position.y,20f);
        targetHitbox = new Circle(position.x,position.y,20f);

        initializePositions(position,deltaMovement);
        initializeTexture(spriteDimensions, spriteId, texturePath);

        deathAnimation = new Animation();
        deathAnimation.setAnimation(9,0.06f,new Vector2(64,64),false,"player_death_f",this);
    }

    public void launch(){
        if(!isInOrbit()){
            return;
        }

        Vector2 b = position.cpy();
        Vector2 vecToPlanet = b.sub(connectedPlanet.getPosition());
        vecToPlanet = vecToPlanet.nor();
        deltaMovement = vecToPlanet.cpy();
        deltaMovement.scl(270f);
        deltaMovement.set(-deltaMovement.y,deltaMovement.x);
        deltaMovement.scl(1.0f/deltaMovement.len());
        rotationSpeed = rotationSpeed*3.141592653f/180.0f;
        deltaMovement.scl(rotationDirection * rotationSpeed * currentOrbitRadius);
        lastConnectedPlanetId = connectedPlanet.getUnitID();
        connectedPlanet = null;
        currentOrbitRadius = 0f;
    }

    public void enterOrbit(Planet connectedPlanet, float orbitRadius){

        if(isInOrbit() || lastConnectedPlanetId == connectedPlanet.getUnitID()){
            return;
        }
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = orbitRadius;

        connectedPlanet.connectSpaceShip(this);

        int i = connectedPlanet.getPosition().y > position.y ? 1:-1;

        if(position.x > prevPosition.x )
            rotationDirection = i * 1;
        else
            rotationDirection = i * -1;

        rotationSpeed = deltaMovement.len() / orbitRadius;
        rotationSpeed = rotationSpeed*180.0f/3.141592653f;
        currentOrbitRadius = orbitRadius;
    }

    public void update(float delta){
        if(connectedPlanet == null) {
            targetPosition.add(deltaMovement.cpy().scl(delta));
            targetHitbox.set(targetPosition,20f);
        }else{
            targetPosition = SpaceMath.rotatePoint(position,connectedPlanet.getPosition(),rotationSpeed*delta,rotationDirection);

            if(connectedPlanet.getIsMoving()){
                targetPosition.add(connectedPlanet.getTranslation());
            }


            currentRotDrawingAngle += rotationDirection * rotationSpeed * delta;

            if(currentRotDrawingAngle > 360)
                currentRotDrawingAngle -= 360;
            if(currentRotDrawingAngle < 0)
                currentRotDrawingAngle += 360;

            sprite.rotate(rotationSpeed*delta*rotationDirection);

            targetHitbox.set(targetPosition,20f);
        }
        //if(isCollided)
        deathAnimation.update(delta,isCollided);

    }

    @Override
    public void render(SpriteBatch g){
        if(!isActive || tex == null){
            return;
        }
        if(!isCollided)
            sprite.draw(g);
        else
            deathAnimation.render(g);
        //g.draw(tex,position.x-spriteDimension.x/2,position.y-spriteDimension.y/2,spriteDimension.x,spriteDimension.y);
    }

    @Override
    public void renderHitboxes(ShapeRenderer sr){
        if(!isDebug)
            return;
        sr.circle(position.x,position.y,20f);
    }

    public boolean isInOrbit(){ return connectedPlanet != null; }

    public Planet getConnectedPlanet(){
        return connectedPlanet;
    }

    @Override
    public void moveUnit(){
        if(isCollided)
            return;
        prevPosition.set(position);
        position.set(targetPosition);
        ((Circle)collisionHitbox).set(targetHitbox);
        sprite.setX(position.x-spriteDimension.x/2);
        sprite.setY(position.y-spriteDimension.y/2);
    }

    public Circle getTargetHitbox(){
        return targetHitbox;
    }

    public void collide(){
        this.isCollided = true;
    }

    public boolean isCollided(){
        return isCollided;
    }

    public void loseShip(){ isLost = true; }

    public boolean isLost(){ return isLost; }

    public void reachGoal(){ this.hasReachedGoal = true; }

    public boolean hasReachedGoal(){ return hasReachedGoal; }

    //METHODS FOR ITEMS
    public void boost(float boostScl,float delta){
        float scaledBoost = 1.0f+boostScl*delta;
        rotationSpeed = rotationSpeed * scaledBoost;
        deltaMovement = deltaMovement.scl(scaledBoost).cpy();

    }

}
