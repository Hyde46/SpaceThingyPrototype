package com.mygdx.game.renderAbleObjects;

/**
 * Created by denis on 5/18/16.
 */
public abstract class AUpdateableObject extends ARenderableObject {

    protected AUpdateableObject(){
        super();
    }

    public abstract void update(float delta);
}
