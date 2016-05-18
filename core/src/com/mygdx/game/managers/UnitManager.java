package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 5/13/16.
 */
public class UnitManager {

    private Array<Unit> units;

    public UnitManager(){
        units = new Array<Unit>();
    }

    public void addUnit(MoveableUnit u){
        units.add(u);
    }

    public void render(SpriteBatch g){
        for(Unit u :units){
            u.render(g);
        }
    }

    public void renderHitboxes(ShapeRenderer d){
        for(Unit u : units){
            u.renderHitboxes(d);
        }
    }

    public void resetUnits(){
        //TODO
    }

    public Array<Unit> getUnits(){
        return units;
    }

    public void deleteUnit(Unit u){
        //TODO
    }
}
