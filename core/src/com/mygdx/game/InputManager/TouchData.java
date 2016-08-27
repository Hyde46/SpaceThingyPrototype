package com.mygdx.game.InputManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.ARenderableObject;

public class TouchData
{
    public Vector3 getPosWorldOrigin() {
        return posWorldOrigin;
    }

    public Vector3 getPosWorldCurrent() {
        return posWorldCurrent;
    }

    public void setPosWorldCurrent(Vector3 posWorldCurrent) {
        this.posWorldCurrent = posWorldCurrent;
    }

    public void setPosWorldOrigin(Vector3 posWorldOrigin) {
        this.posWorldOrigin = posWorldOrigin;
    }

    public enum DirSwipe { DEFAULT, RIGHT, LEFT , UP, DOWN }

    private Array<ARenderableObject> objsOrigin;

    private Vector3 posWorldOrigin;
    private Vector3 posWorldCurrent;
    private Vector2 posOrigin;
    private Vector2 posOriginUnprojected;
    private Vector2 posCurrent;
    private Vector2 posCurrentUnprojected;
    private Vector2 posPrev;
    private Vector2 deltaFrame;
    private Vector2 deltaSwipe;

    private DirSwipe dirSwipePrev;

    private float lengthSwipe;
    private float secPressed;

    public TouchData()
    {
        objsOrigin = new Array<ARenderableObject>();
        dirSwipePrev = DirSwipe.DEFAULT;
    }

    public Array<ARenderableObject> getObjsOrigin() {
        return objsOrigin;
    }
    public void setObjsOrigin(Array<ARenderableObject> objs) { objsOrigin = new Array<ARenderableObject>(objs); }

    public Vector2 getPosOrigin() {
        return posOrigin;
    }
    public void setPosOrigin(Vector2 posOrigin) {
        this.posOrigin = posOrigin;
    }

    public Vector2 getPosOriginUnprojected() { return posOriginUnprojected; }
    public void setPosOriginUnprojected(Vector2 posOriginUnprojected) { this.posOriginUnprojected = posOriginUnprojected; }

    public Vector2 getPosCurrent() {
        return posCurrent;
    }
    public void setPosCurrent(Vector2 posCurrent) {
        this.posCurrent = posCurrent;
    }

    public Vector2 getPosCurrentUnprojected() { return posCurrentUnprojected; }
    public void setPosCurrentUnprojected(Vector2 posCurrentUnprojected) { this.posCurrentUnprojected = posCurrentUnprojected; }

    public Vector2 getPosPrev() {
        return posPrev;
    }
    public void setPosPrev(Vector2 posPrev) {
        this.posPrev = posPrev;
    }

    public Vector2 getDeltaFrame() {
        return deltaFrame;
    }
    public void setDeltaFrame(Vector2 deltaFrame) {
        this.deltaFrame = deltaFrame;
    }

    public Vector2 getDeltaSwipe() {
        return deltaSwipe;
    }
    public void setDeltaSwipe(Vector2 deltaSwipe) {
        this.deltaSwipe = deltaSwipe;
    }

    public DirSwipe getDirSwipePrev() {
        return dirSwipePrev;
    }
    public void setDirSwipePrev(DirSwipe dirSwipesPrev) {
        this.dirSwipePrev = dirSwipesPrev;
    }

    public float getLengthSwipe() {
        return lengthSwipe;
    }
    public void setLengthSwipe(float lengthSwipe) {
        this.lengthSwipe = lengthSwipe;
    }

    public float getSecPressed() {
        return secPressed;
    }
    public void setSecPressed(float secPressed) {
        this.secPressed = secPressed;
    }
}