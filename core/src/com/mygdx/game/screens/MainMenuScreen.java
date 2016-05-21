package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.overworldObjects.LevelBeacon;
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

    private PathNavigationManager pathNavigationManager;

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

        pathNavigationManager = new PathNavigationManager(ship, levelGraph);
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
        game.font.draw(game.batch, game.currentVersion, 5 , 30);
        game.batch.end();
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //render LevelGraph, which in turn renders LevelBeacons
        levelGraph.render(game.shapeRenderer);
        ship.render(game.shapeRenderer);
        game.shapeRenderer.end();
        //process ship's movement
        ship.update(delta);


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            for(LevelBeacon levelBeacon : levelGraph.getLevelBeaconArray()){
                //check if touch was inside the level beacon
                if(levelBeacon.getHitBox().contains(touchPos.x, touchPos.y)){
                    //check if touched level is current level and if ship is still in orbit
                    if(levelBeacon.getLevelId() == levelGraph.getCurrentLevel().getLevelId() && ship.getInOrbit()){
                        game.setScreen(new GameScreen(game));
                        dispose();
                    }else{  //touched level is different from current level
                        if(!ship.getTravelsRoute()){      //only call navigate function, if the ship is not already on route
                            //tell PathNavigationManager to navigate to this level
                            pathNavigationManager.navigateToBeacon(levelBeacon);
                        }
                    }
                }
            }
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
