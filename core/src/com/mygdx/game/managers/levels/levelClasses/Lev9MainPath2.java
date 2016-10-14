package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev9MainPath2 extends Level
{
    public Lev9MainPath2(GameScreen gs)
    {
        nameLevel = "Everything must go";
        nameSystem = "Cjebriev";

        setupFunctions(9);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setCluster(100, 900, 2);

        setCluster(-1000, 1000, 2);
        setCluster(1000, 1200, 2);

        setCluster(-3000, 2000, 2);
        setCluster(3800, 2200, 2);

        setCluster(-1000, 7000, 2);
        setCluster(1200, 6200, 2);

        setObstacleField(-500, 3000, 500, 5000, 300, 5000);

        Planet planetGoal = setPlanet(0, 9000, Planet.TypeOrbit.G190);

        setPlayer(planet1);

        setLevel(gs, planetGoal, new Rectangle(-4000, -500, 5000, 10000));
    }
}