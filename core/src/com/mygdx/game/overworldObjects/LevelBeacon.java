package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Vali on 18.05.2016.
 */
public class LevelBeacon {

    private int levelId;
    protected Shape2D hitBox;
    protected int height, width;
    protected Vector2 position;
    //array that holds the levels that are connected to this on (to implement graph structure)
    private Array<LevelBeacon> connectedBeacons;

    /**
     * Constructor for this class
     */
    public LevelBeacon(){
    }

    /**
     * Initializes position and hitbox of the beacon
     * @param position
     * @param height
     * @param width
     */
    protected void initialize(Vector2 position, int height, int width, int levelId){
        this.position = position.cpy();
        this.hitBox = new Rectangle(position.x, position.y, width, height);
        this.height = height;
        this.width = width;
        this.levelId = levelId;
    }

    /**
     * Renders this LevelBeacon
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(0.8f, 0.1f, 0.8f, 1);
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    /**
     * getter for position of beacon
     * @return position
     */
    public Vector2 getPosition(){
        return position;
    }


}
