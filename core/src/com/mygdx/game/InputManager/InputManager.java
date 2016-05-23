package com.mygdx.game.InputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import java.util.HashMap;

public class InputManager implements InputProcessor
{
    /* static instance */

    public static InputManager instance;

    /* needs to be called before first use */
    public static void setup(Camera cam)
    {
        instance = new InputManager();
        instance.cam = cam;
    }

    final float SWIPE_REC_LENGTH = 20f;
//    public enum TypeInput { TOUCH, RELEASE, DRAG, HOLD, SWIPE}

    Camera cam;

    /* Basic */
    InputManager()
    {
        // this is an input processor that gets registered at the main input
        Gdx.input.setInputProcessor(this);
        objectHolder = new ObjectHolder<ARenderableObject>();
    }

    public void update(float deltaTime)
    {
        for (TouchData td : touchData.values())
        {
            td.setSecPressed(td.getSecPressed() + deltaTime);
            notifyObjectsHold(td);
        }
    }

    /* Touch related */
    public ObjectHolder<ARenderableObject> objectHolder;

    HashMap<Integer, TouchData> touchData = new HashMap<Integer, TouchData>();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {

        //TODO , hier das meinte ich mit unpojectedPos :)
        // das brauchen wir für UI elemente, auch mit swipe, so wies aussieht
        Vector3 posTouch = new Vector3(screenX,screenY,0);
        //screenY = ((int)cam.viewportHeight) -screenY;
        Vector3 posTouchUnproj = new Vector3(screenX,screenY,0);
        //cam.unproject(posTouch);

        Array<ARenderableObject> objs = objectHolder.getObjects();

        System.out.println("holder size " + objs.size);

        Array<IInputHandler> objsHit = new Array<IInputHandler>();
        for(int i = 0; i < objs.size; i++)
        {
            ARenderableObject obj = objs.get(i);
            if (obj instanceof IInputHandler)
            {
                cam.unproject(posTouch);
                if
                (

                    (!obj.isUI() && (obj.getHitbox().contains(posTouch.x,posTouch.y))) ||
                    (obj.isUI() &&(obj.getHitbox().contains(posTouchUnproj.x,posTouchUnproj.y)))
                )
                {
                    objsHit.add((IInputHandler) obj);
                }
                posTouch.set(posTouchUnproj);
            }
        }

        if(objsHit.size == 0)
        {
            return true; // there is no interesting object touched
        }

        TouchData td = new TouchData();
        td.setObjsOrigin(objsHit);
        td.setPosOrigin(new Vector2(posTouch.x, posTouch.y));
        td.setPosOriginUnprojected(new Vector2(posTouchUnproj.x, posTouchUnproj.y));
        td.setPosCurrent(td.getPosOrigin());
        td.setPosCurrentUnprojected(new Vector2(posTouchUnproj.x, posTouchUnproj.y));
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
        if(touchData.containsKey(pointer))
        {
            notifyObjectsRelease(touchData.get(pointer));
            if(touchData.containsKey(pointer)) touchData.remove(pointer);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {

        if(touchData.containsKey(pointer))
        {
            Vector3 vecTouch = new Vector3(screenX, screenY, 0);
            //screenY = ((int)cam.viewportHeight) - screenY;
            Vector3 vecUnPro = new Vector3(screenX, screenY, 0);
           // cam.unproject(vecTouch);

            TouchData td = touchData.get(pointer);
            td.setPosPrev(td.getPosCurrent());
            //td.setPosCurrent(new Vector2(screenX, screenY));
            td.setPosCurrent(new Vector2(vecTouch.x, vecTouch.y));
            td.setPosCurrentUnprojected(new Vector2(vecUnPro.x, vecUnPro.y));
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
                        td.setDirSwipePrev(TouchData.DirSwipe.DOWN);
                    }
                    else // down
                    {
                        td.setDirSwipePrev(TouchData.DirSwipe.UP);
                    }
                }
                else
                {
                    if(sDelta.x < 0) // right
                    {
                        td.setDirSwipePrev(TouchData.DirSwipe.LEFT);
                    }
                    else // left
                    {
                        td.setDirSwipePrev(TouchData.DirSwipe.RIGHT);
                    }
                }
            }
            notifyObjectsSwipe(td);
        }

        return true;
    }

    void notifyObjectsTouch(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnTouch(td);
        }
    }

    void notifyObjectsRelease(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnRelease(td);
        }
    }

    void notifyObjectsDrag(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnDrag(td);
        }
    }

    void notifyObjectsHold(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnHold(td);
        }
    }

    void notifyObjectsSwipe(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
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