package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.overworldObjects.Dialog.DialogManager;
import com.mygdx.game.overworldObjects.LevelGraph;
import com.mygdx.game.overworldObjects.Overlay;
import com.mygdx.game.overworldObjects.OverlayOverworldHUD;
import com.mygdx.game.overworldObjects.Ship;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.utils.JukeBox;

/**
 * Created by denis on 5/6/16.
 */
public class MainMenuScreen implements Screen {

//    final MyGdxGame game;

    OrthographicCamera cam;

    public static OrthographicCamera camFixed;

    CameraHelper cameraHelper;

    CameraManager cameraManager;

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

    //ButtonOptions boTest;

    private int finishedLevel;

    private DialogManager dialogManager;

    private ParallaxBackgroundManager backgroundManager;

    public MainMenuScreen(){
        finishedLevel = 0;      //is beginning
        setupScreen(false);
        setupDialogs(true);
        dialogManager.startPreDialog(0);
        cameraHelper.setCameraManager(cameraManager, dialogManager, 2);
    }

    /**
     * second constructor which is called after a level is done (success or not)
     * @param level
     * @param success
     */
    public MainMenuScreen(int level, boolean success){
        finishedLevel = level;
        System.out.println("Finished Level: " + finishedLevel);

        setupScreen(success);
        //depending on success the boolean showDialog will be set to true of false
        setupDialogs(success);
        //show the dialog
        if(success){
            dialogManager.startPostDialog(finishedLevel);
        }
        cameraHelper.setCameraManager(cameraManager, dialogManager, 2);

    }

    /**
     * method to set up everything needed for main menu screen, called in both constructors
     */
    private void setupScreen(boolean hasFinishedLevel){
        JukeBox.startBGM(0);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.get.setup(cam);
        //create LevelGraph object and initialize it (creating beacons etc)
        this.levelGraph = new LevelGraph();
        levelGraph.initializeGraph(finishedLevel);
        if(hasFinishedLevel)
            levelGraph.unlockNewBeacons(finishedLevel);
        //create ship object and initialize it (connected beacon)
        this.ship = new Ship();
        ship.initialize(levelGraph.getCurrentLevel(), new Vector2(40,40),"ship"+DataPers.dataH().getCurrentSkin()+".png");

        pathNavigationManager = new PathNavigationManager();
        pathNavigationManager.initialize(ship, levelGraph);

        overlay = new Overlay();
        overlay.initialize(true);       //true because it should be seen

        //overlayHUD = new OverlayOverworldHUD();
       // overlayHUD.initialize();

        /*
        boTest = new ButtonOptions();
        boTest.initialize(new Vector2(200,200), 250, 250);
        */

        //add the backgrounds (hex pattern and stars)
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2,true);

        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        //cameraManager.initializeCamera(ship,ship.getPosition());
        //register overlay and cameraHelper to InputManager
        InputManager.get.register(overlay);
        InputManager.get.register(cameraHelper);
    }

    /**
     * method to set up everything needed for the dialog, called in constructor if level was successfully finished
     */
    private void setupDialogs(boolean success){
        //create the dialog manager and initialize the dialogs
        dialogManager = new DialogManager();
        dialogManager.createDialogs();
    }

    @Override
    public void render(float delta) {

        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render static (fixed) elements such as background and option buttons
        game.batch.setProjectionMatrix(camFixed.combined);
        game.batch.begin();
        backgroundManager.render(game.batch);
//        overlayHUD.render(game.batch);
       // boTest.render(game.batch);
        game.font.draw(game.batch, game.currentVersion, 10 , 50);


        game.font.draw(game.batch, "nth start " + DataPers.dataP().nthGame, 600 , 50);

        game.batch.end();

        //render other elements, which have a specific position in the world (e.g. beacons)
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        cameraManager.update(delta);
        //render LevelGraph, which in turn renders LevelBeacons
        levelGraph.renderBeacons(game.batch);
        //render infos above beacons
        if(ship.getInOrbit()){
            levelGraph.renderInfos(game.batch);
        }
        //render ship
        ship.render(game.batch);
        game.batch.end();

        MyGdxGame.game.shapeRenderer.setProjectionMatrix(cam.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        levelGraph.renderEdges(game.shapeRenderer);
        game.shapeRenderer.end();


        //render overlay only if it shell be shown
        if(overlay.getShowOverlay() && game.showOverlay){
            overlay.render(delta);
        }else if(dialogManager.getShowDialog()){
            dialogManager.renderDialog();
        }
        camFixed.update();
        update(delta);
    }

    /**
     * update method for main menu screen
     * @param delta
     */
    private void update(float delta){
        JukeBox.update(delta);
        InputManager.get.update(delta);
        //process ship's movement
        ship.update(delta);
    }

    /**
     * getter for the dialogManager, needed in on touch function of level beacon
     * @return dialogManager
     */
    public DialogManager getDialogManager(){
        return dialogManager;
    }


    @Override
    public void dispose()    {
        levelGraph.dispose();
        levelGraph = null;
        ship.dispose();
        ship = null;
        pathNavigationManager = null;
        overlay.dispose();
        overlay = null;
        dialogManager.dispose();
        dialogManager = null;
        backgroundManager.dispose();
        backgroundManager = null;
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