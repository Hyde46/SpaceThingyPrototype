package com.mygdx.game.managers;

import com.mygdx.game.overworldObjects.LevelBeacon;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Ship;

/**
 * Created by Vali on 18.05.2016.
 */
public class PathNavigationManager {

    private Ship ship;
    private LevelGraph levelGraph;

    public PathNavigationManager(Ship ship, LevelGraph levelGraph){
        this.ship = ship;
        this.levelGraph = levelGraph;
    }

    public void navigateToBeacon(LevelBeacon levelBeacon){
        //set the current level to the new one
        levelGraph.setCurrentLevel(levelBeacon);
        ship.setCurrentLevel(levelBeacon);
        ship.setInOrbit(false);
        //call function in Ship class, which computes the vector to the new beacon
        ship.flyToBeacon(levelBeacon);
    }
}
