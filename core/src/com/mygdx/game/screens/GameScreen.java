package com.mygdx.game.screens;

/**
 * Created by denis on 5/6/16.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;

import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.managers.levels.LevelFactory;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.BackGround;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.utils.SpacePhysiX;

public class GameScreen implements Screen{

//    final MyGdxGame game;

    CameraManager cM;
    CameraHelper cH;

    UnitManager uM;
    SpacePhysiX spX;

    ParallaxBackgroundManager pbM;

    //InputManager iM;
    private LevelFactory levelFactory;

    //Prototype only stuff
    private int finishCounter;
    private boolean hasFinishedLevel;
    private boolean hasWonLevel;
    private boolean isOutOfBounds;

    public GameScreen(int levelToStart) {
        //       this.game = gam;

        // create the camera and the SpriteBatch
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Main and Game have different cams,
        main camera needs to be created in gdxgame and Inputmanger setup there */
        InputManager.setup(camera);

        cM = new CameraManager();
        cH = new CameraHelper();
        pbM = new ParallaxBackgroundManager();
        cM.setCam(camera);
        cH.setCameraManager(cM);
        InputManager.instance.objectHolder.Register(cH);
        MyGdxGame.game.shapeRenderer.setColor(1, 1, 0, 1);
        uM = new UnitManager();

        spX = new SpacePhysiX();


        setLevel(levelToStart);
    }

    @Override
    public void render(float delta) {
        MyGdxGame game = MyGdxGame.game;
        Gdx.gl.glClearColor(33.0f/255.0f, 49.0f/255.0f, 41.0f/255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.uiBatch.begin();
        pbM.render(game.uiBatch);
        game.uiBatch.end();

        cM.update(delta);

        //draw parallax background
        game.batch.setProjectionMatrix(cM.getCam().combined);
        game.batch.begin();
        uM.render(game.batch);
        game.batch.end();


        //draw ui elments which dont get projected by the camera
        game.uiBatch.begin();
        game.font.draw(game.uiBatch, game.currentVersion, 5 , 30);

        if(hasFinishedLevel) {
            if(hasWonLevel)
                game.font.draw(game.uiBatch, "Finished Level !", 200, 1000);
            else if(!isOutOfBounds)
                game.font.draw(game.uiBatch, "You crashed your ship! Q_Q" , 200 , 1000);
            else
                game.font.draw(game.uiBatch, "Your ship got lost! Q_Q", 200, 1000);

        }
        game.uiBatch.end();

        //draw units on top of rest
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setProjectionMatrix(cM.getCam().combined);
        Gdx.gl20.glLineWidth(3 / cM.getCam().zoom);
        //uM.renderHitboxes(game.shapeRenderer);
        game.shapeRenderer.end();

        update(delta);
    }

    public void update(float delta)
    {
        spX.update(delta);
        InputManager.instance.update(delta);
        MyGdxGame.game.fpsLimit.delay();


        //temporary
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

    //just for the prototype !!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
        System.out.println("Loading resources...");
        ((SpaceShip)playerShip).initialize(new Vector2(295,300),new Vector2(5,230),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),240,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40);
        ((Planet)p3).initialize(new Vector2(950,900),320,36,false,"planet1_72x72.png",1,30);
        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90);
        ((Planet)p5).initialize(new Vector2(650,2530),240,36,false,"planet1_72x72.png",1,120);
        ((Planet)p6).initialize(new Vector2(-10,2800),240,50,true,"planet2_100x100.png",2,10);

        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        uM.addUnit(playerShip);
        spX.initializePhysics(uM.getUnits(),this);
        InputManager.instance.objectHolder.Register(p1);
        InputManager.instance.objectHolder.Register(p2);
        InputManager.instance.objectHolder.Register(p3);
        InputManager.instance.objectHolder.Register(p4);
        InputManager.instance.objectHolder.Register(p5);
        InputManager.instance.objectHolder.Register(p6);

        cM.initializeCamera((SpaceShip)playerShip);
        spX.initWorldBounds(new Rectangle(-700,-100,4000,6000));

        pbM.setLayers(2);
        cM.addPBM(pbM);

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
        ((SpaceShip)playerShip).initialize(new Vector2(320,50),new Vector2(5,280),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),320,50,false,"planet2_100x100.png",1,0);
        ((Planet)p2).initialize(new Vector2(1000,850),320,50,false,"planet2_100x100.png",2,20);
        ((Planet)p3).initialize(new Vector2(0,1500),320,36,false,"planet1_72x72.png",1,30);
        ((Planet)p4).initialize(new Vector2(1100,2150),320,50,false,"planet2_100x100.png",2,40);
        ((Planet)p5).initialize(new Vector2(-300,2400),240,36,false,"planet1_72x72.png",1,120);
        ((Planet)p6).initialize(new Vector2(-10,3000),240,50,true,"planet2_100x100.png",2,10);
        /*
        ((Planet)p1).initialize(new Vector2(200,670),240,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(600,1320),320,50,true,"planet2_100x100.png",2,40);
        */
        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        uM.addUnit(playerShip);
        spX.initializePhysics(uM.getUnits(),this);
        InputManager.instance.objectHolder.Register(p1);
        InputManager.instance.objectHolder.Register(p2);
        InputManager.instance.objectHolder.Register(p3);
        InputManager.instance.objectHolder.Register(p4);
        InputManager.instance.objectHolder.Register(p5);
        InputManager.instance.objectHolder.Register(p6);

        //UI init
        cM.initializeCamera((SpaceShip)playerShip);
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

            InputManager.instance.objectHolder.Clear();
            MyGdxGame.game.setScreen(new MainMenuScreen());

        }
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

