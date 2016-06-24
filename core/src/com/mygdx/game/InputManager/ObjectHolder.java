package com.mygdx.game.InputManager;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class ObjectHolder<T>
{
    Array<T> objects;

    public ObjectHolder()
    {
        objects = new Array<T>();
    }

    public void RegisterIntern(T obj)
    {
        if(!objects.contains(obj, false))
            objects.add(obj);
    }

    public void RegisterIntern(Array<T> objs)
    {
        for(int i = 0; i < objs.size; i++) RegisterIntern(objs.get(i));
    }

    public void UnRegisterIntern(T obj)
    {
        if(objects.contains(obj,false))
        objects.removeValue(obj, false);
    }

    public void ClearIntern()
    {
        objects.clear();
    }

    public Array<T> getObjects() { return objects; }
}
