package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev0TheBeginning extends Level
{
    public Lev0TheBeginning(GameScreen gs)
    {
        nameLevel = "The Beginning";
        nameSystem = "Tengo";

        setupFunctions(0);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setPlanet(-50, 900, Planet.TypeOrbit.B480);

        setPlanet(-750, 1450, Planet.TypeOrbit.B320);

        setPlanet(200, 1800, Planet.TypeOrbit.B320);
        setPlanet(-720, 450, Planet.TypeOrbit.B320);
        Planet planet3 = setPlanet(-350, 2200, Planet.TypeOrbit.G320);

        setPlayer(planet1);

        setLevel(gs, planet3, new Rectangle(-1090, -320, 1800, 2900));
    }
}