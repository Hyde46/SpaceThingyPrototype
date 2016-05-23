package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.utils.SpaceMath;

import sun.rmi.runtime.Log;

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
    private boolean travelsRoute;
    private Array<LevelBeacon> currentRoute;
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
        travelsRoute = false;
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
            //if ship is inside of the goal beacon it should start rotating again
            if(currentLevel.getHitbox().contains(position)){
                isInOrbit = true;
                travelsRoute = false;
            }else if(travelsRoute && currentRoute.peek().getHitbox().contains(position)){  //second case: ship has reached next beacon on route
                //in this case we tell the ship to fly to the next beacon in the route
                currentRoute.pop(); //pop (delete) the reached beachon
                flyToBeacon(currentRoute.peek());
            }
        }
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

