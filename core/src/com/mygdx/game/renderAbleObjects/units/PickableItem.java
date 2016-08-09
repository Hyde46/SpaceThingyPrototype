package com.mygdx.game.renderAbleObjects.units;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 8/9/16.
 */
public class PickableItem extends Unit{

    private final static float PICKABLE_ITEM_RADIUS = 32.0f;
    private final static String VISUAL_TYPE_TEXTURE_PATH = "VisualPickable64x64.png";
    private final static String ITEM_TYPE_TEXTURE_PATH = "ItemPickable64x64.png";

    private int itemID;

    private boolean hasBeenPickedUp;

    public PickableItem(){
        super();

    }

    public void initialize(int itemID, Vector2 pos, boolean isVisualType){
        this.itemID = itemID;
        this.collisionHitbox = new Circle(pos.x, pos.y, PICKABLE_ITEM_RADIUS);
        initializePositions(pos.cpy(), new Vector2(0, 0));
        initializeTexture(new Vector2(PICKABLE_ITEM_RADIUS * 2, PICKABLE_ITEM_RADIUS * 2), 0,
                isVisualType ? VISUAL_TYPE_TEXTURE_PATH : ITEM_TYPE_TEXTURE_PATH);
        this.unitType = 2;
        isUI = false;
        hasBeenPickedUp = false;
    }

    public void pickUpItem(){
        hasBeenPickedUp = true;
        isActive = !hasBeenPickedUp;
    }

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
