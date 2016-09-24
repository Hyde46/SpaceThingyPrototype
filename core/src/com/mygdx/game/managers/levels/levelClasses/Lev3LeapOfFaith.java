package com.mygdx.game.managers.levels.levelClasses;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.managers.levels.Level;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.MovingObstacle;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by ilost on 18.09.2016.
 */

public class Lev3LeapOfFaith extends Level
{
    public Lev3LeapOfFaith(GameScreen gs)
    {
        nameLevel = "Leap of Faith";
        nameSystem = "Pero";

        Planet planet1 = setPlanet(0,0, Planet.TypeOrbit.B320);

        setPlanet(-350, 700, Planet.TypeOrbit.B240);
        setPlanet(520, 900, Planet.TypeOrbit.B240);

        setCurrencyVolley(-50, 2050, 100);

        setPlanet(-300, 3200, Planet.TypeOrbit.B320);
        setPlanet(310, 2800, Planet.TypeOrbit.B320);

        Planet planetGoal = setPlanet(0, 3800, Planet.TypeOrbit.G240);

        setPlayer(planet1);

        setCamera(gs, planetGoal, new Rectangle(-400, -200, 1000, 4500));
    }
}