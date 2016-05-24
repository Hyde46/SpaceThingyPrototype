package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private MyGdxGame game;

    public OverlayOverworldHUD(){

    }

    public void initialize(){
        this.game = MyGdxGame.game;
        this.touchHitbox = new Rectangle(0, 0, game.screenWidth, game.screenHeight);
    }

    public void render(float delta){

        game.batch.begin();
        Texture texOptions = new Texture(Gdx.files.internal(PATH_BUTTON_OPTIONS));
        Sprite spriteOptions = new Sprite(texOptions, texOptions.getWidth(), texOptions.getHeight());

        spriteOptions.setY(0);
        spriteOptions.setX(0);
        spriteOptions.draw(game.batch);

        Texture texExit = new Texture(Gdx.files.internal(PATH_BUTTON_EXIT));
        Sprite spriteExit = new Sprite(texExit, texExit.getWidth(), texExit.getHeight());

        spriteExit.setY(0);
        spriteExit.setX(game.screenWidth - spriteExit.getWidth());
        spriteExit.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void renderHitboxes(ShapeRenderer shapeRenderer){

    }

    /**
     * getter for boolean, if overlay should be seen
     * @return showOverlay
     */
}
