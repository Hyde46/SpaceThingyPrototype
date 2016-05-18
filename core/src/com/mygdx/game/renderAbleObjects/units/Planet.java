package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/13/16.
 */
public class Planet extends MoveableUnit {

    //TODO higher abstraction level from planet downwards !

    private static int PLANETIDS = 0;
    /*
    PlanetSystem class ?
     */
    private int orbitRadius;
    private int planetRadius;
    private int planetId;

    public Planet(Vector2 pos, Vector2 spriteDimension, int spriteID,
                  int orbitRadius, int planetRadius){
        super(pos,spriteDimension,spriteID);
        this.orbitRadius = orbitRadius;
        this.planetRadius = planetRadius;
        this.hitbox = new Circle(pos.x,pos.y,planetRadius);
        this.targetHitbox = new Circle(pos.x,pos.y,planetRadius);
        this.planetId = PLANETIDS++;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d){
        if(!isRendered || isDeletable){
            return;
        }
        d.circle(this.position.x,this.position.y,planetRadius);
        d.circle(this.position.x,this.position.y,orbitRadius);
    }

    @Override
    public void update(float delta){

    }

    public int getOrbitRadius(){
        return orbitRadius;
    }

    public int getPlanetRadius(){
        return planetRadius;
    }

    @Override
    public int getPlanetId(){
        return planetId;
    }

    public void reset(){}

}
