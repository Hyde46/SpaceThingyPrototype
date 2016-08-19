package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Items.Item;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 8/19/16.
 */
public class ItemPickerOrbit extends Unit {


    public ItemPickerOrbit(){

    }

    public void initialize(float radius, Vector2 position, String pathToTexture){
        initializePositions(position);
        this.spriteDimension = new Vector2(radius*2, radius*2);
        initializeTexture(spriteDimension, 0, pathToTexture);
        isUI = false;
        isActive = false;
        unitType = UnitType.ITEM_PICKER;
        this.collisionHitbox = new Circle(position.x, position.y, radius);
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
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    public void switchActive(){
        isActive = !isActive;

    }
}
