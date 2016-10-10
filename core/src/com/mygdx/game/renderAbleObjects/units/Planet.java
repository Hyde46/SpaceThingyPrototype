package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

import static com.mygdx.game.renderAbleObjects.units.Planet.TypeOrbit.B240;

/**
 * Created by denis on 5/13/16.
 */
public class Planet extends Unit implements IInputHandler
{
    public enum TypePlanet { SP0,SP1,SP2, MP0,MP1,MP2, P0, P1, P2, P3, P4, P5, P6, P7, P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18, M0, M1, M2 ,M3,M4}
    public enum TypeOrbit { B240, B320, B480, G190, G240, G320, M120, M190}

    private TypePlanet typePlanet;
    private TypeOrbit typeOrbit;

    private float diameterOrbit;
    private float planetRadius;

    private SpaceShip connectedSpaceShip;
    private Sprite orbitSprite;

    private boolean isMoving;
    private Planet connectedPlanet;
    private float rotationSpeed;
    private int rotationDirection;
    private Vector2 translation;

    private boolean isDecisionPlanet;
    private int decisionPlanetID;

    private float gravity;

    private boolean isDragged;

    Sound ejectSound;
    Sound goalSound;

    public Planet() {
        super();
    }

    public void initialize
    (
        Vector2 pos, TypePlanet typePlanet, TypeOrbit typeOrbit,
        float initialRotation, float gravity
    )
    {
        this.typePlanet = typePlanet;
        this.typeOrbit = typeOrbit;

        this.planetRadius = typePlanetToRadius(typePlanet);
        this.diameterOrbit = typeOrbitToDiameter(typeOrbit);

        unitType = UnitType.PLANET;
        this.typeOrbit = typeOrbit;
        this.collisionHitbox = new Circle(pos.x, pos.y, planetRadius);
        touchHitbox = new Circle(pos.x, pos.y, diameterOrbit);
        initializePositions(pos, new Vector2(0, 0));
        initializeTexture(new Vector2(planetRadius * 2, planetRadius * 2), 0, typePlanetToPath(typePlanet));
        sprite.rotate(initialRotation);
        initializeOrbitTex(typeOrbit);
        isMoving = false;
        rotationSpeed = 0.0f;
        rotationDirection = 0;
        translation = new Vector2();
        this.gravity = gravity;
        isDecisionPlanet = false;
        decisionPlanetID = -1;
        if (typeOrbit == TypeOrbit.G190 || typeOrbit == TypeOrbit.G240 || typeOrbit == TypeOrbit.G320)
            this.gravity = 1;

        isDragged = false;
        //ejectSound = Gdx.audio.newSound(Gdx.files.internal("mysound.mp3"));
        goalSound = Gdx.audio.newSound(Gdx.files.internal("finish.mp3"));
    }

    private void initializeOrbitTex(TypeOrbit typeOrbit)
    {
        Texture t = new Texture(typeOrbitToPath(typeOrbit));
        orbitSprite = new Sprite(t, (int) diameterOrbit * 2, (int) diameterOrbit * 2);
        orbitSprite.setCenter(diameterOrbit, diameterOrbit);
        orbitSprite.setX(position.x - diameterOrbit);
        orbitSprite.setY(position.y - diameterOrbit);
    }

