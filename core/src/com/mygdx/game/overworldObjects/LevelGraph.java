package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;

/**
 * Created by Vali on 18.05.2016.
 */
public class LevelGraph {
    //array that holds all the levels
    private Array<LevelBeacon> levelBeacons;
    private LevelBeacon currentLevel;
    private Array<LevelInfo> levelInfos;
    /**
     * Constructor for LevelGraph class
     */
    public LevelGraph(){
        levelBeacons = new Array<LevelBeacon>();
        levelInfos = new Array<LevelInfo>();
    }

    /**
     * function to initialize the LevelGraph, which adds the levels
     */
    public void initializeGraph(int level){
        LevelBeacon levelBeacon1 = new LevelBeacon();
        LevelBeacon levelBeacon2 = new LevelBeacon();
        LevelBeacon levelBeacon3 = new LevelBeacon();
        LevelBeacon levelBeacon4 = new LevelBeacon();
        LevelBeacon levelBeacon5 = new LevelBeacon();
        LevelBeacon levelBeacon6 = new LevelBeacon();
        LevelBeacon levelBeacon7 = new LevelBeacon();
        LevelBeacon levelBeacon8 = new LevelBeacon();
        LevelBeacon levelBeacon9 = new LevelBeacon();

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
        array5.addAll(levelBeacon3, levelBeacon4, levelBeacon6);
        Array<LevelBeacon> array6 = new Array<LevelBeacon>();
        array6.addAll(levelBeacon5, levelBeacon7);
        Array<LevelBeacon> array7 = new Array<LevelBeacon>();
        array7.addAll(levelBeacon6, levelBeacon8);
        Array<LevelBeacon> array8 = new Array<LevelBeacon>();
        array8.addAll(levelBeacon7, levelBeacon9);
        Array<LevelBeacon> array9 = new Array<LevelBeacon>();
        array9.add(levelBeacon8);
        //initialize beacons: position, size, id, array of connected beacons, type of beacon (1=level, 2=shop, 3=hangar), is already activated
        levelBeacon1.initialize(new Vector2(300, 300), 300, 300, 1, array1, 1, true);
        levelBeacon2.initialize(new Vector2(700, 700), 300, 300, 2, array2, 1, true);
        levelBeacon3.initialize(new Vector2(300, 1000), 300, 300, 3, array3, 2, true);
        levelBeacon4.initialize(new Vector2(800, 1200), 300, 300, 4, array4, 3, true);
        levelBeacon5.initialize(new Vector2(500, 1600), 300, 300, 5, array5, 1, false);
        levelBeacon6.initialize(new Vector2(500, 2000), 300, 300, 6, array6, 1, false);
        levelBeacon7.initialize(new Vector2(100, 2000), 300, 300, 7, array7, 1, false);
        levelBeacon8.initialize(new Vector2(-300, 1900), 300, 300, 8, array8, 1, false);
        levelBeacon9.initialize(new Vector2(-300, 2300), 300, 300, 9, array9, 1, false);

        //add beacons to array of this LevelGraph object
        addBeacon(levelBeacon1);
        addBeacon(levelBeacon2);
        addBeacon(levelBeacon3);
        addBeacon(levelBeacon4);
        addBeacon(levelBeacon5);
        addBeacon(levelBeacon6);
        addBeacon(levelBeacon7);
        addBeacon(levelBeacon8);
        addBeacon(levelBeacon9);

        LevelInfo levelInfo1 = new LevelInfo();
        LevelInfo levelInfo2 = new LevelInfo();
        LevelInfo levelInfo3 = new LevelInfo();
        LevelInfo levelInfo4 = new LevelInfo();
        LevelInfo levelInfo5 = new LevelInfo();
        LevelInfo levelInfo6 = new LevelInfo();
        LevelInfo levelInfo7 = new LevelInfo();
        LevelInfo levelInfo8 = new LevelInfo();
        LevelInfo levelInfo9 = new LevelInfo();

        levelInfo1.initialize(new Vector2(300, 620), 300, 100, "level_info.png", 1);
        levelInfo2.initialize(new Vector2(700, 1020), 300, 100, "level_info.png", 2);
        levelInfo3.initialize(new Vector2(300, 1320), 300, 100, "level_info.png", 3);
        levelInfo4.initialize(new Vector2(800, 1520), 300, 100, "level_info.png", 4);
        levelInfo5.initialize(new Vector2(500, 1920), 300, 100, "level_info.png", 5);
        levelInfo6.initialize(new Vector2(500, 2320), 300, 100, "level_info.png", 6);
        levelInfo7.initialize(new Vector2(100, 2320), 300, 100, "level_info.png", 7);
        levelInfo8.initialize(new Vector2(-300, 2220), 300, 100, "level_info.png", 8);
        levelInfo9.initialize(new Vector2(-300, 2620), 300, 100, "level_info.png", 9);

        //add infos to array
        levelInfos.addAll(levelInfo1, levelInfo2, levelInfo3, levelInfo4, levelInfo5, levelInfo6, levelInfo7, levelInfo8, levelInfo9);


        //if game was just started (finished level = 0) set the current level to 1
        if(level == 0){
            setCurrentLevel(levelBeacon1);
        }else{  //otherwise set the current level as the one that was just finished
            setCurrentLevel(levelBeacons.get(level - 1));
        }

        InputManager.get.register(levelBeacon1);
        InputManager.get.register(levelBeacon2);
        InputManager.get.register(levelBeacon3);
        InputManager.get.register(levelBeacon4);
        InputManager.get.register(levelBeacon5);
        InputManager.get.register(levelBeacon6);
        InputManager.get.register(levelBeacon7);
        InputManager.get.register(levelBeacon8);
        InputManager.get.register(levelBeacon9);
        InputManager.get.register(levelInfo1);
        InputManager.get.register(levelInfo2);
        InputManager.get.register(levelInfo3);
        InputManager.get.register(levelInfo4);
        InputManager.get.register(levelInfo5);
        InputManager.get.register(levelInfo6);
        InputManager.get.register(levelInfo7);
        InputManager.get.register(levelInfo8);
        InputManager.get.register(levelInfo9);


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
        this.currentLevel = beacon;
    }

    /**
     * Render every beacon
     * @param batch
     */
    public void renderBeacons(SpriteBatch batch){
        //System.out.println("Level graph current level: " + currentLevel.getLevelId());
        for(LevelBeacon beacon : levelBeacons){
            beacon.render(batch);
        }
    }

    /**
     * renders edges for every beacon
     * @param shapeRenderer
     */
    public void renderEdges(ShapeRenderer shapeRenderer){
        for(LevelBeacon beacon : levelBeacons){
            beacon.renderEdges(shapeRenderer);
        }
    }

    /**
     * renders infos above beacons
     * @param batch
     */
    public void renderInfos(SpriteBatch batch){
      /*  for(LevelInfo info : levelInfos){
            info.render(batch);
        } */
        //not sure yet, if we want to render all of the infos or just the one, where the ship is
        levelInfos.get(currentLevel.getLevelId() - 1).render(batch);
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

    /**
     * clean up stuff, called in MainMenuScreen
     */
    public void dispose(){
        for(LevelBeacon beacon : levelBeacons){
            beacon.dispose();
        }
        for(LevelInfo info : levelInfos){
            info.dispose();
        }
    }
}
