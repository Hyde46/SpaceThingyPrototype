package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev2TheDecision extends Level
{
    /*
        -1 None
        0 left
        1 mid
        2 right
     */
    public static int lastFinishedSide = -1;

    public Lev2TheDecision(GameScreen gs)
    {
        nameLevel = "Decision";
        nameSystem = "Topoga";

        setupFunctions(2);

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        Planet planet2 = setPlanet(-900,500, Planet.TypeOrbit.B240);
        setPlanet(-2500,550, Planet.TypeOrbit.B240);
        setPlanet(-1850,900, Planet.TypeOrbit.B240);
        setItemCurrency(-2600, 1100, 10);
        setItemCurrency(-2700, 1000, 10);
        setItemCurrency(-2500, 1200, 10);
        Planet planet4 = setPlanet(-2750,1500, Planet.TypeOrbit.G320);
        planet4.setDecisionPlanet(0);

        Planet planet7 = setPlanet(200,1450, Planet.TypeOrbit.B240);
        setItemCurrency(250, 900, 100);
        setItemCurrency(230, 600, 100);
        setPlanet(-100,2350, Planet.TypeOrbit.B240);
        setPlanet(600,2950, Planet.TypeOrbit.B240);
        Planet planet12 = setPlanet(-200,3700, Planet.TypeOrbit.G320);
        planet12.setDecisionPlanet(1);

        Planet planet13 = setPlanet(800,300, Planet.TypeOrbit.B240);
        setPlanet(1550,950, Planet.TypeOrbit.B240);
        setPlanet(2600,700, Planet.TypeOrbit.B480);
        setPlanet(1800,200, Planet.TypeOrbit.B320);
        setVisualUpdate(2200,800,2);
        Planet planet16 = setPlanet(2000,1700, Planet.TypeOrbit.G320);
        planet16.setDecisionPlanet(2);

        setPlayer(planet1);

        setMoon(planet2, 400, 30);
        setMoon(planet2, 480, 20);

        setMoon(planet7, 360, 30);
        setMoon(planet7, 500, 20);

        setMoon(planet13, 320, 30);
        setMoon(planet13, 520, 20);

        setLevel(gs, planet16, new Rectangle(-3100,-400,6300,4500));
    }
}