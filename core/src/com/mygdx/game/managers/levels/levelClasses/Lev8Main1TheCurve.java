package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev8Main1TheCurve extends Level
{
    public Lev8Main1TheCurve(GameScreen gs)
    {
        nameLevel = "Edge of an universe";
        nameSystem = "Tau";

        setupFunctions(10);

        Planet planet1 = setPlanet(-1000,0, Planet.TypeOrbit.B320);

        setVisualUpdate(0, 5000, 2);
        setCurrencyVolley(0, 5200, 100);
        setCurrencyVolley(0, 4800, 100);
        setCurrencyVolley(-200, 5000, 100);
        setCurrencyVolley(200, 5000, 100);

        setPlanet(-1400, 1000, Planet.TypeOrbit.B240);
        setPlanet(-1500, 1900, Planet.TypeOrbit.B320);

        setCluster(-2200, 2900, 2);

        setPlanet(-3400, 3500, Planet.TypeOrbit.B320);
        setPlanet(-4500, 5000, Planet.TypeOrbit.B480);

        setCluster(-3500, 6000, 2);

        setObstacleField(-2000, 6555, -2100, 6255, 2, 300);

        setPlanet(-2400, 7000, Planet.TypeOrbit.B240);
        setPlanet(-3500, 8000, Planet.TypeOrbit.B480);

        setCluster(-1500, 8300, 2);

        setCluster(500, 9000, 2);

        setPlanet(1200, 8100, Planet.TypeOrbit.B240);

        setPlanet(2200, 7700, Planet.TypeOrbit.B240);
        setPlanet(3000, 6600, Planet.TypeOrbit.B320);

        setCluster(4500, 4800, 2);

        setObstacleField(3200, 4400, 3000, 4500, 2, 300);

        setPlanet(2200, 3700, Planet.TypeOrbit.B480);

        setPlanet(3200, 2700, Planet.TypeOrbit.B240);
        setPlanet(2000, 1600, Planet.TypeOrbit.B480);

        setObstacleField(0, 400, 0, -400, 6, 600);

        Planet planetGoal = setPlanet(1000, 0, Planet.TypeOrbit.G320);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-4500, -500, 10000, 12000));
    }
}