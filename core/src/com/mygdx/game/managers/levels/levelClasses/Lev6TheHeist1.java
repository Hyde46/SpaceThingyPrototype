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

public class Lev6TheHeist1 extends Level
{
    public Lev6TheHeist1(GameScreen gs)
    {
        nameLevel = "The Deal";
        nameSystem = "Meku";

        //Units init
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();
        Unit p5 = new Planet();
        Unit p6 = new Planet();
        Unit p8 = new Planet();
        Unit p11 = new Planet();

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
        ((Planet)p8).initialize(new Vector2(6000,6000),320,50,true,"planet7_100x100.png",2,10,0.0f);
        ((Planet)p2).initialize(new Vector2(4800,4900),320,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(3900,3760),320,50,false,"planet9_100x100.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(3800,3200),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p5).initialize(new Vector2(2150,2650),240,36,false,"planet1_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(1200,2400),320,50,false,"planet42_100x100.png",2,10,10.0f);
        ((Planet)p11).initialize(new Vector2(1700,1350),480,50,false,"planet7_100x100.png",2,10,10.0f);
        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
        ((SpaceShip)playerShip).initialize(new Vector2(180,0),new Vector2(0,400),(Planet)p1,180,new Vector2(40,40),0);

        // obstacles
        ((MovingObstacle)mObst0).initialize(new Vector2(3000,2150), new Vector2(2,3),40,"asteroid2_80x80.png",12.0f,4);
        ((MovingObstacle)mObst1).initialize(new Vector2(2150,3900), new Vector2(5,6),40,"asteroid2_80x80.png",333.0f,7);
        ((MovingObstacle)mObst2).initialize(new Vector2(1800,3400), new Vector2(2,5),40,"asteroid2_80x80.png",12.0f,4);
        ((MovingObstacle)mObst3).initialize(new Vector2(3600,1200), new Vector2(2,4),40,"asteroid2_80x80.png",223.0f,5);
        ((MovingObstacle)mObst4).initialize(new Vector2(2240,3900), new Vector2(2,2),40,"asteroid2_80x80.png",11.0f,4);
        ((MovingObstacle)mObst5).initialize(new Vector2(3900,1500), new Vector2(5,0),40,"asteroid2_80x80.png",44.0f,5);

        ((UpgradePickable)item1).initialize(5,new Vector2(2900,2670));
        ((CurrencyPickable)item2).initialize(0,new Vector2(2700,1720),200);
        ((CurrencyPickable)item3).initialize(0,new Vector2(2400,1320),200);
        ((CurrencyPickable)item4).initialize(0,new Vector2(1200,1120),200);
        ((CurrencyPickable)item5).initialize(0,new Vector2(1100,1120),200);

        unitManager = new UnitManager();
        unitManager.addUnit(p1);
        unitManager.addUnit(p2);
        unitManager.addUnit(p3);
        unitManager.addUnit(p4);
        unitManager.addUnit(p5);
        unitManager.addUnit(p6);
        unitManager.addUnit(p8);
        unitManager.addUnit(p11);

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
        InputManager.get.register(p11);

        spacePhysiX = new SpacePhysiX();
        spacePhysiX.initializePhysics(unitManager.getUnits(),gs);

        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());

        spacePhysiX.initWorldBounds(new Rectangle(-1000,-1000,7000,7000));

        parallaxBackgroundManager = new ParallaxBackgroundManager();
        parallaxBackgroundManager.setLayers(4,true);
        gs.cM.addPBM(parallaxBackgroundManager);
    }
}