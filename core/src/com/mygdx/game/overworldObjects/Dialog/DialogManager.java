package com.mygdx.game.overworldObjects.Dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 13.06.2016.
 */
public class DialogManager {

    private Array<Dialog> preDialogArray;
    private Array<Dialog> postDialogArray;
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

        //define pre level dialogs

        Array<String> textArray = new Array<String>();
        textArray.addAll("Hey Jason!", "...", "Listen to me!", "Can we finally take a break after this delivery so I can waste all of my money at the arcade?", "First of all you're not doing anything I'm the one flying the ship and second you're not getting paid, Spencer, that's me.", "Just because I'm a robot, right?",
                "Quit playing around. Lets get it done.");
        Array<Integer> avatarArray = new Array<Integer>();
        avatarArray.addAll(2, 1, 2, 2, 1, 2, 1);
        Dialog dialog1 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Jason, we arrived at that stinky facility. Should we grab some coffee while they load our ship?");
        avatarArray = new Array<Integer>();
        avatarArray.add(2);
        Dialog dialog2 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*", "Hello Mr. Thirst, to pick up your package pass through the Topoga System straight north.", "Don't lose too much time on sidetracking.", "*click*", "Jason, Jason! Screw that delivery, I picked up signals on my Radar! There's a Pawn Shop East of this cluster and " +
                "a Hangar somewhere west. Let's check that out!");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(4, 3, 3, 4, 2);
        Dialog dialog3 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*","Hello Jason it's me Karl, long time no see. What brings you to the Meku system?" +
                "I just saw you on the scanners.", "Yeah, it's been a while, what do you got for me?", "I have recently planned a heist, big money involved. If you want a share, help me out. " +
                "I still need a getaway Pilot. First, you need to bring me to the Tairu Planet and then help me disappear.", "I know you've always hated those kind of jobs, but this ship is falling apart.", "Let's get it over with.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(4,3, 1, 3, 2, 1);
        Dialog dialog4 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.add("Start the engines! Go Go Go! We need to get out of here! NOW!");
        avatarArray = new Array<Integer>();
        avatarArray.add(4);
        Dialog dialog5 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("A friend of mine told me the Hangar is right around this corner.", "Euh...","you don't have any friends.", "Fine, I read it online.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(2, 1,1, 2);
        Dialog dialog6 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Jason, now you are quite the specialist in navigating. We got lost.", "*ring ring*", "Hey guys, I hope I'm not disturbing but I kinda... crashed my ship. I got my supplies left with me on Mitaki. ", "Could you please help me out and deliver it to Tentui including me. \n" +
                "There's not much payment since I lost all of my scraps during the crash.", "Finally a Woman on board.", "...");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(2, 4, 3, 3, 2, 1);
        Dialog dialog7 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("So here's the deal. I need to get to Tentui, as i've told you before. It's not far from here.", "If you manage to drop me off in one piece, You'll get a new casing for this ship.", "Don't worry. We will get you there safe.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(3, 3, 1);
        Dialog dialog8 = new Dialog(textArray, avatarArray);
        preDialogArray = new Array<Dialog>();
        preDialogArray.addAll(dialog1, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8);


        //define post level dialogs

        textArray = new Array<String>();
        textArray.addAll("Finally done! You wanted to go to the arcade, right?", "Nice, maybe I'll let you win for once.", "*ring ring*", "Mr. Thirst, we have a new delivery specified for you.", "We, Gen.Corp. want you to go to the ___ system and drop off the package. We will load the cargo at our Gen Corp facility on your route.",
                "*click*", "Harry, always bugging me, when we want to relax.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(1, 2, 4, 3, 3, 4, 1);
        Dialog dialog9 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*", "Mr. Thirst, We have gathered some information on your last delivery. Our Client was unsatisfied with the condition in which you left the package. Please be more careful in the future. ");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(4, 3);
        Dialog dialog16= new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Shiny stuff you got there! We can tinker around with it at the Hangar.", "But if you blow up the ship again, I'll find me another master.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(2, 2);
        Dialog dialog10 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Okay, just wait here. It won't take long. ");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(3);
        Dialog dialog11 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("I Know I always can count on you, when its about simple jobs. Here is your share. " +
                " You won't hear from me anytime soon.", "I don't even want to know what you just did. My conscience is always bugging me after those kind of jobs.", "Don't beat yourself up, everyone has to look out for themselves.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(3, 1, 2);
        Dialog dialog12 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*Radio plays*", "Welcome welcome welcome, this is Radio Nox News. Tonight we've got a very special guest with us.", "Professor Dr. Crane, a high profile Scientist will share his insight on the catastrophic event, when the Kerro Galaxy got completely wiped out.",
                "His studies showed the definitive proof, that this horrible accident was caused by a natural anomaly.", "Turn that down, I don't want to be reminded that everything and everyone I knew ceased to exist.", "Strange how there's only one view on the matter. It's always the same with that damn radio show.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(4, 4, 4, 4, 1, 2);
        Dialog dialog13 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Thanks for picking me up.", "Beep Boop.");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(3, 2);
        Dialog dialog14 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("I guess I'm in your debt.", "How about we figure that out over dinner tonight, I know a nice restaurant two clusters away.", "..." , "You wish!", "Beep Boop");
        avatarArray = new Array<Integer>();
        avatarArray.addAll(3, 1, 3, 3, 2);
        Dialog dialog15 = new Dialog(textArray, avatarArray);
        postDialogArray = new Array<Dialog>();
        postDialogArray.addAll(dialog9,dialog16, dialog10, dialog11, dialog12, dialog13, dialog14, dialog15);
    }


    /**
     * function checks if there is a pre dialog and prepares it if necessary
     * @param reachedLevel
     */
    public void startPreDialog(int reachedLevel){
        System.out.println("pre played: " + DataPers.dataP().preDialogsPlayed);
        System.out.println("Reached level: "+ reachedLevel);
        //check if the dialog was played before by checking persistent data
        if(!DataPers.dataP().preDialogsPlayed.contains(reachedLevel)){
            int dialogIndex = getWhichPreDialog(reachedLevel);

            //if there should not be a dialog displayed this time (dialogIndex = -1) no initialization
            if(dialogIndex != -1){
                showDialog = true;
                //get the dialog which should be shown
                currentDialog = preDialogArray.get(dialogIndex);
                initializeDialog();
                DataPers.dataP().preDialogsPlayed.add(reachedLevel);
                DataPers.saveP();
            }else{
                showDialog = false;
            }


        }
    }

    /**
     * function checks if there is a post dialog and prepares it if necessary
     * @param finishedLevel
     */
    public void startPostDialog(int finishedLevel){
        if(!DataPers.dataP().postDialogsPlayed.contains(finishedLevel)){
            int dialogIndex = getWhichPostDialog(finishedLevel);

            //if there should not be a dialog displayed this time (dialogIndex = -1) no initialization
            if(dialogIndex != -1){
                showDialog = true;
                //get the dialog which should be shown
                currentDialog = postDialogArray.get(dialogIndex);
                initializeDialog();
                DataPers.dataP().postDialogsPlayed.add(finishedLevel);
                DataPers.saveP();
            }else{
                showDialog = false;
            }


        }

    }

    /**
     * the function initalizes everything for the current dialog
     */
    public void initializeDialog(){
                //initialize dialog boxes
                dialogBox1 = new DialogTextArea();
                dialogBox2 = new DialogTextArea();
                //the width of the first box should be almost the rest of the screen (meaning screen width minus the offset from left and a little
                dialogBox1.initialize(new Vector2(300, 250), MyGdxGame.game.screenWidth - 300, 700, "dialogBox.png", this, true);
                //the second box starts from left and leaves room to the right
                dialogBox2.initialize(new Vector2(0, 250), MyGdxGame.game.screenWidth - 300, 700, "dialogBox.png", this, false);

                //initalize dialog avatar with the avatar image of the first entry in the avatar array of this dialog
                dialogAvatar1 = new DialogAvatar();
                dialogAvatar1.initialize(new Vector2(0, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(0)));

                if(currentDialog.getAvatarArray().size > 1){
                    dialogAvatar2 = new DialogAvatar();
                    dialogAvatar2.initialize(new Vector2(MyGdxGame.game.screenWidth - 300, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(1)));
                }

                skipButton = new SkipButton();
                skipButton.initialize(new Vector2(MyGdxGame.game.screenWidth / 2 - 200, 10), 400, 200, "skip_button.png", this);

                //register dialog boxes to InputManager
                InputManager.get.register(dialogBox1);
                InputManager.get.register(dialogBox2);
                InputManager.get.register(skipButton);

                currentDialogStep = 0;

    }



    /**
     * calls the rendering methods of the specific objects
     */
    public void renderDialog(){
        MyGdxGame game = MyGdxGame.game;
        int firstCharacter = currentDialog.getAvatarArray().get(0);
        //if the current step is as big as the length of the current dialog we do not want to show the dialog any longer
        if(currentDialogStep >= currentDialog.getTextArray().size){
            showDialog = false;
        }else {

                    //draw text inside of textarea

            game.uiBatch.setProjectionMatrix(MainMenuScreen.camFixed.combined);
            game.uiBatch.begin();
            //if the first character speaks render the right dialog box, otherwise the left
            //depending on the character speaking the text inside of dialog box 1 or 2 is rendered
            //the text is extracted from the text array and rendered
            if (currentDialog.getAvatarArray().get(currentDialogStep) == firstCharacter) {
                dialogBox1.render(game.uiBatch);
                dialogBox1.renderText(game.uiBatch, currentDialog.getTextArray().get(currentDialogStep));
                //render avatar sprite
                dialogAvatar1.render(game.uiBatch);
                dialogBox1.setActive(true);
                dialogBox2.setActive(false);
            } else {
                dialogBox2.render(game.uiBatch);
                dialogBox2.renderText(game.uiBatch, currentDialog.getTextArray().get(currentDialogStep));
                //render avatar sprite
                dialogAvatar2.render(game.uiBatch);
                dialogBox2.setActive(true);
                dialogBox1.setActive(false);
            }

            skipButton.render(game.uiBatch);
            game.uiBatch.end();
        }
    }


    /**
     * the function gets the currently finished level as parameter and maps this value to the index of the post dialog array, so that the
     * dialog which should be shown can be extracted from that array
     *
     * @param finishedLevel
     * @return index in post dialogArray, -1 if no dialog is shown
     */
    public int getWhichPostDialog(int finishedLevel){
        switch (finishedLevel){
            case 0 :
                return 0;
            case 1 :
                return 1;
            case 2 :
                return 2;
            case 5 :
                return 5;
            case 6 :
                return 6;
            case 7:
                return 7;
            case 8:
                return 3;
            case 9:
                return 4;
            default:
                return -1;
        }
    }
    /**
     * the function gets the currently finished level as parameter and maps this value to the index of the pre dialog array, so that the
     * dialog which should be shown can be extracted from that array
     *
     * @param reachedLevel
     * @return index in pre dialogArray, -1 if no dialog is shown
     */
    public int getWhichPreDialog(int reachedLevel){
        switch (reachedLevel){
            case 0 :
                return 0;
            case 1 :
                return 1;
            case 4 :
                return 2;
            case 5 :
                return 5;
            case 6 :
                return 6;
            case 7:
                return 7;
            case 8:
                return 3;
            case 9:
                return 4;
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
                return "char3.png";         //Jason
            case 2:
                return "char6.png";         //Robot
            case 3:
                return "char5.png";         //Harry
            case 4:
                return "phone.jpg";          //phone
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
        System.out.println("Current Dialog Step: " + currentDialogStep);
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

    /**
     * clean up stuff, called in MainMenuScreen
     */
    public void dispose(){
        postDialogArray = null;
        preDialogArray = null;
        currentDialog = null;
        dialogBox1.dispose();
        dialogBox1 = null;
        dialogBox2.dispose();
        dialogBox2 = null;
        dialogAvatar1.dispose();
        dialogAvatar1 = null;
        dialogAvatar2.dispose();
        dialogAvatar2 = null;
        skipButton.dispose();
        skipButton = null;
    }
}
