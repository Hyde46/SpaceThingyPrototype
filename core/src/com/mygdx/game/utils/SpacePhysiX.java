package com.mygdx.game.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 5/17/16.
 */
public class SpacePhysiX {

    private Array<Unit> units;
    private SpaceShip playerShip;

    public SpacePhysiX(Array<Unit> units){
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
                if(((Circle)u.getHitbox()).overlaps(playerShip.getTargetHitbox())){
                    playerShip.collide();
                }
            }
        }
        //check if playership docs to some orbit
        if(!playerShip.isInOrbit() && !playerShip.isCollided())
            for(Unit u : units) {
                if (u.getUnitType() != 0) {

                    //if player is in range, check if he should dock
                    if(playerShip.getPosition().cpy().sub(u.getPosition()).len() <= ((Planet)u).getOrbitRadius()){ // u = moveableobject
                        Vector2 v = u.getPosition().cpy();
                        Vector2 vecToShip = playerShip.getPosition().cpy();
                        vecToShip.sub(v);
                        System.out.println(vecToShip.dot(playerShip.getDeltaMovement())+"   "+vecToShip.dot(playerShip.getDeltaMovement()));
                        if(vecToShip.dot(playerShip.getDeltaMovement()) <= 30 &&  vecToShip.dot(playerShip.getDeltaMovement()) >= -30){
                            playerShip.enterOrbit((Planet)u, vecToShip.len());

                        }
                    }

                }
            }

        //move non collided units

        for(Unit u : units) {
            u.moveUnit();
        }
    }
}
