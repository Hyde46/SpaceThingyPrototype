package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.units.MoveableUnit;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

/**
 * Created by denis on 5/13/16.
 */
public class UnitManager {

    // TODO Deletion management?

    private Array<MoveableUnit> units;

    public UnitManager(){
        units = new Array<MoveableUnit>();
    }

    public void addUnit(MoveableUnit u){
        units.add(u);
    }

    public void render(SpriteBatch g){
        for(MoveableUnit u :units){
            u.render(g);
        }
    }

    public void renderHitboxes(ShapeRenderer d){
        for(MoveableUnit u : units){
            u.renderHitboxes(d);
        }
    }

    public void resetUnits(){
        //TODO
    }

    public Array<MoveableUnit> getUnits(){
        return units;
    }
}
