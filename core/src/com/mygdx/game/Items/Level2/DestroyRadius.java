// add visual radius (render circle)

package com.mygdx.game.Items.Level2;

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
public class DestroyRadius extends Item implements IInputAnywhere
{
    private final float RADIUS = 200;

    public DestroyRadius(int itemPos, int sideToAdd, GameScreen gs)
    {
        super();
        this.level = 2;
        this.levelPos = itemPos;
        //this.iM = itemManager;
        this.gs = gs;
        sideInHud = sideToAdd;
    }

    @Override
    public void initialize()
    {
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("artificial-planet-icon-256.png",256,posToRender);
        name = "Destroy Radius";
        maxUses = 10;
        uses = maxUses;
        timeCooldown = 4f;

        if(player == null)  player = gs.getPlayerShip();
    }

    @Override
    public void activate() {
        // show radius
    }

    @Override
    public void effectStart() {
        // hide radius
        // destroy planets near


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
    public void OnTouchAnywhere(TouchData td)
    {
        if(stateItem == StateItem.ACTIVATED)
        {
            Vector2 posPlayer = player.getPosition();
            Vector3 posPress = td.getPosWorldCurrent();
            Vector2 posRender = new Vector2(posPress.x, posPress.y);

            if(uses > 0)
            {
                if(Vector2.dst(posPlayer.x, posPlayer.y, posRender.x, posRender.y) < 900f) // && not inside a orbit??
                {
                    uses--;
                    timeCooldown = 4f;
                    stateItem = StateItem.COOLDOWN;
                    RemovePossibleRadius();

                    gs.addPlanet(posRender);
                }
                else
                {
                    stateItem = StateItem.READY;
                    RemovePossibleRadius();
                }
            }
        }
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

    public void update(float delta)
    {
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
        //     MyGdxGame.game.debugFont.draw(sB,"Hello im Aritifical Planet item", (sideInHud*400)+350, 1800);
        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown), (sideInHud *400)+350, 1750);
        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (sideInHud *400)+350, 1700);
    }
}