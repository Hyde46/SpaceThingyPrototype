package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Ship;

/**
 * Created by denis on 5/6/16.
 */
public class MainMenuScreen implements Screen {

    final MyGdxGame game;

    OrthographicCamera cam;

    private LevelGraph levelGraph;

    private Ship ship;

    public MainMenuScreen(final MyGdxGame game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);

        //create LevelGraph object and initialize it (creating beacons etc)
        this.levelGraph = new LevelGraph();
        levelGraph.initializeGraph();

        //create ship object and initialize it (connected beacon)
        this.ship = new Ship();
        ship.initialize(levelGraph.getCurrentLevel());
        game.shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
       // game.font.draw(game.batch, "Some Overworld for you Valli :*", 320, 920);
        game.font.draw(game.batch, "Prototype v0.0.4", 5 , 30);
        game.batch.end();
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //render LevelGraph, which in turn renders LevelBeacons
        levelGraph.render(game.shapeRenderer);
        game.shapeRenderer.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
