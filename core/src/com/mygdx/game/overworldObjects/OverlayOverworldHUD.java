package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 24.05.2016.
 */
public class OverlayOverworldHUD extends ARenderableObject{
    public static final String PATH_BUTTON_OPTIONS = "i-star-200.png";
    public static final String PATH_BUTTON_EXIT = "i-cancel-200.png";

    MyGdxGame game;

    Sprite spriteOptions;
    Sprite spriteExit;

    public OverlayOverworldHUD(){}

    public void initialize(){
        this.game = MyGdxGame.game;
        this.touchHitbox = new Rectangle(0, 0, game.screenWidth, game.screenHeight);

        Texture texOptions = new Texture(Gdx.files.internal(PATH_BUTTON_OPTIONS));
        spriteOptions = new Sprite(texOptions, texOptions.getWidth(), texOptions.getHeight());
        spriteOptions.setY(0);
        spriteOptions.setX(0);

        Texture texExit = new Texture(Gdx.files.internal(PATH_BUTTON_EXIT));
        spriteExit = new Sprite(texExit, texExit.getWidth(), texExit.getHeight());
        spriteExit.setY(0);
        spriteExit.setX(game.screenWidth - spriteExit.getWidth());
    }

    public void render(SpriteBatch batch)
    {
        spriteOptions.draw(batch);
        spriteExit.draw(batch);
    }

    @Override
    public void renderHitboxes(ShapeRenderer shapeRenderer){

    }
}
