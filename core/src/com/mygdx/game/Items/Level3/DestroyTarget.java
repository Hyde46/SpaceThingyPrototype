package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.InputManager.IInputAnywhere;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class DestroyTarget extends Item implements IInputAnywhere
{
    private int side;

    public DestroyTarget(int itemPos, int sideToAdd, ItemManager itemManager, GameScreen gs)
    {
        super();
        this.level = 3;

        this.level = 3;
        this.levelPos = itemPos;
        this.iM = itemManager;
        this.gs = gs;
        side = sideToAdd;
    }
    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("destroy-icon-256.png",256,posToRender);
        name = "Destroy";
        maxUses = 10;
        uses = maxUses;
        timeCooldown = 2f;
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
    public void OnTouch(TouchData td)
    {
        if(stateItem == StateItem.READY && uses > 0)
        {
            activateSuper();
            SetPossibleRadius();
        }
    }

    public void SetPossibleRadius(){}
    public void RemovePossibleRadius(){}

    @Override
    public void OnTouchAnywhere(TouchData td)
    {
        if(stateItem == StateItem.ACTIVATED)
        {
            //Vector2 posPlayer = ss.getPosition();
            Vector3 posPress = td.getPosWorldCurrent();
            Vector2 posRender = new Vector2(posPress.x, posPress.y);

            if(uses > 0)
            {
                if(gs.tryDestroyTarget(posRender))
                {
                    uses--;
                    timeCooldown = 4f;
                    stateItem = StateItem.COOLDOWN;
                    RemovePossibleRadius();
                }
                else
                {
                    stateItem = StateItem.READY;
                    RemovePossibleRadius();
                }
            }
        }
    }

    public void update(float delta)
    {
        //       timeCooldown -= 1f;
        //      if(timeCooldown <= 0) timeCooldown = 1000f;

        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -= delta;
            if(timeCooldown <= 0){
                timeCooldown = 0;
                deactivateSuper();
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
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown), (side*400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (side*400)+350, 1700);
    }
}
