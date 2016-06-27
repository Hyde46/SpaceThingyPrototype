package com.mygdx.game.screens;

/**
 * Created by denis on 5/6/16.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.managers.UnitManager;

import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.managers.levels.LevelFactory;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.utils.SpacePhysiX;

public class GameScreen implements Screen{

    CameraManager cM;
    CameraHelper cH;

    UnitManager uM;
    SpacePhysiX spX;

    ItemManager itemMan;

    ParallaxBackgroundManager pbM;

    private LevelFactory levelFactory;

    //Prototype only stuff
    private int finishCounter;
    public static boolean hasFinishedLevel;
    private boolean hasWonLevel;
    private boolean isOutOfBounds;
    private float[] levelBGColor;

    private int level;

    public GameScreen(int levelToStart) {
        this.level = levelToStart;
        MyGdxGame.game.font.setColor(Color.WHITE);
        // create the camera and the SpriteBatch
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Main and Game have different cams,
        main camera needs to be created in gdxgame and Inputmanger setup there */
        InputManager.get.setup(camera);

        cM = new CameraManager();
        cH = new CameraHelper();
        pbM = new ParallaxBackgroundManager();
        cM.setCam(camera);
        cH.setCameraManager(cM, null);
        InputManager.get.Register(cH);

        MyGdxGame.game.shapeRenderer.setColor(1, 1, 0, 1);

        uM = new UnitManager();

        spX = new SpacePhysiX();

        itemMan = new ItemManager();
        setLevel(levelToStart);
        itemMan.initialize(this);
    }

    @Override
    public void render(float delta) {

        MyGdxGame game = MyGdxGame.game;
        //Gdx.gl.glClearColor(33.0f/255.0f, 49.0f/255.0f, 41.0f/255.0f, 1);  // Blue/green ish background
        Gdx.gl.glClearColor(levelBGColor[0],levelBGColor[1],levelBGColor[2], 1);  // red ish background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw parallax background
        game.uiBatch.begin();
        pbM.render(game.uiBatch);
        game.uiBatch.end();

        cM.update(delta);

        //draw units
        game.batch.setProjectionMatrix(cM.getCam().combined);
        game.batch.begin();
        uM.render(game.batch);
        game.batch.end();


        //draw ui elments which dont get projected by the camera
        game.uiBatch.begin();
        game.debugFont.draw(game.uiBatch, game.currentVersion, 5 , 1900);
        game.debugFont.draw(game.uiBatch, "X: "+(int)(getPlayerShip().getPosition().x / 10),5,1850);
        game.debugFont.draw(game.uiBatch, "Y: "+(int)(getPlayerShip().getPosition().y / 10),5,1800);
        game.debugFont.draw(game.uiBatch, "vel: "+(int)(getPlayerShip().getDeltaMovement().len()),5,1750);
        itemMan.render(game.uiBatch);

        renderFinishedGameState(game);

        game.uiBatch.end();

        //draw hitboxes
        /*
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setProjectionMatrix(cM.getCam().combined);
        Gdx.gl20.glLineWidth(3 / cM.getCam().zoom);
        uM.renderHitboxes(game.shapeRenderer);
        game.shapeRenderer.end();
        */
        update(delta);
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
        itemMan.update(delta);

        spX.update(delta);
        InputManager.get.update(delta);
        MyGdxGame.game.fpsLimit.delay();

        if(hasFinishedLevel)
            finishCounter--;
    }

    /*
    Call this Method from MainMenuScreen to load the appropriate level
    int levelId     The Id of the level which should be loaded ;)
     */
    public void setLevel(int levelId){

        // Level l = LevelFactory.loadLevel(levelId);
        //for now
        switch(levelId) {
            case 1:
                initPrototypeLevel();
                break;
            case 2:
                initPrototypeLevelTwo();
                break;
            default:
                initPrototypeLevel();
        }
    }

    private void initPrototypeLevel(){
        uM.resetUnits();
        finishCounter = 300;
        hasFinishedLevel = false;
        hasWonLevel = false;
        isOutOfBounds = false;
        //Units init
        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
        Unit p7 = new Planet();
        Unit p8 = new Planet();
        Unit p9 = new Planet();
        Unit p10 = new Planet();
        System.out.println("Loading resources...");
        ((SpaceShip)playerShip).initialize(new Vector2(295,300),new Vector2(5,280),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40);
        ((Planet)p3).initialize(new Vector2(950,900),320,36,false,"planet1_72x72.png",1,30);
        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90);
        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120);
        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet2_100x100.png",2,10);
        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet2_100x100.png",2,10);

        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0);
        ((Planet)p7).connectToPlanet((Planet)p6);
        ((Planet)p7).setRotationSpeed(20.0f,1);

        ((Planet)p9).initialize(new Vector2(-200,3800),190,18,false,"moon2_36x36.png",1,0);
        ((Planet)p9).connectToPlanet((Planet)p8);
        ((Planet)p9).setRotationSpeed(35.0f,1);

        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0);
        ((Planet)p10).connectToPlanet((Planet)p2);
        ((Planet)p10).setRotationSpeed(15.0f,-1);

        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        uM.addUnit(p7);
        uM.addUnit(p8);
        uM.addUnit(p9);
        uM.addUnit(p10);
        uM.addUnit(playerShip);
        spX.initializePhysics(uM.getUnits(),this);
        InputManager.get.Register(p1);
        InputManager.get.Register(p2);
        InputManager.get.Register(p3);
        InputManager.get.Register(p4);
        InputManager.get.Register(p5);
        InputManager.get.Register(p6);
        InputManager.get.Register(p7);
        InputManager.get.Register(p8);
        InputManager.get.Register(p9);
        InputManager.get.Register(p10);

        cM.initializeCamera((SpaceShip)playerShip);
        spX.initWorldBounds(new Rectangle(-700,-100,4000,6000));

        pbM.setLayers(2);
        cM.addPBM(pbM);


        levelBGColor = new float[3];
        levelBGColor[0] = 63.0f/255.0f;
        levelBGColor[1] = 31.0f/255.0f;
        levelBGColor[2] = 39.0f/255.0f;
        itemMan.addItem(1,0);

        System.out.println("Done!");
    }
    private void initPrototypeLevelTwo(){
        uM.resetUnits();
        finishCounter = 300;
        hasFinishedLevel = false;
        hasWonLevel = false;
        isOutOfBounds = false;
        //Units init
        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
        System.out.println("Loading resources...");
        ((SpaceShip)playerShip).initialize(new Vector2(320,300),new Vector2(5,350),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),320,50,false,"planet2_100x100.png",1,0);
        ((Planet)p2).initialize(new Vector2(700,850),320,50,false,"planet2_100x100.png",2,20);
        ((Planet)p3).initialize(new Vector2(-600,1500),320,36,false,"planet1_72x72.png",1,30);
        ((Planet)p4).initialize(new Vector2(1100,2150),320,50,false,"planet2_100x100.png",2,40);
        ((Planet)p5).initialize(new Vector2(-300,2400),240,36,false,"planet1_72x72.png",1,120);
        ((Planet)p6).initialize(new Vector2(200,3300),240,50,true,"planet2_100x100.png",2,10);

        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        uM.addUnit(playerShip);
        spX.initializePhysics(uM.getUnits(),this);
        InputManager.get.Register(p1);
        InputManager.get.Register(p2);
        InputManager.get.Register(p3);
        InputManager.get.Register(p4);
        InputManager.get.Register(p5);
        InputManager.get.Register(p6);

        cM.initializeCamera((SpaceShip)playerShip);
        spX.initWorldBounds(new Rectangle(-700,-100,4000,6000));

        pbM.setLayers(2);
        cM.addPBM(pbM);

        levelBGColor = new float[3];
        levelBGColor[0] = 33.0f/255.0f;
        levelBGColor[1] = 49.0f/255.0f;
        levelBGColor[2] = 41.0f/255.0f;
        System.out.println("Done!");
    }

    public void finishLevel(boolean b, boolean isOutOfBounds){
        hasFinishedLevel = true;
        hasWonLevel = b;
        this.isOutOfBounds = isOutOfBounds;
        if(finishCounter <= 0) {
            uM.resetUnits();
            hasFinishedLevel = false;
            hasWonLevel = false;

            InputManager.get.Clear();
            MyGdxGame.game.setScreen(new MainMenuScreen(level,hasWonLevel));

        }
    }

    public SpaceShip getPlayerShip(){
        return uM.getPlayerShip();
    }

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
    }

}

