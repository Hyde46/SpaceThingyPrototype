package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 8/22/16.
 */
public class TeleportRangeDetector extends Unit implements IInputHandler {

    private Vector2 pickedCoordinate;
    private boolean isTeleporting;
    private boolean isTeleportingDone;

    public TeleportRangeDetector(){
        pickedCoordinate =null;
    }

    public void initialize( float radius, Vector2 position, String pathToTexture){
        initializePositions(position);
        spriteDimension = new Vector2(radius*2,radius*2);
        initializeTexture(spriteDimension,0,pathToTexture);
        isUI = false;
        isActive =true;
        this.collisionHitbox = null;
        this.touchHitbox = new Circle(position.x,position.y,radius);
        this.collisionHitbox= new Circle(position.x,position.y,radius);
        unitType = UnitType.TELEPORT_PICKER;
        isTeleporting = false;
        isTeleportingDone = false;
        pickedCoordinate = new Vector2();
    }

    @Override
    public void update(float delta){

    }

    @Override
    public void moveUnit(){


    }

    public void updatePosition(Vector2 posUpdate){
        sprite.setPosition(posUpdate.x-spriteDimension.x/2,posUpdate.y-spriteDimension.y/2);
        ((Circle)collisionHitbox).setPosition(posUpdate);
        ((Circle)touchHitbox).setPosition(posUpdate);
    }


    public boolean isTeleporting(){
        return isTeleporting;
    }

    public Vector2 getPickedCoordinate(){
        return pickedCoordinate;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    public void switchActive(boolean b){
        isActive = b;
    }

    public void OnTouch(TouchData td) {
        if(!isActive()) return;
        //pickedCoordinate = td.getPosCurrent().cpy();
        pickedCoordinate.set(td.getPosWorldCurrent().x,td.getPosWorldCurrent().y);
        isTeleporting = true;
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
    }

    public void OnHold(TouchData td) {

    }

    public void OnSwipe(TouchData td) {

    }

    public void resetIsTelePorting(){
        isTeleporting = false;
        pickedCoordinate = new Vector2();
        isTeleportingDone = true;
    }

    public boolean isDoneTeleporting(){
        return isTeleportingDone;
    }

    public void resetState(){
        isTeleporting = false;
        pickedCoordinate = new Vector2();
        isTeleportingDone = false;
    }

}
