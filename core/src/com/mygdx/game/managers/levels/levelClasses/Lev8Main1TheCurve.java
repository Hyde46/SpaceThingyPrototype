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

        Planet planet1 = setPlanet(-1100,0, Planet.TypeOrbit.B320);

        setVisualUpdate(0, 1300, 2);
        setCurrencyVolley(0, 1500, 100);
        setCurrencyVolley(0, 1100, 100);
        //setCurrencyVolley(-200, 1300, 100);
        //setCurrencyVolley(200, 1300, 100);

        setPlanet(-1400, 650, Planet.TypeOrbit.B240);
        setPlanet(-2150, 850, Planet.TypeOrbit.B320);

        setCluster(-2300, 1600, 2);
        setCurrencyVolley(-3050, 1650, 20);
        setPlanet(-3800, 1700, Planet.TypeOrbit.B320); // planet outside of circle

        //setPlanet(-2100, 2400, Planet.TypeOrbit.B320);

        setCluster(-1300, 3000, 2);
        setCurrencyVolley(-1325, 3850, 20);
        setPlanet(-1350, 4700, Planet.TypeOrbit.B320); // pl outside
        setObstacleField(-760, 2600, -760, 2600, 2, 200);

        setPlanet(-200, 2400, Planet.TypeOrbit.B480);
        setPlanet(500, 3100, Planet.TypeOrbit.B240);
        setObstacleField(500, 3100, 500, 3100, 2, 1650);

        setCluster(1300, 2500, 2);
        setCurrencyVolley(2100, 2450, 20);
        setPlanet(2900, 2400, Planet.TypeOrbit.B320);
        setObstacleField(2900, 2400, 2900, 2400, 2, 1650);

        setObstacleField(1900, 1300, 1900, 1300, 2, 2650);
        setPlanet(1900, 1300, Planet.TypeOrbit.B320);
        setPlanet(1400, 750, Planet.TypeOrbit.B240);

        setObstacleField(0, 450, 0, -100, 6, 650);
        setPlanet(0, 75, Planet.TypeOrbit.B240);
        Planet planetGoal = setPlanet(1100, 0, Planet.TypeOrbit.G320);

        setPlayer(planet1);

        setLevel(gs, planetGoal, new Rectangle(-4500, -500, 8000, 6000));
    }
}