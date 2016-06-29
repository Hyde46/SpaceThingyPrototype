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
public class Overlay extends ARenderableObject implements IInputHandler {
    public static final String PATH_TO_LOGO = "logo.png";

    public static final String PATH_TO_CRYPTIC = "cryptic_font.png";

    private boolean showOverlay;

    private MyGdxGame game;

    private Sprite spriteLogo;
    private Sprite spriteCryptic;

    private Texture textureLogo;
    private Texture textureCryptic;
    public Overlay(){

    }

    /**
     * initialize the overlay: hitbox, textures and sprites
     * @param showOverlay
     */
    public void initialize(boolean showOverlay){
        this.game = MyGdxGame.game;
        this.showOverlay = showOverlay;
        this.touchHitbox = new Rectangle(0, 0, game.screenWidth, game.screenHeight);
        textureLogo =  new Texture(Gdx.files.internal(PATH_TO_LOGO));
        spriteLogo = new Sprite(textureLogo, textureLogo.getWidth(), textureLogo.getHeight());
        float logoPosY = game.screenHeight - textureLogo.getHeight() - 100;
        //position it 100 pixels under top
        spriteLogo.setY(logoPosY);
        //center it horizontally
        spriteLogo.setX(game.screenWidth / 2 - textureLogo.getWidth() / 2);
        //add sprite of cryptic text
        textureCryptic = new Texture(Gdx.files.internal(PATH_TO_CRYPTIC));
        spriteCryptic = new Sprite(textureCryptic, textureCryptic.getWidth(), textureCryptic.getHeight());
        //position it a little under the logo
        spriteCryptic.setY(logoPosY - 500);
        //and centered horizontally
        spriteCryptic.setX(game.screenWidth / 2 - textureCryptic.getWidth() / 2);
    }


    public void render(float delta){
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(1, 1, 1, 0.7f);
        game.shapeRenderer.rect(0, 0, game.screenWidth, game.screenHeight);
        game.shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        //render logo and cryptic text
        game.batch.begin();
        spriteLogo.draw(game.batch);
     //   spriteCryptic.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void renderHitboxes(ShapeRenderer shapeRenderer){

    }

    /**
     * set the boolean if it should be seen to false on tab
     * @param td
     */
    @Override
    public void OnTouch(TouchData td) {
        showOverlay = false;
        game.showOverlay = false;
    }

    @Override
    public void OnRelease(TouchData td) {

    }

    @Override
    public void OnDrag(TouchData td) {

    }

    @Override
    public void OnHold(TouchData td) {
        showOverlay = false;
        game.showOverlay = false;
    }

    @Override
    public void OnSwipe(TouchData td) {

    }

    /**
     * getter for boolean, if overlay should be seen
     * @return showOverlay
     */
    public boolean getShowOverlay(){
        return showOverlay;
    }


}
