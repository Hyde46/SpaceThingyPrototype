package com.mygdx.game.renderAbleObjects.decorations;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MyGdxGame;

/**
 * Created by Vali on 22.07.2016.
 */
public class ArrowButton extends Decoration implements IInputHandler{

    private boolean isUp;
    private boolean isSkin;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, boolean isUp, boolean isSkin){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        initializeTexture(spriteDimension, 0, pathToTexture);
        //we want to know if the button is up or down, so we can properly handle the touch event
        this.isUp = isUp;
        //and we need to know if arrow is for skins or particles
        this.isSkin = isSkin;
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void OnTouch(TouchData td) {
        HangarScreen screen = (HangarScreen) MyGdxGame.game.getScreen();
        if(isSkin){     //skins
            if(isUp){
                if(screen.getCurrentSkin() == 0){
                    //in this case we set the last skin
                    screen.setCurrentSkin(screen.getNumberSkins() - 1);
                }else{
                    //otherwise the skin before
                    screen.setCurrentSkin(screen.getCurrentSkin() - 1);
                }
            }else{
                if(screen.getCurrentSkin() == screen.getNumberSkins() - 1){
                    //it was the last skin, now we set the first
                    screen.setCurrentSkin(0);
                }else{
                    //otherwise the skin after
                    screen.setCurrentSkin(screen.getCurrentSkin() + 1);
                }
            }
        }else{      //Particles
            if(isUp){
                if(screen.getCurrentParticles() == 0){
                    //in this case we set the last skin
                    screen.setCurrentParticles(screen.getNumberParticles() - 1);
                }else{
                    //otherwise the skin before
                    screen.setCurrentParticles(screen.getCurrentParticles() - 1);
                }
            }else{
                if(screen.getCurrentParticles() == screen.getNumberParticles() - 1){
                    //it was the last skin, now we set the first
                    screen.setCurrentParticles(0);
                }else{
                    //otherwise the skin after
                    screen.setCurrentParticles(screen.getCurrentParticles() + 1);
                }
            }
        }

    }

    @Override
    public void OnRelease(TouchData td) {

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


