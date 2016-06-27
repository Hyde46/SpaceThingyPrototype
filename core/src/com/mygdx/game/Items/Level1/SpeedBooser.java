package com.mygdx.game.Items.Level1;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class SpeedBooser extends Item
{
    private SpaceShip player;

    private final static float maxCooldown = 10000;

    private float boostTime;
    private float boostScl;

    public SpeedBooser(int itemPos, ItemManager itemManager)
    {
        super();
        this.level = 1;
        this.levelPos = itemPos;
        this.iM = itemManager;
    }

    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("speedBooster_200x200.png",200,posToRender);
        boostTime = 1000;
        boostScl = 0.01f;
    }

    @Override
    public void activate() {

    }

    @Override
    public void effectStart() {

    }

    @Override
    public void effectEnd() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void OnHold(TouchData td) {
        if(stateItem == StateItem.EFFECT){
            player.boost(boostScl);
            boostTime-=1;
            if(boostTime <= 0){
                effectEndSuper();
                timeCooldown = maxCooldown;
            }
        }
    }

    public void update(float delta)
    {
        if(stateItem == StateItem.ACTIVATED){
            stateItem = StateItem.EFFECT;

            if(player == null)
                player = iM.getPlayer();

            timeCooldown = maxCooldown;
        }
        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -=1;
            if(timeCooldown <= 0){
                deactivateSuper();
            }
        }

    }


}
