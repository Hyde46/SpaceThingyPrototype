package com.mygdx.game.managers.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MainMenuScreen;

import org.w3c.dom.css.Rect;

/**
 * Created by denis on 5/22/16.
 */
public class CameraManager {

    private OrthographicCamera cam;

    private Vector2 screenDim;
    private Vector3 screenCenter;
    private Vector3 screenCenterStatic;
    private Vector3 translation;

    private Vector3 offsetFocusPlayer;

    private float translationDamp;

    private SpaceShip player;

    private ParallaxBackgroundManager pBM;


    private Vector2 trans;

    //fields for camera delay
    private final static int MAX_CAMERA_WAITITNG_TIME = 60;
    private int cameraDelayCounter;
    private boolean isFocusingPlayerInOrbit;


    public CameraManager(){
        screenDim = new Vector2();
        translation = new Vector3(0,0,0);
        screenCenter = new Vector3();
        screenCenter = new Vector3();
        offsetFocusPlayer = new Vector3(0,0,0);
        trans = new Vector2();
    }

    public void initializeCamera(SpaceShip player,Vector2 positionToFocus){
        initfields(player);
        instantFocusCamera(positionToFocus);
    }
    public void initializeCamera(SpaceShip player){
        initfields(player);
        instantFocusCamera(player.getPosition());
    }

    private void initfields(SpaceShip player){
        this.player = player;
        this.screenDim.set(new Vector2(MainMenuScreen.camFixed.viewportWidth,MainMenuScreen.camFixed.viewportHeight));
        screenCenter.set(screenDim.x/2,screenDim.y/2,0);
        translationDamp = 35.0f;
    }
    private void instantFocusCamera(Vector2 focusPoint){
        cam.translate(focusPoint.cpy().x - screenCenter.x ,focusPoint.cpy().y - screenCenter.y );
        translation.set(focusPoint.cpy().x - screenCenter.x , focusPoint.cpy().y - screenCenter.y, 0 );
        cam.update();
        screenCenterStatic = screenCenter.cpy();
        cameraDelayCounter = MAX_CAMERA_WAITITNG_TIME;
        isFocusingPlayerInOrbit = false;
    }

    public void addTranslation(Vector2 translate){
        if(player != null )
            if(player.isInOrbit())
                resetCameraDelay();
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

        if(player != null){
            if(!player.isInOrbit()) {

                focusCamOnPos(player.getPosition());

            }else if(player.isInOrbit() && !isPlayerVisible() || player.isInOrbit() && isFocusingPlayerInOrbit) {
                isFocusingPlayerInOrbit = true;
                cameraDelayCounter -= 1;
                if (cameraDelayCounter <= 0) {
                    focusCamOnPos(player.getConnectedPlanet().getPosition());
                    cameraDelayCounter = 0;
                }
            }
        }

        cam.update();
    }

    private void focusCamOnPos(Vector2 positionToFocus) {
        offsetFocusPlayer = getPointToFocusCamera(); // get the point, where the camera should focus on
        Vector3 positionWorldSpace = new Vector3(positionToFocus.cpy().x,positionToFocus.cpy().y,0);

        Vector3 trans3D = positionWorldSpace.cpy().sub(cam.position.cpy().add(offsetFocusPlayer));
        Vector2 trans2D = new Vector2(trans3D.x,trans3D.y);


        if(trans2D.cpy().sub(translation.x,translation.y).len() >= 5){
            trans2D.scl(1.0f/ translationDamp);
            trans3D.scl(1.0f/ translationDamp);
        }

        cam.translate(trans3D);
        translation.add(trans3D.x, trans3D.y, 0);
        screenCenter.add(trans3D.x,trans3D.y,0);
        pBM.noticeTranslation(trans2D.scl(-1.0f));
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
        if(player.isInOrbit())
            return screenCenterStatic.cpy().add(-screenDim.x/2,-screenDim.y/2,0);
        if( playerVel.x * playerVel.x > playerVel.y * playerVel.y ){ // focus is in the middle, left or right
            float deltaMiddleValueToScreenEnd = MainMenuScreen.camFixed.viewportWidth - screenCenterStatic.x;
            if(playerVel.x < 0){
                return new Vector3(deltaMiddleValueToScreenEnd/2.0f,0,0);
            }else{
                return new Vector3(-deltaMiddleValueToScreenEnd/2.0f,0,0);
            }
        }else if(playerVel.x * playerVel.x < playerVel.y * playerVel.y){ // focus is either on the bottom or top
            float deltaMiddleValueToScreenEnd = MainMenuScreen.camFixed.viewportHeight - screenCenterStatic.y;
            if(playerVel.y < 0){
                return new Vector3(0,deltaMiddleValueToScreenEnd/2.0f,0);
            }else{
                return new Vector3(0,-deltaMiddleValueToScreenEnd/2.0f,0);
            }
        }else{ // focus is the middle of the screen
            return screenCenter.cpy();
        }
    }

    private void resetCameraDelay(){
        cameraDelayCounter = MAX_CAMERA_WAITITNG_TIME;
        isFocusingPlayerInOrbit = false;
    }

    private boolean isPlayerVisible(){
        Vector2 playerPosWorldSpace = player.getPosition().cpy();
        Vector3 cameraCenterWorldSpace = cam.position.cpy();
        Vector2 cameraMargins = new Vector2(screenDim.x/2,screenDim.y/2);
        Rectangle cameraView = new Rectangle(cameraCenterWorldSpace.x - cameraMargins.x,cameraCenterWorldSpace.y - cameraMargins.y,
                cameraMargins.x * 2, cameraMargins.y * 2);

        return cameraView.contains(playerPosWorldSpace);
    }
}
