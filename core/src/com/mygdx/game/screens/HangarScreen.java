package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.ArrowButton;
import com.mygdx.game.renderAbleObjects.decorations.EquipButton;
import com.mygdx.game.renderAbleObjects.decorations.InfoButton;

import com.mygdx.game.renderAbleObjects.decorations.Slot;
import com.mygdx.game.renderAbleObjects.decorations.SlotIcon;
import com.mygdx.game.renderAbleObjects.decorations.uiItemDisplay.ItemDisplayImage;

import java.util.ArrayList;

/**
 * Created by Vali on 10.07.2016.
 */
public class HangarScreen implements Screen {

    OrthographicCamera cam;
    private Array<Slot> itemSlots;
    private Array<ItemDisplayImage> itemIcons;
    private Array<EquipButton> equipButtons;
    private Array<InfoButton> infoButtons;
    private Array<Slot> skinSlots;
    private ArrowButton skinArrowUp;
    private ArrowButton skinArrowDown;
    private Array<Slot> particleSlots;
    private ArrowButton particlesArrowUp;
    private ArrowButton particlesArrowDown;
    private ItemDisplayImage popUp;
    private boolean showPopUp;
    private SlotIcon slot1;
    private SlotIcon slot2;
    // private Array<Slot> itemSlots;
    private ParallaxBackgroundManager backgroundManager;
    private int currentOrderId;
    private int currentItemId;
    private int currentSkin;
    private int currentParticles;
    public static OrthographicCamera camFixed;
    CameraHelper cameraHelper;

    CameraManager cameraManager;

    public HangarScreen(){
        System.out.println("Slot 1: " + DataPers.dataH().getSlot1());
        System.out.println("Slot 2: " + DataPers.dataH().getSlot2());


        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.get.setup(cam);
        //add the backgrounds (hex pattern and stars)
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2, false);

