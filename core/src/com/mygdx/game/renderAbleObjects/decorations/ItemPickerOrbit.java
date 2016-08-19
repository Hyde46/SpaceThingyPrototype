package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by denis on 8/19/16.
 */
public class ItemPickerOrbit extends Unit implements IInputHandler{

    private int pickerOrbitType;
    private float pickerOrbitRadius;

    private Vector2 pickedCoordinate;
    private boolean isPickingItem;

    public ItemPickerOrbit(){
        pickedCoordinate = null;
    }

    public void initialize(int type, float radius, Vector2 position, String pathToTexture){
        initializePositions(position);
        this.pickerOrbitRadius = radius;
        this.pickerOrbitType = type;
        this.spriteDimension = new Vector2(radius*2, radius*2);
        initializeTexture(spriteDimension, 0, pathToTexture);
        isUI = false;
        isActive = true;
        this.collisionHitbox = new Circle(position.x, position.y, radius);
        if(type == 0)
            unitType = UnitType.ITEM_PICKER;
        else {
            unitType = UnitType.ITEM_PICKER_ON_CLICK;
            InputManager.get.register(this);
            this.touchHitbox = new Circle(position.x, position.y, radius);
        }
        isPickingItem = false;
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

    public boolean isPickingItem(){
        return isPickingItem;
    }

    public Vector2 getPickedCoordinate(){
        return pickedCoordinate;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    public void switchActive(){
        isActive = !isActive;

    }

    public void switchActive(boolean b){
        isActive = false;
    }

    public void OnTouch(TouchData td) {
        if(!isActive() || pickerOrbitType == 0) return;
        //pickedCoordinate = td.getPosCurrent().cpy();
        pickedCoordinate = td.getPosCurrentUnprojected().cpy();
        isPickingItem = true;
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
    }

    public void OnHold(TouchData td) {

    }

    public void OnSwipe(TouchData td) {

    }

    public void resetIsPickingItem(){
        isPickingItem = false;
        pickedCoordinate = null;
    }
}
