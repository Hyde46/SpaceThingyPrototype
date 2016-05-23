package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.SOptions;

/**
 * Created by Mechandrius on 23.05.2016.
 */
public class ButtonOptions extends Decoration implements IInputHandler
{

    public void initialize(Vector2 position, int width, int height)
    {
        this.position = position.cpy();
        //       this.hitBox = new Rectangle(position.x, position.y, width, height);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);


        Texture t = new Texture(Gdx.files.internal("planet1_72x72.png"));
        sprite = new Sprite(t,width,height);
        sprite.setCenter(position.x,position.y);
        sprite.setX(position.x-width/2);
        sprite.setY(position.y-height/2);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td) {
        MyGdxGame.game.openScreen(new SOptions());
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
}
