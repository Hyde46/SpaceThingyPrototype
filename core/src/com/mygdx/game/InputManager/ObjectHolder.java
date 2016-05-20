package com.mygdx.game.InputManager;

import java.util.ArrayList;

public class ObjectHolder<T>
{
    ArrayList<T> objects;

    public ObjectHolder()
    {
        objects = new ArrayList<T>();
    }

    public void Register(T obj)
    {
        if(!objects.contains(obj))
            objects.add(obj);
    }

    public void Register(ArrayList<T> objs)
    {
        for(int i = 0; i < objs.size(); i++) Register(objs.get(i));
    }

    public void UnRegister(T obj)
    {
        if(objects.contains(obj))
        objects.remove(obj);
    }

    public void Clear()
    {
        objects.clear();
    }

    public ArrayList<T> getObjects() { return objects; }
}
