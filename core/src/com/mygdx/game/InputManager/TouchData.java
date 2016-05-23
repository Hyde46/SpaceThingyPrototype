package com.mygdx.game.InputManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TouchData
{
    public enum DirSwipe { DEFAULT, RIGHT, LEFT , UP, DOWN }

    Array<IInputHandler> objsOrigin;

    Vector2
        posOrigin,
        posCurrent,
        posPrev,
        deltaFrame,
        deltaSwipe;

    DirSwipe dirSwipePrev;

    float lengthSwipe;
    float secPressed;

    public TouchData()
    {
        objsOrigin = new Array<IInputHandler>();
        dirSwipePrev = DirSwipe.DEFAULT;
    }

    public Array<IInputHandler> getObjsOrigin() {
        return objsOrigin;
    }
    public void setObjsOrigin(Array<IInputHandler> objs) { objsOrigin = objs; }

    public Vector2 getPosOrigin() {
        return posOrigin;
    }
    public void setPosOrigin(Vector2 posOrigin) {
        this.posOrigin = posOrigin;
    }

    public Vector2 getPosCurrent() {
        return posCurrent;
    }
    public void setPosCurrent(Vector2 posCurrent) {
        this.posCurrent = posCurrent;
    }

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
