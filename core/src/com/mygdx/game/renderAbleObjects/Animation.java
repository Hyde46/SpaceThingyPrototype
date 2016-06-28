package com.mygdx.game.renderAbleObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Denis on 28.06.2016.
 */
public class Animation {

    private HashMap<Integer,SpriteFrame> frames;

    private int currentFrame;
    private int maxFrames;
    private float deltaFrame;
    private float deltaFrameCounter;

    private ARenderableObject baseObject;
    private Vector2 widthHeight;
    private boolean isRepeated;
    private boolean isDone;

    public Animation(){
        frames = new HashMap<Integer, SpriteFrame>();
        deltaFrameCounter = 0;
        currentFrame = 1;
        isDone = false;
    }

    public void setAnimation(int frameCount, float deltaFrame, Vector2 widthHeight,boolean isRepeated,String baseFrame, ARenderableObject baseObject){
        this.deltaFrame = deltaFrame;
        maxFrames = frameCount;
        this.baseObject = baseObject;
        this.isRepeated = isRepeated;
        this.widthHeight = widthHeight.cpy();
        for(int i = 0; i < frameCount; ++i){
            SpriteFrame f = new SpriteFrame();
            f.initializePositions(baseObject.getPosition().cpy().sub(widthHeight.cpy().scl(0.5f)));
            f.initializeTexture(widthHeight,0,baseFrame+(i+1)+".png");
            frames.put(i+1,f);
        }
    }

    public void update(float delta,boolean updateFrames){
        if(updateFrames) {
            deltaFrameCounter += delta;
            if (deltaFrameCounter >= deltaFrame) {
                deltaFrameCounter = 0;
                currentFrame += 1;
                if (currentFrame > maxFrames) {
                    currentFrame = 1;
                    if(!isRepeated)
                        isDone = true;
                }
            }
        }
        for(int i = 0; i < maxFrames; ++i) {
            frames.get(i+1).sprite.setPosition(baseObject.getPosition().x-widthHeight.cpy().scl(0.5f).x,baseObject.getPosition().y-widthHeight.cpy().scl(0.5f).y);
        }
    }

    public void render(SpriteBatch sB){
        if(isDone)
            return;
        frames.get(currentFrame).render(sB);
    }
}
