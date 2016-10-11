package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev3LeapOfFaith extends Level
{
    public Lev3LeapOfFaith(GameScreen gs)
    {
        nameLevel = "Leap of Faith";
        nameSystem = "Pero";

        setupFunctions(3);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setPlanet(-350, 700, Planet.TypeOrbit.B240);
        setPlanet(520, 900, Planet.TypeOrbit.B240);

        setCurrencyVolley(-50, 2050, 12);

        setPlanet(-300, 3200, Planet.TypeOrbit.B320);
        setPlanet(310, 2800, Planet.TypeOrbit.B320);

        Planet planetGoal = setPlanet(0, 3800, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-800, -400, 1600, 4500));
    }
}