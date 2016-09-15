package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.uiItemDisplay.ItemDisplayImage;
import com.mygdx.game.renderAbleObjects.decorations.uiItemDisplay.ItemDisplayButtonReturn;

/**
 * Created by Vali on 22.07.2016.
 */
public class ItemScreen implements Screen
{
    OrthographicCamera cam;
    public static OrthographicCamera camFixed;
    CameraHelper cameraHelper;
    CameraManager cameraManager;
    private ParallaxBackgroundManager backgroundManager;

    // render
    private ItemDisplayImage itemImage;
    private ItemDisplayImage itemDescription;
    private ItemDisplayButtonReturn returnButton;

    private int idItem;

    private int levelidreturn;

    public ItemScreen(int itemId, int idReturn, int levelidreturn)
    {
        this.levelidreturn = levelidreturn;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        InputManager.setup(cam);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);

        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2,false);

        this.idItem = itemId;
        //icon of the item
        itemImage = new ItemDisplayImage();
        //center it horizontally
        itemImage.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 400, MyGdxGame.game.screenHeight - 1000), 800, 800, "item_image.png");
        //description of the item
        itemDescription = new ItemDisplayImage();
        itemDescription.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 400, MyGdxGame.game.screenHeight - 1600), 800, 500, "item_description.png");

        returnButton = new ItemDisplayButtonReturn();
        returnButton.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 200, 100), 400, 200, "return_button.png", idReturn,levelidreturn);
        InputManager.get.register(returnButton);

        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        //InputManager.get.register(cameraHelper);
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

        game.uiBatch.begin();
            game.debugFont.draw(game.uiBatch, "IM ITEM of ID: " + idItem, 30, 30);
        game.uiBatch.end();
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