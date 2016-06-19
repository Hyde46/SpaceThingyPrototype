package com.mygdx.game.Items;

/**
 * Created by Mechandrius on 19.06.2016.
 */
public abstract class Item
{
    float timeCooldown;
    public boolean inCooldown = false;

    public abstract void activate();
    public abstract void deactivate();

    public abstract void effect();

    /* when item is thrown out of inventory, etc
    -> item will reset, cancels everything */
    public abstract void reset();

    public void update(float delta)
    {

    }
}
