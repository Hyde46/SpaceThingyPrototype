package com.mygdx.game.Items.Level2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class Break extends Item
{
    private SpaceShip player;

    private final static float maxCooldown = 5000;
    private final static float maxBoostTime = 1000;

    private float boostTime;
    private float boostScl;

    private boolean isActivated;

    private int side;

    public Break(int itemPos,int sideToAdd, ItemManager itemManager)
    {
        super();
        this.level = 2;
        this.levelPos = itemPos;
        this.iM = itemManager;
        isActivated = false;
        side = sideToAdd;
    }

    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("break_200x200.png",200,posToRender);
        boostTime = maxBoostTime;
        boostScl = -1.1f;
        itemName = "Break";
        maxUses = 3;
        uses = maxUses;
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
    public void OnRelease(TouchData td) {
        isActivated = false;
    }

    @Override
    public void OnHold(TouchData td) {
        isActivated = true;
    }

    public void update(float delta)
    {
        if(stateItem == StateItem.ACTIVATED){
            stateItem = StateItem.EFFECT;

            if(player == null)
                player = iM.getPlayer();

            timeCooldown = maxCooldown;
        }
        if(stateItem == StateItem.EFFECT && isActivated){
            player.boost(boostScl,delta);
            boostTime-=1000*delta;
            if(boostTime <= 0){
                effectEndSuper();
                boostTime = maxBoostTime;
                timeCooldown = maxCooldown;
            }
        }
        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -=1000*delta;
            if(timeCooldown <= 0){
                deactivateSuper();
                timeCooldown = 0;
            }
        }

    }


    public void render(SpriteBatch sB){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(sB);

        //Debug
        MyGdxGame.game.debugFont.draw(sB,"Break Time: "+(int)boostTime, (side*400)+350, 1800);
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown/100), (side*400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (side*400)+350, 1700);



    }


}
