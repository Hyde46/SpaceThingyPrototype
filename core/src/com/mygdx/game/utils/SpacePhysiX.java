package com.mygdx.game.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.screens.GameScreen;

import org.w3c.dom.css.Rect;

/**
 * Created by denis on 5/17/16.
 */
public class SpacePhysiX {

    private Array<Unit> units;
    private SpaceShip playerShip;

    private Rectangle worldBound;

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

    public void initWorldBounds(Rectangle wB){
        worldBound = wB;
    }

    public void update(float delta) {
        //update all target locations of units, what they want to do
        updateUnits(delta);
        //resolve all collisions
        resolveCollisions();
        //check if player in worldbounds.
        checkLevelboundaryCondition();
        //check if playership docs to some orbit
        dockPlayerToOrbit();
        //Move Units after resolving alle collisions
        moveAllUnits();
        //check if player has reached some goal planet
        resolveFinishLogic();
    }

    private void updateUnits(float delta) {
        for(Unit u : units){
            u.update(delta);
        }
    }

    private void resolveCollisions() {
        for(Unit u : units){
            if(u.getUnitType() != 0){ //0 = playership
                //player crashes into planet
                if(((Circle)u.getCollisionHitbox()).overlaps(playerShip.getTargetHitbox())){
                    playerShip.collide();
                }
            }
        }
    }

    private void checkLevelboundaryCondition() {
        if(! worldBound.contains(playerShip.getPosition())){
            playerShip.loseShip();
        }
    }

    private void dockPlayerToOrbit() {
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
    }

    private void resolveFinishLogic() {
        if(playerShip.hasReachedGoal()){
            gs.finishLevel(true,false);
        }else if(playerShip.isCollided()){
            gs.finishLevel(false,false);
        }else if(playerShip.isLost()){
            gs.finishLevel(false,true);
        }
    }

    private void moveAllUnits() {
        for(Unit u : units) {
            u.moveUnit();
        }
    }

    public void reset(){
        units = null;
        playerShip = null;
    }
}
