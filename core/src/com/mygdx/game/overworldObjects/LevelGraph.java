package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.levels.Level;

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
        LevelBeacon levelBeacon3 = new LevelBeacon();
        LevelBeacon levelBeacon4 = new LevelBeacon();
        LevelBeacon levelBeacon5 = new LevelBeacon();

        //create arrays for the connected beacons and add the corresponding beacons -> the arrays are passed to the initialize method of LevelBeacon
        Array<LevelBeacon> array1 = new Array<LevelBeacon>();
        array1.add(levelBeacon2);
        Array<LevelBeacon> array2 = new Array<LevelBeacon>();
        array2.addAll(levelBeacon1, levelBeacon3, levelBeacon4);
        Array<LevelBeacon> array3 = new Array<LevelBeacon>();
        array3.addAll(levelBeacon2, levelBeacon5);
        Array<LevelBeacon> array4 = new Array<LevelBeacon>();
        array4.addAll(levelBeacon2, levelBeacon5);
        Array<LevelBeacon> array5 = new Array<LevelBeacon>();
        array5.addAll(levelBeacon3, levelBeacon4);
        //initialize beacons: position, size, id, array of connected beacons
        levelBeacon1.initialize(new Vector2(300, 300), 200, 200, 1, array1, false);
        levelBeacon2.initialize(new Vector2(700, 700), 200, 200, 2, array2, false);
        levelBeacon3.initialize(new Vector2(300, 1000), 200, 200, 3, array3, false);
        levelBeacon4.initialize(new Vector2(800, 1200), 200, 200, 4, array4, false);
        levelBeacon5.initialize(new Vector2(500, 1600), 200, 200, 5, array5, true);
        //add beacons to array of this LevelGraph object
        addBeacon(levelBeacon1);
        addBeacon(levelBeacon2);
        addBeacon(levelBeacon3);
        addBeacon(levelBeacon4);
        addBeacon(levelBeacon5);
        setCurrentLevel(levelBeacon1);

        InputManager.instance.objectHolder.Register(levelBeacon1);
        InputManager.instance.objectHolder.Register(levelBeacon2);
        InputManager.instance.objectHolder.Register(levelBeacon3);
        InputManager.instance.objectHolder.Register(levelBeacon4);
        InputManager.instance.objectHolder.Register(levelBeacon5);
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