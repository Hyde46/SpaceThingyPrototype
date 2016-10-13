package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev10Main3Unknown extends Level
{
    public Lev10Main3Unknown(GameScreen gs)
    {
        nameLevel = "Endless skies";
        nameSystem = "Tau";

        setupFunctions(8);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 900, 2);

        setCluster(-100, 2000, 3);
        setCluster(950, 2400, 1);

        setItemCurrency(50, 2950, 20);

        setCluster(200, 3900, 1);

        setCluster(-150, 5100, 2);

        setCluster(-1150, 5500, 2);
        setCluster(180, 6000, 3);

        setItemCurrency(15, 6550, 20);

        setCluster(-150, 7100, 1);

        setCluster(180, 9000, 3);

        setItemCurrency(50, 1500, 20);

        setCluster(-80, 10000, 3);

//
//        setCurrencyVolley(140, 13000, 100);
//
//        setCluster(360, 14000, 3);
//
//        setCluster(-260, 17000, 3);

//        setObstacleField(-1000, 500, 1000, 500, 10, 1000);
//        setObstacleField(-1000, 2000, 1000, 2000, 10, 1000);
//        setObstacleField(-1000, 4000, 1000, 4000, 10, 1000);
//        setObstacleField(-1000, 10000, 1000, 10000, 10, 1000);

        Planet planetGoal = setPlanet(0, 11500, Planet.TypeOrbit.G320);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-2500, -500, 5000, 20000));
    }
}