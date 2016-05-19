package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Vali on 18.05.2016.
 */
public class LevelGraph {
    //array that holds on the levels
    private Array<LevelBeacon> levelBeacons;
    private LevelBeacon currentLevel;
    /**
     * Constructor for LevelGraph class
     */
    public LevelGraph(){
        levelBeacons = new Array<LevelBeacon>();
    }

    /**
     * function to initialize the LevelGraph, which adds the levels
     */
    public void initializeGraph(){
        LevelBeacon levelBeacon1 = new LevelBeacon();
        LevelBeacon levelBeacon2 = new LevelBeacon();
        levelBeacon1.initialize(new Vector2(300, 300), 200, 300, 1);
        levelBeacon2.initialize(new Vector2(700, 700), 300, 200, 2);
        addBeacon(levelBeacon1);
        addBeacon(levelBeacon2);
        setCurrentLevel(levelBeacon1);
    }
    /**
     * function to add level beacon to the already existing list
     * @param beacon
     */
    public void addBeacon(LevelBeacon beacon){
        levelBeacons.add(beacon);
    }

    /**
     * function to set the current level
     * @param beacon
     */
    public void setCurrentLevel(LevelBeacon beacon){
        currentLevel = beacon;
    }
    /**
     * Render every beacon
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        for(LevelBeacon beacon : levelBeacons){
            beacon.render(shapeRenderer);
        }
    }

    /**
     * getter for current level
     * @return currentLevel
     */
    public LevelBeacon getCurrentLevel(){
        return currentLevel;
    }

    /**
     * getter for array of level beacons
     * @return levelBeacons
     */
    public Array<LevelBeacon> getLevelBeaconArray(){
        return levelBeacons;
    }
}
