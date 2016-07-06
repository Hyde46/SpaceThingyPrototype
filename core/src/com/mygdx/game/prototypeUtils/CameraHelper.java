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
import com.mygdx.game.screens.MainMenuScreen;

/**
 * Created by denis on 5/22/16.
 */
public class CameraHelper extends ARenderableObject implements IInputHandler {

    private CameraManager cM;

    private DialogManager dialogManager;
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
    public void setCameraManager(CameraManager cM, DialogManager dialogManager){
        this.cM = cM;
        hitbox.set(0,0, MainMenuScreen.camFixed.viewportWidth, MainMenuScreen.camFixed.viewportHeight);
        isUI = true;
        this.dialogManager = dialogManager;
    }

    public void OnTouch(TouchData td) {
        return;
        //System.out.println(td.getPosCurrent());
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
        //player == null so that the helper can be used in in overworld
        if(cM.getPlayer() != null){//|| cM.getPlayer().isInOrbit()) {
            //check if dialog manager is null (ingame) or if a dialog is shown (overworld) -> no camera movement
            if(dialogManager == null || !dialogManager.getShowDialog()){
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
