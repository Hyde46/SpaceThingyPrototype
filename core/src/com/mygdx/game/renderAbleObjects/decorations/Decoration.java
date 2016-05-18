package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.renderAbleObjects.RenderAbleObject;

/**
 * Created by denis on 5/13/16.
 */
public abstract class Decoration extends RenderAbleObject {

    /*
     * Buttons und sowas hier branchen lassen?
     *
     */
    public Decoration(Vector2 pos, Vector2 spriteDimension, int spriteID ){
        super(pos,spriteDimension,spriteID);
    }
}
