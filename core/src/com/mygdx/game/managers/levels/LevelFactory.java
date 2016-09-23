package com.mygdx.game.managers.levels;

import com.mygdx.game.managers.levels.levelClasses.Lev0TheBeginning;
import com.mygdx.game.managers.levels.levelClasses.Lev10MainPath3;
import com.mygdx.game.managers.levels.levelClasses.Lev1TheDelivery;
import com.mygdx.game.managers.levels.levelClasses.Lev2TheDecision;
import com.mygdx.game.managers.levels.levelClasses.Lev3ATestOfFaith;
import com.mygdx.game.managers.levels.levelClasses.Lev4Mira1;
import com.mygdx.game.managers.levels.levelClasses.Lev5Mira2;
import com.mygdx.game.managers.levels.levelClasses.Lev6TheHeist1;
import com.mygdx.game.managers.levels.levelClasses.Lev7TheHeist2;
import com.mygdx.game.managers.levels.levelClasses.Lev8MainPath1;
import com.mygdx.game.managers.levels.levelClasses.Lev9MainPath2;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by denis on 5/18/16.
 */
public class LevelFactory
{
    public LevelFactory(){}

    public static Level loadLevel(int levelId, GameScreen gs)
    {
        switch(levelId){
            case 1:
                return new Lev0TheBeginning(gs);
            case 2:
                return new Lev1TheDelivery(gs);
            case 5:
                return new Lev2TheDecision(gs);
            case 8:
                return new Lev7TheHeist2(gs);

            case 9:
                return new Lev4Mira1(gs);
            case 6:
                return new Lev3ATestOfFaith(gs);
            case 7:
                return new Lev6TheHeist1(gs);
            case 10:

                return new Lev5Mira2(gs);
            case 11:
                return new Lev8MainPath1(gs);
            case 12:
                return new Lev9MainPath2(gs);
            case 13:
                return new Lev10MainPath3(gs);
            default:
                return null;
        }
    }
}

