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
import com.mygdx.game.InputManager.IInputListener;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.Slot;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopBannerMiddleInventory;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopBannerMiddleShop;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopButtonBuy;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopButtonInfo;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopBannerTop;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopTabBack;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopTabBuy;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopButtonSell;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopImageItem;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopTabSell;

import java.util.ArrayList;

/**
 * Created by Vali on 23.05.2016.
 */
public class ScreenShop implements Screen
{
    private final String nameStatic = "objStatic";
    private final String nameDynamic = "objDynamic";

    // render
    private ParallaxBackgroundManager backgroundManager;

    private ShopBannerTop banner;

    private ShopTabBuy tabBuy;
    private ShopTabSell tabSell;
    private ShopTabBack tabBack;

    private ShopBannerMiddleShop bannerMiddleShop;
    private ShopBannerMiddleInventory bannerMiddleInv;

    private Array<Slot> panels;
    private Array<ShopImageItem> imgsItem;
    private Array<ShopButtonInfo> slotsInfo;
    private Array<ShopButtonBuy> slotsBuy;
    private Array<ShopButtonSell> slotsSell;



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
    private boolean isBuyMode = true;

    public ScreenShop(int levelShop)
    {
        this.levelShop = levelShop;

        idsItemPlayer = DataPers.dataP().idsItemsPlayer;
        idsItemShop = DataPers.dataS().idsItemsShopOfLevel.get(levelShop);
        creditsPlayer = DataPers.dataP().credits;

        panels = new Array<Slot>();
        imgsItem = new Array<ShopImageItem>();
        slotsInfo = new Array<ShopButtonInfo>();
        slotsBuy = new Array<ShopButtonBuy>();
        slotsSell = new Array<ShopButtonSell>();

        setShopStatics();
        buildShop();
    }

    public void toggleBuyMode()
    {
        isBuyMode = !isBuyMode;
        buildShop();
    }

    public void saveShop()
    {
        DataPers.dataP().credits = creditsPlayer;
        DataPers.dataP().idsItemsPlayer = idsItemPlayer;
        DataPers.dataS().idsItemsShopOfLevel.set(levelShop, idsItemShop);
    }

    int w = MyGdxGame.game.screenWidth;
    int h = MyGdxGame.game.screenHeight;

    int marginTop = 50;
//    int marginTabs = 25;
    int marginTabsH = 25;

    int wBanner = 900;
    int hBanner = 400;

    int wOffsetScreen = (w - wBanner) / 2;

    int yBannerTop = h - marginTop - hBanner;

    int wTab = 225;
    int hTab = 150;

    int yTabs = yBannerTop - marginTabsH - hTab;
    int marginTabsW = 112;

    int wBannerMiddle = wBanner;
    int hBannerMiddle = 200;

    int yBannerMiddle = yTabs - marginTabsH - hBannerMiddle;

    int wPanel = wBanner;
    int hPanel = 150;

    int yPanelFirst = yBannerMiddle - 25 - hPanel;

    int marginPanels = 10;

    int offsetWPanels = hPanel + marginPanels;


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
        InputManager.get.clearGroup(nameDynamic);

        if(panels != null) panels.clear();
        if(imgsItem != null) imgsItem.clear();
        if(slotsInfo != null) slotsInfo.clear();
        if(slotsBuy != null) slotsBuy.clear();
        if(slotsSell != null) slotsSell.clear();

        panels = new Array<Slot>();
        imgsItem = new Array<ShopImageItem>();
        slotsInfo = new Array<ShopButtonInfo>();
        slotsBuy = new Array<ShopButtonBuy>();
        slotsSell = new Array<ShopButtonSell>();

        banner = new ShopBannerTop();
        banner.initialize(new Vector2(wOffsetScreen, yBannerTop), 900, 400);
        InputManager.get.register(nameDynamic, tabSell);
        banner.registerListener(new IInputListener()
        {
            public void OnPress(){
                System.out.println("pressed with listener");
            }
        });

        tabBuy = new ShopTabBuy();
        tabBuy.initialize(new Vector2(wOffsetScreen, yTabs), wTab, hTab, this);
        InputManager.get.register(nameDynamic, tabBuy);

        tabSell = new ShopTabSell();
        tabSell.initialize(new Vector2(wOffsetScreen + wTab + marginTabsW, yTabs), wTab, hTab, this);
        InputManager.get.register(nameDynamic, tabSell);

