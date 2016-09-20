package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;


/**
 * Created by Vali on 17.08.2016.
 */
public class LevelInfo extends ARenderableObject implements IInputHandler {

    private int levelId;
    private int hops;
    private String levelName;
    private boolean isExpanded;

    public void initialize(Vector2 position, int width, int height, String pathToTexture, int levelId, String levelName)
    {
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.spriteDimension = new Vector2(width, height);
        this.levelId = levelId;
        this.levelName = levelName;
        this.hops = DataPers.dataP().hopsPerLevel[levelId];
        this.isExpanded = false;
        initializeTexture(spriteDimension, 0, pathToTexture);
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    @Override
    public void render(SpriteBatch batch){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(batch);
        MyGdxGame.game.font.setColor(Color.BLACK);
        MyGdxGame.game.font.draw(batch, levelName, position.x + 10, position.y + spriteDimension.y - 30);
        //if it is expanded right now we want to show the hops for this level
        if(isExpanded){
            MyGdxGame.game.font.draw(batch, "Hops: " + hops, position.x + 10, position.y + spriteDimension.y - 130);
        }
        MyGdxGame.game.font.setColor(Color.WHITE);

    }

    @Override
    public void OnTouch(TouchData td){
        MainMenuScreen screen = (MainMenuScreen) MyGdxGame.game.current;
        //only react to touch if dialog is not shown
        if(!screen.getDialogManager().getShowDialog()){
            if(isExpanded){
                initialize(position, 300, 100, "level_info.png", levelId, levelName);
                isExpanded = false;
            }else{
                initialize(position, 300, 500, "level_info_expanded.png", levelId, levelName);
                isExpanded = true;
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
