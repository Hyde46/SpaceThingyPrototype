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
    private Array<DialogAvatar> dialogAvatars;
    private SkipButton skipButton;
    private int currentDialogStep;
    private boolean showDialog;

    public enum Avatar{JASON_HAPPY,JASON_NORMAL,JASON_ANGRY,HARRY_NORMAL,HARRY_ANGRY,HARRY_SCREAMING,SPENCER,NEWS_ANCHOR,PHONE,BOUNTY_NORMAL,BOUNTY_ANGRY,GIRL_NORMAL,GIRL_HAPPY,GIRL_ANGRY};


    /**
     * the dialog array, which holds dialog objects in the order in which they will be executed during the game is initialized.
     * Therefore dialog objects are created and added to that array.
     */
    public void createDialogs(){

        //define pre level dialogs

        Array<String> textArray = new Array<String>();
        textArray.addAll("Hey Jason!", "...", "Listen to me!", "Can we finally take a break after this delivery so I can waste all of my money at the arcades?", "First of all, you're not doing anything. I'm the one,who is flying the ship and secondly, you're not getting paid, Spencer, I am.", "Is it because I am a robot? Great.",
                "Quit playing around. Let's get the job done.");
        Array<DialogManager.Avatar> avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.SPENCER, Avatar.JASON_NORMAL, Avatar.SPENCER, Avatar.SPENCER, Avatar.JASON_NORMAL, Avatar.SPENCER, Avatar.JASON_ANGRY);
        Dialog dialog1 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Jason, we arrived at that stinky facility. Should we grab some coffee while they are load our ship?");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.add(Avatar.SPENCER);
        Dialog dialog2 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*", "Hello Mr. Thirst, to pick up your package pass through the Topoga System in the north.", "Don't lose too much time sidetracking.", "*click*", "Jason, Jason! Screw that delivery, I picked up signals on my Radar! There's a Pawn Shop east of this cluster and " +
                "a hangar somewhere to the west. Let's check that out!");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE, Avatar.HARRY_NORMAL, Avatar.HARRY_NORMAL, Avatar.PHONE, Avatar.SPENCER);
        Dialog dialog3 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*","Hello Jason, it's me, Karl. Long time, no see. What brings you to the Meku system? " +
                "I just saw you on the radar.", "Yeah, it's been a while. What do you got for me?", "I have recently planned a heist, big money involved. If you want a share, help me out. " ,
                "I still need a getaway pilot. Are you in?","Go on..."," I need you to bring me to the Tairu Planet and then help me disappear.", "I know, you've always hated those kind of jobs. This ship is falling apart. We need the money ", "Alright, let's get it over with.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE,Avatar.BOUNTY_NORMAL, Avatar.JASON_NORMAL, Avatar.BOUNTY_NORMAL,Avatar.BOUNTY_NORMAL,Avatar.JASON_ANGRY,Avatar.BOUNTY_NORMAL, Avatar.SPENCER, Avatar.JASON_ANGRY);
        Dialog dialog4 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Start the engines! Go Go Go! We need to get out of here!"," NOW!");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.BOUNTY_ANGRY,Avatar.BOUNTY_ANGRY);
        Dialog dialog5 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("A friend of mine told me the hangar is right around the corner.", "Euh...","How would you know, you don't have any friends.", "Fine, I read it online.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.SPENCER, Avatar.JASON_NORMAL, Avatar.JASON_HAPPY, Avatar.SPENCER);
        Dialog dialog6 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Jason, now you are quite the specialist in navigating. We got lost.", "*ring ring*", "Hey guys, I hope I'm not disturbing you but I kinda... crashed my ship. I'm stuck on Mitaki with all my supplies. ", "Would you mind delivering them to Tentui and take me with you. \n" +
                "I can't pay you much since I lost all of my Scraps during the crash.", "Finally a woman on board.", "...");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.SPENCER, Avatar.PHONE, Avatar.GIRL_NORMAL, Avatar.GIRL_NORMAL, Avatar.SPENCER, Avatar.JASON_ANGRY);
        Dialog dialog7 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("So here's the deal: I need to get to Tentui, as I've told you before. It's not far from here.", "If you manage to drop me off in one piece, You'll get a new casing for this ship.", "Don't worry. We will get you there safely.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.GIRL_NORMAL, Avatar.GIRL_HAPPY, Avatar.JASON_NORMAL);
        Dialog dialog8 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("*ring ring*","Mr. Thirst, you made it, finally. I've been waiting for you. There are plenty of jobs for you. But first, I want to ask you something.","What is it? ",
                "It's a small favor. GenCorp got me a cloned tiger as a bonus reward. Could you deliver it to my personal storage on my home planet?","A tiger?","In stasis, of course.","I assume the cage is included?",
                "No Problem, Harry. I'll get right to it, and just call me Jason.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE,Avatar.HARRY_NORMAL, Avatar.JASON_NORMAL,Avatar.HARRY_NORMAL,Avatar.JASON_NORMAL,Avatar.HARRY_NORMAL,Avatar.SPENCER,Avatar.JASON_NORMAL);
        Dialog dialog17 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("*ring ring*","Mr.Thirst, about the shipments I had for you. There is a rather urgent one" +
                ". Be professional!","As usual, Harry!","*click*","I thought you told him to call you Jason.","...");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE,Avatar.HARRY_NORMAL,Avatar.JASON_NORMAL,Avatar.PHONE,Avatar.SPENCER,Avatar.JASON_NORMAL);
        Dialog dialog18 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("Let's dump that tracked package and get out of here. Harry seemed really concerned.","Where should we head to next?","We are not safe here, not safe anywhere from GenCorp.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.JASON_NORMAL, Avatar.SPENCER, Avatar.JASON_NORMAL);
        Dialog dialog19 = new Dialog(textArray, avatarArray);

        preDialogArray = new Array<Dialog>();
        preDialogArray.addAll(dialog1, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7, dialog8,dialog17,dialog18,dialog19);


        //define post level dialogs

        textArray = new Array<String>();
        textArray.addAll("It's over, finally! You wanted to go to the arcades, right?", "Nice, maybe I'll let you win for once.", "*ring ring*", "Mr. Thirst, we have a new job for you.", "We, GenCorp, want you to go to the Bera system and drop off a package. We will load the cargo at our GenCorp facility on your route.",
                "*click*", "Harry, always bugging me, when we want to relax.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.JASON_NORMAL, Avatar.SPENCER, Avatar.PHONE, Avatar.HARRY_NORMAL, Avatar.HARRY_NORMAL, Avatar.PHONE, Avatar.JASON_ANGRY);
        Dialog dialog9 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*ring ring*", "Mr. Thirst, We have gathered some information on your last delivery."," Our Client was unsatisfied with the condition in which you left the package. Please be more careful in the future. ");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE, Avatar.HARRY_NORMAL,Avatar.BOUNTY_ANGRY);
        Dialog dialog16= new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Shiny new things you got there! We can tinker around with it at the hangar.", "But if you blow up the ship again, I'll find me another master.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.SPENCER, Avatar.SPENCER);
        Dialog dialog10 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Okay, just wait here. It won't take long. ");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.BOUNTY_NORMAL);
        Dialog dialog11 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("I know I can always count on you, for simple jobs. Here is your share. " +
                " You won't hear from me anytime soon.", "Don't give me any details. My conscience is always bothering me after those kind of jobs.", "Don't beat yourself up, everyone has to look out for themselves.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.BOUNTY_NORMAL, Avatar.JASON_ANGRY, Avatar.SPENCER);
        Dialog dialog12 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("*Radio plays*", "Welcome, Welcome, Welcome, this is Radio Nox News!"," Tonight we've got a very special guest with us.", "Professor Dr. Crane, a high profile scientist will share his insights on the catastrophic event of the Kerro Galaxy wipe-out.",
                "His studies provide the definitive proof, that this horrible accident was a natural disaster.", "Turn that off, I don't want to be reminded that everything and everyone I knew ceased to exist.", "Strange how they only report one point of view on the matter. It's always the same with that damn radio chanel.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.PHONE, Avatar.NEWS_ANCHOR,Avatar.NEWS_ANCHOR, Avatar.NEWS_ANCHOR, Avatar.NEWS_ANCHOR, Avatar.JASON_ANGRY, Avatar.SPENCER);
        Dialog dialog13 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("Thanks for picking me up.", "Beep Boop.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.GIRL_HAPPY, Avatar.SPENCER);
        Dialog dialog14 = new Dialog(textArray, avatarArray);
        textArray = new Array<String>();
        textArray.addAll("I guess, I owe you.", "How about you take me out for dinner tonight. I know a nice restaurant two clusters away.", "..." , "You wish!");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.GIRL_NORMAL, Avatar.JASON_HAPPY, Avatar.GIRL_ANGRY, Avatar.GIRL_HAPPY);
        Dialog dialog15 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("Harry? 'The tiger' has landed.","Good job, Mr.Thirst. By the way I've got some well paying jobs for you at GenCorp.");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.JASON_HAPPY, Avatar.HARRY_NORMAL);
        Dialog dialog20 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("Jason! How could you hit that asteroid, you idiot?! Now I have to clean up everything.",
                "Damn. Spencer go! Check if the package is fine!",
                "Just a sec.",
                "Jason, the package got shaken up really badly. Now there's a big hole in the bottom.",
                "Do you see the papers falling out of it?",
                "Put them back, we are not supposed to see them!",
                "But look, it's a receipt, addressed to that Crane guy. It shows a really big payment from GenCorp.",
                "Professor Dr. Crane? Isn't he the guy who claimed that the destruction of our home galaxy was caused " +
                        "by a natural disaster? What's his deal with GenCorp?",
                "*ring ring*",
                "Mr. Thirst, we got the information that your ship got hit by an asteroid.",
                "...",
                "Also the safety lock of the package alarmed us of it being broken.",
                "I am sorry, after we hit that damn asteroid, the box just popped open.",
                "We didn't see anything, and I didn't even touch it.",
                "Mr. Thirst, we will send a supervisor to your location. We want to examine the package.",
                "...",
                "Actually Jason, better don't be there when they arrive.",
                "Please...",
                "*click*");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.SPENCER,Avatar.JASON_NORMAL,Avatar.SPENCER,Avatar.SPENCER,Avatar.SPENCER,Avatar.JASON_ANGRY,
                Avatar.SPENCER,Avatar.JASON_NORMAL,Avatar.PHONE,Avatar.HARRY_ANGRY,Avatar.JASON_NORMAL,Avatar.HARRY_SCREAMING,Avatar.JASON_NORMAL,Avatar.SPENCER,Avatar.HARRY_NORMAL,Avatar.HARRY_NORMAL,Avatar.HARRY_NORMAL,Avatar.HARRY_NORMAL,Avatar.PHONE);
        Dialog dialog21 = new Dialog(textArray, avatarArray);

        textArray = new Array<String>();
        textArray.addAll("Alright we put quiet some distance between us and them. They shouldn't be able to find us.",
                "For now...",
                "But what about now? Should we take off to another galaxy?",
                "No way! GenCorp is dirtier than most assume.",
                "We need to find Prof. Crane and find out what really happened.",
                "What about his ties to GenCorp?",
                "We should risk it and meet up with him.",
        "To be continued...");
        avatarArray = new Array<DialogManager.Avatar>();
        avatarArray.addAll(Avatar.JASON_NORMAL,Avatar.SPENCER,Avatar.SPENCER,Avatar.JASON_ANGRY,Avatar.JASON_NORMAL,Avatar.SPENCER,Avatar.JASON_NORMAL,Avatar.PHONE);
        Dialog dialog22 = new Dialog(textArray, avatarArray);
        postDialogArray = new Array<Dialog>();
        postDialogArray.addAll(dialog9,dialog16, dialog10, dialog11, dialog12, dialog13, dialog14, dialog15,dialog20,dialog21,dialog22);
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

                dialogAvatars = new Array<DialogAvatar>();
                for(int i = 0; i < currentDialog.getAvatarArray().size; i++){
                    DialogAvatar dialogAvatar = new DialogAvatar();
                    if(currentDialog.getAvatarArray().get(i) == currentDialog.getAvatarArray().get(0)){
                        dialogAvatar.initialize(new Vector2(0, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(i)));
                    }else{
                        dialogAvatar.initialize(new Vector2(MyGdxGame.game.screenWidth - 300, 400), 300, 400, getAvatarPath(currentDialog.getAvatarArray().get(i)));
                    }
                    dialogAvatars.add(dialogAvatar);
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
        Avatar firstCharacter = currentDialog.getAvatarArray().get(0);
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
                dialogBox1.setActive(true);
                dialogBox2.setActive(false);
            } else {
//                Avatar secondCharacter = currentDialog.getAvatarArray().get(1);
 //               if(currentDialog.getAvatarArray().get(currentDialogStep) != secondCharacter){
   //                 dialogAvatar2.changeTexture(getAvatarPath(currentDialog.getAvatarArray().get(currentDialogStep)));
    //            }
                dialogBox2.render(game.uiBatch);
                dialogBox2.renderText(game.uiBatch, currentDialog.getTextArray().get(currentDialogStep));
                //render avatar sprite
                dialogBox2.setActive(true);
                dialogBox1.setActive(false);
            }
            dialogAvatars.get(currentDialogStep).render(game.uiBatch);


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
            case 10:
                return 8;
            case 11:
                return 9;
            case 12:
                return 10;
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
            case 10:
                return 8;
            case 11:
                return 9;
            case 12:
                return 10;
            default:
                return -1;
        }
    }

    /**
     * map the ints inside of avatar array to a specific character, meaning to a specific image path
     * @param avatar
     * @return
     */
    public String getAvatarPath(Avatar avatar){
        switch (avatar){
            case JASON_NORMAL:
                return "jason_normal.png";
            case JASON_HAPPY:
                return "jason_happy.png";
            case JASON_ANGRY:
                return "jason_serious.png";
            case HARRY_ANGRY:
                return "harry_serious.png";
            case HARRY_NORMAL:
                return "harry_normal.png";
            case HARRY_SCREAMING:
                return "harry_screaming.png";
            case SPENCER:
                return "spencer.png";
            case NEWS_ANCHOR:
                return "newsanchor.png";
            case BOUNTY_NORMAL:
                return "bounty_normal.png";
            case BOUNTY_ANGRY:
                return "bounty_angry.png";
            case PHONE:
                return "phone.png";
            case GIRL_ANGRY:
                return "girl_angry.png";
            case GIRL_HAPPY:
                return "girl_happy.png";
            case GIRL_NORMAL:
                return "girl_normal.png";
            default:
                return "jason_normal.png";
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
