package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.ArrowButton;
import com.mygdx.game.renderAbleObjects.decorations.EquipButton;
import com.mygdx.game.renderAbleObjects.decorations.InfoButton;
import com.mygdx.game.renderAbleObjects.decorations.Slot;

/**
 * Created by Vali on 10.07.2016.
 */
public class HangarScreen implements Screen {

    OrthographicCamera cam;
    private Array<Slot> itemSlots;
    private Array<EquipButton> equipButtons;
    private Array<InfoButton> infoButtons;
    private Array<Slot> skinSlots;
    private ArrowButton skinArrowUp;
    private ArrowButton skinArrowDown;
    private Array<Slot> particleSlots;
    private ArrowButton particlesArrowUp;
    private ArrowButton particlesArrowDown;
    // private Array<Slot> itemSlots;
    private ParallaxBackgroundManager backgroundManager;
    private ScrollPane scrollPane;
    private int currentId;
    private int currentSkin;
    private int currentParticles;
    public static OrthographicCamera camFixed;
    CameraHelper cameraHelper;

    CameraManager cameraManager;

    public HangarScreen(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.get.setup(cam);
        //add the backgrounds (hex pattern and stars)
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2,false);

        //create skin functionality
        Slot skinSlot1 = new Slot();
        skinSlot1.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin.png");
        Slot skinSlot2 = new Slot();
        skinSlot2.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin2.png");
        Slot skinSlot3 = new Slot();
        skinSlot3.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin3.png");

        skinSlots = new Array<Slot>();
        skinSlots.addAll(skinSlot1, skinSlot2, skinSlot3);
        currentSkin = 0;
        skinArrowUp = new ArrowButton();
        skinArrowUp.initialize(new Vector2(300, MyGdxGame.game.screenHeight - 200), 200, 200, "arrow_up.png", true, true);    //true for up, true for skin
        skinArrowDown = new ArrowButton();
        skinArrowDown.initialize(new Vector2(300, MyGdxGame.game.screenHeight - 800), 200, 200, "arrow_down.png", false, true);
        InputManager.get.Register(skinArrowUp);
        InputManager.get.Register(skinArrowDown);

        //create particle functionality
        Slot particleSlot1 = new Slot();
        particleSlot1.initialize(new Vector2(MyGdxGame.game.screenWidth - 600, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles1.png");

        Slot particleSlot2 = new Slot();
        particleSlot2.initialize(new Vector2(MyGdxGame.game.screenWidth - 600, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles2.png");

        Slot particleSlot3 = new Slot();
        particleSlot3.initialize(new Vector2(MyGdxGame.game.screenWidth - 600, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles3.png");

        particleSlots = new Array<Slot>();
        particleSlots.addAll(particleSlot1, particleSlot2, particleSlot3);
        currentParticles = 0;
        particlesArrowUp = new ArrowButton();
        particlesArrowUp.initialize(new Vector2(MyGdxGame.game.screenWidth - 500, MyGdxGame.game.screenHeight - 200), 200, 200, "arrow_up.png", true, false);    //true for up, false for particles
        particlesArrowDown = new ArrowButton();
        particlesArrowDown.initialize(new Vector2(MyGdxGame.game.screenWidth - 500, MyGdxGame.game.screenHeight - 800), 200, 200, "arrow_down.png", false, false);
        InputManager.get.Register(particlesArrowUp);
        InputManager.get.Register(particlesArrowDown);


        int numberOfItems = 12;
        //add all of the item slots to array
        itemSlots = new Array<Slot>();
        equipButtons = new Array<EquipButton>();
        infoButtons = new Array<InfoButton>();

        int width = MyGdxGame.game.screenWidth - 200;
        int posY = 800;
        for(int i = 0; i < numberOfItems; i++){
            Slot slot = new Slot();
            //intialize item slot
            slot.initialize(new Vector2(100, posY), width, 200, "item_slot.png");
            EquipButton equipButton = new EquipButton();
            equipButton.initialize(new Vector2(width - 200, posY), 250, 200, "equip_button.png", i);
            InputManager.get.Register(equipButton);
            InfoButton infoButton = new InfoButton();
            infoButton.initialize(new Vector2(width - 500, posY), 250, 200, "info_button.png", i);
            InputManager.get.Register(infoButton);
            posY -= 200;
            itemSlots.add(slot);
            equipButtons.add(equipButton);
            infoButtons.add(infoButton);
    }


        currentId = -1;
        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        InputManager.get.Register(cameraHelper);

    }

    @Override
    public void render(float delta){
        MyGdxGame game = MyGdxGame.game;
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.uiBatch.setProjectionMatrix(camFixed.combined);
        game.uiBatch.begin();
        backgroundManager.render(game.uiBatch);
        game.uiBatch.end();
        cameraManager.update(delta);
        game.batch.setProjectionMatrix(cameraManager.getCam().combined);
        game.batch.begin();
        for(Slot slot : itemSlots){
           slot.render(game.batch);
       }
        for(EquipButton button : equipButtons){
            button.render(game.batch);
        }
        for(InfoButton button : infoButtons){
            button.render(game.batch);
        }
        skinArrowDown.render(game.batch);
        skinArrowUp.render(game.batch);
        skinSlots.get(currentSkin).render(game.batch);
        particleSlots.get(currentParticles).render(game.batch);
        particlesArrowUp.render(game.batch);
        particlesArrowDown.render(game.batch);
        game.batch.end();
        camFixed.update();
        update(delta);
    }

    /**
     * update method for hangar screen
     * @param delta
     */
    private void update(float delta){
        InputManager.get.update(delta);
    }

    /**
     * getter for item slots
     * @return
     */
    public Array<Slot> getItemSlots(){
        return itemSlots;
    }

    /**
     * getter for previous id
     * @return
     */
    public int getCurrentId(){
        return currentId;
    }

    /**
     * setter for previous id
     * @param id
     */
    public void setCurrentId(int id){
        this.currentId = id;
    }

    /**
     * getter for skin
     * @return current skin
     */
    public int getCurrentSkin(){
        return currentSkin;
    }

    /**
     * setter for current skin
     * @param skin
     */
    public void setCurrentSkin(int skin){
        this.currentSkin = skin;
    }

    /**
     * getter for the number of activated skins, needed in arrow button class
     * @return number of skins
     */
    public int getNumberSkins(){
        return skinSlots.size;
    }

    /**
     * getter for current particles
     * @return current particles
     */
    public int getCurrentParticles(){
        return currentParticles;
    }

    /**
     * setter for current particles
     * @param particles
     */
    public void setCurrentParticles(int particles){
        this.currentParticles = particles;
    }

    /**
     * getter for the number of activated particles, needed in arrow button class
     * @return number of particles
     */
    public int getNumberParticles(){
        return particleSlots.size;
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