        //create pop up functionality
        popUp = new ItemDisplayImage();
        //we want to center it on screen
        popUp.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 400, MyGdxGame.game.screenHeight / 2 - 400), 800, 800, "hangar_pop_up.png");
        slot1 = new SlotIcon();
        slot1.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 350, MyGdxGame.game.screenHeight / 2 - 150), 300, 300, "slot1_icon.png");
        slot2 = new SlotIcon();
        slot2.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 + 50, MyGdxGame.game.screenHeight / 2 - 150), 300, 300, "slot2_icon.png");
        InputManager.get.register(slot1);
        InputManager.get.register(slot2);

        showPopUp = false;
        //create skin functionality
        Slot skinSlot1 = new Slot();
        skinSlot1.initialize(new Vector2(100, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin.png");

        Slot skinSlot2 = new Slot();
        skinSlot2.initialize(new Vector2(100, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin2.png");

        Slot skinSlot3 = new Slot();
        skinSlot3.initialize(new Vector2(100, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin3.png");

        skinSlots = new Array<Slot>();
        skinSlots.addAll(skinSlot1, skinSlot2, skinSlot3);
        currentSkin = 0;
        skinArrowUp = new ArrowButton();
        skinArrowUp.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 200), 200, 200, "arrow_up.png", true, true);    //true for up, true for skin
        skinArrowDown = new ArrowButton();
        skinArrowDown.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 800), 200, 200, "arrow_down.png", false, true);
        InputManager.get.register(skinArrowUp);
        InputManager.get.register(skinArrowDown);

        //create particle functionality
        Slot particleSlot1 = new Slot();
        particleSlot1.initialize(new Vector2(MyGdxGame.game.screenWidth - 500, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles1.png");

        Slot particleSlot2 = new Slot();
        particleSlot2.initialize(new Vector2(MyGdxGame.game.screenWidth - 500, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles2.png");

        Slot particleSlot3 = new Slot();
        particleSlot3.initialize(new Vector2(MyGdxGame.game.screenWidth - 500, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_particles3.png");

        particleSlots = new Array<Slot>();
        particleSlots.addAll(particleSlot1, particleSlot2, particleSlot3);
        currentParticles = 0;
        particlesArrowUp = new ArrowButton();
        particlesArrowUp.initialize(new Vector2(MyGdxGame.game.screenWidth - 400, MyGdxGame.game.screenHeight - 200), 200, 200, "arrow_up.png", true, false);    //true for up, false for particles
        particlesArrowDown = new ArrowButton();
        particlesArrowDown.initialize(new Vector2(MyGdxGame.game.screenWidth - 400, MyGdxGame.game.screenHeight - 800), 200, 200, "arrow_down.png", false, false);
        InputManager.get.register(particlesArrowUp);
        InputManager.get.register(particlesArrowDown);


        //add all of the item slots to array
        itemSlots = new Array<Slot>();
        equipButtons = new Array<EquipButton>();
        infoButtons = new Array<InfoButton>();
        itemIcons = new Array<ItemDisplayImage>();
        ArrayList<Integer> itemsInPossession = DataPers.dataP().idsItemsPlayer;

        int width = MyGdxGame.game.screenWidth - 200;
        int posY = 800;
        for(int i = 0; i < itemsInPossession.size(); i++){
            System.out.println("Item id: " + itemsInPossession.get(i));
            Slot slot = new Slot();
            //initialize item slot
            slot.initialize(new Vector2(100, posY), width, 200, "item_slot.png");
            ItemDisplayImage itemIcon = new ItemDisplayImage();
            itemIcon.initialize(new Vector2(120, posY), 200, 200, "item_icon.png");
            EquipButton equipButton = new EquipButton();
            equipButton.initialize(new Vector2(width - 200, posY), 250, 200, "equip_button.png", i, itemsInPossession.get(i));
            InputManager.get.register(equipButton);
            InfoButton infoButton = new InfoButton();
            infoButton.initialize(new Vector2(width - 500, posY), 250, 200, "info_button.png", i, itemsInPossession.get(i), 4);
            InputManager.get.register(infoButton);
            posY -= 200;
            itemSlots.add(slot);
            itemIcons.add(itemIcon);
            equipButtons.add(equipButton);
            infoButtons.add(infoButton);
        }

        currentOrderId = -1;
        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        InputManager.get.register(cameraHelper);


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
        for(ItemDisplayImage icon : itemIcons){
            icon.render(game.batch);
        }
        skinArrowDown.render(game.batch);
        skinArrowUp.render(game.batch);
        skinSlots.get(currentSkin).render(game.batch);
        particleSlots.get(currentParticles).render(game.batch);
        particlesArrowUp.render(game.batch);
        particlesArrowDown.render(game.batch);

        game.batch.end();
        game.uiBatch.setProjectionMatrix(camFixed.combined);
        game.uiBatch.begin();
        if(showPopUp){
            popUp.render(game.uiBatch);
            slot1.render(game.uiBatch);
            slot2.render(game.uiBatch);
        }
        game.uiBatch.end();
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


    public void saveSettings(){
        DataPers.dataH().setSlot1(slot1.getItemId());
        DataPers.dataH().setSlot2(slot2.getItemId());
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
    public int getCurrentOrderId(){
        return currentOrderId;
    }

    /**
     * setter for previous id
     * @param id
     */
    public void setCurrentOrderId(int id){
        this.currentOrderId = id;
    }

    /**
     * getter for the item id that was just chosen
     * @return item id
     */
    public int getCurrentItemId() {
        return currentItemId;
    }

    /**
     * setter for the item id that was just chosen
     * @param currentItemId
     */
    public void setCurrentItemId(int currentItemId) {
        this.currentItemId = currentItemId;
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

    /**
     * setter for boolean, if pop up should be shown -> used in equip button class
     * @param show
     */
    public void setShowPopUp(boolean show){
        this.showPopUp = show;
    }
    @Override
    public void dispose()    {
        itemSlots.clear();
        itemIcons.clear();
        equipButtons.clear();
        infoButtons.clear();
        skinSlots.clear();
        skinArrowUp = null;
        skinArrowDown = null;
        particleSlots.clear();
        particlesArrowDown = null;
        particlesArrowUp = null;
        popUp = null;
        slot1 = null;
        slot2 = null;
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