package com.mygdx.game.managers.levels.levelClasses;

import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 23.09.2016.
 */

public class Lev1337Test extends Level
{
    public Lev1337Test(GameScreen gs)
    {
        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B240);
        setMoon(planet1, 400, 10);
        Planet planet2 = setPlanet(0,800, Planet.TypeOrbit.G320);

        setCluster(0, 1200, 3);

        setPlayer(planet1);

        setObstacleMoving(-100, 300, 100, 300, 2, 5);

        setItemCurrency(0, 350, 100);
        setItemCurrency(0, 400, 100);
        setItemCurrency(0, 450, 100);

        setCurrencyVolley(200, 300, 100);

        setCamera(gs, planet2, null);
    }
}