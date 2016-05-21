package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/21/16.
 */
public class BackGround extends Decoration {

    public BackGround(){
        super();
    }

    public void initialize(Vector2 pos, Vector2 spriteDimension, int spriteID, String texturePath){
        initializeTexture(spriteDimension,spriteID,texturePath);
        initializePositions(pos);
    }

    public void renderHitboxes(ShapeRenderer s){
        return;
    }
}
