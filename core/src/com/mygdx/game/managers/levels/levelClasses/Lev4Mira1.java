package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev4Mira1 extends Level
{
    public Lev4Mira1(GameScreen gs)
    {
        nameLevel = "An Unexpected Encounter";
        nameSystem = "Mitaki";

        setupFunctions(4);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 1200, 2);
        setCluster(-900, 900, 3);

        setCurrencyVolley(-1400, 1550, 10);
        setVisualUpdate(-1650,1750,5);
        setCurrencyVolley(-1900, 1950, 10);

        setCluster(-100, 2200, 2);

        setCluster(-1500, 2700, 3);

        setCluster(-3000, 2800, 2);
        Planet planetGoal = setPlanet(-3500, 3500, Planet.TypeOrbit.G320);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-4000, -500, 6000, 4500));
    }
}