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

public class Lev4Mira1 extends Level
{
    public Lev4Mira1(GameScreen gs)
    {
        nameLevel = "An Unexpected Encounter";
        nameSystem = "Mitaki";

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 1200, 2);
        setCluster(-900, 900, 3);

        setCurrencyVolley(-1400, 1550, 100);
        setCurrencyVolley(-1900, 1950, 100);

        setCluster(-100, 2200, 2);

        setCluster(-3000, 2800, 2);
        Planet planetGoal = setPlanet(-3500, 3500, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-4000, -500, 6000, 4500));
    }
}