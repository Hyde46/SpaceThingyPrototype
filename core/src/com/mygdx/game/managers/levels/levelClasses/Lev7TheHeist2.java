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

public class Lev7TheHeist2 extends Level
{
    public Lev7TheHeist2(GameScreen gs)
    {
        nameLevel = "The Heist";
        nameSystem = "Loki";

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setObstacleMoving(200, 300, 600, 600, 3);

        setPlanet(1000,1000, Planet.TypeOrbit.B240);

        setObstacleMoving(1000, 1000, 1400, 1400, 2);

        setCurrencyVolley(1500, 1500, 10);

        setPlanet(1700,1900, Planet.TypeOrbit.B320);

        setCluster(1700, 3000, 3);

        setCluster(300, 2600, 2);     Planet goalPlanet =  setPlanet(200,4000, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, goalPlanet, new Rectangle(-400, -400, 4000, 5000));



    }
}