        tabBack = new ShopTabBack();
        tabBack.initialize(new Vector2(wOffsetScreen + 2 * wTab + 2 * marginTabsW, yTabs), wTab, hTab);
        InputManager.get.register(nameDynamic, tabBack);

        if(isBuyMode)
        {
            bannerMiddleShop = new ShopBannerMiddleShop();
            bannerMiddleShop.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle);
            InputManager.get.register(nameDynamic, bannerMiddleShop);

            for (int i = 0; i < idsItemShop.size(); i++)
            {
                int posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, idsItemShop.get(i));
            }
        }
        else
        {
            bannerMiddleInv = new ShopBannerMiddleInventory();
            bannerMiddleInv.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle);
            InputManager.get.register(nameDynamic, bannerMiddleInv);

            for (int i = 0; i < idsItemPlayer.size(); i++)
            {
                int posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, idsItemPlayer.get(i));
            }
        }

        saveShop();
    }

    public void addSlot(int yOfPanel, int idItem)
    {
        Slot slot = new Slot();
        slot.initialize(new Vector2(wOffsetScreen, yOfPanel), wPanel, 150, "shop-panel-900-150.png");
        panels.add(slot);

//        ShopImageItem imgItem = new ShopImageItem();
//        imgItem.initialize(new Vector2(wOffsetPanel + 1 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "shop-btn-image.png", idItem);
//        //InputManager.get.register(nameDynamic, imgItem);
//        imgsItem.add(imgItem);
//
//        ShopButtonInfo btnInfo = new ShopButtonInfo();
//        btnInfo.initialize(new Vector2(wOffsetPanel + 2 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-info-180-140.png", idItem, levelShop);
//        InputManager.get.register(nameDynamic, btnInfo);
//        slotsInfo.add(btnInfo);
//
//        if(isBuyMode)
//        {
//            ShopButtonBuy btnBuy = new ShopButtonBuy();
//            btnBuy.initialize(new Vector2(wOffsetPanel + 3 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-buy-180-140.png", levelShop, idItem, this);
//            InputManager.get.register(nameDynamic, btnBuy);
//            slotsBuy.add(btnBuy);
//        }
//        else
//        {
//            ShopButtonSell btnSell = new ShopButtonSell();
//            btnSell.initialize(new Vector2(wOffsetPanel + 3 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-sell-180-140.png", levelShop, idItem, this);
//            InputManager.get.register(nameDynamic, btnSell);
//            slotsSell.add(btnSell);
//        }
    }

    @Override
    public void render(float delta)
    {
        cam.update();

        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();

        banner.render(game.batch);

        tabBuy.render(game.batch);
        tabSell.render(game.batch);
        tabBack.render(game.batch);

        if(isBuyMode)
        {
            bannerMiddleShop.render(game.batch);
        }
        else
        {
            bannerMiddleInv.render(game.batch);
        }


        for(Slot slot : panels){
            slot.render(game.batch);

//            int xText = (int)slot.getPosition().x + wForthPanel - wOffsetButton;
//            int yText = (int)slot.getPosition().y + (int)(0.5 * hButton) + hOffsetButton;
//            MyGdxGame.game.font.draw(MyGdxGame.game.batch, "Item " + 1, xText, yText);
        }

        for(ShopImageItem img : imgsItem)
        {
            img.render(game.batch);
        }

        for(ShopButtonInfo button : slotsInfo)
        {
            button.render(game.batch);
        }

        if(isBuyMode)
        {
            for(ShopButtonBuy button : slotsBuy)
            {
                button.render(game.batch);
            }
        }
        else
        {
            for(ShopButtonSell button : slotsSell)
            {
                button.render(game.batch);
            }
        }
        game.batch.end();
    }

    @Override
    public void dispose() {}
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
//        banner = new ShopBannerTop();
//        banner.initialize(new Vector2(wOffsetPanel, yBanner), 900, 400, "shop-banner-900-400.png");
//
//        // dieser button bekommt keinen touch ... obwohl gleich initialisiert wie Tab sell ...
//        tabBuy = new ShopTabBuy();
//        tabBuy.initialize(new Vector2(1 * wForth - wOffsetButton, yTabs), 180, 140, "btn-buy-180-140.png", this);
//        InputManager.get.register(nameStatic, tabBuy);
//
//        tabSell = new ShopTabSell();
//        tabSell.initialize(new Vector2(2 * wForth - wOffsetButton, yTabs), 180, 140, "btn-sell-180-140.png", this);
//        InputManager.get.register(nameStatic, tabSell);
