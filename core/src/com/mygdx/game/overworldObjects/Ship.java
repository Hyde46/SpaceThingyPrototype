package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.utils.SpaceMath;

import sun.rmi.runtime.Log;

/**
 * Created by Vali on 18.05.2016.
 */
public class Ship extends ARenderableObject{
    private LevelBeacon currentLevel;
    private float rotationSpeed;
    private int rotationDirection;
    private boolean isInOrbit;
    private boolean travelsRoute;
    private Array<LevelBeacon> currentRoute;
    private Vector2 vectorToBeacon;


    /**
     * Initializes the ship with a given position
     * @param currentLevel
     */
    public void initialize(LevelBeacon currentLevel, Vector2 spriteDimensions, String texturePath){

        //the y position is the y position of the beacon + half of its height (ergo 150) so the ship centered vertically
        initializePositions(new Vector2(currentLevel.getPosition().x, currentLevel.getPosition().y + currentLevel.getHeight() / 2));
        this.currentLevel = currentLevel;
        rotationSpeed = 80.5f;
        rotationDirection = -1;     //clockwise or counterclockwise
        isInOrbit = true;
        travelsRoute = false;
        this.spriteDimension = spriteDimensions;
        //sprite id needs to be changed later
        initializeTexture(spriteDimensions, 0, texturePath);
        //rotate the sp
    }

    public void renderHitboxes(ShapeRenderer shapeRenderer){

    }


    /**
     * updates movement of ship
     * @param delta
     */
    public void update(float delta){
        //if ship is currently circling around planet the circling movement shell be made
        if(isInOrbit){
            //set new position, which will be rendered in next frame
            position = SpaceMath.rotatePoint(position, currentLevel.getPositionCenter(), rotationSpeed * delta, rotationDirection);

            //rotate the sprite so that it looks like the ship actually circles around beacon (just like ingame)
            sprite.rotate(rotationSpeed*delta*rotationDirection);
        }else{
            //set new position in direction to the new beacon
            position.add(vectorToBeacon.cpy().scl(delta));
            //if ship is inside of the goal beacon it should start rotating again
            if(currentLevel.getHitbox().contains(position)){
                isInOrbit = true;
                travelsRoute = false;
                //rotate ship again
                sprite.setRotation(currentLevel.getPositionCenter().cpy().sub(position.cpy()).angle());
            }else if(travelsRoute && currentRoute.peek().getHitbox().contains(position)){  //second case: ship has reached next beacon on route
                //in this case we tell the ship to fly to the next beacon in the route
                currentRoute.pop(); //pop (delete) the reached beacon
                flyToBeacon(currentRoute.peek());
            }
        }
        //set the sprite to the new position, so that the sprite is rendered on the correct new position
        sprite.setX(position.x-spriteDimension.x/2);
        sprite.setY(position.y-spriteDimension.y/2);
    }

    /**
     * start the route after it has been set and this function has been called by PathNavigationManager
     */
    public void startRoute(){
        System.out.println("size of route: " + currentRoute.size);
        flyToBeacon(currentRoute.peek()); //peek returns the last item (without deleting)
    }
    /**
     * fly to the given level beacon
     * @param beacon given by PathNavigationManager
     */
    public void flyToBeacon(LevelBeacon beacon){
        vectorToBeacon = beacon.getPositionCenter().cpy().sub(position.cpy());
        System.out.println("Angle: " + vectorToBeacon.angle());
        //rotate the ship so that it aims at the new beacon
        //therefore we need get the angle of the vector to the beacon
        sprite.setRotation(vectorToBeacon.angle() - 90);
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

    /**
     * set, if ship travels a route right now
     * @param travelsRoute
     */
    public void setTravelsRoute(boolean travelsRoute){
        this.travelsRoute = travelsRoute;
    }

    /**
     * set the route, that the ship is currently on
     * @param currentRoute
     */
    public void setCurrentRoute(Array<LevelBeacon> currentRoute){
        this.currentRoute = currentRoute;
    }

    /**
     * getter for boolean, if ship is in orbit
     * @return
     */
    public boolean getInOrbit(){
        return isInOrbit;
    }

    public boolean getTravelsRoute(){
        return travelsRoute;
    }


}

