package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 12.07.2016.
 */
public class EquipButton extends Decoration implements IInputHandler{
    private int id;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int id){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.id = id;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td) {
        HangarScreen screen = (HangarScreen) MyGdxGame.game.current;
        Array<Slot> itemSlots = screen.getItemSlots();
        int currentId = screen.getCurrentId();
        //reinitialize the last item slot, so that it is now deactivated again
        if(currentId != -1){
            itemSlots.get(currentId).initialize(itemSlots.get(currentId).getPosition(), (int) itemSlots.get(currentId).getSpriteDimensions().x, (int) itemSlots.get(currentId).getSpriteDimensions().y, "item_slot.png");
        }
        //initialize the object again with a different texture path
        itemSlots.get(id).initialize(itemSlots.get(id).getPosition(), (int) itemSlots.get(id).getSpriteDimensions().x, (int) itemSlots.get(id).getSpriteDimensions().y, "item_slot_activated.png");
        screen.setCurrentId(id);
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
