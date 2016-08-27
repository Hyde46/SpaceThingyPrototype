package com.mygdx.game.managers.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by denis on 5/18/16.
 */
public class LevelFactory {

    private LevelLoader ll;

    public LevelFactory(){

    }


    public static Level loadLevel(int levelId, GameScreen gs){

        switch(levelId){
            case 1:
                return generateLevelZero(gs);
            case 2:
                return generateLevelOne(gs);
            case 3:
            case 4:
            case 5:
            default: return null;
        }

    }

    private static Level generateLevelZero(GameScreen gs){

        //Units init
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
        Unit p12 = new Planet();

        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);


        //Moons
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
        InputManager.get.register(p9);
        InputManager.get.register(p10);
        InputManager.get.register(p11);
        InputManager.get.register(p12);

        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));

        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(4,true);
        gs.cM.addPBM(pbM);

        Level lvl = new Level();
        lvl.unitManager = uM;
        lvl.parallaxBackgroundManager = pbM;
        lvl.spacePhysiX = spX;

        return lvl;
    }
    private static Level generateLevelOne(GameScreen gs){
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

        // ((SpaceShip)playerShip).initialize(new Vector2(320,300),new Vector2(5,350),null,0,new Vector2(40,40),"ship1_40x40.png",0);
        ((Planet)p1).initialize(new Vector2(200,670),320,50,false,"planet3_100x100.png",1,0,10.0f);
        ((Planet)p2).initialize(new Vector2(1300,750),320,50,false,"planet4_100x100.png",2,20,10.0f);
        ((Planet)p3).initialize(new Vector2(1600,2350),320,65,false,"planet5_130x130.png",1,30,10.0f);
        ((Planet)p4).initialize(new Vector2(300,2850),320,75,false,"planet7_150x150.png",2,40,10.0f);
        ((Planet)p5).initialize(new Vector2(600,4000),240,36,false,"planet6_72x72.png",1,120,10.0f);
        ((Planet)p6).initialize(new Vector2(800,4700),240,50,false,"planet2_100x100.png",2,10,10.0f);
        ((Planet)p7).initialize(new Vector2(1800,5300),240,50,true,"planet8_100x100.png",2,10,10.0f);

        ((SpaceShip)playerShip).initialize(new Vector2(500,670),new Vector2(5,350),(Planet)p1,300,new Vector2(40,40),0);


        //initialize moons
        ((Planet)p8).initialize(new Vector2(1270,2350),190,18,false,"moon2_36x36.png",1,0,10.0f);
        ((Planet)p8).connectToPlanet((Planet)p3);
        ((Planet)p8).setRotationSpeed(20.0f,1);

        ((Planet)p9).initialize(new Vector2(340,4000),190,18,false,"moon1_36x36.png",1,0,10.0f);
        ((Planet)p9).connectToPlanet((Planet)p5);
        ((Planet)p9).setRotationSpeed(27.0f,-1);
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

        uM.addUnit(playerShip);
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

        gs.cM.initializeCamera((SpaceShip)playerShip,p7.getPosition());
        spX.initWorldBounds(new Rectangle(-1700,-1100,5000,7000));
        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(2,true);
        gs.cM.addPBM(pbM);

        Level level = new Level();
        level.parallaxBackgroundManager = pbM;
        level.unitManager = uM;
        level.spacePhysiX = spX;
        return level;
    }
}
