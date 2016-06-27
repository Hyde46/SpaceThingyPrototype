package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.Level1.SpeedBooser;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.GameScreen;

/**
 * Created by Denis on 27.06.2016.
 */
public class ItemManager {

    //some sort of safedata, with informations about which items the player has is needed

    private Item[] items;
    private int[] itemIds;

    private int itemCounter;

    private GameScreen gs;

    public ItemManager(){
        items = new Item[2];
        itemIds = new int[2];
        items[0] = null;
        items[1] = null;
        itemIds[0] = -1;
        itemIds[1] = -1;
    }

    public void initialize(GameScreen gs){
        this.gs = gs;
    }

    public boolean addItem(int itemId, int sideToAdd){
        if(itemId == itemIds[0] || itemId == itemIds[1]){
            return false;
        }
        int itemPos = getItemPos(sideToAdd);

        switch (itemId) {
            case 1: items[sideToAdd] = new SpeedBooser(itemPos,this);
                    itemIds[sideToAdd] = itemId;
                    items[sideToAdd].initialize();
                    InputManager.get.Register(items[sideToAdd]);
                    break;
            default: break;
        }

        return true;
    }

    public boolean removeItemSlot(int sideToRemove){

        return true;
    }

    private int getItemPos(int sideToAdd){
        if(     (itemIds[0] == -1 && itemIds[1] == -1) ||
                (itemIds[sideToAdd] != -1 && itemIds[1-sideToAdd] == -1))
        {
            return 2;
        }

        if(itemIds[sideToAdd] == -1 && itemIds[1-sideToAdd] != -1){
            return sideToAdd;
        }
        return 2;
    }

    public void render(SpriteBatch batch){
        if(items[0] != null) {
            items[0].render(batch);
        }
        if(items[1] != null){
            items[1].render(batch);
        }
    }

    public void update(float delta){
        if(items[0] != null) {
            items[0].update(delta);
        }
        if(items[1] != null){
            items[1].update(delta);
        }
    }

    public SpaceShip getPlayer(){
        return gs.getPlayerShip();
    }
}
