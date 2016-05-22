package com.mygdx.game.managers.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Created by denis on 5/22/16.
 */
public class CameraManager {

    private OrthographicCamera cam;

    private Vector2 screenDim;
    private Vector2 translation;

    private Vector2 deltaTranslation;

    private final float maxSpeed = 100.0f;

    private SpaceShip player;

    public CameraManager(){
        screenDim = new Vector2();
        translation = new Vector2(0,0);
        deltaTranslation = new Vector2();
    }

    public void initializeCamera(SpaceShip player){
        this.player = player;
        this.screenDim.set(screenDim);

        cam.translate(player.getPosition().x,player.getPosition().y);
        translation.set(player.getPosition().x,player.getPosition().y);
    }

    public void setCam(OrthographicCamera cam){
        this.cam = cam;
    }
    public void setScreenDim(Vector2 screenDim){
        this.screenDim.set(screenDim);
    }

    public OrthographicCamera getCam(){
        return cam;
    }

    public void update(float delta){
        cam.update();

    }
}
