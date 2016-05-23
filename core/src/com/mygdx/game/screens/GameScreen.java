package com.mygdx.game.screens;

/**
 * Created by denis on 5/6/16.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;

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

    //InputManager iM;
    private LevelFactory levelFactory;

    //Prototype only stuff
    private int finishCounter;
    private boolean hasFinishedLevel;
    private boolean hasWonLevel;

    public GameScreen() {
 //       this.game = gam;

        // create the camera and the SpriteBatch
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);

        /* Main and Game have different cams,
        main camera needs to be created in gdxgame and Inputmanger setup there */
        InputManager.setup(camera);

        cM = new CameraManager();
        cH = new CameraHelper();
        cM.setCam(camera);
        cH.setCameraManager(cM);
        InputManager.instance.objectHolder.Register(cH);
        MyGdxGame.game.shapeRenderer.setColor(1, 1, 0, 1);
        uM = new UnitManager();

        spX = new SpacePhysiX();


        setLevel(0);
    }

    @Override
    public void render(float delta) {
        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(33.0f/255.0f, 49.0f/255.0f, 41.0f/255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cM.update(delta);
        game.batch.setProjectionMatrix(cM.getCam().combined);
        game.batch.begin();
        uM.render(game.batch);
        game.batch.end();
        game.uiBatch.begin();
        game.font.draw(game.uiBatch, game.currentVersion, 5 , 30);
        if(hasFinishedLevel) {
            if(hasWonLevel)
                game.font.draw(game.uiBatch, "Finished Level !", 200, 1000);
            else
                game.font.draw(game.uiBatch, "You crashed your ship! Q_Q" , 200 , 1000);

        }
        game.uiBatch.end();

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

        //for now
        levelId = 1;
        Level l = LevelFactory.loadLevel(levelId);
        initPrototypeLevel();

        //TODO also very important:)
        //we dont have any sort of level loading mechanism at the moment.
        //to remove the hardcoding of the level in the gamescreen which is not optimal
        //initLevel();

    }

    //just for the prototype !!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void initPrototypeLevel(){
        uM.resetUnits();
        finishCounter = 500;
        hasFinishedLevel = false;
        hasWonLevel = false;

        //Units init
        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        System.out.println("Loading resources...");
        ((SpaceShip)playerShip).initialize(new Vector2(260,550),new Vector2(5,160),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),240,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(700,1620),320,50,false,"planet2_100x100.png",2,40);
        ((Planet)p3).initialize(new Vector2(-300,1400),240,36,false,"planet1_72x72.png",1,0);
        /*
        ((Planet)p2).initialize(new Vector2(600,1320),240,50,true,"planet2_100x100.png",2,40);
        ((Planet)p1).initialize(new Vector2(200,670),240,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(600,1320),240,50,true,"planet2_100x100.png",2,40);
        ((Planet)p1).initialize(new Vector2(200,670),240,36,false,"planet1_72x72.png",1,0);
        ((Planet)p2).initialize(new Vector2(600,1320),320,50,true,"planet2_100x100.png",2,40);
        */
        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(playerShip);
        spX.initializePhysics(uM.getUnits(),this);
        InputManager.instance.objectHolder.Register(p1);
        InputManager.instance.objectHolder.Register(p2);

        //UI init
        Decoration bg = new BackGround();
        Decoration hex = new BackGround();
        ((BackGround)bg).initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_stars.png");
        ((BackGround)hex).initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_hex.png");
        uM.addDeco(bg);
        uM.addDeco(hex);
        cM.initializeCamera((SpaceShip)playerShip);
        System.out.println("Done!");
    }

    public void finishLevel(boolean b){
        hasFinishedLevel = true;
        hasWonLevel = b;
        if(finishCounter <= 0)
            MyGdxGame.game.setScreen(new MainMenuScreen());
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

