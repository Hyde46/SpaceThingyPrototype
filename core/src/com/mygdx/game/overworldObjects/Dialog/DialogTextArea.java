package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Mechandrius on 24.05.2016.
 */
public class DialogTextArea extends Decoration implements IInputHandler
{

    private int width;
    private int height;
    private int textPosition;   //will indicate how much of the text has been rendered
    private String currentText;
    private DialogManager dialogManager;
    private boolean isPressed;
    private boolean isActive;

    public void initialize(Vector2 position, int width, int height, String texturePath, DialogManager dialogManager, boolean isActive)
    {
        this.position = position.cpy();

        this.touchHitbox = new Rectangle(position.x, position.y, width, height);

        this.spriteDimension = new Vector2(width, height);

        this.width = width;

        this.height = height;

        this.textPosition = 1;

        this.dialogManager = dialogManager;

        this.isPressed = false;

        this.isActive = isActive;

        initializeTexture(spriteDimension, 0, texturePath);

        isUI = true;

        System.out.println("hitbox: " + touchHitbox);


    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {
        d.setColor(Color.WHITE);
        d.rect(position.x, position.y, width, height);
    }

    /**
     * renders the text inside of the dialog box
     * @param batch
     */
    public void renderText(SpriteBatch batch, String text){
        currentText = text;
        //if the text position is greater or equal than the text length, it means that we already want to show the whole text
        if(textPosition >= text.length()){
            MyGdxGame.game.dialogFont.draw(batch, currentText, position.x + 40, position.y + height - 40, width - 40, Align.topLeft, true);
        }else{
            //otherwise we just want to show a substring
            MyGdxGame.game.dialogFont.draw(batch, currentText, position.x + 40, position.y + height - 40, 0, textPosition, width - 40, Align.topLeft, true);
            //afterwords we increment text position so that in the next rendering frame one more letter will be shown etc
            textPosition++;
        }
    }

    /**
     * setter for isActive
     * @param isActive
     */
    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    @Override
    public void OnTouch(TouchData td) {
        System.out.println("Box touched");
        //if the textposition is smaller than the dialog text that means, that not all of the text is shown
        //therefore we want to show the whole text in this case -> set textposition to length of string
        //ask if currentText is already set, because it can happen that the newly rendered dialog box gets the touch and the text is not set yet
        //Because of the same problem we have a boolean which checks, if this method was just called, isPressed is set to false in onRelease
        if(currentText != null && !isPressed && isActive){
            if(textPosition < currentText.length()){
                textPosition = currentText.length();
            }else{
                //otherwise the text is already displayed, therefore we want to increment the step of the dialog so that the next text will be rendered
                dialogManager.incrementCurrentDialogStep();
                System.out.println("Box wird getouched und step wird inkrementiert");
            }
            isPressed = true;
        }
    }

    @Override
    public void OnRelease(TouchData td) {
        isPressed = false;
        System.out.println("Box wird released");

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
