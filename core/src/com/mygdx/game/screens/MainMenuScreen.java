package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.overworldObjects.LevelBeacon;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Overlay;
import com.mygdx.game.overworldObjects.Ship;

/**
 * Created by denis on 5/6/16.
 */
public class MainMenuScreen implements Screen {

//    final MyGdxGame game;

    OrthographicCamera cam;

    public LevelGraph getLevelGraph() {
        return levelGraph;
    }

    public Ship getShip() {
        return ship;
    }

    public PathNavigationManager getPathNavigationManager() {
        return pathNavigationManager;
    }

    private LevelGraph levelGraph;

    private Ship ship;

    private PathNavigationManager pathNavigationManager;

    private Overlay overlay;

    public MainMenuScreen(){
//        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, MyGdxGame.game.screenWidth, MyGdxGame.game.screenHeight);
        InputManager.setup(cam);
        //create LevelGraph object and initialize it (creating beacons etc)
        this.levelGraph = new LevelGraph();
        levelGraph.initializeGraph();

        //create ship object and initialize it (connected beacon)
        this.ship = new Ship();
        ship.initialize(levelGraph.getCurrentLevel());

        pathNavigationManager = new PathNavigationManager(ship, levelGraph);
        MyGdxGame.game.shapeRenderer.setProjectionMatrix(cam.combined);

        overlay = new Overlay();
        overlay.initialize(true);       //true because it should be seen
        //register overlay to InputManager
        InputManager.instance.objectHolder.Register(overlay);
    }

    @Override
    public void render(float delta) {


        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.font.draw(game.batch, game.currentVersion, 5 , 30);
        game.batch.end();

        //render LevelGraph, which in turn renders LevelBeacons
        levelGraph.render(game.shapeRenderer);
        //render ship
        ship.render(game.shapeRenderer);
        //render overlay only if it shell be shown
        if(overlay.getShowOverlay()){
            overlay.render(delta);
        }


        //process ship's movement
        ship.update(delta);
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
