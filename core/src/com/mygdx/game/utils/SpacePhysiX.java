package com.mygdx.game.utils;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 5/17/16.
 */
public class SpacePhysiX {

    private Array<Unit> units;

    public SpacePhysiX(Array<Unit> units){
        this.units = units;
    }

    public void update(float delta) {
        //update all target locations of units, what they want to do
        for(Unit u : units){
            u.update(delta);
        }
        //resolve all collisions
        for(Unit u : units){
            /*
            if(u.getUnitID() != playerID){
                //player crashes into planet
                if(u.getTargetHitbox().overlaps(player.getTargetHitbox())){
                    player.collide();
                }
            }
            */
        }
        //check if playership docs to some orbit
        /*
        if(!player.isInOrbit() && !player.isCollided())
            for(MoveableUnit u : units) {
                if (u.getUnitID() != playerID) {

                    //if player is in range, check if he should dock
                    if(player.getPosition().cpy().sub(u.getPosition()).len() <= ((Planet)u).getOrbitRadius()){ // u = moveableobject
                        Vector2 v = u.getPosition().cpy();
                        Vector2 vecToShip = player.getPosition().cpy();
                        vecToShip.sub(v);
                        System.out.println(vecToShip.dot(player.getDeltaMovement())+"   "+vecToShip.dot(player.getDeltaMovement()));
                        if(vecToShip.dot(player.getDeltaMovement()) <= 30 &&  vecToShip.dot(player.getDeltaMovement()) >= -30){
                            player.enterOrbit(u.getPlanetId(),vecToShip.len(), v);

                        }
                    }

                }
            }
        */
        //move non collided units
        /*
        for(Unit u : units){
            if(!u.isCollided()) {
                u.moveUnit();
            }else {
                u.reset();
            }
        }
        */
    }
}
