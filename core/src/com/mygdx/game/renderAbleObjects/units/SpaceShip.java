package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.Animation;
import com.mygdx.game.renderAbleObjects.decorations.ItemPickerOrbit;
import com.mygdx.game.renderAbleObjects.decorations.TeleportRangeDetector;
import com.mygdx.game.utils.SpaceMath;
import com.mygdx.game.utils.SpacePhysiX;

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

    private boolean isPhasedOut;

    //flicker effect
    private boolean isFlickering;

    private int skinID;

    public SpaceShip(){
        super();
        deltaMovement = new Vector2();
        isPhasedOut = false;
    }

    public void initialize(Vector2 position,Vector2 deltaMovement,Planet connectedPlanet, float currentOrbitRadius, Vector2 spriteDimensions, int spriteId){
        unitType = UnitType.SPACE_SHIP;
        isCollided = false;
        isLost = false;
        hasReachedGoal = false;
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = currentOrbitRadius;
        this.spriteDimension.set(spriteDimensions);
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

        isItemPickerActive = false;

    }
    public void setSkin(int skinId){
        this.skinID = skinId;
        initializeTexture(spriteDimension, 0, "ship"+skinId+".png");
        deathAnimation = new Animation();
        deathAnimation.setAnimation(9,0.06f,new Vector2(64,64),false,"player_death"+skinId%2+"_f",this);
    }

    public void launch(){
        if(!isInOrbit()){
            return;
        }

        Vector2 b = position.cpy();
        Vector2 vecToPlanet = b.sub(connectedPlanet.getPosition());
        vecToPlanet = vecToPlanet.nor();
        deltaMovement = vecToPlanet.cpy();
        deltaMovement.set(-deltaMovement.y,deltaMovement.x);
        deltaMovement.scl(1.0f/deltaMovement.len());
        rotationSpeed = rotationSpeed* SpacePhysiX.PI/180.0f;
        deltaMovement.scl(rotationDirection * rotationSpeed * currentOrbitRadius);
        lastConnectedPlanetId = connectedPlanet.getUnitID();
        connectedPlanet = null;
        currentOrbitRadius = 0f;
    }

    public boolean enterOrbit(Planet connectedPlanet, float orbitRadius){

        if(isInOrbit() || lastConnectedPlanetId == connectedPlanet.getUnitID()){
            return false;
        }
        this.connectedPlanet = connectedPlanet;
        this.currentOrbitRadius = orbitRadius;

        connectedPlanet.connectSpaceShip(this);

        int i = connectedPlanet.getPosition().y > position.y ? 1:-1;

        if(position.x - prevPosition.x > 0.1 )
            rotationDirection = i * 1;
        else if(position.x - prevPosition.x < -0.1)
            rotationDirection = i * -1;
        else{
            rotationDirection = connectedPlanet.getPosition().x > position.x ? -1 : 1 ;
        }

        rotationSpeed = deltaMovement.len() / orbitRadius;
        rotationSpeed = rotationSpeed*180.0f/SpacePhysiX.PI;
        currentOrbitRadius = orbitRadius;

        return true;
    }

    public void update(float delta){

        if(connectedPlanet == null) {
            targetPosition.add(deltaMovement.cpy().scl(delta));
            targetHitbox.set(targetPosition,20f);
        }else{
            //let space ship fall into planet
            pullSpaceShipToPlanet(delta);

            //rotate space ship
            rotateSpaceShip(delta);

            //update angle to draw sprite
            updateSprite(delta);
            //update hitbox
            targetHitbox.set(targetPosition,20f);
        }
        //if(isCollided)
        deathAnimation.update(delta,isCollided);

    }

    private void pullSpaceShipToPlanet(float delta) {
        Vector2 fall = connectedPlanet.getPosition().cpy().sub(position).nor().scl(-connectedPlanet.getGravity()*delta);
        position.sub(fall);
        currentOrbitRadius -= fall.len();
        //get speed by falling into the planet
        rotationSpeed = rotationSpeed + (1/currentOrbitRadius) * connectedPlanet.getGravity() * 220 * delta;
    }

    private void rotateSpaceShip(float delta) {
        targetPosition = SpaceMath.rotatePoint(position,connectedPlanet.getPosition(),rotationSpeed*delta,rotationDirection);
        //make up for rotation if connected planet is also rotating
        if(connectedPlanet.getIsMoving()){
            targetPosition.add(connectedPlanet.getTranslation());
        }
    }

    private void updateSprite(float delta) {

        //TODO: Not working correct. Needs a bigger angle of rotation
        currentRotDrawingAngle += rotationDirection * rotationSpeed * delta;
        if(currentRotDrawingAngle > 360)
            currentRotDrawingAngle -= 360;
        if(currentRotDrawingAngle < 0)
            currentRotDrawingAngle += 360;
        sprite.rotate(rotationSpeed*delta*rotationDirection);
    }

    @Override
    public void render(SpriteBatch g){
        if(!isActive || tex == null || isFlickering){
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

    public boolean isPhasedOut(){ return isPhasedOut; }

    //ITEM Stuff
    private boolean isItemPickerActive;
    private ItemPickerOrbit itemPickerOrbit;
    private boolean isItemPickerActiveRadius;
    private ItemPickerOrbit itemPickerOrbitRadius;
    private boolean isTeleportActive;
    private TeleportRangeDetector teleportRangeDetector;
    private boolean isRandomTeleportActive;
    private TeleportRangeDetector teleportRangeDetectorR;

    public void boost(float boostScl,float delta){
        float scaledBoost = 1.0f+boostScl*delta;
        rotationSpeed = rotationSpeed * scaledBoost;
        deltaMovement = deltaMovement.scl(scaledBoost).cpy();

    }
    public void phaseOut(boolean b){
        isPhasedOut = b;
        isFlickering = true;
    }
    public void flicker(boolean b){
        isFlickering = b;
    }


    public boolean isItemPickerActive(){
        return itemPickerOrbit != null ? itemPickerOrbit.isActive() : false;
    }
    public boolean isItemPickerActiveRadius(){
        return itemPickerOrbitRadius != null ? itemPickerOrbitRadius.isActive() : false;
    }
    public boolean isTeleportActive(){
        return teleportRangeDetector != null ? teleportRangeDetector.isActive() : false;
    }
    public boolean isRandomTeleportActive(){
        return teleportRangeDetectorR != null ? teleportRangeDetectorR.isActive() : false;
    }
    public Circle getPickerCollisionHitbox(){
        return (Circle)(itemPickerOrbitRadius.getCollisionHitbox());
    }

    public ItemPickerOrbit getItemPickerOrbit(){
        return itemPickerOrbit;
    }

    public TeleportRangeDetector getTeleportRangeDetector(){ return teleportRangeDetector;}
    public TeleportRangeDetector getRandomTeleportRangeDetector(){ return teleportRangeDetectorR;}

    public void setItemPickerOrbit(ItemPickerOrbit itemPickerOrbit){
        this.itemPickerOrbit = itemPickerOrbit;
        isItemPickerActive = true;
    }
    public void setItemPickerOrbitRadius(ItemPickerOrbit itemPickerOrbit){
        this.itemPickerOrbitRadius = itemPickerOrbit;
        isItemPickerActiveRadius = true;
    }

    public void setTeleportRangeDetector(TeleportRangeDetector tprd){
        this.teleportRangeDetector = tprd;
        isTeleportActive = true;
    }

    public void setTeleportRangeDetectorRandom(TeleportRangeDetector tprd){
        this.teleportRangeDetectorR = tprd;
        isRandomTeleportActive = true;
    }

    public void teleport(Vector2 teleportPosition){
        targetPosition.set(teleportPosition.cpy());
        moveUnit();
    }

}
