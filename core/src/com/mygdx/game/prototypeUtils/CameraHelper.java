package com.mygdx.game.prototypeUtils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.renderAbleObjects.ARenderableObject;

/**
 * Created by denis on 5/22/16.
 */
public class CameraHelper extends ARenderableObject implements IInputHandler {

    private CameraManager cM;

    private Vector2 touchPos;
    private Vector2 lastUpdatedPos;
    private Vector2 diff;
    private boolean isUpdate;

    private Rectangle hitbox;

    public CameraHelper(){
        touchPos = new Vector2();
        lastUpdatedPos = new Vector2();
        diff = new Vector2();
        hitbox = new Rectangle();
        isUpdate = false;
    }
    public void setCameraManager(CameraManager cM){
        this.cM = cM;
        hitbox.set(0,0,cM.getCam().viewportWidth, cM.getCam().viewportHeight);
        isUI = true;
    }

    public void OnTouch(TouchData td) {
        return;
        //System.out.println(td.getPosCurrent());
    }

    public void OnRelease(TouchData td) {
    }

    public void OnDrag(TouchData td) {
        /*
        touchPos.set(td.getPosCurrent());
        System.out.println(touchPos);
        if(touchPos.cpy().sub(lastUpdatedPos).len() != 0){
            diff = touchPos.cpy().sub(lastUpdatedPos);
            cM.addTranslation(diff);
            lastUpdatedPos.set(touchPos);
        }
        */
        if(cM.getPlayer().isInOrbit()) {
            System.out.println(td.getDeltaSwipe());
            cM.addTranslation(new Vector2(-td.getDeltaSwipe().x, td.getDeltaSwipe().y));
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
