package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.MovingObstacle;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev3ATestOfFaith extends Level
{
    public Lev3ATestOfFaith(GameScreen gs)
    {
        nameLevel = "A Test of Faith";
        nameSystem = "Pero";

        //Units init
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
 //       Unit p7 = new Planet();
        Unit p8 = new Planet();
 //       Unit p9 = new Planet();
 //       Unit p10 = new Planet();
        Unit p11 = new Planet();
 //       Unit p12 = new Planet();
//
        Unit mObst0 = new MovingObstacle();
        Unit mObst1 = new MovingObstacle();
        Unit mObst2 = new MovingObstacle();
        Unit mObst3 = new MovingObstacle();
        Unit mObst4 = new MovingObstacle();
        Unit mObst5 = new MovingObstacle();

        Unit item1 = new UpgradePickable();
        Unit item2 = new CurrencyPickable();
        Unit item3 = new CurrencyPickable();
        Unit item4 = new CurrencyPickable();
        Unit item5 = new CurrencyPickable();

        Unit playerShip = new SpaceShip();

        // planets
        ((Planet)p8).initialize(new Vector2(-4400,4000),320,50,true,"planet7_100x100.png",2,10,0.0f);
        ((Planet)p2).initialize(new Vector2(-3800,3900),320,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(-2900,2760),320,50,false,"planet9_100x100.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(-2800,2200),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p5).initialize(new Vector2(-1150,1650),240,36,false,"planet1_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(-400,1400),320,50,false,"planet42_100x100.png",2,10,10.0f);
        ((Planet)p11).initialize(new Vector2(-700,450),480,50,false,"planet7_100x100.png",2,10,10.0f);
        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
        ((SpaceShip)playerShip).initialize(new Vector2(180,0),new Vector2(0,400),(Planet)p1,180,new Vector2(40,40),0);

        // moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);

        // obstacles
        ((MovingObstacle)mObst0).initialize(new Vector2(-2000,1150), new Vector2(2,3),40,"asteroid2_80x80.png",12.0f,4);
        ((MovingObstacle)mObst1).initialize(new Vector2(-1150,2900), new Vector2(5,6),40,"asteroid2_80x80.png",333.0f,7);
        ((MovingObstacle)mObst2).initialize(new Vector2(-1800,2400), new Vector2(2,5),40,"asteroid2_80x80.png",12.0f,4);
        ((MovingObstacle)mObst3).initialize(new Vector2(-2600,1200), new Vector2(2,4),40,"asteroid2_80x80.png",223.0f,5);
        ((MovingObstacle)mObst4).initialize(new Vector2(-2240,2900), new Vector2(2,2),40,"asteroid2_80x80.png",11.0f,4);
        ((MovingObstacle)mObst5).initialize(new Vector2(-2900,1500), new Vector2(5,0),40,"asteroid2_80x80.png",44.0f,5);

        ((UpgradePickable)item1).initialize(5,new Vector2(-1600,1670));
        ((CurrencyPickable)item2).initialize(0,new Vector2(-2500,1720),200);
        ((CurrencyPickable)item3).initialize(0,new Vector2(-2500,1720),200);
        ((CurrencyPickable)item4).initialize(0,new Vector2(-2300,1520),200);
        ((CurrencyPickable)item5).initialize(0,new Vector2(-2000,1620),200);

        unitManager = new UnitManager();
        unitManager.addUnit(p1);
        unitManager.addUnit(p2);
        unitManager.addUnit(p3);
        unitManager.addUnit(p4);
        unitManager.addUnit(p5);
        unitManager.addUnit(p6);
//        unitManager.addUnit(p7);
        unitManager.addUnit(p8);
 //       unitManager.addUnit(p9);
//        unitManager.addUnit(p10);
        unitManager.addUnit(p11);
//        unitManager.addUnit(p12);

        unitManager.addUnit(mObst0);
        unitManager.addUnit(mObst1);
        unitManager.addUnit(mObst2);
        unitManager.addUnit(mObst3);
        unitManager.addUnit(mObst4);
        unitManager.addUnit(mObst5);

        unitManager.addUnit(item1);
        unitManager.addUnit(item2);
        unitManager.addUnit(item3);
        unitManager.addUnit(item4);
        unitManager.addUnit(item5);

        unitManager.addUnit(playerShip);

        InputManager.get.register(p1);
        InputManager.get.register(p2);
        InputManager.get.register(p3);
        InputManager.get.register(p4);
        InputManager.get.register(p5);
        InputManager.get.register(p6);
//        InputManager.get.register(p7);
  //      InputManager.get.register(p9);
 //       InputManager.get.register(p10);
        InputManager.get.register(p11);
  //      InputManager.get.register(p12);

        spacePhysiX = new SpacePhysiX();
        spacePhysiX.initializePhysics(unitManager.getUnits(),gs);

        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());

        spacePhysiX.initWorldBounds(new Rectangle(-9000,-2000,10000,8000));

        parallaxBackgroundManager = new ParallaxBackgroundManager();
        parallaxBackgroundManager.setLayers(4,true);
        gs.cM.addPBM(parallaxBackgroundManager);
    }
}