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
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.UnitManager;

import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.utils.SpacePhysiX;

public class GameScreen implements Screen {

    final MyGdxGame game;

    OrthographicCamera camera;

    UnitManager uM;
    SpacePhysiX spX;

    //temporär
    SpaceShip player;
    Vector3 touchPos;

    public GameScreen(final MyGdxGame gam) {
        this.game = gam;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);

        game.shapeRenderer.setProjectionMatrix(camera.combined);

        uM = new UnitManager();

        Planet p = new Planet(new Vector2(400,500),new Vector2(50,50),1,250,15);
        Planet p2 = new Planet(new Vector2(760,1500),new Vector2(50,50),1,300,25);
        player = new SpaceShip(new Vector2(p.getPosition().x+p.getOrbitRadius(),p.getPosition().y), new Vector2(10,10),
                                        p.getPlanetId(), 250, p.getPosition());
        uM.addUnit(p);
        uM.addUnit(p2);
        uM.addUnit(player);

        spX = new SpacePhysiX(uM.getUnits(),player.getUnitID());

        touchPos = new Vector3();

    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        uM.render(game.batch);
        game.font.draw(game.batch, "Prototype v0.0.3", 5 , 30);
        game.batch.end();
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(1, 1, 0, 1);
        Gdx.gl20.glLineWidth(3 / camera.zoom);
        uM.renderHitboxes(game.shapeRenderer);
        game.shapeRenderer.end();
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
        update(delta);
    }

    public void update(float delta)
    {
        handleInput(delta);
        spX.update(delta);
    }

    private void handleInput(float delta){
        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            player.launch();

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

