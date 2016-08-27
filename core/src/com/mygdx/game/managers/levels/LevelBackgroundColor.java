package com.mygdx.game.managers.levels;

/**
 * Created by Denis on 27.08.2016.
 */
public class LevelBackgroundColor {


    public static float[] getBackGroundColor(int level){
        float r=0,g=0,b=0;
        switch(level){
            case 0:
                r = 63.0f;
                g = 31.0f;
                b = 39.0f;
                break;
            case 2:
                r = 33.0f;
                g = 49.0f;
                b = 41.0f;
                break;
            case 3:
                r = 153.0f;
                g = 51.0f;
                b = 51.0f;
                break;
            case 4:
                r = 64.0f;
                g = 104.0f;
                b = 176.0f;
                break;
            case 5:
                r = 98.0f;
                g = 57.0f;
                b = 142.0f;
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9 :
                break;
            case 10:
                break;
            default:
        }
        return new float[] {r/255.0f,g/255.0f,b/255.0f};
    }
}
