package com.mygdx.game.managers.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Created by denis on 5/22/16.
 */
public class CameraManager {

    private OrthographicCamera cam;

    private Vector2 screenDim;
    private Vector3 screenCenter;
    private Vector3 translation;

    private Vector3 deltaTranslationSpeed;

    private final float maxSpeed = 100.0f;

    private SpaceShip player;


    private Vector2 trans;

    public CameraManager(){
        screenDim = new Vector2();
        translation = new Vector3(0,0,0);
        deltaTranslationSpeed = new Vector3();
        screenCenter = new Vector3();


        trans = new Vector2();
    }

    public void initializeCamera(SpaceShip player, Vector2 screenDim){
        this.player = player;
        this.screenDim.set(screenDim);
        screenCenter.set(screenDim.x/2,screenDim.y/2,0);
        cam.unproject(screenCenter);
        cam.translate(player.getPosition().x-screenCenter.x/2,player.getPosition().y-screenCenter.y/2);
        translation.set(player.getPosition().x-screenCenter.x/2,player.getPosition().y-screenCenter.y/2,0);
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

        //cam.unproject(screenCenter);
        if(!player.isInOrbit()) {
            trans = player.getPosition().cpy().add(translation.x - screenCenter.x, translation.y - screenCenter.y).scl(1.0f);
            cam.translate(trans);
            translation.sub(trans.x, trans.y, 0);
        }
        cam.update();

    }
}
