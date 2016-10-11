package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev5Mira2 extends Level
{
    public Lev5Mira2(GameScreen gs)
    {
        nameLevel = "A Mistery";
        nameSystem = "Tentui";

        setupFunctions(5);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 1000, 2);
        setCluster(-1900, 900, 3);

        setCluster(2000, 2200, 2);
        setCluster(-2200, 1900, 3);

        setCurrencyVolley(-600, 700, 14);
        setCurrencyVolley(-2200, 2700, 14);

        setObstacleMoving(-1000, 500, 1, 1, 2);
        setObstacleMoving(0, 2000, -1, -1, 2);
        setObstacleMoving(-3000, 1000, 1, 1, 2);

        setCluster(-100, 2200, 2);

        setCluster(-3000, 2800, 2);
        Planet planetGoal = setPlanet(-3500, 3500, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-4000, -500, 6000, 4500));
    }
}