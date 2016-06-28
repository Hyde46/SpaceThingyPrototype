package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

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
        this.gs = gs;
        side = sideToAdd;

    }
  
    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("speedBooster_200x200.png",200,posToRender);
        itemName = "Artificial Planet";
        maxUses = 10;
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

    SpaceShip ss;

    @Override
    public void OnTouch(TouchData td)
    {
        if(stateItem == StateItem.READY)
        {
            activateSuper();

            if(gs == null) System.out.println("gs null???");

            //if(ss == null) ss = gs.getPlayerShip();

            SetPossibleRadius();

            timeCooldown = 5f;
        };

        if(stateItem == StateItem.ACTIVATED)
        {
            Vector2 posPlayer = ss.getPosition();
            Vector2 posPress = td.getPosCurrent();

            if(Vector2.dst(posPlayer.x, posPlayer.y, posPress.x, posPress.y) < 300)
            {
                timeCooldown = 5f;
                stateItem = StateItem.COOLDOWN;
                RemovePossibleRadius();

                gs.addPlanet(posPress);

                stateItem = StateItem.READY;
            }

        }
    }

public void SetPossibleRadius(){}

public void RemovePossibleRadius(){}

    public void update(float delta)
    {
        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -=1000*delta;
            if(timeCooldown <= 0){
                deactivateSuper();
                timeCooldown = 0;
            }
        }
    }


     public void render(SpriteBatch sB)
     {

        if(!isActive || tex == null){
            return;
        }
        sprite.draw(sB);

        //Debug
        MyGdxGame.game.debugFont.draw(sB,"Hello im Aritifical Planet item", (side*400)+350, 1800);
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown/100), (side*400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (side*400)+350, 1700);
    }
}
