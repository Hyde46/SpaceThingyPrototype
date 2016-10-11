package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 17.08.2016.
 */
public class SlotIcon extends Decoration implements IInputHandler{

    private int itemId;
    private int slotId;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int slotId)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, MyGdxGame.game.screenHeight-position.y-height, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
        itemId = 0;
        this.slotId = slotId;
        isUI = true;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * getter for id of item, used in Hangarscreen
     * @return id
     */
    public int getItemId(){
        return  itemId;
    }

    /**
     * setter for item id, used in EquipButton
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public void OnTouch(TouchData td){

        HangarScreen screen = (HangarScreen) MyGdxGame.game.current;
        if(screen.getShowPopUp()){
            int previousItemId = itemId;
            itemId = screen.getCurrentItemId();
            if(slotId == 1){
                screen.getSelectedSlot1().changeTexture(ItemManager.getItemTexturePath(itemId));
                //if the slot was previously occupied by another item we need to find that item and change the Unequip button
            }else{
                screen.getSelectedSlot2().changeTexture(ItemManager.getItemTexturePath(itemId));
            }
            for(EquipButton button : screen.getEquipButtons()){
                if(button.getItemId() == previousItemId){
                    button.changeTexture("equip_button.png");
                    break;
                }
            }
            screen.setShowPopUp(false);
            screen.saveSettings();

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


