package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Vali on 13.06.2016.
 */
public class Dialog {
    private Array<String> textArray;
    private Array<Integer> avatarArray;

    public Dialog(Array<String> textArray, Array<Integer> avatarArray){
        this.textArray = textArray;
        this.avatarArray = avatarArray;
    }

    /**
     * getter for textArray
     * @return the array that contains the dialog texts
     */
    public Array<String> getTextArray(){
        return textArray;
    }

    /**
     * getter for avatarArray
     * @return the array that contains the ints which reference the characters
     */
    public Array<Integer> getAvatarArray(){
        return avatarArray;
    }
}
