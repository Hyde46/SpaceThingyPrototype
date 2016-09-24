package com.mygdx.game.managers;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.overworldObjects.LevelBeacon;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Ship;

/**
 * Created by Vali on 18.05.2016.
 */
public class PathNavigationManager {

    private Ship ship;
    private LevelGraph levelGraph;
    private Array<LevelBeacon> path;

    public PathNavigationManager(){

        this.path = new Array<LevelBeacon>();
    }

    public void initialize(Ship ship, LevelGraph levelGraph){
        this.ship = ship;
        this.levelGraph = levelGraph;
    }

    /**
     * function that sets the new current level and calls a method to compute the route
     * @param levelBeacon is the level that was tabbed
     */
    public void navigateToBeacon(LevelBeacon levelBeacon){
        //the compute route function returns an Array with the shortest path to the beacon
        path = computeRoute(levelBeacon);
        if(path != null){
            //set the current level to the new one
            System.out.println("Touched beacon: " + levelBeacon.getIdLevel());
            levelGraph.setCurrentLevel(levelBeacon);
            ship.setCurrentLevel(levelBeacon);
            ship.setInOrbit(false);
            ship.setTravelsRoute(true);
            ship.setCurrentRoute(path);
            //call function in Ship class to start the route; this function in turn calls flyToBeacon(), which computes the vector
            System.out.println("Ship should start route now");
            ship.startRoute();
        }

    }

    /**
     * Computes the route to the new level by searching the graph
     * @param goalBeacon
     */
    public Array<LevelBeacon> computeRoute(LevelBeacon goalBeacon){
        LevelBeacon currentLevel = levelGraph.getCurrentLevel();
        LevelBeacon node;
        int numberBeacons = levelGraph.getLevelBeaconArray().size;
        Array<Boolean> visited = new Array<Boolean>(numberBeacons);
        Array<LevelBeacon> queue = new Array<LevelBeacon>();
        //stores the previous node for every visited beacon (to backtrack later to find the path)
        Array<LevelBeacon> previousNode = new Array<LevelBeacon>();
        //initialize visited array with entries false and previous array with null
        for(int i = 0; i < numberBeacons; i++){
            visited.add(false);
            previousNode.add(null);
        }
        //add the first node to the queue (which is the current level)
        queue.add(currentLevel);
        //and mark it as visited
        visited.set(currentLevel.getIdLevel(), true);
        //search quickest route through breadth first search
        while (queue.size != 0){
            node = queue.pop();
            //if the popped node is the goal level return
            if(node.getIdLevel() == goalBeacon.getIdLevel()){
                return backtrack(previousNode, currentLevel, goalBeacon);
            }
            //add the connected beacons (if they are not yet visited) to the queue
            for(LevelBeacon connectedNode : node.getConnectedBeacons()){
                //only process node if it has not been visited by algorithm and beacon is already activated!
                if(!visited.get(connectedNode.getIdLevel()) && connectedNode.getActivated()){
                    queue.add(connectedNode);
                    visited.set(connectedNode.getIdLevel(), true);
                    //set the previous node for this one
                    previousNode.set(connectedNode.getIdLevel(), node);
                }
            }
        }
        return null;
    }

    /**
     * helper function to backtrack the bfs to find the shortest path
     * @param previousNode
     * @param startBeacon
     * @param goalBeacon
     * @return
     */
    public Array<LevelBeacon> backtrack(Array<LevelBeacon> previousNode, LevelBeacon startBeacon, LevelBeacon goalBeacon){
        Array<LevelBeacon> path = new Array<LevelBeacon>();
        LevelBeacon currentBeacon = goalBeacon;
        //backtrack through the previousNode array and add the beacons to the path until the starting beacon is found
        while(currentBeacon.getIdLevel() != startBeacon.getIdLevel()){
            path.add(currentBeacon);
            //set the new current beacon as the previous one (parent node)
            currentBeacon = previousNode.get(currentBeacon.getIdLevel());
        }
        return path;
    }
}
