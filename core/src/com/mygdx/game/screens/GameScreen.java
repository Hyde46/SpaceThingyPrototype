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
import com.mygdx.game.managers.UnitManager;

import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.managers.levels.LevelFactory;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.utils.SpacePhysiX;

public class GameScreen implements Screen {

    final MyGdxGame game;

    OrthographicCamera camera;

    UnitManager uM;
    SpacePhysiX spX;

    //InputManager iM;
    private LevelFactory levelFactory;

    public GameScreen(final MyGdxGame gam) {
        this.game = gam;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);

        game.shapeRenderer.setProjectionMatrix(camera.combined);

        uM = new UnitManager();

        spX = new SpacePhysiX();

        setLevel(0);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        uM.render(game.batch);
        game.font.draw(game.batch, "Prototype v0.0.5", 5 , 30);
        game.batch.end();
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(1, 1, 0, 1);
        Gdx.gl20.glLineWidth(3 / camera.zoom);
        uM.renderHitboxes(game.shapeRenderer);
        game.shapeRenderer.end();

        update(delta);
    }

    public void update(float delta)
    {
        //iM.update(delta);
        spX.update(delta);
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
        spX.initializePhysics(uM.getUnits());
    }

    //just for the prototype !!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void initPrototypeLevel(){
        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        System.out.println("Loading resources...");
        ((SpaceShip)playerShip).initialize(new Vector2(650,550),new Vector2(5,160),null,0,new Vector2(10,10),null,0);
        ((Planet)p1).initialize(new Vector2(200,670),240,36,"planet1.png",1);
        ((Planet)p2).initialize(new Vector2(600,1320),320,50,"planet3.png",2);
        uM.addUnit(playerShip);
        uM.addUnit(p1);
        uM.addUnit(p2);
        System.out.println("Done!");

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

