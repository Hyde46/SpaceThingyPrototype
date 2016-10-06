/*
    Tab zwischen sell und buy funktioniert nur wenn der scrollview an der initial funktion ist !?!?!?
*/

package com.mygdx.game.screens.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputListener;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.GenericElement;
import com.mygdx.game.screens.ItemScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;
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
    private Array<GenericElement> panelsImage;
    private Array<GenericElement> panelsInfo;
    private Array<GenericElement> panelsBuy;
    private Array<GenericElement> panelsSell;

    //  cam
    public static OrthographicCamera camFixed;
    OrthographicCamera cam;
    CameraHelper cameraHelper;
    CameraManager cameraManager;

    // sizes
    int w = MyGdxGame.game.screenWidth;
    int h = MyGdxGame.game.screenHeight;

    int marginTop = 50;
    int marginTabsH = 25;

    int wBanner = 900;
    int hBanner = 400;

    int wOffsetScreen = (w - wBanner) / 2;

    int yBannerTop = h - marginTop - hBanner;

    int wTab = 250;
    int hTab = 200;

    int yTabs = yBannerTop - marginTabsH - hTab;
    int marginTabsW = 87;

    int wBannerMiddle = wBanner;
    int hBannerMiddle = 200;

    int yBannerMiddle = yTabs - marginTabsH - hBannerMiddle;

    int wPanel = 880;
    int hPanel = 200;

    int yPanelFirst = yBannerMiddle - 25 - hPanel;

    int marginPanels = 10;

    int offsetWPanels = hPanel + marginPanels;

    int offsetWScreenRed = wOffsetScreen + marginTabsH;

    int wButton = 250;
    int hButton = 200;
    int marginButtonsX = 57;
    int marginButtonsY = 0;

    int offsetButton0 = wOffsetScreen + marginButtonsX; // used for item icon
    int offsetButton1 = MyGdxGame.game.screenWidth - 700;
    int offsetButton2 = MyGdxGame.game.screenWidth - 400;

    private ShopLogic shopLogic;

    public ScreenShop(int levelShop, int levelId, boolean isBuyMove)
    {
        shopLogic = new ShopLogic(levelShop, levelId);
        shopLogic.setBuyMode(isBuyMove);
        shopLogic.loadDataShop();

        panels = new Array<GenericElement>();
        panelsImage = new Array<GenericElement>();
        panelsInfo = new Array<GenericElement>();
        panelsBuy = new Array<GenericElement>();
        panelsSell = new Array<GenericElement>();

        setShopStatics();
        drawDynamicElements();

        JukeBox.startBGM(-1);
    }

    public void setBuyMode(boolean isBuyMode)
    {
        shopLogic.setBuyMode(isBuyMode);
        drawDynamicElements();
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

    public void drawDynamicElements()
    {
        clearDynamicTextures();
        InputManager.get.clearGroup(nameDynamic);

        if(panels != null) panels.clear();
        if(panelsImage != null) panelsImage.clear();
        if(panelsInfo != null) panelsInfo.clear();
        if(panelsBuy != null) panelsBuy.clear();
        if(panelsSell != null) panelsSell.clear();

        panels = new Array<GenericElement>();
        panelsImage = new Array<GenericElement>();
        panelsInfo = new Array<GenericElement>();
        panelsBuy = new Array<GenericElement>();
        panelsSell = new Array<GenericElement>();


        bannerTop = new GenericElement();
        bannerTop.initialize(new Vector2(wOffsetScreen, yBannerTop), 900, 400, "shop-banner-900-400.png");

        tabBuy = new GenericElement();
        tabBuy.initialize(new Vector2(offsetWScreenRed, yTabs), wTab, hTab, "buy_button.png");
        tabBuy.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                setBuyMode(true);
            }
        });
        InputManager.get.register(nameDynamic, tabBuy);

        tabSell = new GenericElement();
        tabSell.initialize(new Vector2(offsetWScreenRed + wTab + marginTabsW, yTabs), wTab, hTab, "sell_button.png");
        tabSell.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                setBuyMode(false);
            }
        });
        InputManager.get.register(nameDynamic, tabSell);


        int posPanel = 0;

        if(shopLogic.isBuyMode())
        {
            bannerMiddleShop = new GenericElement();
            bannerMiddleShop.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle, "shop-banner-shop-900-200.png");
            for (int i = 0; i < shopLogic.getIdsItemShop().size(); i++)
            {
                posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, shopLogic.getIdsItemShop().get(i));
            }
        }
        else
        {
            bannerMiddleInv = new GenericElement();
            bannerMiddleInv.initialize(new Vector2(wOffsetScreen, yBannerMiddle), wBannerMiddle, hBannerMiddle, "shop-banner-inventory-900-200.png");

            for (int i = 0; i < shopLogic.getIdsItemPlayer().size(); i++)
            {
                posPanel = yPanelFirst - (i * offsetWPanels);
                addSlot(posPanel, shopLogic.getIdsItemPlayer().get(i));
            }
        }

        tabBack = new GenericElement();
        tabBack.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 200, posPanel - 300), 400, hButton, "return_button.png");
        tabBack.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                MyGdxGame.game.openScreen(new MainMenuScreen(shopLogic.getLevelId(),true));
            }
        });
        InputManager.get.register(nameDynamic, tabBack);
        shopLogic.saveDataShop();
    }

    private void addSlot(int yOfPanel, int idItem)
    {
        //int idSlot
        final int idItemTemp = idItem;

        GenericElement panel = new GenericElement();
        panel.initialize(new Vector2(offsetWScreenRed, yOfPanel), wPanel, hPanel, "item_slot.png");
        panels.add(panel);

        // here comes the icon
        GenericElement imgItem = new GenericElement();
        imgItem.initialize(new Vector2(offsetButton0, yOfPanel + marginButtonsY), 200, 200, ItemManager.getItemTexturePath(idItem));
        panelsImage.add(imgItem);

        GenericElement btnInfo = new GenericElement();
        btnInfo.initialize(new Vector2(offsetButton1, yOfPanel + marginButtonsY), wButton, hButton, "info_button.png");
        panelsInfo.add(btnInfo);
        btnInfo.setListener(new InputListener(){
            public void OnTouch(TouchData td){
                MyGdxGame.game.openScreen(new ItemScreen(idItemTemp, shopLogic.getLevelShop(),shopLogic.getLevelId(), shopLogic.isBuyMode()));
            }
        });
        InputManager.get.register(nameDynamic, btnInfo);

        if(shopLogic.isBuyMode())
        {
            GenericElement btnBuy = new GenericElement();
            btnBuy.initialize(new Vector2(offsetButton2, yOfPanel + marginButtonsY), wButton, hButton, "buy_button.png");
            InputManager.get.register(nameDynamic, btnBuy);
            panelsBuy.add(btnBuy);
            btnBuy.setListener(new InputListener(){
                public void OnTouch(TouchData td){
                shopLogic.buyItem(idItemTemp);
                drawDynamicElements();
                }
            });
        }
        else
        {
            GenericElement btnSell = new GenericElement();
            btnSell.initialize(new Vector2(offsetButton2, yOfPanel + marginButtonsY), wButton, hButton, "sell_button.png");
            InputManager.get.register(nameDynamic, btnSell);
            panelsSell.add(btnSell);
            btnSell.setListener(new InputListener(){
                public void OnTouch(TouchData td){
                shopLogic.sellItem(idItemTemp);
                drawDynamicElements();
                }
            });
        }
    }

    @Override
    public void render(float delta)
    {
        cam.update();

        MyGdxGame game = MyGdxGame.game;

        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.uiBatch.begin();

        backgroundManager.render(game.uiBatch);

        game.uiBatch.end();

        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();

        bannerTop.render(game.batch);

        tabBuy.render(game.batch);
        tabSell.render(game.batch);
        tabBack.render(game.batch);

        if(shopLogic.isBuyMode())
        {
            bannerMiddleShop.render(game.batch);

            for (int i = 0; i < shopLogic.getIdsItemShop().size(); i++)
            {
                panels.get(i).render(game.batch);
                panelsInfo.get(i).render(game.batch);
                panelsBuy.get(i).render(game.batch);
                panelsImage.get(i).render(game.batch);
            }
        }
        else
        {
            bannerMiddleInv.render(game.batch);

            for (int i = 0; i < shopLogic.getIdsItemPlayer().size(); i++)
            {
                panels.get(i).render(game.batch);
                panelsInfo.get(i).render(game.batch);
                panelsSell.get(i).render(game.batch);
                panelsImage.get(i).render(game.batch);

            }
        }

        game.font.draw(game.batch, "Credits " + shopLogic.getCreditsPlayer(), 550, 1150);

        game.batch.end();


        JukeBox.update(delta);
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