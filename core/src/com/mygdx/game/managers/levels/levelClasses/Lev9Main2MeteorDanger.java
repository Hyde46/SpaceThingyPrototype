package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 23.09.2016.
 */

public class Lev9Main2MeteorDanger extends Level
{
    public Lev9Main2MeteorDanger(GameScreen gs)
    {
        nameLevel = "Meteor Danger";
        nameSystem = "Cjebriev";

        setupFunctions(11);

        Planet planet1 = setPlanet(-1500,0, Planet.TypeOrbit.B240);

        setCluster(-1700, 1500, 2);

        setCurrencyVolley(-850, 2000, 20);
        setObstacleField(-850, 2000, -850, 2000, 3, 1000);

        setCluster(0, 2500, 4);

        setObstacleField(850, 3000, 850, 3000, 2, 1000);

        setCluster(1700, 3500, 2);

        setCurrencyVolley(1600, 4250, 20);

        // alternate path
        setPlanet(-500,150, Planet.TypeOrbit.B480);

        setObstacleField(250, 350, 850, 3000, 2, 200);

        setPlanet(1000,550, Planet.TypeOrbit.B320);

        setCurrencyVolley(1350, 1000, 20);

        setPlanet(1700,1450, Planet.TypeOrbit.B320);

        setVisualUpdate(500, 1150, 1);

        setPlanet(1500,2150, Planet.TypeOrbit.B240);
        setPlanet(2100,2550, Planet.TypeOrbit.B480);

        Planet planet2 = setPlanet(1500,5000, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setLevel(gs, planet2, new Rectangle(-2500, -500, 5500, 6000));
    }
}