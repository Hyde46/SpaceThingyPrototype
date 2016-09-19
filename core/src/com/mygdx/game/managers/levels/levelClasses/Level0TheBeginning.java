package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Asteroid;
import com.mygdx.game.renderAbleObjects.units.MovingObstacle;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by ilost on 18.09.2016.
 */

public class Level0TheBeginning extends Level
{
    public Level0TheBeginning(GameScreen gs)
    {
        nameLevel = "The Beginning";
        nameSystem = "Tengo";

        Unit playerShip = new SpaceShip();
        Unit p1 = new Planet();
        Unit p2 = new Planet();
        Unit p3 = new Planet();
        Unit p4 = new Planet();

        Unit asteroid = new Asteroid();
        Unit mObst = new MovingObstacle();

        ((Planet)p1).initialize(new Vector2(200,670),320,36,false,"planet1_72x72.png",1,0,0);
        ((SpaceShip)playerShip).initialize(new Vector2(360,670),new Vector2(0,400),(Planet)p1,150,new Vector2(40,40),0);
        ((Planet)p2).initialize(new Vector2(-220,1450),320,50,false,"planet2_100x100.png",2,40,5.0f);
        ((Planet)p3).initialize(new Vector2(820,1600),480,50,false,"planet9_100x100.png",1,30,5.0f);
        ((Planet)p4).initialize(new Vector2(-100,2250),320,50,true,"planet7_100x100.png",2,90,5.0f);

        ((Asteroid)asteroid).initialize(new Vector2(-600,670),new Vector2(3,2),40,"asteroid1_80x80.png",0.0f,1,0.2f);
        ((MovingObstacle)mObst).initialize(new Vector2(-200,200), new Vector2(2,0),40,"asteroid2_80x80.png",0.0f,0.3f);
        UnitManager uM = new UnitManager();
        uM.addUnit(p1);
        uM.addUnit(p2);
        uM.addUnit(p3);
        uM.addUnit(p4);
        uM.addUnit(playerShip);
        uM.addUnit(asteroid);
        uM.addUnit(mObst);

        SpacePhysiX spX = new SpacePhysiX();
        spX.initializePhysics(uM.getUnits(),gs);
        InputManager.get.register(p1);
        InputManager.get.register(p2);
        InputManager.get.register(p3);
        InputManager.get.register(p4);

        gs.cM.initializeCamera((SpaceShip)playerShip,p4.getPosition());
        spX.initWorldBounds(new Rectangle(-800,-300,2000,3000));

        ParallaxBackgroundManager pbM = new ParallaxBackgroundManager();
        pbM.setLayers(4,true);
        gs.cM.addPBM(pbM);

        unitManager = uM;
        parallaxBackgroundManager = pbM;
        spacePhysiX = spX;
    }
}