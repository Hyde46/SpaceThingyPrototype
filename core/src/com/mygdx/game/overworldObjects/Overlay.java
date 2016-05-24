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

    public Overlay(){

    }

    public void initialize(boolean showOverlay){
        this.game = MyGdxGame.game;
        this.showOverlay = showOverlay;
        this.touchHitbox = new Rectangle(0, 0, game.screenWidth, game.screenHeight);
    }


    public void render(float delta){
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(new Color(1, 1, 1, 0.7f));
        game.shapeRenderer.rect(0, 0, game.screenWidth, game.screenHeight);
        game.shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        //render logo
        game.batch.begin();
        Texture texture = new Texture(Gdx.files.internal(PATH_TO_LOGO));
        Sprite sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        //position it 100 pixels under top
        float logoPosY = game.screenHeight - texture.getHeight() - 100;
        sprite.setY(logoPosY);
        //center it horizontally
        sprite.setX(game.screenWidth / 2 - texture.getWidth() / 2);
        sprite.draw(game.batch);

        //add sprite of cryptic text
        Texture textureCryptic = new Texture(Gdx.files.internal(PATH_TO_CRYPTIC));
        Sprite spriteCryptic = new Sprite(textureCryptic, textureCryptic.getWidth(), textureCryptic.getHeight());
        //position it a little under the logo
        spriteCryptic.setY(logoPosY - 500);
        //and centered horizontally
        spriteCryptic.setX(game.screenWidth / 2 - textureCryptic.getWidth() / 2);
        spriteCryptic.draw(game.batch);
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
