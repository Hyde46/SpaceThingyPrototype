package com.mygdx.game.InputManager;

import com.badlogic.gdx.Gdx;
/*
    - Register objs with the IInputHandler attached to them in the ObjectHolder
    (dont forget to delete them after use)
    - Inside the IInputHandler overridden methods use TouchData to get info over
    deltas, touch duration and even swipe direction

    - The InputManager needs a tick to update the hold button times
*/

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager implements InputProcessor
{
    public final float SWIPE_REC_LENGTH = 20f;
    public enum TypeInput { TOUCH, RELEASE, DRAG, HOLD, SWIPE}

    /* Singleton */
    static InputManager instance;

    public static InputManager get()
    {
        if(instance == null) instance = new InputManager();
        return instance;
    }

    /* Basic */
    InputManager()
    {
        // this is an input processor that gets registered at the main input
        Gdx.input.setInputProcessor(this);
    }

    public void Tick(float deltaTime)
    {
        for (TouchData td : touchData.values())
        {
            td.setSecPressed(td.getSecPressed() + deltaTime);
            notifyObjectsHold(td);
        }
    }

    /* Touch related */
    public ObjectHolder<Integer> objectHolder;

    HashMap<Integer, TouchData> touchData = new HashMap<Integer, TouchData>();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        // get objs list from oh
/*        for(int i = 0; i < objs.size(); i++)
        {
            if(IInputHandler.class.isInstance(new Integer(3)))
            {
                touchData.get(pointer).getObjsOrigin().add((IInputHandler)new ObjectHolder<Integer>());
            }
        }*/

        TouchData td = new TouchData();
        td.setPosOrigin(new Vector2(screenX, screenY));
        td.setPosCurrent(td.getPosOrigin());
        td.setPosPrev(td.getPosOrigin());
        td.setDeltaFrame(Vector2.Zero);
        td.setDeltaSwipe(Vector2.Zero);
        td.setSecPressed(0f);
        td.setLengthSwipe(0f);

        touchData.put(pointer, td);
        notifyObjectsTouch(td);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        notifyObjectsRelease(touchData.get(pointer));
        if(touchData.containsKey(pointer)) touchData.remove(touchData.get(pointer));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        TouchData td = touchData.get(pointer);
        td.setPosPrev(td.getPosCurrent());
        td.setPosCurrent(new Vector2(screenX, screenY));
        td.setDeltaFrame(new Vector2(td.getPosCurrent().x - td.getPosPrev().x, td.getPosCurrent().y - td.getPosPrev().y));
        td.setDeltaSwipe(new Vector2(td.getDeltaSwipe().x + td.getDeltaFrame().x, td.getDeltaSwipe().y + td.getDeltaFrame().y));
        float lengthSwipe = Vector2.dst(td.getPosCurrent().x, td.getPosCurrent().y, td.getPosPrev().x, td.getPosPrev().y);
        td.setLengthSwipe(td.getLengthSwipe() + lengthSwipe);

        notifyObjectsDrag(td);

        if(td.getLengthSwipe() >= SWIPE_REC_LENGTH)
        {
            Vector2 sDelta = td.getDeltaSwipe();
            td.setDeltaSwipe(Vector2.Zero);
            if(Math.abs(sDelta.x) < Math.abs(sDelta.y))
            {
                if(sDelta.y > 0) // up
                {
                    td.setDirSwipePrev(TouchData.DirSwipe.UP);
                }
                else // down
                {
                    td.setDirSwipePrev(TouchData.DirSwipe.DOWN);
                }
            }
            else
            {
                if(sDelta.x < 0) // right
                {
                    td.setDirSwipePrev(TouchData.DirSwipe.RIGHT);
                }
                else // left
                {
                    td.setDirSwipePrev(TouchData.DirSwipe.LEFT);
                }
            }
        }
        notifyObjectsSwipe(td);
        return true;
    }

    void notifyObjectsTouch(TouchData td)
    {
        ArrayList<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size(); i++)
        {
            objsOrigin.get(i).OnTouch(td);
        }
    }

    void notifyObjectsRelease(TouchData td)
    {
        ArrayList<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size(); i++)
        {
            objsOrigin.get(i).OnRelease(td);
        }
    }

    void notifyObjectsDrag(TouchData td)
    {
        ArrayList<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size(); i++)
        {
            objsOrigin.get(i).OnDrag(td);
        }
    }

    void notifyObjectsHold(TouchData td)
    {
        ArrayList<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size(); i++)
        {
            objsOrigin.get(i).OnHold(td);
        }
    }

    void notifyObjectsSwipe(TouchData td)
    {
        ArrayList<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size(); i++)
        {
            objsOrigin.get(i).OnSwipe(td);
        }
    }

    // unsused keyboard calls
    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(int amount) { return false; }
}