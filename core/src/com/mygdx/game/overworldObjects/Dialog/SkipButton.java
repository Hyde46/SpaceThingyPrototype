package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private SkipButtonOverlay skipButtonOverlay;
    private final static String overlayTexture = "skip_button_overlay.png";
    private int progress;
    private int height;
    private boolean showOverlay;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, DialogManager dialogManager)
    {
        initializePositions(position);
        this.height = height;
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.dialogManager = dialogManager;
        skipButtonOverlay = new SkipButtonOverlay();
        //initialize texture is method of ARenderableObject
        initializeTexture(spriteDimension, 0, pathToTexture);
        progress = 0;
        skipButtonOverlay.initialize(getPosition(), progress, height, overlayTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * override render function to also render the overlay if needed
     * @param batch
     */
    @Override
    public void render(SpriteBatch batch){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(batch);
        if(showOverlay){
            skipButtonOverlay.render(batch);
        }
    }

    @Override
    public void OnTouch(TouchData td) {
        dialogManager.skipDialog();
    }

    @Override
    public void OnRelease(TouchData td) {
        //reset the progress
    //    progress = 0;
    }

    @Override
    public void OnDrag(TouchData td) {

    }

    @Override
    public void OnHold(TouchData td) {
        progress += 10;
        //progress will indicate the width of the overlay
        showOverlay = true;
        //change width of overlay
        skipButtonOverlay.getSprite().setBounds(getPosition().x, getPosition().y, progress, height);
        if(progress >= 400){
            dialogManager.skipDialog();
        }
    }

    @Override
    public void OnSwipe(TouchData td) {

    }
}


