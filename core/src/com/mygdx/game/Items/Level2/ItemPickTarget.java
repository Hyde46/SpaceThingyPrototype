package com.mygdx.game.Items.Level2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.decorations.ItemPickerOrbit;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class ItemPickTarget extends Item
{


    private float pickerMaxRadius;

    private ItemPickerOrbit itemPickerOrbit;

    private SpaceShip player;
    private Vector2 spaceShipPosition;

    private final static float maxCooldown = 5000;
    private final static float maxPickTime = 3500;

    private float pickerTime;


    public ItemPickTarget(int itemPos,int sideToAdd)
    {
        super();
        this.level = 2;
        sideInHud = sideToAdd;
        levelPos = itemPos;
        itemPickerOrbit = new ItemPickerOrbit();
    }

    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("itempickertarget_200x200.png",200,posToRender);
        pickerMaxRadius = 240;
        player = ItemManager.get.getPlayer();
        spaceShipPosition = player.getPosition();
        itemPickerOrbit.initialize(1,pickerMaxRadius,spaceShipPosition,"orbit_"+(int)(pickerMaxRadius*2)+ "x" + (int) (pickerMaxRadius * 2) + ".png");
        name = "Picker Target";
        uses = 3;
        ItemManager.get.addUnitToManager(itemPickerOrbit);
        player.setItemPickerOrbit(itemPickerOrbit);
        pickerTime = maxPickTime;
    }

    @Override
    public void OnTouch(TouchData td) {
        if(ItemManager.get.getPlayer().isCollided()||ItemManager.get.hasLevelEnded() || ItemManager.get.getPlayer().isInOrbit())
            return;
        if(stateItem == StateItem.READY && uses > 0){
            activateSuper();
            uses--;
        }else if(stateItem == StateItem.EFFECT){

            itemPickerOrbit.switchActive(false);
            effectEndSuper();
        }
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
    public void update(float delta){
        if(ItemManager.get.getPlayer().isInOrbit()){
            itemPickerOrbit.switchActive(false);
            effectEndSuper();
        }
        if(stateItem == StateItem.ACTIVATED){
            stateItem = StateItem.EFFECT;
            timeCooldown = maxCooldown;
            itemPickerOrbit.switchActive(true);
        }
        if(stateItem == StateItem.EFFECT ){
            itemPickerOrbit.updatePosition(player.getPosition());
            pickerTime -= 1000*delta;
            if(pickerTime <= 0){
                itemPickerOrbit.switchActive(false);
                effectEndSuper();
            }
        }
        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -=1000*delta;
            if(timeCooldown <= 0){
                deactivateSuper();
                timeCooldown = 0;
                pickerTime = maxPickTime;
            }
        }
    }

    public void render(SpriteBatch sB){

        if(!isActive || tex == null){
            return;
        }

        sprite.draw(sB);

        //Debug
//        MyGdxGame.game.debugFont.draw(sB,"Pick Time: "+(int)pickerTime, (sideInHud *400)+350, 1800);
//        MyGdxGame.game.debugFont.draw(sB,"Cooldown: "+(int)(timeCooldown/100), (sideInHud *400)+350, 1750);
//        MyGdxGame.game.debugFont.draw(sB,"Uses: "+uses, (sideInHud *400)+350, 1700);

    }
}
