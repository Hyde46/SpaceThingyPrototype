package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.Items.Level1.SpeedBooser;
import com.mygdx.game.Items.Level2.Break;
import com.mygdx.game.Items.Level3.ArtificialPlanet;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.modelItems.ModelItemsShop;
import com.mygdx.game.modelItems.ModelItemsShopManager;
import com.mygdx.game.prototypeUtils.CameraHelper;
import com.mygdx.game.renderAbleObjects.decorations.EquipButton;
import com.mygdx.game.renderAbleObjects.decorations.InfoButton;
import com.mygdx.game.renderAbleObjects.decorations.Slot;

/**
 * Created by Vali on 23.05.2016.
 */
public class ShopScreenBuy implements Screen
{
    OrthographicCamera cam;
 //   private Array<Slot> itemSlots;
 //   private Array<EquipButton> equipButtons;
  //  private Array<InfoButton> infoButtons;
 //   private Array<Slot> skinSlots;

 //   private ArrowButton skinArrowUp;
 //   private ArrowButton skinArrowDown;
  //  private Array<Slot> particleSlots;
  //  private ArrowButton particlesArrowUp;
  //  private ArrowButton particlesArrowDown;
    // private Array<Slot> itemSlots;


    private ParallaxBackgroundManager backgroundManager;

  //  private ScrollPane scrollPane;
   // private int currentId;
  //  private int currentSkin;
  //  private int currentParticles;
    public static OrthographicCamera camFixed;
    CameraHelper cameraHelper;

    CameraManager cameraManager;

    public ShopScreenBuy(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080, 1920);

        camFixed = new OrthographicCamera();
        camFixed.setToOrtho(false, 1080, 1920);
        InputManager.get.setup(cam);
        //add the backgrounds (hex pattern and stars)
        backgroundManager = new ParallaxBackgroundManager();
        backgroundManager.setLayers(2);

        //create skin functionality
        Slot skinSlot1 = new Slot();
        skinSlot1.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin.png");
        Slot skinSlot2 = new Slot();
        skinSlot2.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin2.png");
        Slot skinSlot3 = new Slot();
        skinSlot3.initialize(new Vector2(200, MyGdxGame.game.screenHeight - 600), 400, 400, "ship_skin3.png");
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
        String nameShopCurrent = "FirstShop";

        ModelItemsShop shop = new ModelItemsShop();
        shop.addItem(new SpeedBooser(0, 0, ItemManager.get));
        shop.addItem(new ArtificialPlanet(0, 0, ItemManager.get, null));
        shop.addItem(new Break(0, 0, ItemManager.get));

        ModelItemsShopManager.get().AddShop(nameShopCurrent, shop);

        int numberOfItems = shop.getITems().size;

        //add all of the item slots to array
    //    itemSlots = new Array<Slot>();
     //   equipButtons = new Array<EquipButton>();
     //   infoButtons = new Array<InfoButton>();

        int width = MyGdxGame.game.screenWidth - 200;
        int posY = 800;
        for(int i = 0; i < numberOfItems; i++)
        {
            posY = 800 - 200*i;

            Slot slot = new Slot();
            //intialize item slot
            slot.initialize(new Vector2(100, posY), width, 200, "item_slot.png");
            EquipButton equipButton = new EquipButton();
            equipButton.initialize(new Vector2(width - 200, posY), 250, 200, "equip_button.png", i);
            InputManager.get.register(equipButton);
            InfoButton infoButton = new InfoButton();
            infoButton.initialize(new Vector2(width - 500, posY), 250, 200, "info_button.png", i);
            InputManager.get.register(infoButton);

         //   itemSlots.add(slot);
           // equipButtons.add(equipButton);
          //  infoButtons.add(infoButton);
        }


  //      currentId = -1;



        cameraManager = new CameraManager();
        cameraHelper = new CameraHelper();
        cameraManager.setCam(cam);
        cameraManager.addPBM(backgroundManager);
        cameraHelper.setCameraManager(cameraManager, null, 4);
        InputManager.get.register(cameraHelper);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        MyGdxGame.game.batch.setProjectionMatrix(cam.combined);
        //draw string indicating, that this is a shop (to be removed later)
        MyGdxGame.game.batch.begin();
        MyGdxGame.game.font.draw(MyGdxGame.game.batch, "Prototype v0.0.4", 5 , 30);
        MyGdxGame.game.font.draw(MyGdxGame.game.batch, "This is a shop!", 320, 920);
        MyGdxGame.game.batch.end();
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
