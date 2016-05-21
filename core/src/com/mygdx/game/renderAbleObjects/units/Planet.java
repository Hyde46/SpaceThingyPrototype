package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;

/**
 * Created by denis on 5/13/16.
 */
public class Planet extends Unit implements IInputHandler {

    private float orbitRadius;
    private float planetRadius;
    private boolean isGoalPlanet;
    private SpaceShip connectedSpaceShip;

    public Planet() {
        super();
    }

    public void initialize(Vector2 pos, float orbitRadius, float planetRadius, boolean isGoalPlanet, String texturePath, int spriteId) {
        unitType = 1;
        this.orbitRadius = orbitRadius;
        this.planetRadius = planetRadius;
        this.isGoalPlanet = isGoalPlanet;
        this.collisionHitbox = new Circle(pos.x, pos.y, planetRadius);
        touchHitbox = new Circle(pos.x, pos.y, orbitRadius);
        initializePositions(pos, new Vector2(0, 0));
        initializeTexture(new Vector2(planetRadius * 2, planetRadius * 2), spriteId, texturePath);
    }

    @Override
    public void moveUnit() {
        this.position.set(this.targetPosition);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {
        if (!isDebug) {
            return;
        }
        d.circle(this.position.x, this.position.y, planetRadius);
        d.circle(this.position.x, this.position.y, orbitRadius);
    }

    @Override
    public void update(float delta) {

    }

    public float getOrbitRadius() {
        return orbitRadius;
    }

    public float getPlanetRadius() {
        return planetRadius;
    }

    public void connectSpaceShip(SpaceShip ss) {
        this.connectedSpaceShip = ss;
        if (isGoalPlanet)
            connectedSpaceShip.reachGoal();
    }

    private void launchSpaceShip() {
        connectedSpaceShip.launch();
        connectedSpaceShip = null;
    }

    public void OnTouch(TouchData td) {
        System.out.println("planet " + getUnitID() + " touched at (" + td.getPosCurrent().x + ", " + td.getPosCurrent().x + ")");
        if(connectedSpaceShip != null){
            connectedSpaceShip.launch();
            connectedSpaceShip = null;
        }
    }

    public void OnRelease(TouchData td) {
        System.out.println("planet " + getUnitID() + " released at (" + td.getPosCurrent().x + ", " + td.getPosCurrent().x + ")");
    }

    public void OnDrag(TouchData td) {
        System.out.println("planet " + getUnitID() + " dragged at (" + td.getPosCurrent().x + ", " + td.getPosCurrent().x + ")");
    }

    public void OnHold(TouchData td) {

    }

    public void OnSwipe(TouchData td) {
        System.out.println("planet " + getUnitID() + " swiped to dir: " + td.getDirSwipePrev().toString());
    }
}