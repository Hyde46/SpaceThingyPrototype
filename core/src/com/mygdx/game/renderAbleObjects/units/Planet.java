package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by denis on 5/13/16.
 */
public class Planet extends Unit implements IInputHandler {

    private float orbitRadius;
    private float planetRadius;
    private boolean isGoalPlanet;
    private SpaceShip connectedSpaceShip;
    private Sprite orbitSprite;

    private boolean isMoving;
    private Planet connectedPlanet;
    private float rotationSpeed;
    private int rotationDirection;
    private Vector2 translation;

    private float gravity;

    public Planet() {
        super();
    }

    public void initialize(Vector2 pos, float orbitRadius, float planetRadius,
                           boolean isGoalPlanet, String texturePath, int spriteId, float initialRotation, float gravity) {
        unitType = UnitType.PLANET;
        this.orbitRadius = orbitRadius;
        this.planetRadius = planetRadius;
        this.isGoalPlanet = isGoalPlanet;
        this.collisionHitbox = new Circle(pos.x, pos.y, planetRadius);
        touchHitbox = new Circle(pos.x, pos.y, orbitRadius);
        initializePositions(pos, new Vector2(0, 0));
        initializeTexture(new Vector2(planetRadius * 2, planetRadius * 2), spriteId, texturePath);
        sprite.rotate(initialRotation);
        initializeOrbitTex(isGoalPlanet);
        isMoving = false;
        rotationSpeed = 0.0f;
        rotationDirection = 0;
        translation = new Vector2();
        this.gravity = gravity;
    }

    private void initializeOrbitTex(boolean isGoalPlanet) {

        /* orbit radius kann zwischen vorbestimmten sprite größen sein ... gefährlich */
        Texture t;
        if (isGoalPlanet)
            t = new Texture(Gdx.files.internal("orbit2_goal_" + (int) (orbitRadius * 2) + "x" + (int) (orbitRadius * 2) + ".png"));
        else
            t = new Texture(Gdx.files.internal("orbit2_" + (int) (orbitRadius * 2) + "x" + (int) (orbitRadius * 2) + ".png"));
        orbitSprite = new Sprite(t, (int) orbitRadius * 2, (int) orbitRadius * 2);
        orbitSprite.setCenter(orbitRadius, orbitRadius);
        orbitSprite.setX(position.x - orbitRadius);
        orbitSprite.setY(position.y - orbitRadius);
    }

    @Override
    public void render(SpriteBatch g) {
        if (!isActive || tex == null) {
            return;
        }
        if (orbitSprite != null)
            orbitSprite.draw(g);

        if (sprite != null)
            sprite.draw(g);

        //g.draw(tex,position.x-spriteDimension.x/2,position.y-spriteDimension.y/2,spriteDimension.x,spriteDimension.y);
    }

    @Override
    public void moveUnit() {
        translation = new Vector2(targetPosition.cpy().x - position.cpy().x, targetPosition.cpy().y - position.cpy().y);
        orbitSprite.translate(translation.x, translation.y);
        sprite.translate(translation.x, translation.y);
        ((Circle) collisionHitbox).setPosition(targetPosition);
        ((Circle) touchHitbox).setPosition(targetPosition);
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
        if (isMoving) {
            //Stuff for moving planet


            //Rotation around another planet
            if (connectedPlanet != null) {
                targetPosition = (SpaceMath.rotatePoint(position, connectedPlanet.getPosition(), rotationSpeed * delta, rotationDirection));
            }
        }
    }

    public void connectToPlanet(Planet p) {
        connectedPlanet = p;
        isMoving = true;
    }

    public void setRotationSpeed(float rs, int rd) {
        rotationSpeed = rs;
        rotationDirection = rd;
    }

    public Vector2 getTranslation() {
        return translation;
    }

    public boolean getIsMoving() {
        return isMoving;
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
        if (connectedSpaceShip != null && !GameScreen.hasFinishedLevel) {
            launchSpaceShip();
        }
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
    }

    public void OnHold(TouchData td) {

    }

    public void OnSwipe(TouchData td) {
        //System.out.println("planet " + getUnitID() + " swiped to dir: " + td.getDirSwipePrev().toString());
    }

    public float getGravity(){
        return gravity;
    }


    public void dispose(){
        sprite.getTexture().dispose();
        sprite = null;
        orbitSprite.getTexture().dispose();
        orbitSprite = null;
    }

}