package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Vali on 18.05.2016.
 */
public class Ship {
    protected Vector2 position;
    protected final static float SHIP_RADIUS = 5;
    private LevelBeacon currentLevel;
    private float rotationSpeed;
    private int rotationDirection;

    public Ship(){
        position = new Vector2();
    }

    /**
     * Initializes the ship with a given position
     * @param currentLevel
     */
    public void initialize(LevelBeacon currentLevel){
        position = currentLevel.getPosition().cpy();
        this.currentLevel = currentLevel;
        rotationSpeed = 1.5f;
        rotationDirection = 1;     //clockwise or counterclockwise
    }

    /**
     * Renders the ship: circle with help of ShapeRenderer
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.circle(position.x, position.y, SHIP_RADIUS);
    }

    public void update(){
        //if ship is currently circling around planet the circling movement shell be made
        if(currentLevel != null){
            //set new position, which will be rendered in next frame
            position = SpaceMath.rotatePoint(position, currentLevel.getPosition(), rotationSpeed, rotationDirection);
        }
    }
}
