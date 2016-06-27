package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Items.renderAbles.ItemButton;

/**
 * Created by Mechandrius on 19.06.2016.
 */
public abstract class Item
{
    public enum StateItem { READY, ACTIVATED, EFFECT, COOLDOWN }


    public int level;
    public StateItem stateItem;

    /*
    *Determines wether the item is on pos 0(left) oder 1(right) oder 2(mitte)
     */
    protected int levelPos;
    protected ItemButton itemButton;

    public Item()
    {
        stateItem = StateItem.READY;
    }

    public float timeCooldown;

    public void activateSuper()
    {
        stateItem = StateItem.ACTIVATED;
    }
    public void deactivateSuper()
    {
        stateItem = StateItem.READY;
    }

    public void effectStartSuper()
    {
        stateItem = StateItem.EFFECT;
    }

    public void effectEndSuper()
    {
        stateItem = StateItem.COOLDOWN;
    }

    public abstract void activate();
    public abstract void effectStart();
    public abstract void effectEnd();
    public abstract void deactivate();

    public abstract void initialize();

    /* when item is thrown out of inventory, etc
    -> item will reset, cancels everything */
    public abstract void reset();

    // will be overriden in subclass because some items dont need the update
    public void update(float delta){}

    public abstract void render(SpriteBatch sB);
}
