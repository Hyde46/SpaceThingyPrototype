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

        setCurrencyVolley(-1600, 750, 100);

        setCluster(-1700, 1500, 2);

        setObstacleField(-850, 2000, -850, 2000, 3, 1000);

        setCluster(0, 2500, 4);

        setVisualUpdate(850, 3000, 1);

        setObstacleField(850, 3000, 850, 3000, 3, 1000);

        setCluster(1700, 3500, 2);

        setCurrencyVolley(1600, 4250, 100);

        Planet planet2 = setPlanet(1500,5000, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planet2, new Rectangle(-2500, -500, 6000, 6000));
    }
}