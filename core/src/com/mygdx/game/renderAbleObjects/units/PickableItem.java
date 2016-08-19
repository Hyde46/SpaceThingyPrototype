package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.levels.LevelState;

/**
 * Created by denis on 8/9/16.
 */
abstract public class PickableItem extends Unit{

    private final static float PICKABLE_ITEM_RADIUS = 32.0f;
    private final static String VISUAL_TYPE_TEXTURE_PATH = "VisualPickable64x64.png";
    private final static String ITEM_TYPE_TEXTURE_PATH = "ItemPickable64x64.png";
    private final static String CURRENCY_TYPE_TEXTURE_PATH = "CurrencyPickable64x64.png";

    private int itemID;

    private boolean hasBeenPickedUp;

    /*
    0 - Visual Type
    1 - ship upgrade
    2 - currency
     */
    private int pickableType;


    public PickableItem(){
        super();
    }

    protected void initializeItem(int itemID, Vector2 pos, int pickableType){
        this.itemID = itemID;
        this.collisionHitbox = new Circle(pos.x, pos.y, PICKABLE_ITEM_RADIUS);
        initializePositions(pos.cpy(), new Vector2(0, 0));
        initializeTexture(new Vector2(PICKABLE_ITEM_RADIUS * 2, PICKABLE_ITEM_RADIUS * 2), 0,
                pickableType == 2 ? CURRENCY_TYPE_TEXTURE_PATH : (
                        pickableType == 0 ? VISUAL_TYPE_TEXTURE_PATH : ITEM_TYPE_TEXTURE_PATH
                        ));
        this.unitType = UnitType.PICKABLE_ITEM;
        isUI = false;
        hasBeenPickedUp = false;
        this.pickableType = pickableType;
    }

    public void pickUpItem(LevelState levelState) {
        hasBeenPickedUp = true;
        isActive = !hasBeenPickedUp;
        updateLevelState(levelState);
    }

    protected abstract void updateLevelState(LevelState levelState);

    @Override
    public void moveUnit() {
        return;
    }

    @Override
    public void update(float delta) {
        return;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {
        return;
    }


}
