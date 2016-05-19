package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Vali on 18.05.2016.
 */
public class Ship {
    protected Vector2 position;
    protected final static float SHIP_RADIUS = 50;
    private LevelBeacon currentLevel;
    private float rotationSpeed;
    private int rotationDirection;
    private boolean isInOrbit;
    private Vector2 vectorToBeacon;

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
        rotationSpeed = 60.5f;
        rotationDirection = 1;     //clockwise or counterclockwise
        isInOrbit = true;
    }

    /**
     * Renders the ship: circle with help of ShapeRenderer
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.circle(position.x, position.y, SHIP_RADIUS);
    }

    /**
     * Updates movement of Ship
     */
    public void update(float delta){
        //if ship is currently circling around planet the circling movement shell be made
        if(isInOrbit){
            //set new position, which will be rendered in next frame
            position = SpaceMath.rotatePoint(position, currentLevel.getPositionCenter(), rotationSpeed * delta, rotationDirection);
        }else{
            //set new position in direction to the new beacon
            position.add(vectorToBeacon.cpy().scl(delta));
            //if ship is inside of the new beacon it should start rotating again
            if(currentLevel.getHitBox().contains(position)){
                isInOrbit = true;
            }
        }
    }

    /**
     * fly to the given level beacon
     * @param beacon given by PathNavigationManager
     */
    public void flyToBeacon(LevelBeacon beacon){
        vectorToBeacon = beacon.getPosition().cpy().sub(position.cpy());
    }
    /**
     * set current level
     * @param beacon
     */
    public void setCurrentLevel(LevelBeacon beacon){
        currentLevel = beacon;
    }

    /**
     * set, if ship is in orbit right now
     * @param isInOrbit
     */
    public void setInOrbit(boolean isInOrbit){
        this.isInOrbit = isInOrbit;
    }
}
