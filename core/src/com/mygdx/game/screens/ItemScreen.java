package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.renderAbleObjects.decorations.ItemImage;
import com.mygdx.game.renderAbleObjects.decorations.ToHangarButton;

/**
 * Created by Vali on 22.07.2016.
 */
public class ItemScreen implements Screen  {
    OrthographicCamera cam;
    public static OrthographicCamera camFixed;
    private ParallaxBackgroundManager backgroundManager;
    private ItemImage itemImage;
    private ItemImage itemDescription;
    private ToHangarButton returnButton;
    private int itemId;


    public ItemScreen(int itemId){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.setup(cam);
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2,false);
        this.itemId = itemId;
        //icon of the item
        itemImage = new ItemImage();
        //center it horizontally
        itemImage.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 400, MyGdxGame.game.screenHeight - 1000), 800, 800, "item_image.png");
        //description of the item
        itemDescription = new ItemImage();
        itemDescription.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 400, MyGdxGame.game.screenHeight - 1600), 800, 500, "item_description.png");
        returnButton = new ToHangarButton();
        returnButton.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 200, 100), 400, 200, "return_button.png");
        InputManager.get.register(returnButton);

    }

    @Override
    public void render(float delta){
        MyGdxGame game = MyGdxGame.game;
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.uiBatch.setProjectionMatrix(camFixed.combined);
        game.uiBatch.begin();
        backgroundManager.render(game.uiBatch);
        game.uiBatch.end();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        itemImage.render(game.batch);
        itemDescription.render(game.batch);
        returnButton.render(game.batch);
        game.batch.end();
    }

    @Override
    public void dispose()    {

    }
    @Override
    public void show()    {

    }
    @Override
    public void hide()    {

    }
    @Override
    public void resume()    {

    }
    @Override
    public void resize(int x, int y)    {

    }
    @Override
    public void pause()    {

    }

}
