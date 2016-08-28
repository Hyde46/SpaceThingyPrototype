package com.mygdx.game.screens;

/**
 * Created by denis on 5/6/16.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.UnitManager;

import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.managers.levels.LevelBackgroundColor;
import com.mygdx.game.managers.levels.LevelFactory;
import com.mygdx.game.managers.levels.LevelState;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.utils.JukeBox;
import com.mygdx.game.utils.SpacePhysiX;

import java.util.Random;

public class GameScreen implements Screen{

    private Level levelContainer;

    CameraHelper cH;
    public CameraManager cM;

    public static OrthographicCamera camFixed;

    private int finishCounter;
    public static boolean hasFinishedLevel;
    private boolean isShowingFinishScreen;
    private boolean hasWonLevel;
    private boolean isOutOfBounds;
    private float[] levelBGColor;

    private LevelState levelState;

    private int level;

    public GameScreen(int levelToStart)
    {
        System.out.println(DataPers.dataP().credits);
        this.level = levelToStart;
        // create the camera and the SpriteBatch
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Main and Game have different cams,
        main camera needs to be created in gdxgame and Inputmanger setup there */
        InputManager.get.setup(camera);
        cM = new CameraManager();
        cH = new CameraHelper();
        cM.setCam(camera);
        cH.setCameraManager(cM, null, 0);
        InputManager.get.register(cH);

        MyGdxGame.game.shapeRenderer.setColor(1, 1, 0, 1);

        levelState = new LevelState();

        ItemManager.initialize(this);

        DataPers.dataP().nthGame++;
        DataPers.saveP();
        setLevel(levelToStart);
    }

    @Override
    public void render(float delta)
    {
        MyGdxGame game = MyGdxGame.game;
        Gdx.gl.glClearColor(levelBGColor[0],levelBGColor[1],levelBGColor[2], 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(isShowingFinishScreen){
            renderFinishScreen(delta);
            update(delta);
            return;
        }
        //draw parallax background

        game.uiBatch.begin();
        game.uiBatch.setProjectionMatrix(camFixed.combined);
        levelContainer.parallaxBackgroundManager.render(game.uiBatch);
        game.uiBatch.end();
        cM.update(delta);

        //draw units
        game.batch.setProjectionMatrix(cM.getCam().combined);
        game.batch.begin();
        levelContainer.unitManager.render(game.batch);
        game.batch.end();

        //draw ui elments which dont get projected by the camera

        game.uiBatch.begin();
        game.uiBatch.setProjectionMatrix(camFixed.combined);
        game.debugFont.draw(game.uiBatch, game.currentVersion, 5 , 1900);
        game.debugFont.draw(game.uiBatch, "X: "+(int)(getPlayerShip().getPosition().x / 10),5,1850);
        game.debugFont.draw(game.uiBatch, "Y: "+(int)(getPlayerShip().getPosition().y / 10),5,1800);
        game.debugFont.draw(game.uiBatch, "vel: "+(int)(getPlayerShip().getDeltaMovement().len()),5,1750);
        game.debugFont.draw(game.uiBatch, "Currency: "+levelState.getCurrency(), 5, 1700);
        game.debugFont.draw(game.uiBatch, "Hops: "+levelState.getHops(), 5, 1650);
        ItemManager.get.render(game.uiBatch);

        renderFinishedGameState(game);

        game.uiBatch.end();

        camFixed.update();

        update(delta);
    }

    private void renderFinishScreen(float delta){

    }

    private void renderFinishedGameState(MyGdxGame game) {
        if(hasFinishedLevel) {
            if(hasWonLevel) {

                game.font.draw(game.uiBatch, "Finished Level !", 180, 1000);

            }else if(!isOutOfBounds) {

                game.font.draw(game.uiBatch, "You crashed your ship!", 180, 1000);

            }else {

                game.font.draw(game.uiBatch, "Your ship got lost!", 180, 1000);

            }
        }
    }

    public void update(float delta)
    {
        JukeBox.update(delta);
        ItemManager.get.update(delta);

        levelContainer.spacePhysiX.update(delta);
        InputManager.get.update(delta);
        MyGdxGame.game.fpsLimit.delay();

        if(hasFinishedLevel)
            finishCounter--;
    }

    /*
    Call this Method from MainMenuScreen to load the appropriate level
    int levelId     The Id of the level which should be loaded ;)
     */
    public void setLevel(int levelId)
    {
        int currentSkin = prepareLevelFields(levelId);
        levelContainer = LevelFactory.loadLevel(levelId,this);
        getPlayerShip().setSkin(currentSkin);
        ItemManager.get.setItems(ItemManager.convertOrdinalToItemName(DataPers.dataH().getSlot1()),
                ItemManager.convertOrdinalToItemName(DataPers.dataH().getSlot2()));
        JukeBox.initialize();
        JukeBox.startBGM(levelId);
    }

    private int prepareLevelFields(int levelId) {
        //levelContainer.unitManager.resetUnits();
        levelState.resetState();
        isShowingFinishScreen = false;
        levelBGColor = LevelBackgroundColor.getBackGroundColor(levelId);
        int currentSkin = DataPers.dataH().getCurrentSkin();
        finishCounter = 200;
        hasFinishedLevel = false;
        hasWonLevel = false;
        isOutOfBounds = false;
        return currentSkin;
    }

    public void finishLevel(boolean b, boolean isOutOfBounds){
        hasFinishedLevel = true;
        hasWonLevel = b;
        this.isOutOfBounds = isOutOfBounds;
        if(finishCounter <= 0) {
            if(hasWonLevel)
                levelState.safeState();
            levelContainer.unitManager.resetUnits();
            hasFinishedLevel = false;
            hasWonLevel = false;
            levelContainer.parallaxBackgroundManager.dispose();
            InputManager.get.clearAll();
            MyGdxGame.game.openScreen(new MainMenuScreen(level,hasWonLevel));
        }
    }

    public LevelState getLevelState(){
        return levelState;
    }

    //////////////
    //Item Methods
    //////////////
    public void addUnitToManager(Unit u){
        levelContainer.unitManager.addUnit(u);
    }

    public void addDecoToManager(Decoration d){
        levelContainer.unitManager.addDeco(d);
    }
    public SpaceShip getPlayerShip(){
        return levelContainer.unitManager.getPlayerShip();
    }

    public void addPlanet(Vector2 posWorld)
    {
        Planet planetTemp = new Planet();
        planetTemp.initialize(posWorld,320,64,false,"artificial-planet-sprite-128.png",1,(new Random()).nextInt(360),10.0f);
        levelContainer.unitManager.addUnit(planetTemp);
        InputManager.get.register(planetTemp);
    }

    public boolean tryDestroyTarget(Vector2 posWorld)
    {
        boolean hasFound = false;
        Array<Unit> units = levelContainer.unitManager.getUnits();
        for (Unit unit: units)
        {
            if(((ARenderableObject)unit).getCollisionHitbox().contains(posWorld))
            {
                levelContainer.unitManager.deleteUnit(unit);
                InputManager.get.unRegister(unit);
                hasFound = true;
            }
        }

        return hasFound;
    }

    //////////////
    //////////////
    //////////////

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        levelContainer.unitManager.resetUnits();
        ItemManager.get.dispose();
    }

    public void finishLevelImidiate(){
        finishCounter = 1;
    }

    public boolean isLevelFinished(){
        return hasFinishedLevel;
    }
}