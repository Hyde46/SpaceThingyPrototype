package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

import javax.xml.crypto.Data;

/**
 * Created by Vali on 12.07.2016.
 */
public class EquipButton extends Decoration implements IInputHandler{
    private int orderId;
    private int itemId;
    private boolean isEquipped;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int orderId, int itemId, boolean isEquipped){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.orderId = orderId;
        this.itemId = itemId;
        this.isEquipped = isEquipped;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * function to change texture
     * @param path
     */
    public void changeTexture(String path){
        initializeTexture(spriteDimension, 0, path);
    }

    /**
     * getter for item id
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    @Override
    public void OnTouch(TouchData td) {
        HangarScreen screen = (HangarScreen) MyGdxGame.game.current;
        if(!isEquipped) {
            Array<Slot> itemSlots = screen.getItemSlots();
            int currentId = screen.getCurrentOrderId();
            //reinitialize the last item slot, so that it is now deactivated again
            if (currentId != -1) {
                itemSlots.get(currentId).initialize(itemSlots.get(currentId).getPosition(), (int) itemSlots.get(currentId).getSpriteDimensions().x, (int) itemSlots.get(currentId).getSpriteDimensions().y, "item_slot.png");
            }
            //initialize the object again with a different texture path
            itemSlots.get(orderId).initialize(itemSlots.get(orderId).getPosition(), (int) itemSlots.get(orderId).getSpriteDimensions().x, (int) itemSlots.get(orderId).getSpriteDimensions().y, "item_slot_activated.png");
            screen.setCurrentOrderId(orderId);
            screen.setCurrentItemId(itemId);
            screen.setShowPopUp(true);
            changeTexture("unequip_button.png");
            isEquipped = true;
        }else{
            if(DataPers.dataH().getSlot1() == itemId){
                screen.getSelectedSlot1().changeTexture("item_icon.png");
                screen.getSlot1().setItemId(-1);
            }else{
                screen.getSelectedSlot2().changeTexture("item_icon.png");
                screen.getSlot2().setItemId(-1);
            }
            screen.saveSettings();
            changeTexture("equip_button.png");
            isEquipped = false;
        }
    }

    @Override
    public void OnRelease(TouchData td) {

    }

    @Override
    public void OnDrag(TouchData td) {

    }

    @Override
    public void OnHold(TouchData td) {

    }

    @Override
    public void OnSwipe(TouchData td) {

    }

}
