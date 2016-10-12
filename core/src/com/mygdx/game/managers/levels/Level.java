package com.mygdx.game.managers.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.managers.UnitManager;
import com.mygdx.game.managers.background.ParallaxBackgroundManager;
import com.mygdx.game.renderAbleObjects.units.Asteroid;
import com.mygdx.game.renderAbleObjects.units.CurrencyPickable;
import com.mygdx.game.renderAbleObjects.units.MovingObstacle;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.UpgradePickable;
import com.mygdx.game.renderAbleObjects.units.VisualPickable;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.GenRandomSeed;
import com.mygdx.game.utils.SpacePhysiX;
import java.util.Random;

/**
 * Created by denis on 5/18/16.
 */

public abstract class Level
{
    public UnitManager unitManager;
    public SpacePhysiX spacePhysiX;
    public ParallaxBackgroundManager parallaxBackgroundManager;

    public String nameLevel;
    public String nameSystem;

    protected SpaceShip playerShip;

    private GenRandomSeed genRand;

    protected void setupFunctions(long seed)
    {
        genRand = new GenRandomSeed(seed);
        unitManager = new UnitManager();
        spacePhysiX = new SpacePhysiX();
        parallaxBackgroundManager = new ParallaxBackgroundManager();
    }

    protected UpgradePickable setItemUpdate(int x, int y, int idItem)
    {
        UpgradePickable item = new UpgradePickable();
        item.initialize(idItem, new Vector2(x,y));
        unitManager.addUnit(item);
        return item;
    }

    protected VisualPickable setVisualUpdate(int x, int y, int idItem)
    {
        VisualPickable item = new VisualPickable();
        item.initialize(idItem, new Vector2(x,y));
        unitManager.addUnit(item);
        return item;
    }

    protected CurrencyPickable setItemCurrency(int x, int y, int amount)
    {
        CurrencyPickable itemCurr = new CurrencyPickable();
        itemCurr.initialize(5,new Vector2(x,y),amount);
        unitManager.addUnit(itemCurr);
        return itemCurr;
    }

    protected void setCurrencyVolley(int x, int y, int amount)
    {
        float lengthSide = 110;
        float angle = (float)Math.random() * 90;

        setItemCurrency(x,y, amount);

        for(int i = 0; i < 4; i++)
        {
            Vector2 temp = new Vector2(1,1).setAngle(angle + i * 90).setLength(lengthSide);
            setItemCurrency(x + (int)temp.x, y + (int)temp.y, amount);
        }
    }

    protected Planet setPlanet(int x, int y, Planet.TypeOrbit typeOrbit)
    {
        //int idSpriteRandom = (int)(Math.random() * 9);
        int idSpriteRandom = genRand.getInt((24-0)+1)+0;
        Planet.TypePlanet typePlanet = Planet.TypePlanet.values()[idSpriteRandom];
        float rotationRandom = (float)Math.random() * 360;
        float gravityRandom = genRand.getFloat() * (12f-1f)+1f;

        Planet planet = new Planet();
        planet.initialize(new Vector2(x,y), typePlanet, typeOrbit, rotationRandom, gravityRandom);
        unitManager.addUnit(planet);

        InputManager.get.register(planet);

        return planet;
    }

    protected Planet setMoon(Planet parent, float radius, float speedRot)
    {
        //int idxSprite = 9 + (int)(Math.random() * 3);
        int idxSprite = genRand.getInt((29-25)+1)+25;
        Planet.TypePlanet typePlanet = Planet.TypePlanet.values()[ idxSprite ];

        int idxTypeOrbit = 6 + (int)(Math.random() * 2);
        Planet.TypeOrbit typeOrbit = Planet.TypeOrbit.values()[idxTypeOrbit];

        float rotationRandom = (float)Math.random() * 360;
        float gravityRandom = genRand.getFloat() * (5f-1f)+1f;

        int dir = (Math.random() < 0.5)? -1 : 1;

        float lengthSide = (int)(Math.sqrt(radius * radius + radius * radius) / 2);

        Vector2 temp = new Vector2(1,1).setToRandomDirection();
        temp.x *= lengthSide;
        temp.y *= lengthSide;

        Vector2 posParent = parent.getPosition();

        Vector2 posNew = new Vector2(posParent.x + temp.x, posParent.y + temp.y);

        Planet planet = new Planet();
        planet.initialize(posNew, typePlanet, typeOrbit, rotationRandom, gravityRandom);
        planet.connectToPlanet(parent);
        planet.setRotationSpeed(speedRot,dir);

        unitManager.addUnit(planet);

        InputManager.get.register(planet);

        return planet;
    }

