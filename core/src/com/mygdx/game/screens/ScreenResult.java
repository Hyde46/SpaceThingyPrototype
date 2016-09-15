package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputListener;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.Level1.ItemPickerRadius;
import com.mygdx.game.Items.Level2.ItemPickTarget;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.managers.levels.LevelState;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.GenericElement;
import com.mygdx.game.utils.JukeBox;

/**
 * Created by ilost on 31.08.2016.
 */

public class ScreenResult implements Screen
{
    //  cam
    public static OrthographicCamera camFixed;
    OrthographicCamera cam;
    //CameraHelper cameraHelper;
    CameraManager cameraManager;

    // render
    private ParallaxBackgroundManager backgroundManager;
    private GenericElement infoResult;

    private GenericElement btnBack;

    int w = MyGdxGame.game.screenWidth;
    int h = MyGdxGame.game.screenHeight;

    int wFrame = 900;
    int hFrame = (int)(h * 0.8);

    int wInfoResult = 900;
    int hInfoResult = 200;

    int marginVert = 50;

    int marginTextBegin = 50;

    int offsetFrameW = ((w-wFrame) / 2);
    int offsetFrameH = ((h-hFrame) / 2);

    int offsetText0 = offsetFrameW + marginTextBegin;
    int offsetText1 = offsetText0 + marginVert + ((wFrame - marginVert) / 2);

    int hMiddle = hFrame - 2*hInfoResult;
    int hMiddleAvail = (int)(hMiddle * 0.5);

    int offsetHMiddleAvail = offsetFrameH + hInfoResult + (hMiddle - hMiddleAvail) / 2;

    private int getOffsetHElementMiddle(int line, int hSpace, int nElement, int offsetTextSize)
    {
        int partSpace = hSpace / nElement;
        int offsetText = (partSpace / 2) - offsetTextSize;

        return line * partSpace + offsetText;
    }

    private boolean result;
    private int level;
    private String name;
    private Array<Item> itemsFound;
    private int currency;
    private int steps;

    private LevelState levelState;

    public ScreenResult(final LevelState levelState)
    {
        setBase();
        this.levelState = levelState;
        result = true;
        level = levelState.getCurrentLevel();
        name = levelState.getLevelName();
        currency = levelState.getCurrency();
        steps = levelState.getHops();

        itemsFound = new Array<Item>();
        itemsFound.add(new ItemPickerRadius(1,1));
        itemsFound.add(new ItemPickTarget(1,1));
        itemsFound.add(new ItemPickerRadius(1,1));

        infoResult = new GenericElement();
        infoResult.initialize(new Vector2(offsetFrameW, offsetFrameH + hFrame - hInfoResult), wInfoResult, hInfoResult, "shop-banner-shop-900-200.png");

        btnBack = new GenericElement();
        btnBack.initialize(new Vector2(offsetFrameW, offsetFrameH), wInfoResult, hInfoResult, "shop-banner-shop-900-200.png");
        btnBack.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                MyGdxGame.game.openScreen(new MainMenuScreen(levelState.getCurrentLevel(),levelState.getHasWon()));
            }
        });
        InputManager.get.register(btnBack);
    }

    public void setBase()
    {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        InputManager.get.setup(cam);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);

        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2, true);

        cameraManager = new CameraManager();
        //cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        //cameraHelper.setCameraManager(cameraManager, null, 4);
        //InputManager.get.register(cameraHelper);
    }

    @Override
    public void render(float delta)
    {
        cam.update();
        JukeBox.update(delta);

        MyGdxGame game = MyGdxGame.game;
        game.font.setColor(Color.WHITE);
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();

        backgroundManager.render(game.batch);

        infoResult.render(game.batch);
        btnBack.render(game.batch);

        game.font.draw(game.batch, "Level:", offsetText0 , offsetHMiddleAvail + getOffsetHElementMiddle(2, hMiddleAvail, 3, 5));
        game.font.draw(game.batch, name, offsetText1 , offsetHMiddleAvail + getOffsetHElementMiddle(2, hMiddleAvail, 3, 5));

        game.font.draw(game.batch, "Scraps:", offsetText0 , offsetHMiddleAvail + getOffsetHElementMiddle(1, hMiddleAvail, 3, 5));
        game.font.draw(game.batch, ""+currency, offsetText1 , offsetHMiddleAvail + getOffsetHElementMiddle(1, hMiddleAvail, 3, 5));

        game.font.draw(game.batch, "Steps:", offsetText0 , offsetHMiddleAvail + getOffsetHElementMiddle(0, hMiddleAvail, 3, 5));
        game.font.draw(game.batch, steps + "", offsetText1 , offsetHMiddleAvail + getOffsetHElementMiddle(0, hMiddleAvail, 3, 5));

        game.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        infoResult.dispose();
        btnBack.dispose();
    }
}