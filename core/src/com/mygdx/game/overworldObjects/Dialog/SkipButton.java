package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;

/**
 * Created by Vali on 13.06.2016.
 */
public class SkipButton extends Decoration implements IInputHandler {

    private DialogManager dialogManager;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, DialogManager dialogManager)
    {
        initializePositions(position);

        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.dialogManager = dialogManager;

        //initialize texture is method of ARenderableObject
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td) {
        dialogManager.skipDialog();
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


