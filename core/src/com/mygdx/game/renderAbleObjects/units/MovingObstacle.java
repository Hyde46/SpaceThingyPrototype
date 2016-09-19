package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Denis on 19.09.2016.
 */
public class MovingObstacle extends Unit{

    private boolean isMoving;

    private int movingDirection;
    private float maxMovingUnits;
    private float currentMovedUnits;

    public MovingObstacle(){
        isMoving = false;
    }

    public void initialize(Vector2 pos, Vector2 movement, float planetRadius, String texturePath, float initialRotation, float maxMovingUnits){
        unitType = Unit.UnitType.OBSTACLE;
        this.collisionHitbox = new Circle(pos.x, pos.y, planetRadius);
        initializePositions(pos, new Vector2(0, 0));
        initializeTexture(new Vector2(planetRadius * 2, planetRadius * 2), 0, texturePath);
        sprite.rotate(initialRotation);
        isMoving = movement!=null;
        this.deltaMovement = movement.cpy();
        this.maxMovingUnits = maxMovingUnits;
        this.currentMovedUnits = 0.5f*maxMovingUnits;
        movingDirection = 1;
    }
    public void update(float delta){
        this.targetPosition = position.cpy().add(deltaMovement.cpy().scl(movingDirection));
        currentMovedUnits += movingDirection * delta;
        if(currentMovedUnits*currentMovedUnits >= maxMovingUnits*maxMovingUnits){
            movingDirection = -movingDirection;
            currentMovedUnits = 0;
        }
    }

    public void renderHitboxes(ShapeRenderer sr){

    }

    public void moveUnit(){
        Vector2 translation = new Vector2(targetPosition.cpy().x - position.cpy().x, targetPosition.cpy().y - position.cpy().y);
        sprite.translate(translation.x, translation.y);
        ((Circle) collisionHitbox).setPosition(targetPosition);
        this.position.set(this.targetPosition.cpy());
    }
}
