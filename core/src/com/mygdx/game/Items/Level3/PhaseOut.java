package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class PhaseOut extends Item
{

    private SpaceShip player;

    private static float maxCooldown = 5000;
    private final static float maxPhaseoutTime = 900;

    private float phaseOutTime;

    private boolean isPhasedOut;

    private int side;

    private int flickerCounter;
    private int flickerDelay;
    private int maxFlickerDelay;

    public PhaseOut(int itemPos, int sideToAdd, ItemManager im)
    {
        super();
        this.level = 3;
        this.isPhasedOut = false;
        this.iM = im;
        side = sideToAdd;
        this.levelPos = itemPos;
    }
    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("phaseOut_200x200.png",200,posToRender);
        this.phaseOutTime = maxPhaseoutTime;
        itemName = "Phaseout";
        maxUses = 2;
        uses = maxUses;
        flickerCounter = 0;
        flickerDelay = 0;
        maxFlickerDelay = 3;

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

    public void update(float delta){

        if(stateItem == StateItem.ACTIVATED){
            stateItem = StateItem.EFFECT;

            if(player == null)
                player = iM.getPlayer();

            isPhasedOut = true;
            player.phaseOut(isPhasedOut);

            timeCooldown = maxCooldown;
            flickerCounter = 0;
            flickerDelay = 0;
        }
        if(stateItem == StateItem.EFFECT && isPhasedOut){
            phaseOutTime-=1000*delta;
            if(phaseOutTime<=0){
                effectEndSuper();
                phaseOutTime = maxPhaseoutTime;
                timeCooldown = maxCooldown;
                player.phaseOut(false);
            }
            flickerDelay+=1;
            if(flickerDelay >= maxFlickerDelay){
                flickerCounter +=1;
                flickerDelay = 0;
            }
            player.flicker(flickerCounter%2 == 0);
        }
        if(stateItem == StateItem.COOLDOWN){
            player.flicker(false);
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
        MyGdxGame.game.debugFont.draw(sB,"Phase out Time: "+(int)phaseOutTime, (side*400)+350, 1800);
        MyGdxGame.game.debugFont.draw(sB,"Phased out: "+isPhasedOut, (side*400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown/100), (side*400)+350, 1700);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (side*400)+350, 1650);



    }
}
