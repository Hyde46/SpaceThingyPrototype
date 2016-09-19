package com.mygdx.game.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.PickableItem;
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

    private Rectangle worldBound;

    private GameScreen gs;

    //tune this down, if the game starts running slower
    private final static int PHYSIC_TICKS = 30;
    private final static float DOT_PRODUCT_BOUNDARIES = 30.0f;

    public final static float PI = 3.141592653f;

    public SpacePhysiX(){
    }

    public void initializePhysics(Array<Unit> units, GameScreen gs){
        this.gs = gs;
        this.units = units;
        for(Unit u : units){
            if(u.getUnitType() == Unit.UnitType.SPACE_SHIP)
                playerShip = (SpaceShip)u;
        }
    }

    public void initWorldBounds(Rectangle wB){
        worldBound = wB;
    }

    public void update(float delta) {
        //update all target locations of units, what they want to do
        updateUnits(delta);

        resolveItemLogic(delta);
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

    private void resolveItemLogic(float delta){
        //Teleport
        if(playerShip.isTeleportActive()){
            if(playerShip.getTeleportRangeDetector().isTeleporting()){
                playerShip.teleport(playerShip.getTeleportRangeDetector().getPickedCoordinate());
                playerShip.getTeleportRangeDetector().resetIsTelePorting();
            }
        }
        if(playerShip.isRandomTeleportActive()){
            if(playerShip.getRandomTeleportRangeDetector().isTeleporting()){
                playerShip.teleport(playerShip.getRandomTeleportRangeDetector().getPickedCoordinate());
                playerShip.getRandomTeleportRangeDetector().resetIsTelePorting();
            }
        }
        //ItemPicker Target
        if(playerShip.isItemPickerActive()){
            if(playerShip.getItemPickerOrbit().isPickingItem()) {
                Vector2 pickingItemCoordinate = playerShip.getItemPickerOrbit().getPickedCoordinate();
                for(Unit u : units){
                    if(u.getUnitType() == Unit.UnitType.PICKABLE_ITEM){

                        if( ((PickableItem)u).getTouchableHitbox().contains(pickingItemCoordinate)) {
                            ((PickableItem) u).pickUpItem(gs.getLevelState());
                        }
                    }
                    playerShip.getItemPickerOrbit().resetIsPickingItem();
                }
            }
        }
    }

    private void updateUnits(float delta) {
        for(Unit u : units){
            u.update(delta);
        }
    }

    private void resolveCollisions() {
        for(Unit u : units){
            if(u.getUnitType() != Unit.UnitType.SPACE_SHIP && u.getUnitType() != Unit.UnitType.ITEM_PICKER
                    && u.getUnitType() != Unit.UnitType.TELEPORT_PICKER){ //0 = playership

                //ItemPickerRadius
                if(u.isActive() && playerShip.isItemPickerActiveRadius() && u.getUnitType() == Unit.UnitType.PICKABLE_ITEM){
                    if((playerShip.getPickerCollisionHitbox()).overlaps((Circle)u.getCollisionHitbox())){
                        ((PickableItem)u).pickUpItem(gs.getLevelState());
                    }
                }
                //player crashes into stuff
                if(((Circle)u.getCollisionHitbox()).overlaps(playerShip.getTargetHitbox()) && !playerShip.isPhasedOut() && u.isActive()){
                    if(u.getUnitType() == Unit.UnitType.PLANET ||u.getUnitType() == Unit.UnitType.OBSTACLE ) { // if not colliding with an item
                        playerShip.collide();
                    }else if(u.getUnitType() == Unit.UnitType.PICKABLE_ITEM){
                        ((PickableItem)u).pickUpItem(gs.getLevelState());

                    }
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
            if (!playerShip.isInOrbit() && !playerShip.isCollided() && !playerShip.isPhasedOut()) {
                for (Unit u : units) {
                    //resolve planet collision
                    if (u.getUnitType() == Unit.UnitType.PLANET) {
                        //if player is in range, check if he should dock
                        if (playerShip.getPosition().cpy().sub(u.getPosition()).len() <= ((Planet) u).getOrbitRadius()) { // u = moveableobject
                            Vector2 v = u.getPosition().cpy();
                            Vector2 vecToShip = playerShip.getPosition().cpy();
                            if(dotProductToShipTickCollision(PHYSIC_TICKS,vecToShip,v)){
                                if(playerShip.enterOrbit((Planet) u, vecToShip.len()))
                                    gs.getLevelState().hop();
                            }

                        }

                    }
                }
            }
        }
    }

    private boolean dotProductToShipTickCollision(int ticks ,Vector2 vecToShip, Vector2 planetPos){
        Vector2 startPosition = playerShip.getPosition();
        Vector2 targetPosition = playerShip.getTargetPosition();
        Vector2 deltaPosition = startPosition.cpy().sub(targetPosition.cpy());
        for(int i = 0; i <= ticks; i++){
            Vector2 tickpos = startPosition.cpy().add(deltaPosition.cpy().scl((float)i/(float)ticks));
            Vector2 vts = planetPos.cpy().sub(tickpos);
            if (vts.dot(playerShip.getDeltaMovement().cpy().scl(0.1f)) <= DOT_PRODUCT_BOUNDARIES && vts.dot(playerShip.getDeltaMovement().cpy().scl(0.1f)) >= -DOT_PRODUCT_BOUNDARIES) {
                vecToShip.set(vts.cpy());
                return true;
            }
        }

        return false;
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
