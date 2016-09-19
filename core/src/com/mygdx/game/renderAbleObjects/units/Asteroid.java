package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Denis on 19.09.2016.
 */
public class Asteroid extends Unit {

    private boolean isMoving;
    private int rotationDirection;
    private float rotationSpeed;

    public Asteroid(){
        isMoving = false;
    }

    public void initialize(Vector2 pos,Vector2 movement, float planetRadius, String texturePath, float initialRotation,int rotationDirection,float rotationSpeed){
        unitType = UnitType.OBSTACLE;
        this.collisionHitbox = new Circle(pos.x, pos.y, planetRadius);
        initializePositions(pos, new Vector2(0, 0));
        initializeTexture(new Vector2(planetRadius * 2, planetRadius * 2), 0, texturePath);
        sprite.rotate(initialRotation);
        isMoving = movement!=null;
        this.deltaMovement = movement.cpy();
        this.rotationDirection = rotationDirection;
        this.rotationSpeed = rotationSpeed;
    }
    public void update(float delta){
        this.targetPosition = position.cpy().add(deltaMovement);
        sprite.rotate(rotationDirection*rotationSpeed);
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
