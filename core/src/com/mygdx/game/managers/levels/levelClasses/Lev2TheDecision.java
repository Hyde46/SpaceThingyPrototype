package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

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
        nameLevel = "The Decision";
        nameSystem = "Topoga";

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);
        Planet planet2 = setPlanet(-900,500, Planet.TypeOrbit.B240);
        setPlanet(-1850,900, Planet.TypeOrbit.B240);
        Planet planet4 = setPlanet(-2750,1500, Planet.TypeOrbit.B320);
        planet4.setDecisionPlanet(0);

        Planet planet7 = setPlanet(200,1450, Planet.TypeOrbit.B240);
        setPlanet(-100,2350, Planet.TypeOrbit.B240);
        setPlanet(600,2950, Planet.TypeOrbit.B240);
        Planet planet12 = setPlanet(-200,3700, Planet.TypeOrbit.B320);
        planet12.setDecisionPlanet(1);

        Planet planet13 = setPlanet(800,300, Planet.TypeOrbit.B240);
        setPlanet(1550,950, Planet.TypeOrbit.B240);
        setPlanet(2600,700, Planet.TypeOrbit.B480);
        Planet planet16 = setPlanet(2000,1700, Planet.TypeOrbit.B240);
        planet16.setDecisionPlanet(2);

        setPlayer(planet1);

        setMoon(planet2, 400, 30);
        setMoon(planet2, 480, 20);

        setMoon(planet7, 360, 30);
        setMoon(planet7, 500, 20);

        setMoon(planet13, 320, 30);
        setMoon(planet13, 520, 20);

        setCamera(gs, planet16, new Rectangle(-3100,-400,6100,4500));
    }
}