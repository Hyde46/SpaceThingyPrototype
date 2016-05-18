package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/13/16.
 */
public class Planet extends Unit {

    private float orbitRadius;
    private float planetRadius;

    public Planet(){
        super();
    }

    public void initialize( Vector2 pos, float orbitRadius, float planetRadius ){
        this.orbitRadius = orbitRadius;
        this.planetRadius = planetRadius;
        this.hitbox = new Circle(pos.x,pos.y,planetRadius);
    }

    @Override
    public void moveUnit(){
        this.position.set(this.targetPosition);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d){
        if(!isActive){
            return;
        }
        d.circle(this.position.x,this.position.y,planetRadius);
        d.circle(this.position.x,this.position.y,orbitRadius);
    }

    @Override
    public void update(float delta){

    }

    public float getOrbitRadius(){
        return orbitRadius;
    }

    public float getPlanetRadius(){
        return planetRadius;
    }


}
