package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 13.06.2016.
 */
public class DialogManager {

    private Array<Dialog> dialogArray;
    private Dialog currentDialog;
    private DialogTextArea dialogBox1;
    private DialogTextArea dialogBox2;
    private DialogAvatar dialogAvatar1;
    private DialogAvatar dialogAvatar2;
    private SkipButton skipButton;
    private int currentDialogStep;
    private boolean showDialog;


    /**
     * the dialog array, which holds dialog objects in the order in which they will be executed during the game is initialized.
     * Therefore dialog objects are created and added to that array.
     */
    public void createDialogs(){
        Array<String> textArray1 = new Array<String>();
        textArray1.addAll("Welcome to the game!!", "This dialog has two characters");
        Array<Integer> avatarArray1 = new Array<Integer>();
        avatarArray1.addAll(3, 5);
        Dialog dialog1 = new Dialog(textArray1, avatarArray1);
        Array<String> textArray2 = new Array<String>();
        textArray2.addAll("This is the dialog after we successfully finished level 1!", "It has 2 characters. And the second character speaks a bit longer, cause he has to explain something really important.", "And the first one speaks twice");
        Array<Integer> avatarArray2 = new Array<Integer>();
        avatarArray2.addAll(3, 5, 3);
        Dialog dialog2 = new Dialog(textArray2, avatarArray2);
        dialogArray = new Array<Dialog>();
        dialogArray.addAll(dialog1, dialog2);

    }

    /**
     * the function initalized everything for the current dialog
     * @param finishedLevel
     */
    public void initializeDialog(int finishedLevel, boolean success){
        if(success){

            int dialogIndex = getWhichDialog(finishedLevel);

            //if there should not be a dialog displayed this time (dialogIndex = -1) no initialization
            if(dialogIndex != -1) {
                showDialog = true;
                //get the dialog which should be shown
                currentDialog = dialogArray.get(dialogIndex);

                //initialize dialog boxes
                dialogBox1 = new DialogTextArea();
                dialogBox2 = new DialogTextArea();
                //the width of the first box should be almost the rest of the screen (meaning screen width minus the offset from left and a little
                dialogBox1.initialize(new Vector2(300, 250), MyGdxGame.game.screenWidth - 300, 700, "dialogBox.png", this);
                //the second box starts from left and leaves room to the right
                dialogBox2.initialize(new Vector2(0, 250), MyGdxGame.game.screenWidth - 300, 700, "dialogBox.png", this);

                //initalize dialog avatar with the avatar image of the first entry in the avatar array of this dialog
                dialogAvatar1 = new DialogAvatar();
                dialogAvatar1.initialize(new Vector2(0, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(0)));

                dialogAvatar2 = new DialogAvatar();
                dialogAvatar2.initialize(new Vector2(MyGdxGame.game.screenWidth - 300, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(1)));

                skipButton = new SkipButton();
                skipButton.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 200, 10), 400, 200, "skip_button.png", this);

                //register dialog boxes to InputManager
                InputManager.get.register(dialogBox1);
                InputManager.get.register(dialogBox2);
                InputManager.get.register(skipButton);

                currentDialogStep = 0;
            }else{
                showDialog = false;
            }
        }else{
            showDialog = false;
        }
    }

    /**
     * calls the rendering methods of the specific objects
     */
    public void renderDialog(){
        MyGdxGame game = MyGdxGame.game;
        //if the current step is as big as the length of the current dialog we do not want to show the dialog any longer
        if(currentDialogStep >= currentDialog.getTextArray().size){
            showDialog = false;
        }else {


            //draw text inside of textarea
            game.font.setColor(Color.BLACK);

            game.uiBatch.setProjectionMatrix(MainMenuScreen.camFixed.combined);
            game.uiBatch.begin();
            //if the first character speaks render the right dialog box, otherwise the left
            if (currentDialogStep % 2 == 0) {
                dialogBox1.render(game.uiBatch);
            } else {
                dialogBox2.render(game.uiBatch);
            }
            //depending on the step of the dialog the text inside of dialog box 1 or 2 is rendered
            //the text is extracted from the text array and rendered
            if (currentDialogStep % 2 == 0) {
                dialogBox1.renderText(game.uiBatch, currentDialog.getTextArray().get(currentDialogStep));
                //render avatar sprite
                dialogAvatar1.render(game.uiBatch);
            } else {
                dialogBox2.renderText(game.uiBatch, currentDialog.getTextArray().get(currentDialogStep));
                //render avatar sprite
                dialogAvatar2.render(game.uiBatch);
            }
            skipButton.render(game.uiBatch);
            game.uiBatch.end();
        }
    }


    /**
     * the function gets the currently finished level as parameter and maps this value to the index of the dialog array, so that the
     * dialog which should be shown can be extracted from that array
     *
     * @param finishedLevel
     * @return index in dialogArray, -1 if no dialog is shown
     */
    public int getWhichDialog(int finishedLevel){
        switch (finishedLevel){
            case 0 :
                return 0;
            case 1 :
                return 1;
            case 6 :
                return 2;
            case 12 :
                return 3;
            default:
                return -1;
        }
    }

    /**
     * map the ints inside of avatar array to a specific character, meaning to a specific image path
     * @param avatar
     * @return
     */
    public String getAvatarPath(int avatar){
        switch (avatar){
            case 1:
                return "char1.png";         //e.g. Jason
            case 2:
                return "char2.png";         //e.g. Harry
            case 3:
                return "char3.png";
            case 4:
                return "char4.png";
            case 5:
                return "char5.png";
            case 6:
                return "char6.png";
            default:
                return "char1.png";
        }
    }

    /**
     * incrementer for the current dialog step, is used by DialogTextArea class: when user clicks on area we want the next step
     */
    public void incrementCurrentDialogStep(){
        currentDialogStep++;
    }

    /**
     * getter for showDialog, needed in MainMenuScreen to check if dialog should be rendered
     * @return showDialog
     */
    public boolean getShowDialog(){
        return showDialog;
    }

    /**
     * skips the dialog by setting showDialog to false
     */
    public void skipDialog(){
        showDialog = false;
    }
}
