package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev6TheHeist1 extends Level
{
    public Lev6TheHeist1(GameScreen gs)
    {
        nameLevel = "The devil";
        nameSystem = "Meku";

        setupFunctions(6);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 1000, 2);
        setCluster(1900, 900, 3);

        setCurrencyVolley(100, 2400, 14);

        setCluster(2000, 2200, 2);

        setCluster(2800, 1900, 3);

        setCurrencyVolley(600, 700, 10);
//        setCurrencyVolley(2200, 3400, 100);

        setObstacleMoving(1000, 500, 0, 1000, 2);
        setObstacleMoving(-200, 2000, -2000, 2000, 2);
        setObstacleMoving(-3000, 1000, 0, 3000, 2);
        setObstacleMoving(4000, 4000, -1, -1, 2);
        setObstacleMoving(0, 2000, -2000, 2000, 2);
        setObstacleMoving(-3000, 1000, 0, 3000, 2);

        setCluster(100, 2200, 2);
        setCluster(3000, 2800, 2);

        setVisualUpdate(3300,3100,3);
        Planet planetGoal = setPlanet(3500, 3500, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-1000, -500, 6000, 5000));


    }
}