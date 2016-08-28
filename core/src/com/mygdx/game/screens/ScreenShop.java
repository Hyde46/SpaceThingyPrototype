/*
    Tab zwischen sell und buy funktioniert nur wenn der scrollview an der initial funktion ist !?!?!?
*/

package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputListener;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.GenericElement;
import com.mygdx.game.utils.JukeBox;

import java.util.ArrayList;

/**
 * Created by Henri on 23.05.2016.
 */
public class ScreenShop implements Screen
{
    private final String nameStatic = "objStatic";
    private final String nameDynamic = "objDynamic";

    // render
    private ParallaxBackgroundManager backgroundManager;

    private GenericElement bannerTop;

    private GenericElement tabBuy;
    private GenericElement tabSell;
    private GenericElement tabBack;

    private GenericElement bannerMiddleShop;
    private GenericElement bannerMiddleInv;

    private Array<GenericElement> panels;
//    private Array<ShopImageItem> imgsItem;
    private Array<GenericElement> panelsInfo;
    private Array<GenericElement> panelsBuy;
    private Array<GenericElement> panelsSell;

    //  cam
    public static OrthographicCamera camFixed;
    OrthographicCamera cam;
    CameraHelper cameraHelper;
    CameraManager cameraManager;

    ArrayList<Integer> idsItemPlayer;
    ArrayList<Integer> idsItemShop;
    int creditsPlayer;

    // shop
    private int levelShop;
    private boolean isBuyMode;

    // sizes
    int w = MyGdxGame.game.screenWidth;
    int h = MyGdxGame.game.screenHeight;

    int marginTop = 50;
    int marginTabsH = 25;

    int wBanner = 900;
    int hBanner = 400;

    int wOffsetScreen = (w - wBanner) / 2;

    int yBannerTop = h - marginTop - hBanner;

    int wTab = 225;
    int hTab = 150;

    int yTabs = yBannerTop - marginTabsH - hTab;
    int marginTabsW = 87;

    int wBannerMiddle = wBanner;
    int hBannerMiddle = 200;

    int yBannerMiddle = yTabs - marginTabsH - hBannerMiddle;

    int wPanel = wBanner - 2 * marginTabsH;
    int hPanel = 150;

    int yPanelFirst = yBannerMiddle - 25 - hPanel;

    int marginPanels = 10;

    int offsetWPanels = hPanel + marginPanels;

    int offsetWScreenRed = wOffsetScreen + marginTabsH;

    int wButton = 207;
    int hButton = 100;
    int marginButtonsX = 57;
    int marginButtonsY = 25;

    int offsetButton0 = wOffsetScreen + marginButtonsX; // used for item icon
    int offsetButton1 = wOffsetScreen + 2 * marginButtonsX + wButton;
    int offsetButton2 = wOffsetScreen + 3 * marginButtonsX + 2 * wButton;

    public ScreenShop(int levelShop)
    {
        this.levelShop = levelShop;
        isBuyMode = true;
        JukeBox.startBGM(-1);
        idsItemPlayer = DataPers.dataP().idsItemsPlayer;
        idsItemShop = DataPers.dataS().idsItemsShopOfLevel.get(levelShop);
        creditsPlayer = DataPers.dataP().credits;

        panels = new Array<GenericElement>();
 //       imgsItem = new Array<ShopImageItem>();
        panelsInfo = new Array<GenericElement>();
        panelsBuy = new Array<GenericElement>();
        panelsSell = new Array<GenericElement>();

        setShopStatics();
        buildShop();
    }

    public void setBuyMode(boolean isBuyMode)
    {
        this.isBuyMode = isBuyMode;
        buildShop();
    }

