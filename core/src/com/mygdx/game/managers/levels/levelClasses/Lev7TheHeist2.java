package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev7TheHeist2 extends Level
{
    public Lev7TheHeist2(GameScreen gs)
    {
        nameLevel = "The Heist";
        nameSystem = "Loki";

        setupFunctions(7);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setObstacleMoving(200, 900, 800, 300, 3);

        setPlanet(1000,1000, Planet.TypeOrbit.B240);

        setObstacleMoving(1000, 1400, 1400, 900, 2);

        setCurrencyVolley(1500, 1500, 10);

        setPlanet(1700,1900, Planet.TypeOrbit.B320);

        setCluster(1700, 3000, 3);

        setObstacleField(1300, 2500, 2000, 2800, 5, 2);

        setCluster(300, 2600, 2);

        setCurrencyVolley(300, 3400, 10);

        setObstacleMoving(1500, 00, 200, 300, 3);

        setObstacleMoving(600, 5000, 300, 3000, 1);

        Planet goalPlanet =  setPlanet(200,4000, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, goalPlanet, new Rectangle(-400, -400, 4000, 5000));



    }
}