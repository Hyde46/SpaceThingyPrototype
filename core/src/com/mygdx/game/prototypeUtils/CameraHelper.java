package com.mygdx.game.prototypeUtils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.overworldObjects.Dialog.DialogManager;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by denis on 5/22/16.
 */
public class CameraHelper extends ARenderableObject implements IInputHandler {

    private CameraManager cM;

    private DialogManager dialogManager;
    private int screenType; //1 = game screen, 2 = overworld, 3 = shop, 4 = hangar
    private Vector2 touchPos;
    private Vector2 lastUpdatedPos;
    private Vector2 diff;
    private boolean isUpdate;

    private Rectangle hitbox;

    public CameraHelper(){
        super();
        touchPos = new Vector2();
        lastUpdatedPos = new Vector2();
        diff = new Vector2();
        hitbox = new Rectangle();
        isUpdate = false;
    }

    public void setCameraManager(CameraManager cM, DialogManager dialogManager, int screenType){
        this.cM = cM;
        hitbox.set(0,0, MainMenuScreen.camFixed.viewportWidth, MainMenuScreen.camFixed.viewportHeight);
        isUI = true;
        this.dialogManager = dialogManager;
        this.screenType = screenType;
    }

    public void OnTouch(TouchData td) {
        return;
        //System.out.println(td.getPosCurrent());
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
        //player == null so that the helper can be used in in overworld TODO talk to denis

            //check if dialog manager is null (ingame) or if a dialog is shown (overworld) -> no camera movement
            if(dialogManager == null || !dialogManager.getShowDialog()){
                //if screen is 4 (hangar) only translate in y direction)
                switch(screenType){
                    case 1:

                    case 2:
                        cM.addTranslation(new Vector2(-td.getDeltaSwipe().x, td.getDeltaSwipe().y));
                        break;
                    case 3:
                    case 4:
                        cM.addTranslation(new Vector2(0, td.getDeltaSwipe().y));
                        break;
                    default:
                        cM.addTranslation(new Vector2(-td.getDeltaSwipe().x, td.getDeltaSwipe().y));
                }

            }

    }

    public void OnHold(TouchData td) {

    }

    public void OnSwipe(TouchData td) {
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void renderHitboxes(ShapeRenderer sr){

    }

}
