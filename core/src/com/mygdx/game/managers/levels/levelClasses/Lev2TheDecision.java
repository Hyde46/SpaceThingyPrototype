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

public class Lev2TheDecision extends Level
{
    /*
        -1 None
        0 left
        1 mid
        2 right
     */
    public static int lastFinishedSide = -1;

    public Lev2TheDecision(GameScreen gs)
    {
        nameLevel = "The Decision";
        nameSystem = "Topoga";

        //Units init
        Unit playerShip = new SpaceShip();
        //left arm
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
        Unit p7 = new Planet();
        Unit p8 = new Planet();
        //middle arm
        Unit p9 = new Planet();
        Unit p10 = new Planet();
        Unit p11 = new Planet();
        Unit p12 = new Planet();
        Unit p13 = new Planet();
        //right arm
        Unit p14 = new Planet();
        Unit p15 = new Planet();
        Unit p16 = new Planet();
        Unit p17 = new Planet();

        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
        ((Planet)p2).initialize(new Vector2(-900,500),480,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(-1850,900),190,18,false,"moon2_36x36.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(-2750,1500),320,50,true,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p4).setDecisionPlanet(0);

        ((Planet)p9).initialize(new Vector2(200,1450),190,18,false,"moon1_36x36.png",2,90,15.0f);
        ((Planet)p10).initialize(new Vector2(-100,2350),240,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p11).initialize(new Vector2(600,2950),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p12).initialize(new Vector2(-200,3700),190,50,true,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p12).setDecisionPlanet(1);

        ((Planet)p14).initialize(new Vector2(800,300),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p15).initialize(new Vector2(1550,950),190,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p16).initialize(new Vector2(2600,700),320,50,true,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p16).setDecisionPlanet(2);


        ((SpaceShip)playerShip).initialize(new Vector2(150,0),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);

        //Moons

        ((Planet)p5).initialize(new Vector2(-1380,500),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p5).connectToPlanet((Planet)p2);
        ((Planet)p5).setRotationSpeed(30.0f,1);


        ((Planet)p6).initialize(new Vector2(-1180,500),120,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p6).connectToPlanet((Planet)p2);
        ((Planet)p6).setRotationSpeed(40.0f,-1);

        ((Planet)p7).initialize(new Vector2(-1510,900),190,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p7).connectToPlanet((Planet)p3);
        ((Planet)p7).setRotationSpeed(27.0f,1);

        ((Planet)p8).initialize(new Vector2(-2190,900),190,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p8).connectToPlanet((Planet)p3);
        ((Planet)p8).setRotationSpeed(27.0f,1);

        ((Planet)p13).initialize(new Vector2(-100,1450),240,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p13).connectToPlanet((Planet)p9);
        ((Planet)p13).setRotationSpeed(27.0f,1);

        ((Planet)p17).initialize(new Vector2(1150,950),240,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p17).connectToPlanet((Planet)p15);
        ((Planet)p17).setRotationSpeed(37.0f,-1);


        UnitManager uM = new UnitManager();
        //planets
        //left arm
        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(p5);
        uM.addUnit(p6);
        //middle arm
        uM.addUnit(p9);
        uM.addUnit(p10);
        uM.addUnit(p11);
        uM.addUnit(p12);
        //right arm
        uM.addUnit(p14);
        uM.addUnit(p15);
        uM.addUnit(p16);


        //moons
        //left arm
        uM.addUnit(p7);
        uM.addUnit(p8);
        //middle arm
        uM.addUnit(p13);
        //right arm
        uM.addUnit(p17);

        uM.addUnit(playerShip);

        Unit item1 = new UpgradePickable();
        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
        uM.addUnit(item1);

        Unit item2 = new CurrencyPickable();
        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
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
        InputManager.get.register(p12);

        InputManager.get.register(p13);

        InputManager.get.register(p14);
        InputManager.get.register(p15);
        InputManager.get.register(p16);
        InputManager.get.register(p17);

        gs.cM.initializeCamera((SpaceShip)playerShip,playerShip.getPosition());
        spX.initWorldBounds(new Rectangle(-3100,-400,6100,4500));

        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(4,true);
        gs.cM.addPBM(pbM);

        unitManager = uM;
        parallaxBackgroundManager = pbM;
        spacePhysiX = spX;

    }
}