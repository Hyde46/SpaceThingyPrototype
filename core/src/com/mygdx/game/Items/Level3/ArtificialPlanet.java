package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class ArtificialPlanet extends Item
{



    private int side;

    public ArtificialPlanet(int itemPos, int sideToAdd, ItemManager itemManager, GameScreen gs)
    {
        super();
        this.level = 3;
        this.levelPos = itemPos;
        this.iM = itemManager;
        side = sideToAdd;
    }

    


  
    @Override
    public void initialize(){

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
    public void OnTouch(TouchData td) {
        /*
        if(stateItem == StateItem.READY){
            stateItem = StateItem.ACTIVATED;

             

            if(player == null)
                player = iM.getPlayer();


SetPossibleRadius();


            timeCooldown = maxCooldown;
        };


if(stateItem == StateItem.ACTIVATED){
Vector2 posPlayer;
Vector2 posPress = td.posCurrent;

if(Vector2.Distance < 300)
{
RemovePossibleRadius();
gs.addPlanet(Vector2 pos);




stateItem = StateItem.READY;



}

}*/
    }

public void SetPossibleRadius(){}

public void RemovePossibleRadius(){}

    public void update(float delta)
    {
        /*
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
*/
    }


     public void render(SpriteBatch sB){
         /*
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(sB);

        //Debug
        MyGdxGame.game.debugFont.draw(sB,"Boost Time: "+(int)boostTime, (side*400)+350, 1800);
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown/100), (side*400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (side*400)+350, 1700);

    */

    }
}