    protected Asteroid setAsteroid(int xStart, int yStart, int xEnd, int yEnd, float speed)
    {

        Asteroid asteroid = new Asteroid();
        //asteroid.initialize(new Vector2(xStart, yStart, );
        return null;
    }

    protected MovingObstacle setObstacleMoving(int xStart, int yStart, int xEnd, int yEnd, float speed)
    {
        String pathTexture = "asteroid_80x80_" + genRand.getInt(2) + ".png";
        float rotationRandom = genRand.getFloat() * 360;

        float distance = Vector2.dst(xStart, yStart, xEnd, yEnd);

        Vector2 toGoal = new Vector2(xEnd - xStart, yEnd - yStart);
        toGoal.x /= distance * 1/speed;
        toGoal.y /= distance * 1/speed;

        MovingObstacle obstacle = new MovingObstacle();
        obstacle.initialize(new Vector2(xStart,yStart), toGoal, distance, 40, pathTexture, rotationRandom);
        unitManager.addUnit(obstacle);

        return obstacle;
    }

    protected void setObstacleField(int xStart, int yStart, int xEnd, int yEnd, int amount, float deviation)
    {
        for(int i = 0; i < amount; i++)
        {
            float xDeviation = genRand.getFloat() * deviation - deviation;
            float yDeviation = genRand.getFloat() * deviation - deviation;

            Vector2 posStart;
            Vector2 posEnd;

            if(i % 2 == 0)
            {
                posStart = new Vector2(xEnd + xDeviation, yEnd + yDeviation);
                posEnd = new Vector2(xStart + xDeviation, yStart + yDeviation);
            }
            else
            {
                posStart = new Vector2(xStart + xDeviation, yStart + yDeviation);
                posEnd = new Vector2(xEnd + xDeviation, yEnd + yDeviation);
            }

            float speed = 1 + genRand.getFloat() * 3.5f;

            setObstacleMoving((int)posStart.x, (int)posStart.y, (int)posEnd.y, (int)posEnd.y, speed);
        }
    }

    protected SpaceShip setPlayer(Planet parent)
    {
        Vector2 posParent = parent.getPosition();
        SpaceShip playerShip = new SpaceShip();
        playerShip.initialize(new Vector2(posParent.x + 160, 0), new Vector2(0, 400), parent, 160, new Vector2(40,40));
        unitManager.addUnit(playerShip);
        this.playerShip = playerShip;
        parent.setGravityToZero();
        return playerShip;
    }

    protected Planet setCluster(int x, int y, int nOrbiting)
    {
        Planet.TypeOrbit typeOrbit = (genRand.getFloat() < 0.5)? Planet.TypeOrbit.B320 : Planet.TypeOrbit.B480;
        Planet parent = setPlanet(x, y, typeOrbit);

        for(int i = 0; i < nOrbiting; i++)
        {
            float radiusOrbiter = 280 + i * 240 + genRand.getFloat() * 260;
            float speedOrbiter = 5 + genRand.getFloat() * 20;
            setMoon(parent, radiusOrbiter, speedOrbiter);
        }

        return parent;
    }

    protected void setCamera(GameScreen gs, Planet goal, Rectangle bounds)
    {
        spacePhysiX = new SpacePhysiX();
        spacePhysiX.initializePhysics(unitManager.getUnits(),gs);

        gs.cM.initializeCamera(playerShip,goal.getPosition());

        if(bounds == null) bounds = new Rectangle(-2000,-2000,4000,4000);
        spacePhysiX.initWorldBounds(bounds);

        parallaxBackgroundManager = new ParallaxBackgroundManager();
        parallaxBackgroundManager.setLayers(4,true);
        gs.cM.addPBM(parallaxBackgroundManager);
    }
}