    @Override
    public void render(SpriteBatch g)
    {
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
        d.circle(this.position.x, this.position.y, diameterOrbit);
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

    public void setRotationSpeed(float rs, int rd)
    {
        rotationSpeed = rs;
        rotationDirection = rd;
    }

    public Vector2 getTranslation() {
        return translation;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public float getDiameterOrbit() {
        return diameterOrbit;
    }

    public float getPlanetRadius() {
        return planetRadius;
    }

    public void connectSpaceShip(SpaceShip ss)
    {
        this.connectedSpaceShip = ss;
        if (typeOrbit == TypeOrbit.G190 || typeOrbit == TypeOrbit.G240 || typeOrbit == TypeOrbit.G320) {
            connectedSpaceShip.reachGoal();
            goalSound.play();
        }
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
        isDragged = true;

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

    public void setDecisionPlanet(int id){
        this.isDecisionPlanet = true;
        this.decisionPlanetID = id;
    }

    public boolean isDecisionPlanet(){
        return isDecisionPlanet;
    }
    public int getDecisionPlanetID(){
        return decisionPlanetID;
    }

    // helper

    private String typePlanetToPath(TypePlanet typePlanet)
    {
        switch(typePlanet)
        {
            case SP0: return "planet_72x72_0.png";
            case SP1: return "planet_72x72_1.png";
            case SP2: return "planet_72x72_2.png";
            case MP0: return "planet_90x90_0.png";
            case MP1: return "planet_90x90_1.png";
            case MP2: return "planet_90x90_2.png";
            case P0: return "planet_100x100_0.png";
            case P1: return "planet_100x100_1.png";
            case P2: return "planet_100x100_2.png";
            case P3: return "planet_100x100_3.png";
            case P4: return "planet_100x100_4.png";
            case P5: return "planet_100x100_5.png";
            case P6: return "planet_100x100_6.png";
            case P7: return "planet_100x100_7.png";
            case P8: return "planet_100x100_8.png";
            case P9: return "planet_110x110_0.png";
            case P10: return "planet_110x110_1.png";
            case P11: return "planet_110x110_2.png";
            case P12: return "planet_120x120_0.png";
            case P13: return "planet_120x120_1.png";
            case P14: return "planet_120x120_2.png";
            case P15: return "planet_130x130_0.png";
            case P16: return "planet_130x130_1.png";
            case P17: return "planet_130x130_2.png";
            case P18: return "planet_130x130_3.png";
            case M0: return "moon_36x36_0.png";
            case M1: return "moon_36x36_1.png";
            case M2: return "moon_36x36_2.png";
            case M3: return "moon4_40x40.png";
            case M4: return "moon5_40x40.png";
            default: return "";
        }
    }

    private float typePlanetToRadius(TypePlanet typePlanet)
    {
        if(typePlanet.ordinal() >= 0 && typePlanet.ordinal() <= 2)
            return 36f;
        else if(typePlanet.ordinal() >= 3 && typePlanet.ordinal() <= 5)
            return 45f;
        else if(typePlanet.ordinal() >= 6 && typePlanet.ordinal() <= 14)
            return 50f;
        else if(typePlanet.ordinal() >= 15 && typePlanet.ordinal() <= 17)
            return 55f;
        else if(typePlanet.ordinal() >= 18 && typePlanet.ordinal() <= 20)
            return 60f;
        else if(typePlanet.ordinal() >= 21 && typePlanet.ordinal() <= 24)
            return 65f;
        else if(typePlanet.ordinal() >= 25 && typePlanet.ordinal() <= 27)
            return 18f;
        else if(typePlanet.ordinal() >= 28 && typePlanet.ordinal() <= 29)
            return 20f;

            return 0f;
    }

    private String typeOrbitToPath(TypeOrbit typeOrbit)
    {
        switch(typeOrbit)
        {
            case B240: return "orbit_base_480x480.png";
            case B320: return "orbit_base_640x640.png";
            case B480: return "orbit_base_960x960.png";
            case G190: return "orbit_goal_380x380.png";
            case G240: return "orbit_goal_480x480.png";
            case G320: return "orbit_goal_640x640.png";
            case M120: return "orbit_moon_240x240.png";
            case M190: return "orbit_moon_380x380.png";
            default: return "";
        }

//        int radius = Integer.parseInt(typeOrbit.toString().substring(1));
//        String type = (typeOrbit.ordinal() >= 0 || typeOrbit.ordinal() <= 2)? "base" :
//            (typeOrbit.ordinal() >= 4 || typeOrbit.ordinal() <= 5)? "goal" : "moon";
//        return "orbit_" + type + "_" + radius*2 + "x" + radius * 2 + ".png";
    }

    private float typeOrbitToDiameter(TypeOrbit typeOrbit)
    {
        return Integer.parseInt(typeOrbit.toString().substring(1));
    }

    public void setGravityToZero(){
        this.gravity = 1f;
    }
}