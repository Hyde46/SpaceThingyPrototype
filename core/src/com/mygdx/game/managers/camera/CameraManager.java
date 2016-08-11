package com.mygdx.game.managers.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MainMenuScreen;

/**
 * Created by denis on 5/22/16.
 */
public class CameraManager {

    private OrthographicCamera cam;

    private Vector2 screenDim;
    private Vector3 screenCenter;
    private Vector3 translation;

    private Vector3 offsetFocusplayer;

    private float translationDamp;

    private final float maxSpeed = 100.0f;

    private SpaceShip player;

    private ParallaxBackgroundManager pBM;


    private Vector2 trans;


    public CameraManager(){
        screenDim = new Vector2();
        translation = new Vector3(0,0,0);
        screenCenter = new Vector3();
        offsetFocusplayer = new Vector3(0,0,0);
        trans = new Vector2();
    }

    public void initializeCamera(SpaceShip player){
        this.player = player;
        this.screenDim.set(new Vector2(MainMenuScreen.camFixed.viewportWidth,MainMenuScreen.camFixed.viewportHeight));
        screenCenter.set(screenDim.x/2,screenDim.y/2,0);
        cam.translate(player.getPosition().cpy().x - screenCenter.x , player.getPosition().cpy().y - screenCenter.y );
        translation.set(player.getPosition().cpy().x - screenCenter.x , player.getPosition().cpy().y - screenCenter.y, 0 );
        cam.update();
        translationDamp = 25.0f;
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

        // only follow player if he is in orbit
        if(player != null && !player.isInOrbit()) {

            offsetFocusplayer = getPointToFocusCamera(); // get the point, where the camera should focus on

            Vector3 playerWorldSpace = new Vector3(player.getPosition().cpy().x,player.getPosition().cpy().y,0);

            Vector3 trans3D = playerWorldSpace.cpy().sub(cam.position.cpy().add(offsetFocusplayer));
            Vector2 trans2D = new Vector2(trans3D.x,trans3D.y);

            if(trans2D.cpy().sub(translation.x,translation.y).len() >= 5){
                trans2D.scl(1.0f/ translationDamp);
                trans3D.scl(1.0f/ translationDamp);
            }

            cam.translate(trans3D);
            translation.sub(trans3D.x, trans3D.y, 0);
            screenCenter.add(trans3D.x,trans3D.y,0);
            pBM.noticeTranslation(trans2D.scl(-1.0f));

       }else{// else scroll back to player if cam is too far away and has not been touched for some seconds

        }

        cam.update();
    }

    public void addPBM(ParallaxBackgroundManager pbM){
        this.pBM = pbM;
    }

    /*
    Calculates depending on the movement of the player ship, where the
    camera focuses the player ship.
     */
    private Vector3 getPointToFocusCamera(){
        Vector2 playerVel = player.getDeltaMovement().cpy();

        if( playerVel.x * playerVel.x > playerVel.y * playerVel.y ){ // focus is in the middle, left or right
            float deltaMiddleValueToScreenEnd = MainMenuScreen.camFixed.viewportWidth - screenCenter.x;
            if(playerVel.x < 0){
                return new Vector3(deltaMiddleValueToScreenEnd/2.0f,0,0);
            }else{
                return new Vector3(-deltaMiddleValueToScreenEnd/2.0f,0,0);
            }
        }else if(playerVel.x * playerVel.x < playerVel.y * playerVel.y){ // focus is either on the bottom or top
            float deltaMiddleValueToScreenEnd = MainMenuScreen.camFixed.viewportHeight - screenCenter.y;
            if(playerVel.y < 0){
                return new Vector3(0,deltaMiddleValueToScreenEnd/2.0f,0);
            }else{
                return new Vector3(0,-deltaMiddleValueToScreenEnd/2.0f,0);
            }
        }else{ // focus is the middle of the screen
            return screenCenter.cpy();
        }
    }
}
