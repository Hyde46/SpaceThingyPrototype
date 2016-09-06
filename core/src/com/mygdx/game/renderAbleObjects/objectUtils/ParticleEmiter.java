package com.mygdx.game.renderAbleObjects.objectUtils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Particle;

/**
 * Created by denis on 8/1/16.
 */
public class ParticleEmiter {

    private Array<Particle> particles = new Array<Particle>();
    private Array<Integer> removableParticleIndices = new Array<Integer>();

    public ParticleEmiter(){

    }

    public void update(float delta){
        for(Particle p : particles){
            p.update(delta);

        }

    }

    public void render(SpriteBatch g ){
        for(Particle p : particles){
            p.render(g);
        }
    }
}
