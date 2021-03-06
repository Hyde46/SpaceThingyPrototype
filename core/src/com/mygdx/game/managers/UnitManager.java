package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.renderAbleObjects.units.Planet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;

/**
 * Created by denis on 5/13/16.
 */
public class UnitManager {

    private Array<Unit> units;
    private Array<Decoration> decos;

    public UnitManager(){
        units = new Array<Unit>();
        decos = new Array<Decoration>();
    }

    public void addUnit(Unit u){
        units.add(u);
    }
    public void addDeco(Decoration d) { decos.add(d);}

    public void render(SpriteBatch g){

        for(Unit u :units){
            u.render(g);
        }
        for(Decoration d : decos){
            d.render(g);
        }
    }

    public void renderHitboxes(ShapeRenderer d){
        for(Unit u : units){
            u.renderHitboxes(d);
        }
    }

    public void resetUnits(){
        for(Unit u : units)
            u.dispose();
        for(Decoration d : decos)
            d.dispose();
        units.clear();
        decos.clear();
        //TODO
    }

    public Array<Unit> getUnits(){
        return units;
    }

    public void deleteUnit(Unit u){
        // xD wundere mich warum mein item tut aber die unit nicht deleted wird
        //TODO
        if(units.contains(u, false))
        {
            System.out.println("removed");
            units.removeValue(u, false);
        }
    }

    public SpaceShip getPlayerShip(){
        for(Unit u : units){
            if(u.getUnitType() == Unit.UnitType.SPACE_SHIP )
                return (SpaceShip)u;
        }
        return null;
    }

    public Planet getPlanetConnectedToPlayer(){
        for(Unit u : units){
            if(u.getUnitType() == Unit.UnitType.SPACE_SHIP){
                return ((SpaceShip)u).getConnectedPlanet();
            }
        }
        return null;
    }
}
