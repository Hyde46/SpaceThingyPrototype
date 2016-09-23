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
        Unit p7 = new Planet();
        Unit p8 = new Planet();
        Unit p9 = new Planet();
        Unit p10 = new Planet();
        Unit p11 = new Planet();
        Unit p12 = new Planet();

        Unit mObst0 = new MovingObstacle();
        Unit mObst1 = new MovingObstacle();
        Unit mObst2 = new MovingObstacle();
        Unit mObst3 = new MovingObstacle();
        Unit mObst4 = new MovingObstacle();
        Unit mObst5 = new MovingObstacle();

        Unit item1 = new UpgradePickable();
        Unit item2 = new CurrencyPickable();

        Unit playerShip = new SpaceShip();

        // planets
        ((Planet)p8).initialize(new Vector2(2000,2000),320,50,true,"planet7_100x100.png",2,10,0.0f);
        ((Planet)p2).initialize(new Vector2(1800,1900),320,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(1900,1760),320,50,false,"planet9_100x100.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(1800,1200),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p5).initialize(new Vector2(1150,1650),240,36,false,"planet1_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(200,400),320,50,false,"planet42_100x100.png",2,10,10.0f);
        ((Planet)p11).initialize(new Vector2(500,350),480,50,false,"planet7_100x100.png",2,10,10.0f);
        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);

        // moons
        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p7).connectToPlanet((Planet)p6);
        ((Planet)p7).setRotationSpeed(20.0f,1);
        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p9).connectToPlanet((Planet)p11);
        ((Planet)p9).setRotationSpeed(25.0f,1);
        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p12).connectToPlanet((Planet)p11);
        ((Planet)p12).setRotationSpeed(45.0f,-1);
        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p10).connectToPlanet((Planet)p2);
        ((Planet)p10).setRotationSpeed(15.0f,-1);

        // obstacles
        ((MovingObstacle)mObst0).initialize(new Vector2(1000,1150), new Vector2(2,3),40,"asteroid2_80x80.png",0.0f,1);
        ((MovingObstacle)mObst1).initialize(new Vector2(1150,900), new Vector2(5,6),40,"asteroid2_80x80.png",0.0f,1);
        ((MovingObstacle)mObst2).initialize(new Vector2(800,1400), new Vector2(2,5),40,"asteroid2_80x80.png",0.0f,1);
        ((MovingObstacle)mObst3).initialize(new Vector2(600,1200), new Vector2(2,4),40,"asteroid2_80x80.png",0.0f,1);
        ((MovingObstacle)mObst4).initialize(new Vector2(1240,900), new Vector2(2,2),40,"asteroid2_80x80.png",0.0f,1);
        ((MovingObstacle)mObst5).initialize(new Vector2(900,500), new Vector2(5,0),40,"asteroid2_80x80.png",0.0f,1);

        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);

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
        uM.addUnit(p12);

        uM.addUnit(mObst0);
        uM.addUnit(mObst1);
        uM.addUnit(mObst2);
        uM.addUnit(mObst3);
        uM.addUnit(mObst4);
        uM.addUnit(mObst5);

        uM.addUnit(item1);
        uM.addUnit(item2);

        uM.addUnit(playerShip);

        InputManager.get.register(p1);
        InputManager.get.register(p2);
        InputManager.get.register(p3);
        InputManager.get.register(p4);
        InputManager.get.register(p5);
        InputManager.get.register(p6);
        InputManager.get.register(p7);
        InputManager.get.register(p9);
        InputManager.get.register(p10);
        InputManager.get.register(p11);
        InputManager.get.register(p12);

        SpacePhysiX spX = new SpacePhysiX();
        spX.initializePhysics(uM.getUnits(),gs);

        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));

        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(4,true);
        gs.cM.addPBM(pbM);

        unitManager = uM;
        parallaxBackgroundManager = pbM;
        spacePhysiX = spX;
    }
}