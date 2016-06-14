package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.overworldObjects.Dialog.DialogAvatar;
import com.mygdx.game.overworldObjects.Dialog.DialogManager;
import com.mygdx.game.overworldObjects.Dialog.DialogTextArea;
import com.mygdx.game.overworldObjects.LevelBeacon;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Overlay;
import com.mygdx.game.overworldObjects.OverlayOverworldHUD;
import com.mygdx.game.overworldObjects.Ship;
import com.mygdx.game.renderAbleObjects.decorations.BackGround;
import com.mygdx.game.renderAbleObjects.decorations.ButtonOptions;

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

    OverlayOverworldHUD overlayHUD;

    ButtonOptions boTest;

    private int finishedLevel;

    private DialogManager dialogManager;

    private BackGround backGroundHex;

    private BackGround backGroundStars;

    private ParallaxBackgroundManager backgroundManager;
    public MainMenuScreen(){
//        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
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

        overlayHUD = new OverlayOverworldHUD();
        overlayHUD.initialize();

        boTest = new ButtonOptions();
        boTest.initialize(new Vector2(200,200), 250, 250);

        //set finished level, needs to be changed later...
        finishedLevel = 0;

        //create the dialog manager and initialize the dialogs
        dialogManager = new DialogManager();
        dialogManager.createDialogs();
        //show the dialog
        dialogManager.initializeDialog(finishedLevel);

        //add the backgrounds (hex pattern and stars)
        backGroundHex = new BackGround();
        backGroundHex.initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_hex.png");

        backGroundStars = new BackGround();
        backGroundStars.initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_stars0.png");

        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2);

        //register overlay and dialogbox to InputManager
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
        backgroundManager.render(game.batch);
    //    backGroundHex.render(game.batch);
    //    backGroundStars.render(game.batch);
        game.font.draw(game.batch, game.currentVersion, 5 , 30);
        //render LevelGraph, which in turn renders LevelBeacons
        levelGraph.renderBeacons(game.batch);
        game.batch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        levelGraph.renderEdges(game.shapeRenderer);
        game.shapeRenderer.end();

        //render ship
        ship.render(game.shapeRenderer);
        //render overlay only if it shell be shown
        if(overlay.getShowOverlay()){
            overlay.render(delta);
        }else if(dialogManager.getShowDialog()){
            dialogManager.renderDialog();
        }

        overlayHUD.render(delta);

        boTest.render(game.batch);

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