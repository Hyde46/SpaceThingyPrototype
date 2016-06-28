package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.Level1.SpeedBooser;
import com.mygdx.game.Items.Level2.Break;
import com.mygdx.game.Items.Level3.ArtificialPlanet;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MyGdxGame;

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

    public boolean setItems(int itemIdLeft, int itemIdRight){
        if(itemIdLeft == -1 && itemIdRight != -1){
            addItem(itemIdRight,1,true);
            return true;
        }
        if(itemIdLeft != -1 && itemIdRight == -1){
            addItem(itemIdLeft,0,true);
            return true;
        }
        if(itemIdLeft != -1  && itemIdRight != -1){
            addItem(itemIdLeft,0,false);
            addItem(itemIdRight,1,false);
            return true;
        }
        return false;
    }

    public boolean addItem(int itemId, int sideToAdd, boolean isOneItem){
        if(itemId == itemIds[0] || itemId == itemIds[1]){
            return false;
        }
       // int itemPos = getItemPos(sideToAdd);
        int itemPos = isOneItem?2:sideToAdd;

        switch (itemId) {
            case 1: items[sideToAdd] = new SpeedBooser(itemPos,sideToAdd,this);
                    break;
            case 6:items[sideToAdd] = new Break(itemPos,sideToAdd,this);
                    break;
            //case 7:items[sideToAdd] = new ArtificialPlanet(itemPos,sideToAdd,this,GameScreen gs);
                    //break;
            default: break;
        }
        itemIds[sideToAdd] = itemId;
        items[sideToAdd].initialize();
        InputManager.get.Register(items[sideToAdd]);

        return true;
    }

    public boolean removeItemSlot(int sideToRemove){

        return true;
    }

    public void render(SpriteBatch batch){
        //Debug
        if(items[0] != null){
            MyGdxGame.game.debugFont.draw(batch,"Item 1: "+items[0].getItemName(),350,1900);
            MyGdxGame.game.debugFont.draw(batch,"Status: "+items[0].getState(),350,1850);
        }else{
            MyGdxGame.game.debugFont.draw(batch,"Item 1: NONE",350,1900);
        }
        if(items[1] != null){
            MyGdxGame.game.debugFont.draw(batch,"Item 2: "+items[1].getItemName(),750,1900);
            MyGdxGame.game.debugFont.draw(batch,"Status: "+items[1].getState(),750,1850);
        }else{
            MyGdxGame.game.debugFont.draw(batch,"Item 2: NONE",700,1900);
        }

        //button render
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
