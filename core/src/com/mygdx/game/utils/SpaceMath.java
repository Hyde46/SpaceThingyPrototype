package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by denis on 5/13/16.
 */
public class SpaceMath {

    public static Vector2 rotatePoint(Vector2 pointToRotate, Vector2 centerPoint, double angleInDegrees, int direction){
        double angleInRadians = angleInDegrees * (Math.PI / 180);
        double cosTheta = Math.cos(direction*angleInRadians);
        double sinTheta = Math.sin(direction*angleInRadians);
        Vector2 returnVec = new Vector2();
        returnVec.x = (float)
                (cosTheta * (pointToRotate.x - centerPoint.x) -
                        sinTheta * (pointToRotate.y - centerPoint.y) + centerPoint.x);
        returnVec.y =
                (float)
                        (sinTheta * (pointToRotate.x - centerPoint.x) +
                                cosTheta * (pointToRotate.y - centerPoint.y) + centerPoint.y);
        return returnVec;
    }

    public static Vector2 getPosToRender(int levelPos){
        Vector2 screenDimensions = new Vector2(1080, 1920);
        Vector2 posToRender = new Vector2();
        posToRender.y = screenDimensions.y * 0.1f;
        if(levelPos == 2){
            posToRender.x = screenDimensions.x/2 - 100;
        }else if(levelPos == 1){
            posToRender.x = screenDimensions.x/2 + 150;
        }else if(levelPos == 0){
            posToRender.x = screenDimensions.x/2 - 350;
        }
        return posToRender.cpy();
    }
}