//    private static Level generateLevelZero(GameScreen gs){
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(-220,1450),320,50,false,"planet2_100x100.png",2,40,5.0f);
//        ((Planet)p3).initialize(new Vector2(820,1600),480,50,false,"planet9_100x100.png",1,30,5.0f);
//        ((Planet)p4).initialize(new Vector2(-100,2250),320,50,true,"planet7_100x100.png",2,90,5.0f);
//
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(playerShip);
//
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p4.getPosition());
//        spX.initWorldBounds(new Rectangle(-800,-300,2000,3000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelOne(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
//        ((Planet)p2).initialize(new Vector2(-550,920),480,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(-250,1900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-850,1750),190,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(500,840),190,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(600,2400),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p7).initialize(new Vector2(1350,1750),320,50,true,"planet42_100x100.png",2,10,10.0f);
//
//
//        ((SpaceShip)playerShip).initialize(new Vector2(150,0),new Vector2(0,300),(Planet)p1,150,new Vector2(40,40),0);
//
//        //Moons
//
//        ((Planet)p8).initialize(new Vector2(-320,0),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p8).connectToPlanet((Planet)p1);
//        ((Planet)p8).setRotationSpeed(10.0f,1);
//
//        ((Planet)p9).initialize(new Vector2(-1030,920),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p2);
//        ((Planet)p9).setRotationSpeed(35.0f,1);
//
//        ((Planet)p10).initialize(new Vector2(-800,920),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(25.0f,-1);
//
//        ((Planet)p11).initialize(new Vector2(280,2400),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p11).connectToPlanet((Planet)p6);
//        ((Planet)p11).setRotationSpeed(25.0f,-1);
//
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,870));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1600),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p8);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-1100,-400,3000,4000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelTwo(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-1100,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelThree(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelFour(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelFive(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelSix(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//
//    private static Level generateLevelSeven(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelEight(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
//        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//        ((Planet)p2).initialize(new Vector2(800,1720),320,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(950,900),320,50,false,"planet9_100x100.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-300,1700),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p5).initialize(new Vector2(450,2530),240,36,false,"planet1_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(-110,2800),320,50,false,"planet42_100x100.png",2,10,10.0f);
//        ((Planet)p8).initialize(new Vector2(130,3800),320,50,true,"planet7_100x100.png",2,10,0.0f);
//        ((Planet)p11).initialize(new Vector2(1230,3480),480,50,false,"planet7_100x100.png",2,10,10.0f);
//
//
//        //Moons
//        ((Planet)p7).initialize(new Vector2(-430,2800),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p6);
//        ((Planet)p7).setRotationSpeed(20.0f,1);
//
//
//        ((Planet)p9).initialize(new Vector2(1680,3480),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p11);
//        ((Planet)p9).setRotationSpeed(25.0f,1);
//        ((Planet)p12).initialize(new Vector2(950,3480),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p12).connectToPlanet((Planet)p11);
//        ((Planet)p12).setRotationSpeed(45.0f,-1);
//
//        ((Planet)p10).initialize(new Vector2(480,1720),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p10).connectToPlanet((Planet)p2);
//        ((Planet)p10).setRotationSpeed(15.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        uM.addUnit(playerShip);
//
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p8.getPosition());
//        spX.initWorldBounds(new Rectangle(-700,-1100,4000,7000));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }
//    private static Level generateLevelNine(GameScreen gs){
//
//        Unit playerShip = new SpaceShip();
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        Unit p9 = new Planet();
//
//        // ((SpaceShip)playerShip).initialize(new Vector2(320,300),new Vector2(5,350),null,0,new Vector2(40,40),"ship1_40x40.png",0);
//        ((Planet)p1).initialize(new Vector2(200,670),320,50,false,"planet3_100x100.png",1,0,10.0f);
//        ((Planet)p2).initialize(new Vector2(1300,750),320,50,false,"planet4_100x100.png",2,20,10.0f);
//        ((Planet)p3).initialize(new Vector2(1600,2350),320,65,false,"planet5_130x130.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(300,2850),320,75,false,"planet7_150x150.png",2,40,10.0f);
//        ((Planet)p5).initialize(new Vector2(600,4000),240,36,false,"planet6_72x72.png",1,120,10.0f);
//        ((Planet)p6).initialize(new Vector2(800,4700),240,50,false,"planet2_100x100.png",2,10,10.0f);
//        ((Planet)p7).initialize(new Vector2(1800,5300),240,50,true,"planet8_100x100.png",2,10,10.0f);
//
//        ((SpaceShip)playerShip).initialize(new Vector2(500,670),new Vector2(5,350),(Planet)p1,300,new Vector2(40,40),0);
//
//
//        //initialize moons
//        ((Planet)p8).initialize(new Vector2(1270,2350),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p8).connectToPlanet((Planet)p3);
//        ((Planet)p8).setRotationSpeed(20.0f,1);
//
//        ((Planet)p9).initialize(new Vector2(340,4000),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p9).connectToPlanet((Planet)p5);
//        ((Planet)p9).setRotationSpeed(27.0f,-1);
//        UnitManager uM = new UnitManager();
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        uM.addUnit(p9);
//
//        uM.addUnit(playerShip);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//        InputManager.get.register(p7);
//        InputManager.get.register(p8);
//        InputManager.get.register(p9);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,p7.getPosition());
//        spX.initWorldBounds(new Rectangle(-1700,-1100,5000,7000));
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(2,true);
//        gs.cM.addPBM(pbM);
//
//        Level level = new Level();
//        level.parallaxBackgroundManager = pbM;
//        level.unitManager = uM;
//        level.spacePhysiX = spX;
//        level.nameLevel = "First Steps";
//        return level;
//
//    }
//    private static Level generateLevelTen(GameScreen gs){
//
//        //Units init
//        Unit playerShip = new SpaceShip();
//        //left arm
//        Unit p1 = new Planet();
//        Unit p2 = new Planet();
//        Unit p3 = new Planet();
//        Unit p4 = new Planet();
//        Unit p5 = new Planet();
//        Unit p6 = new Planet();
//        Unit p7 = new Planet();
//        Unit p8 = new Planet();
//        //middle arm
//        Unit p9 = new Planet();
//        Unit p10 = new Planet();
//        Unit p11 = new Planet();
//        Unit p12 = new Planet();
//        Unit p13 = new Planet();
//        //right arm
//        Unit p14 = new Planet();
//        Unit p15 = new Planet();
//        Unit p16 = new Planet();
//        Unit p17 = new Planet();
//
//        ((Planet)p1).initialize(new Vector2(0,0),320,36,false,"planet1_72x72.png",1,0,0);
//        ((Planet)p2).initialize(new Vector2(-900,500),480,50,false,"planet2_100x100.png",2,40,10.0f);
//        ((Planet)p3).initialize(new Vector2(-1850,900),190,18,false,"moon2_36x36.png",1,30,10.0f);
//        ((Planet)p4).initialize(new Vector2(-2750,1500),320,50,true,"planet2_100x100.png",2,90,10.0f);
//
//        ((Planet)p9).initialize(new Vector2(200,1450),190,18,false,"moon1_36x36.png",2,90,15.0f);
//        ((Planet)p10).initialize(new Vector2(-100,2350),240,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p11).initialize(new Vector2(600,2950),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p12).initialize(new Vector2(-200,3700),190,50,true,"planet2_100x100.png",2,90,10.0f);
//
//        ((Planet)p14).initialize(new Vector2(800,300),320,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p15).initialize(new Vector2(1550,950),190,50,false,"planet2_100x100.png",2,90,10.0f);
//        ((Planet)p16).initialize(new Vector2(2600,700),320,50,true,"planet2_100x100.png",2,90,10.0f);
//
//
//
//        ((SpaceShip)playerShip).initialize(new Vector2(150,0),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
//
//        //Moons
//
//        ((Planet)p5).initialize(new Vector2(-1380,500),190,18,false,"moon1_36x36.png",1,0,10.0f);
//        ((Planet)p5).connectToPlanet((Planet)p2);
//        ((Planet)p5).setRotationSpeed(30.0f,1);
//
//
//        ((Planet)p6).initialize(new Vector2(-1180,500),120,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p6).connectToPlanet((Planet)p2);
//        ((Planet)p6).setRotationSpeed(40.0f,-1);
//
//        ((Planet)p7).initialize(new Vector2(-1510,900),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p7).connectToPlanet((Planet)p3);
//        ((Planet)p7).setRotationSpeed(27.0f,1);
//
//        ((Planet)p8).initialize(new Vector2(-2190,900),190,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p8).connectToPlanet((Planet)p3);
//        ((Planet)p8).setRotationSpeed(27.0f,1);
//
//        ((Planet)p13).initialize(new Vector2(-100,1450),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p13).connectToPlanet((Planet)p9);
//        ((Planet)p13).setRotationSpeed(27.0f,1);
//
//        ((Planet)p17).initialize(new Vector2(1150,950),240,18,false,"moon2_36x36.png",1,0,10.0f);
//        ((Planet)p17).connectToPlanet((Planet)p15);
//        ((Planet)p17).setRotationSpeed(37.0f,-1);
//
//
//        UnitManager uM = new UnitManager();
//        //planets
//        //left arm
//        uM.addUnit(p1);
//        uM.addUnit(p2);
//        uM.addUnit(p3);
//        uM.addUnit(p4);
//        uM.addUnit(p5);
//        uM.addUnit(p6);
//        //middle arm
//        uM.addUnit(p9);
//        uM.addUnit(p10);
//        uM.addUnit(p11);
//        uM.addUnit(p12);
//        //right arm
//        uM.addUnit(p14);
//        uM.addUnit(p15);
//        uM.addUnit(p16);
//
//
//        //moons
//        //left arm
//        uM.addUnit(p7);
//        uM.addUnit(p8);
//        //middle arm
//        uM.addUnit(p13);
//        //right arm
//        uM.addUnit(p17);
//
//        uM.addUnit(playerShip);
//
//        Unit item1 = new UpgradePickable();
//        ((UpgradePickable)item1).initialize(5,new Vector2(100,670));
//        uM.addUnit(item1);
//
//        Unit item2 = new CurrencyPickable();
//        ((CurrencyPickable)item2).initialize(0,new Vector2(700,1720),200);
//        uM.addUnit(item2);
//        SpacePhysiX spX = new SpacePhysiX();
//        spX.initializePhysics(uM.getUnits(),gs);
//        InputManager.get.register(p1);
//        InputManager.get.register(p2);
//        InputManager.get.register(p3);
//        InputManager.get.register(p4);
//        InputManager.get.register(p5);
//        InputManager.get.register(p6);
//
//        InputManager.get.register(p7);
//        InputManager.get.register(p8);
//
//        InputManager.get.register(p9);
//        InputManager.get.register(p10);
//        InputManager.get.register(p11);
//        InputManager.get.register(p12);
//
//        InputManager.get.register(p13);
//
//        InputManager.get.register(p14);
//        InputManager.get.register(p15);
//        InputManager.get.register(p16);
//        InputManager.get.register(p17);
//
//        gs.cM.initializeCamera((SpaceShip)playerShip,playerShip.getPosition());
//        spX.initWorldBounds(new Rectangle(-3100,-400,6100,4500));
//
//        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
//        pbM.setLayers(4,true);
//        gs.cM.addPBM(pbM);
//
//        Level lvl = new Level();
//        lvl.unitManager = uM;
//        lvl.parallaxBackgroundManager = pbM;
//        lvl.spacePhysiX = spX;
//
//        lvl.nameLevel = "Beginning";
//
//        return lvl;
//    }

