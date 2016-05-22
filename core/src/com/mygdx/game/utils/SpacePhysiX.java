package com.mygdx.game.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by denis on 5/17/16.
 */
public class SpacePhysiX {

    private Array<Unit> units;
    private SpaceShip playerShip;

    private GameScreen gs;

    public SpacePhysiX(){
    }

    public void initializePhysics(Array<Unit> units, GameScreen gs){
        this.gs = gs;
        this.units = units;
        for(Unit u : units){
            if(u.getUnitType() == 0)
                playerShip = (SpaceShip)u;
        }
    }

    public void update(float delta) {
        //update all target locations of units, what they want to do
        for(Unit u : units){
            u.update(delta);
        }
        //resolve all collisions
        for(Unit u : units){
            if(u.getUnitType() != 0){ //0 = playership
                //player crashes into planet
                if(((Circle)u.getCollisionHitbox()).overlaps(playerShip.getTargetHitbox())){
                    playerShip.collide();
                }
            }
        }
        //check if playership docs to some orbit
        if(playerShip != null) {
            if (!playerShip.isInOrbit() && !playerShip.isCollided()) {
                for (Unit u : units) {
                    if (u.getUnitType() != 0) {
                        //if player is in range, check if he should dock
                        if (playerShip.getPosition().cpy().sub(u.getPosition()).len() <= ((Planet) u).getOrbitRadius()) { // u = moveableobject
                            Vector2 v = u.getPosition().cpy();
                            Vector2 vecToShip = playerShip.getPosition().cpy();
                            vecToShip.sub(v);
                            if (vecToShip.dot(playerShip.getDeltaMovement().cpy().scl(0.1f)) <= 100 && vecToShip.dot(playerShip.getDeltaMovement().cpy().scl(0.1f)) >= -100) {
                                playerShip.enterOrbit((Planet) u, vecToShip.len());

                            }
                        }

                    }
                }
            }
        }
        for(Unit u : units) {
            u.moveUnit();
        }
        //check if player has reached some goal planet
        if(playerShip.isHasReachedGoal()){
            gs.finishLevel(true);
        }else if(playerShip.isCollided()){
            gs.finishLevel(false);
        }
    }

    public void reset(){
        units = null;
        playerShip = null;
    }
}
