package com.mygdx.game.managers.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;

/**
 * Created by denis on 5/22/16.
 */
public class CameraManager {

    private OrthographicCamera cam;

    private Vector2 screenDim;
    private Vector3 screenCenter;
    private Vector3 translation;

    private float translationDamp;

    private final float maxSpeed = 100.0f;

    private SpaceShip player;

    private ParallaxBackgroundManager pBM;


    private Vector2 trans;

    public CameraManager(){
        screenDim = new Vector2();
        translation = new Vector3(0,0,0);
        screenCenter = new Vector3();
        trans = new Vector2();
    }

    public void initializeCamera(SpaceShip player){
        this.player = player;
        //this.screenDim.set(new Vector2(cam.viewportWidth,cam.viewportHeight));
        this.screenDim.set(new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        screenCenter.set(screenDim.x/2,screenDim.y/2,0);
        cam.unproject(screenCenter);
        cam.translate(player.getPosition().x-screenCenter.x/2,player.getPosition().y-screenCenter.y/2);
        translation.set(player.getPosition().x-screenCenter.x/2,player.getPosition().y-screenCenter.y/2,0);

        translationDamp = 50.0f;
    }

    public void addTranslation(Vector2 translate){
        translation.sub(translate.x,translate.y,0);
        cam.translate(translate);
        cam.update();
        pBM.noticeTranslation(translate.scl(-1.0f));
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

    public SpaceShip getPlayer() { return player; }

    public void update(float delta){
        //player != null, so the camera manager can be used in overworld
        if(player != null && !player.isInOrbit()) {
            trans = player.getPosition().cpy().add(translation.x - screenCenter.x, translation.y - screenCenter.y).scl(1.0f);
            if(trans.cpy().sub(translation.x,translation.y).len() >= 10){
                trans.scl(1.0f/ translationDamp);
            }
            cam.translate(trans);
            translation.sub(trans.x, trans.y, 0);

            pBM.noticeTranslation(trans.scl(-1.0f));
        }
        cam.update();

    }

    public void addPBM(ParallaxBackgroundManager pbM){
        this.pBM = pbM;
    }
}