    public void setShopStatics()
    {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        InputManager.get.setup(cam);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);

        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2, true);

        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        InputManager.get.register(nameStatic, cameraHelper);
    }

    public void buyItem(int idItem)
    {
        int price = 20;

        if(creditsPlayer >= price)
        {
            creditsPlayer -= price;
            idsItemPlayer.add(idItem);
            idsItemShop.remove(new Integer(idItem));

            buildShop();
        }
        else
        {
            // no money
        }
    }

    public void sellItem(int idItem)
    {
        int creditsSell = 20;

        creditsPlayer += creditsSell;
        idsItemPlayer.remove(new Integer(idItem));

        idsItemShop.add(idItem);

        buildShop();
    }

    public void buildShop()
    {
        clearDynamicTextures();
        InputManager.get.clearGroup(nameDynamic);

        if(panels != null) panels.clear();
  //      if(imgsItem != null) imgsItem.clear();
        if(panelsInfo != null) panelsInfo.clear();
        if(panelsBuy != null) panelsBuy.clear();
        if(panelsSell != null) panelsSell.clear();

        panels = new Array<GenericElement>();
   //     imgsItem = new Array<ShopImageItem>();
        panelsInfo = new Array<GenericElement>();
        panelsBuy = new Array<GenericElement>();
        panelsSell = new Array<GenericElement>();

        bannerTop = new GenericElement();
        bannerTop.initialize(new Vector2(wOffsetScreen, yBannerTop), 900, 400, "shop-banner-900-400.png");

        tabBuy = new GenericElement();
        tabBuy.initialize(new Vector2(offsetWScreenRed, yTabs), wTab, hTab, "shop-tab-buy-225-150.png");
        tabBuy.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                setBuyMode(true);
                System.out.println("pressed buy tab");
            }
        });
        InputManager.get.register(nameDynamic, tabBuy);

        tabSell = new GenericElement();
        tabSell.initialize(new Vector2(offsetWScreenRed + wTab + marginTabsW, yTabs), wTab, hTab, "shop-tab-sell-225-150.png");
        tabSell.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                setBuyMode(false);
                System.out.println("pressed sell tab");
            }
        });
        InputManager.get.register(nameDynamic, tabSell);

        tabBack = new GenericElement();
        tabBack.initialize(new Vector2(offsetWScreenRed + 2 * wTab + 2 * marginTabsW, yTabs), wTab, hTab, "shop-tab-back-225-150.png");
        tabBack.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                MyGdxGame.game.openScreen(new MainMenuScreen());
            }
        });
        InputManager.get.register(nameDynamic, tabBack);

        if(isBuyMode)
        {
            bannerMiddleShop = new GenericElement();
            bannerMiddleShop.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle, "shop-banner-shop-900-200.png");

            for (int i = 0; i < idsItemShop.size(); i++)
            {
                int posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, idsItemShop.get(i));
            }
        }
        else
        {
            bannerMiddleInv = new GenericElement();
            bannerMiddleInv.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle, "shop-banner-inventory-900-200.png");

            for (int i = 0; i < idsItemPlayer.size(); i++)
            {
                int posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, idsItemPlayer.get(i));
            }
        }

        saveShop();
    }

    private void addSlot(int yOfPanel, int idItem)
    {
        final int idItemTemp = idItem;

        GenericElement panel = new GenericElement();
        panel.initialize(new Vector2(offsetWScreenRed, yOfPanel), wPanel, hPanel, "shop-panel-850-150.png");
        panels.add(panel);

        // here comes the icon

//        ShopImageItem imgItem = new ShopImageItem();
//        imgItem.initialize(new Vector2(offsetButton0, yOfPanel + marginButtonsY), wButton, hButton, idItem);
//        //InputManager.get.register(nameDynamic, imgItem);
//        imgsItem.add(imgItem);

        GenericElement btnInfo = new GenericElement();
        btnInfo.initialize(new Vector2(offsetButton1, yOfPanel + marginButtonsY), wButton, hButton, "shop-panel-info-207-100.png");
        panelsInfo.add(btnInfo);
        btnInfo.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                MyGdxGame.game.openScreen(new ItemScreen(idItemTemp, levelShop));
            }
        });
        InputManager.get.register(nameDynamic, btnInfo);

        if(isBuyMode)
        {
            GenericElement btnBuy = new GenericElement();
            btnBuy.initialize(new Vector2(offsetButton2, yOfPanel + marginButtonsY), wButton, hButton, "shop-panel-buy-207-100.png");
            InputManager.get.register(nameDynamic, btnBuy);
            panelsBuy.add(btnBuy);
            btnBuy.setListener(new InputListener(){
                public void OnTouch(TouchData td){
                    buyItem(idItemTemp);
                }
            });
        }
        else
        {
            GenericElement btnSell = new GenericElement();
            btnSell.initialize(new Vector2(offsetButton2, yOfPanel + marginButtonsY), wButton, hButton, "shop-panel-sell-207-100.png");
            InputManager.get.register(nameDynamic, btnSell);
            panelsSell.add(btnSell);
            btnSell.setListener(new InputListener(){
                public void OnTouch(TouchData td){
                    sellItem(idItemTemp);
                }
            });
        }
    }

    @Override
    public void render(float delta)
    {
        cam.update();
        JukeBox.update(delta);
        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();

        bannerTop.render(game.batch);

        tabBuy.render(game.batch);
        tabSell.render(game.batch);
        tabBack.render(game.batch);

        if(isBuyMode)
        {
            bannerMiddleShop.render(game.batch);

            for (int i = 0; i < idsItemShop.size(); i++)
            {
                panels.get(i).render(game.batch);
                panelsInfo.get(i).render(game.batch);
                panelsBuy.get(i).render(game.batch);
            }
        }
        else
        {
            bannerMiddleInv.render(game.batch);

            for (int i = 0; i < idsItemPlayer.size(); i++)
            {
                panels.get(i).render(game.batch);
                panelsInfo.get(i).render(game.batch);
                panelsSell.get(i).render(game.batch);
            }
        }

        game.batch.end();
    }

    private void saveShop()
    {
        DataPers.dataP().credits = creditsPlayer;
        DataPers.dataP().idsItemsPlayer = idsItemPlayer;
        DataPers.dataS().idsItemsShopOfLevel.set(levelShop, idsItemShop);
        DataPers.saveP();
        DataPers.saveS();
    }

    private void clearDynamicTextures()
    {
        if(bannerMiddleInv != null) bannerMiddleInv.dispose();
        if(bannerMiddleShop != null) bannerMiddleShop.dispose();

        for (int i = 0; i < panels.size; i++)
        {
            panels.get(i).dispose();
            panelsInfo.get(i).dispose();
        }

        for (int i = 0; i < panelsBuy.size; i++) panelsBuy.get(i).dispose();
        for (int i = 0; i < panelsSell.size; i++) panelsSell.get(i).dispose();
    }

    @Override
    public void dispose()
    {
        bannerTop.dispose();
        backgroundManager.dispose();
        clearDynamicTextures();
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


//        int w = MyGdxGame.game.screenWidth;
//        int h = MyGdxGame.game.screenHeight;

//        int wButton = 180;
//
//        int yTabs = (int)(h * 0.65);
//
//
//        int wOffsetButton = wButton / 2;
//
//        bannerTop = new ShopBannerTop();
//        bannerTop.initialize(new Vector2(wOffsetPanel, yBanner), 900, 400, "shop-bannerTop-900-400.png");
//
//        // dieser button bekommt keinen touch ... obwohl gleich initialisiert wie Tab sell ...
//        tabBuy = new ShopTabBuy();
//        tabBuy.initialize(new Vector2(1 * wForth - wOffsetButton, yTabs), 180, 140, "btn-buy-180-140.png", this);
//        InputManager.get.register(nameStatic, tabBuy);
//
//        tabSell = new ShopTabSell();
//        tabSell.initialize(new Vector2(2 * wForth - wOffsetButton, yTabs), 180, 140, "btn-sell-180-140.png", this);
//        InputManager.get.register(nameStatic, tabSell);
