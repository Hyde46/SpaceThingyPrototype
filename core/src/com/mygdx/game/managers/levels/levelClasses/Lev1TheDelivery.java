package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev1TheDelivery extends Level
{
    public Lev1TheDelivery(GameScreen gs)
    {
        nameLevel = "The Delivery";
        nameSystem = "Bera";

        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
        Unit p7 = new Planet();
        Unit p8 = new Planet();
        Unit p9 = new Planet();
        Unit p10 = new Planet();
        Unit p11 = new Planet();

        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
        ((Planet)p2).initialize(new Vector2(-550,920),480,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(-250,1900),320,50,false,"planet9_100x100.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(-850,1750),190,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p5).initialize(new Vector2(500,840),190,36,false,"planet1_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(600,2400),320,50,false,"planet42_100x100.png",2,10,10.0f);
        ((Planet)p7).initialize(new Vector2(1350,1750),320,50,true,"planet42_100x100.png",2,10,10.0f);

        ((SpaceShip)playerShip).initialize(new Vector2(150,0),new Vector2(0,300),(Planet)p1,150,new Vector2(40,40),0);

        ((Planet)p8).initialize(new Vector2(-320,0),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p8).connectToPlanet((Planet)p1);
        ((Planet)p8).setRotationSpeed(10.0f,1);

        ((Planet)p9).initialize(new Vector2(-1030,920),240,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p9).connectToPlanet((Planet)p2);
        ((Planet)p9).setRotationSpeed(35.0f,1);

        ((Planet)p10).initialize(new Vector2(-800,920),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p10).connectToPlanet((Planet)p2);
        ((Planet)p10).setRotationSpeed(25.0f,-1);

        ((Planet)p11).initialize(new Vector2(280,2400),190,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p11).connectToPlanet((Planet)p6);
        ((Planet)p11).setRotationSpeed(25.0f,-1);

        UnitManager uM = new UnitManager();
        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        uM.addUnit(p7);
        uM.addUnit(p8);
        uM.addUnit(p9);
        uM.addUnit(p10);
        uM.addUnit(p11);
        uM.addUnit(playerShip);

        Unit item1 = new UpgradePickable();
        ((UpgradePickable)item1).initialize(5,new Vector2(100,870));
        uM.addUnit(item1);

        Unit item2 = new CurrencyPickable();
        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1600),200);
        uM.addUnit(item2);
        SpacePhysiX spX = new SpacePhysiX();
        spX.initializePhysics(uM.getUnits(),gs);
        InputManager.get.register(p1);
        InputManager.get.register(p2);
        InputManager.get.register(p3);
        InputManager.get.register(p4);
        InputManager.get.register(p5);
        InputManager.get.register(p6);
        InputManager.get.register(p7);
        InputManager.get.register(p8);
        InputManager.get.register(p9);
        InputManager.get.register(p10);
        InputManager.get.register(p11);

        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
        spX.initWorldBounds(new Rectangle(-1100,-400,3000,4000));

        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(4,true);
        gs.cM.addPBM(pbM);

        unitManager = uM;
        parallaxBackgroundManager = pbM;
        spacePhysiX = spX;
    }
}