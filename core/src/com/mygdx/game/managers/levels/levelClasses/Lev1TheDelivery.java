package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev1TheDelivery extends Level
{
    public Lev1TheDelivery(GameScreen gs)
    {
        nameLevel = "The Delivery";
        nameSystem = "Bera";

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setPlanet(-350, 900, Planet.TypeOrbit.B240);
        setPlanet(520, 1250, Planet.TypeOrbit.B240);
        setPlanet(-300, 1600, Planet.TypeOrbit.B320);

        Planet planet3 = setPlanet(-250, 2400, Planet.TypeOrbit.G240);

        setItemCurrency(-250, 2020, 100);

        setPlayer(planet1);

        setCamera(gs, planet3, new Rectangle(-400, -200, 1000, 2800));
    }
}