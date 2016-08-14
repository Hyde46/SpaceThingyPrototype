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
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.Slot;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopButtonBuy;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopButtonInfo;
import com.mygdx.game.renderAbleObjects.decorations.uiShop.ShopImageBanner;
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
    private ShopImageBanner banner;

    private Array<Slot> panels;
    private Array<ShopImageItem> imgsItem;
    private Array<ShopButtonInfo> slotsInfo;
    private Array<ShopButtonBuy> slotsBuy;
    private Array<ShopButtonSell> slotsSell;
    private ParallaxBackgroundManager backgroundManager;
    private ShopTabSell tabSell;
    private ShopTabBuy tabBuy;

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

    int wForth = w / 4;

    int wPanel = 900;
    int hPanel = 200;
    int wOffsetPanel = (w - wPanel) / 2;
    int wForthPanel = wPanel / 4;

    int wButton = 180;
    int hButton = 140;
    int wOffsetButton = (wButton / 2);
    int hOffsetButton = (hPanel - hButton) / 2;

    int hBanner = 400;
    int yBanner = (int)(h * 0.75);


    public void setShopStatics()
    {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
        InputManager.get.setup(cam);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);

        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2, true);

        int w = MyGdxGame.game.screenWidth;
        int h = MyGdxGame.game.screenHeight;

        int wButton = 180;

        int yTabs = (int)(h * 0.65);


        int wOffsetButton = wButton / 2;

        banner = new ShopImageBanner();
        banner.initialize(new Vector2(wOffsetPanel, yBanner), 900, 400, "shop-banner-900-400.png");

        tabBuy = new ShopTabBuy();
        tabBuy.initialize(new Vector2(1 * wForth - wOffsetButton, yTabs), 180, 140, "btn-buy-180-140.png", this);
        InputManager.get.register(nameStatic, tabBuy);

        tabSell = new ShopTabSell();
        tabSell.initialize(new Vector2(2 * wForth - wOffsetButton, yTabs), 180, 140, "btn-sell-180-140.png", this);
        InputManager.get.register(nameStatic, tabSell);

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

        int w = MyGdxGame.game.screenWidth;
        int h = MyGdxGame.game.screenHeight;

        int yPicture = (int)(h * 0.95);
        int hPicture = (int)(h * 0.2);

        int xPanel = (w / 2) - 450;
        int wPanel =  (int)(w * 0.9);

        int width = 900;
        int posY = 800;

        if(isBuyMode)
        {
            for (int i = 0; i < idsItemShop.size(); i++)
            {
                posY = 800 - 200 * i;

                addSlot(posY, idsItemShop.get(i));
            }
        }
        else
        {
            for (int i = 0; i < idsItemPlayer.size(); i++)
            {
                posY = 800 - 200 * i;

                addSlot(posY, idsItemPlayer.get(i));
            }
        }

        saveShop();
    }

    public void addSlot(int yOfPanel, int idItem)
    {
        Slot slot = new Slot();
        slot.initialize(new Vector2(wOffsetPanel, yOfPanel), wPanel, 200, "item-panel-900-200.png");
        panels.add(slot);

        ShopImageItem imgItem = new ShopImageItem();
        imgItem.initialize(new Vector2(wOffsetPanel + 1 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "shop-btn-image.png", idItem);
        //InputManager.get.register(nameDynamic, imgItem);
        imgsItem.add(imgItem);

        ShopButtonInfo btnInfo = new ShopButtonInfo();
        btnInfo.initialize(new Vector2(wOffsetPanel + 2 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-info-180-140.png", idItem, levelShop);
        InputManager.get.register(nameDynamic, btnInfo);
        slotsInfo.add(btnInfo);

        if(isBuyMode)
        {
            ShopButtonBuy btnBuy = new ShopButtonBuy();
            btnBuy.initialize(new Vector2(wOffsetPanel + 3 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-buy-180-140.png", levelShop, idItem, this);
            InputManager.get.register(nameDynamic, btnBuy);
            slotsBuy.add(btnBuy);
        }
        else
        {
            ShopButtonSell btnSell = new ShopButtonSell();
            btnSell.initialize(new Vector2(wOffsetPanel + 3 * wForthPanel - wOffsetButton, yOfPanel + hOffsetButton), wButton, hButton, "btn-sell-180-140.png", levelShop, idItem, this);
            InputManager.get.register(nameDynamic, btnSell);
            slotsSell.add(btnSell);
        }
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

        for(Slot slot : panels){
            slot.render(game.batch);

            int xText = (int)slot.getPosition().x + wForthPanel - wOffsetButton;
            int yText = (int)slot.getPosition().y + (int)(0.5 * hButton) + hOffsetButton;
            MyGdxGame.game.font.draw(MyGdxGame.game.batch, "Item " + 1, xText, yText);
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

        game.uiBatch.begin();
            banner.render(game.uiBatch);
            tabBuy.render(game.uiBatch);
            tabSell.render(game.uiBatch);
        game.uiBatch.end();
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