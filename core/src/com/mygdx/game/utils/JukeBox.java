package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by denis on 8/28/16.
 */
public class JukeBox {

    private static Music currentBGM;
    private static int nextBGMLevelID;

    private static float currentFadeinPercent;
    private static float maxVolumePercent = 100;
    private static float minVolumePercent = 0;
    private static float fadeStep;

    private static boolean isInitialized;

    private static boolean hasStopped;

    public static void initialize(){
        currentFadeinPercent = 0;
        isInitialized = true;
        fadeStep = 5f;
        hasStopped = false;
    }

    public static void startBGM(int level){
        if(!isInitialized)
            initialize();
        nextBGMLevelID = level;
        currentFadeinPercent = minVolumePercent;
    }

    public static void stopBGM(){
        currentBGM.stop();
        hasStopped = true;
    }

    public static void update(float delta){
        if(hasStopped)
            return;
        if(currentFadeinPercent == 0){//start fadein with new music
            if(currentBGM != null)
                currentBGM.stop();
            currentBGM = Gdx.audio.newMusic(Gdx.files.internal(getNextBGMPath())); ;
            currentBGM.setVolume(minVolumePercent);
            currentBGM.setLooping(true);
            currentBGM.play();
        }
        currentFadeinPercent += fadeStep;
        if(currentFadeinPercent > maxVolumePercent)
            currentFadeinPercent = maxVolumePercent;

        if(currentBGM != null)
            currentBGM.setVolume(currentFadeinPercent);
    }

    private static String getNextBGMPath(){
        String pathToBGM = "Space/";
        switch(nextBGMLevelID){
            case -1://shop
                pathToBGM += "shop";
                break;
            case -2: //overworld
                pathToBGM += "level2";
                break;
            case 0:
                pathToBGM += "level7_1";
                break;
            case 1:
                pathToBGM += "level7_2";
                break;
            case 4:
                pathToBGM += "level3";
                break;
            case 5:
                pathToBGM += "level5";
                break;
            case 6:
                pathToBGM += "level5";
                break;
            case 7:
                pathToBGM += "level5";
                break;
            case 8:
                pathToBGM += "level1_1";
                break;
            case 9:
                pathToBGM += "level1_3";
                break;
            case 10:
                pathToBGM += "level4_1";
                break;
            case 11:
                pathToBGM += "level4_2";
                break;
            case 12:
                pathToBGM += "level4_3";
                break;
            default:
                pathToBGM += "overworld";
        }
        pathToBGM += ".wav";
        return pathToBGM;
    }

    public static void dispose(){
        currentBGM.dispose();
    }

}
