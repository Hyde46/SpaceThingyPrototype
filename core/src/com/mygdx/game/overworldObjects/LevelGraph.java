package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.dataPersistence.DataPers;

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
        LevelBeacon levelBeacon10 = new LevelBeacon();
        LevelBeacon levelBeacon11= new LevelBeacon();
        LevelBeacon levelBeacon12 = new LevelBeacon();
        LevelBeacon levelBeacon13 = new LevelBeacon();

        //create arrays for the connected beacons and add the corresponding beacons -> the arrays are passed to the initialize method of LevelBeacon
        Array<LevelBeacon> array1 = new Array<LevelBeacon>();
        array1.add(levelBeacon2);
        Array<LevelBeacon> array2 = new Array<LevelBeacon>();
        array2.addAll(levelBeacon1, levelBeacon5);
        Array<LevelBeacon> array3 = new Array<LevelBeacon>();
        array3.addAll(levelBeacon5);
        Array<LevelBeacon> array4 = new Array<LevelBeacon>();
        array4.addAll(levelBeacon6);
        Array<LevelBeacon> array5 = new Array<LevelBeacon>();
        array5.addAll(levelBeacon6, levelBeacon7, levelBeacon9 , levelBeacon3, levelBeacon2,levelBeacon11);
        Array<LevelBeacon> array6 = new Array<LevelBeacon>();
        array6.addAll(levelBeacon5, levelBeacon4);
        Array<LevelBeacon> array7 = new Array<LevelBeacon>();
        array7.addAll(levelBeacon5, levelBeacon8);
        Array<LevelBeacon> array8 = new Array<LevelBeacon>();
        array8.addAll(levelBeacon7);
        Array<LevelBeacon> array9 = new Array<LevelBeacon>();
        array9.addAll(levelBeacon5,levelBeacon10);
        Array<LevelBeacon> array10 = new Array<LevelBeacon>();
        array10.add(levelBeacon9);
        Array<LevelBeacon> array11 = new Array<LevelBeacon>();
        array11.addAll(levelBeacon5,levelBeacon12);
        Array<LevelBeacon> array12 = new Array<LevelBeacon>();
        array12.addAll(levelBeacon11,levelBeacon13);
        Array<LevelBeacon> array13 = new Array<LevelBeacon>();
        array13.add(levelBeacon12);
        //initialize beacons: position, size, id, array of connected beacons, type of beacon (1=level, 2=shop, 3=hangar), is already activated
        boolean[] playableLevel = DataPers.dataP().playableLevel;
        levelBeacon1.initialize(new Vector2(300, 300), 300, 300, 1, array1, 1, playableLevel[1]);
        levelBeacon2.initialize(new Vector2(300, 900), 300, 300, 2, array2, 1, playableLevel[2]);
        levelBeacon3.initialize(new Vector2(1100, 1950), 300, 300, 3, array3, 2, playableLevel[3]);
        levelBeacon4.initialize(new Vector2(-450, 1050), 300, 300, 4, array4, 3, playableLevel[4]);
        levelBeacon5.initialize(new Vector2(500, 1500), 300, 300, 5, array5, 1, playableLevel[5]);
        levelBeacon6.initialize(new Vector2(-50, 1350), 300, 300, 6, array6, 1, playableLevel[6]);
        levelBeacon7.initialize(new Vector2(1050, 1400), 300, 300, 7, array7, 1, playableLevel[7]);
        levelBeacon8.initialize(new Vector2(1550, 1275), 300, 300, 8, array8, 1, playableLevel[8]);
        levelBeacon9.initialize(new Vector2(0, 2000), 300, 300, 9, array9, 1, playableLevel[9]);
        levelBeacon10.initialize(new Vector2(-350, 2250), 300, 300, 10, array10, 1, playableLevel[10]);
        levelBeacon11.initialize(new Vector2(600, 1950), 300, 300, 11, array11, 1, playableLevel[11]);
        levelBeacon12.initialize(new Vector2(750, 2300), 300, 300, 12, array12, 1, playableLevel[12]);
        levelBeacon13.initialize(new Vector2(0, 2550), 300, 300, 13, array13, 1, playableLevel[13]);

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
        addBeacon(levelBeacon10);
        addBeacon(levelBeacon11);
        addBeacon(levelBeacon12);
        addBeacon(levelBeacon13);

        LevelInfo levelInfo1 = new LevelInfo();
        LevelInfo levelInfo2 = new LevelInfo();
        LevelInfo levelInfo3 = new LevelInfo();
        LevelInfo levelInfo4 = new LevelInfo();
        LevelInfo levelInfo5 = new LevelInfo();
        LevelInfo levelInfo6 = new LevelInfo();
        LevelInfo levelInfo7 = new LevelInfo();
        LevelInfo levelInfo8 = new LevelInfo();
        LevelInfo levelInfo9 = new LevelInfo();
        LevelInfo levelInfo10 = new LevelInfo();
        LevelInfo levelInfo11 = new LevelInfo();
        LevelInfo levelInfo12 = new LevelInfo();
        LevelInfo levelInfo13 = new LevelInfo();

        levelInfo1.initialize(levelBeacon1.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 1);
        levelInfo2.initialize(levelBeacon2.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 2);
        levelInfo3.initialize(levelBeacon3.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 3);
        levelInfo4.initialize(levelBeacon4.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 4);
        levelInfo5.initialize(levelBeacon5.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 5);
        levelInfo6.initialize(levelBeacon6.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 6);
        levelInfo7.initialize(levelBeacon7.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 7);
        levelInfo8.initialize(levelBeacon8.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 8);
        levelInfo9.initialize(levelBeacon9.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 9);
        levelInfo10.initialize(levelBeacon10.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 10);
        levelInfo11.initialize(levelBeacon11.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 11);
        levelInfo12.initialize(levelBeacon12.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 12);
        levelInfo13.initialize(levelBeacon13.getPosition().cpy().add(0,320), 300, 100, "level_info.png", 13);

        //add infos to array
        levelInfos.addAll(levelInfo1, levelInfo2, levelInfo3, levelInfo4, levelInfo5, levelInfo6, levelInfo7, levelInfo8, levelInfo9
        ,levelInfo10,levelInfo11,levelInfo12,levelInfo13);


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
        InputManager.get.register(levelBeacon10);
        InputManager.get.register(levelBeacon11);
        InputManager.get.register(levelBeacon12);
        InputManager.get.register(levelBeacon13);
        InputManager.get.register(levelInfo1);
        InputManager.get.register(levelInfo2);
        InputManager.get.register(levelInfo3);
        InputManager.get.register(levelInfo4);
        InputManager.get.register(levelInfo5);
        InputManager.get.register(levelInfo6);
        InputManager.get.register(levelInfo7);
        InputManager.get.register(levelInfo8);
        InputManager.get.register(levelInfo9);
        InputManager.get.register(levelInfo10);
        InputManager.get.register(levelInfo11);
        InputManager.get.register(levelInfo12);
        InputManager.get.register(levelInfo13);
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

    public void unlockNewBeacons(int finishedLevel){
        Array<LevelBeacon> levelsToUnlock = new Array<LevelBeacon>();
        for(LevelBeacon lB : levelBeacons){
            if(lB.getLevelId() == finishedLevel){
                levelsToUnlock = lB.getConnectedBeacons();
            }
        }
        for(LevelBeacon levelBeacon : levelsToUnlock){
            levelBeacon.activate();
        }
    }
}
