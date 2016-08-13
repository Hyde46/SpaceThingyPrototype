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
import com.mygdx.game.renderAbleObjects.decorations.EquipButton;
import com.mygdx.game.renderAbleObjects.decorations.InfoButton;
import com.mygdx.game.renderAbleObjects.decorations.Slot;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ButtonShopBuy;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ButtonShopInfo;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ButtonShopSell;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.TabBuy;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.TabSell;

import java.util.ArrayList;

/**
 * Created by Vali on 23.05.2016.
 */
public class ScreenShop implements Screen
{
    public boolean isBuyMode() {
        return isBuyMode;
    }

    public void setBuyMode(boolean buyMode)
    {
        isBuyMode = buyMode;
    }

    private boolean isBuyMode = true;
    private int levelShop;

    OrthographicCamera cam;

    // things that need rendering
    private Array<Slot> slotsPanel;
    private Array<ButtonShopInfo> slotsInfo;
    private Array<ButtonShopBuy> slotsBuy;
    private Array<ButtonShopSell> slotsSell;

    private ParallaxBackgroundManager backgroundManager;

  //  private ScrollPane scrollPane;
   // private int currentId;
  //  private int currentSkin;
  //  private int currentParticles;
    public static OrthographicCamera camFixed;
    CameraHelper cameraHelper;
    CameraManager cameraManager;

    public ScreenShop(int levelShop)
    {
        this.levelShop = levelShop;
        buildShop();
    }

    //create skin functionality
//        Slot skinSlot1 = new Slot();
//        skinSlot1.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin.png");
//        Slot skinSlot2 = new Slot();
//        skinSlot2.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin2.png");
//        Slot skinSlot3 = new Slot();
//        skinSlot3.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin3.png");
/*
        skinSlots = new Array<Slot>();
        skinSlots.addAll(skinSlot1, skinSlot2, skinSlot3);
        currentSkin = 0;
        skinArrowUp = new ArrowButton();
        skinArrowUp.initialize(new Vector2(300, MyGdxGame.game.screenHeight - 200), 200, 200, "arrow_up.png", true, true);    //true for up, true for skin
        skinArrowDown = new ArrowButton();
        skinArrowDown.initialize(new Vector2(300, MyGdxGame.game.screenHeight - 800), 200, 200, "arrow_down.png", false, true);
        InputManager.get.register(skinArrowUp);
        InputManager.get.register(skinArrowDown);

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
        InputManager.get.register(particlesArrowUp);
        InputManager.get.register(particlesArrowDown);
*/


//        ModelItemsShop shop = new ModelItemsShop();
//        shop.addItem(new SpeedBooser(0, 0, ItemManager.get));
//        shop.addItem(new ArtificialPlanet(0, 0, ItemManager.get, null));
//        shop.addItem(new Break(0, 0, ItemManager.get));
//
//        ModelItemsShopManager.get().AddShop(nameShopCurrent, shop);

    private TabBuy tabBuy;
    private TabSell tabSell;

    public void buildShop()
    {
        String nameShopCurrent = "Level 1 Shop";

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080, 1920);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.get.setup(cam);
        //add the backgrounds (hex pattern and stars)
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2, true);

        int w = MyGdxGame.game.screenWidth;
        int h = MyGdxGame.game.screenHeight;

        int yPicture = (int)(h * 0.95);
        int hPicture = (int)(h * 0.2);
        int yTabs = (int)(h * 0.65);

        int xPanel = (w / 2) - 450;
        int wPanel =  (int)(w * 0.9);

        int width = 900;
        int posY = 800;

        tabBuy = new TabBuy();
        tabBuy.initialize(new Vector2(150, yTabs), 180, 140, "btn-buy-180-140.png", this);
        InputManager.get.register(tabBuy);

        tabSell = new TabSell();
        tabSell.initialize(new Vector2(400, yTabs), 180, 140, "btn-sell-180-140.png", this);
        InputManager.get.register(tabSell);

        slotsPanel = new Array<Slot>();
        slotsInfo = new Array<ButtonShopInfo>();
        slotsBuy = new Array<ButtonShopBuy>();
        slotsSell = new Array<ButtonShopSell>();

        ArrayList<Integer> idsItemsAvailable =  DataPers.dataS().idsItemsAvailable.get(levelShop);

        int numberOfItems = idsItemsAvailable.size();

        for(int i = 0; i < numberOfItems; i++)
        {
            posY = 800 - 200*i;

            Slot slot = new Slot();
            slot.initialize(new Vector2(xPanel, posY), wPanel, 200, "item-slot-900-200.png");
            slotsPanel.add(slot);

            ButtonShopInfo btnInfo = new ButtonShopInfo();
            btnInfo.initialize(new Vector2(w - 550, posY + (int)(0.5 * (200 - 140))), 180, 140, "btn-info-180-140.png", idsItemsAvailable.get(i), levelShop);
            InputManager.get.register(btnInfo);
            slotsInfo.add(btnInfo);

            if(isBuyMode)
            {
                ButtonShopBuy btnBuy = new ButtonShopBuy();
                btnBuy.initialize(new Vector2(w - 350, posY + (int) (0.5 * (200 - 140))), 180, 140, "btn-buy-180-140.png", levelShop, idsItemsAvailable.get(i), this);
                InputManager.get.register(btnBuy);
                slotsBuy.add(btnBuy);
            }
            else
            {
                ButtonShopSell btnSell = new ButtonShopSell();
                btnSell.initialize(new Vector2(w - 350, posY + (int) (0.5 * (200 - 140))), 180, 140, "btn-sell-180-140.png", levelShop, idsItemsAvailable.get(i), this);
                InputManager.get.register(btnSell);
                slotsSell.add(btnSell);
            }
        }

        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        InputManager.get.register(cameraHelper);
    }

    @Override
    public void render(float delta) {
        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MyGdxGame.game.batch.setProjectionMatrix(cam.combined);
        //draw string indicating, that this is a shop (to be removed later)
        MyGdxGame.game.batch.begin();
        //MyGdxGame.game.font.draw(MyGdxGame.game.batch, "Prototype v0.0.4", 5 , 30);
        //MyGdxGame.game.font.draw(MyGdxGame.game.batch, "This is a shop!", 320, 920);

        for(Slot slot : slotsPanel){
            slot.render(game.batch);
        }

        for(ButtonShopInfo button : slotsInfo)
        {
            button.render(game.batch);
        }

        if(isBuyMode)
        {
            for(ButtonShopBuy button : slotsBuy)
            {
                button.render(game.batch);
            }
        }
        else
        {
            for(ButtonShopSell button : slotsSell)
            {
                button.render(game.batch);
            }
        }
        MyGdxGame.game.batch.end();

        game.uiBatch.begin();
        tabBuy.render(game.uiBatch);
        tabSell.render(game.uiBatch);
        game.uiBatch.end();

        cam.update();
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
    public void resize(int x, int y)
    {

    }
    @Override
    public void pause()    {

    }
}