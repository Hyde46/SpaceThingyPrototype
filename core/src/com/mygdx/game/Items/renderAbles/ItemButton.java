package com.mygdx.game.Items.renderAbles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;

/**
 * Created by Denis on 27.06.2016.
 */
public class ItemButton extends Decoration implements IInputHandler {


    public ItemButton(){

    }

    public void initialize(String texturePath, int widthHeight, Vector2 posToRender){
        initializePositions(posToRender.cpy());
        initializeTexture(new Vector2(widthHeight,widthHeight), 0, texturePath);
    }

    @Override
    public void render(SpriteBatch batch){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(batch);
    }

    @Override
    public void OnTouch(TouchData td) {

    }

    @Override
    public void OnRelease(TouchData td) {

    }

    @Override
    public void OnDrag(TouchData td) {

    }

    @Override
    public void OnHold(TouchData td) {

    }

    @Override
    public void OnSwipe(TouchData td) {

    }
    @Override
    public void renderHitboxes(ShapeRenderer r){

    }
}
