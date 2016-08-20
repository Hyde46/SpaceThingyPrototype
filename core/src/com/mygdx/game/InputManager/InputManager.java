/*
    Bessere Handhabung:
        objs in gruppe registrieren und nacher ganze gruppe mit string name löschen

    Für touch everywhere muss speziell in gruppe "e" registriert werden
        - spart durchlauf aller obj mit jedem touch

*/

package com.mygdx.game.InputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.ARenderableObject;

import java.util.ArrayList;
import java.util.HashMap;

public class InputManager implements InputProcessor
{
    // general
    public static InputManager get;
    private Camera cam;

    // input relevant
    private static HashMap<Integer, TouchData> touchData = new HashMap<Integer, TouchData>();
    private final float SWIPE_REC_LENGTH = 20f;

    // objects
    private HashMap<String, Array<ARenderableObject>> objects;
    private final String nameGroupGeneral = "g";
    private final String nameGroupTouchEverywhere = "e";

    // setup
    InputManager()
    {
        Gdx.input.setInputProcessor(this); // this is an input processor that gets registered at the main input
        objects = new HashMap<String, Array<ARenderableObject>>();
    }

    public static void setup(Camera cam)
    {
        get = new InputManager();
        get.cam = cam;
    }

    /* Use update to be able to measure time intervals */
    public void update(float deltaTime)
    {
        for (TouchData td : touchData.values())
        {
            td.setSecPressed(td.getSecPressed() + deltaTime);
            notifyObjectsHold(td);
        }
    }

    // registration of objs
    public void register(ARenderableObject obj)
    {
        registerIntern(nameGroupGeneral, obj);
    }
    public void register(String name, ARenderableObject obj)
    {
        registerIntern(name, obj);
    }
    public void unRegister(ARenderableObject obj)
    {
        unRegisterIntern(nameGroupGeneral, obj);
    }
    public void unRegister(String name, ARenderableObject obj)
    {
        unRegisterIntern(name, obj);
    }
    public void clearGeneral()
    {
        clearGroupIntern(nameGroupGeneral);
    }
    public void clearGroup(String name)
    {
        clearGroupIntern(name);
    }
    public void clearAll()
    {
        ArrayList<String> keysGroups = new ArrayList<String>(objects.keySet());
        for (int i = 0; i < keysGroups.size(); i++) clearGroupIntern(keysGroups.get(i));
    }
    public Array<ARenderableObject> getObjectsGeneral()
    {
        return getObjectsIntern(nameGroupGeneral);
    }
    public Array<ARenderableObject> getObjectsGroup(String name)
    {
        return getObjectsIntern(name);
    }
    public Array<ARenderableObject> getObjectsAll()
    {
        ArrayList<String> keysGroups = new ArrayList<String>(objects.keySet());
        Array<ARenderableObject> objsAll = new Array<ARenderableObject>();

        for (int i = 0; i < keysGroups.size(); i++)
        {
            objsAll.addAll(objects.get(keysGroups.get(i)));
        }

        return objsAll;
    }

    // local
    private void registerIntern(String name, ARenderableObject obj)
    {
        if(!objects.containsKey(name))
        {
            objects.put(name, new Array<ARenderableObject>());
            objects.get(name).add(obj);
        }
        else
        {
            if(!objects.get(name).contains(obj, false))
                objects.get(name).add(obj);
        }
    }

    private void unRegisterIntern(String name, ARenderableObject obj)
    {
        if(objects.get(name).contains(obj,false)) objects.get(name).removeValue(obj, false);
        if(objects.get(name).size == 0) objects.remove(name);
    }

    private void clearGroupIntern(String name)
    {
        if(objects.containsKey(name))
        {
            objects.get(name).clear();
            objects.remove(name);
        }
    }

    private Array<ARenderableObject> getObjectsIntern(String name)
    {
        return objects.get(name);
    }

    // read input
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        // das brauchen wir für UI elemente, auch mit swipe, so wies aussieht
        Vector3 posTouch = new Vector3(screenX,screenY,0);

 //       System.out.println("touch " + posTouch);
 //       System.out.println("touch unpro start" + cam.unproject(posTouch));

//        cam.unproject(posTouch);
//        Vector3 posUnproj = cam.unproject(posTouch);

        //screenY = ((int)cam.viewportHeight) -screenY;
        Vector3 posTouchUnproj = new Vector3(screenX,screenY,0);
        //cam.unproject(posTouch);

        Array<IInputHandler> objsHit = new Array<IInputHandler>();

        ArrayList<String> keys = new ArrayList<String>(objects.keySet());
        for (int idxGroup = 0; idxGroup < keys.size(); idxGroup++)
        {
            Array<ARenderableObject> objsOfGroup = getObjectsGroup(keys.get(idxGroup));

            for (int i = 0; i < objsOfGroup.size; i++)
            {
                ARenderableObject obj = (ARenderableObject)objsOfGroup.get(i);
                if (obj instanceof IInputHandler)
                {
                    cam.unproject(posTouch);
       //             System.out.println("unpro inside " + cam.unproject(posTouch));

                    if(!obj.isUI() && (obj.getHitbox().contains(posTouch.x, posTouch.y)))
                    {
                        objsHit.add((IInputHandler) obj);
                    }

                    if(obj.isUI() && (obj.getHitbox().contains(posTouchUnproj.x, posTouchUnproj.y)))
                    {
                        objsHit.add((IInputHandler) obj);
                    }

//                    if
//                    (
//                        (!obj.isUI() && (obj.getHitbox().contains(posTouch.x, posTouch.y))) ||
//                        (obj.isUI() && (obj.getHitbox().contains(posTouchUnproj.x, posTouchUnproj.y)))
//                    )
//                    {
//                        objsHit.add((IInputHandler) obj);
//                    }

                    posTouch.set(posTouchUnproj);
                }
            }
        }

        if(objsHit.size == 0)
        {
            return true; // there is no interesting object touched
        }

        Vector3 vecTouch = new Vector3(screenX, screenY, 0);

        TouchData td = new TouchData();
        td.setPosWorldOrigin(cam.unproject(new Vector3(screenX,screenY,0)));
        td.setPosWorldCurrent(cam.unproject(new Vector3(screenX,screenY,0)));
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
        notifyObjectsTouchAnywhere(td);
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

            td.setPosWorldOrigin(cam.unproject(new Vector3(screenX,screenY,0)));
            td.setPosWorldCurrent(cam.unproject(new Vector3(screenX,screenY,0)));
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

    private void notifyObjectsTouchAnywhere(TouchData td)
    {
        if(objects.containsKey(nameGroupTouchEverywhere))
        {
            for(int i = 0; i < objects.get(nameGroupTouchEverywhere).size; i++)
            {
                if(objects.get(nameGroupTouchEverywhere).get(i) instanceof IInputAnywhere)
                    ((IInputAnywhere)objects.get(nameGroupTouchEverywhere).get(i)).OnTouchAnywhere(td);
            }
        }
    }

    private void notifyObjectsTouch(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnTouch(td);

            if(objsOrigin.get(i) instanceof ARenderableObject)
            {
                ((ARenderableObject)objsOrigin.get(i)).notifyPress();
            }
        }
    }

    private void notifyObjectsRelease(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnRelease(td);
        }
    }

    private void notifyObjectsDrag(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnDrag(td);
        }
    }

    private void notifyObjectsHold(TouchData td)
    {
        Array<IInputHandler> objsOrigin = td.getObjsOrigin();
        for(int i = 0; i < objsOrigin.size; i++)
        {
            objsOrigin.get(i).OnHold(td);
        }
    }

    private void notifyObjectsSwipe(TouchData td)
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