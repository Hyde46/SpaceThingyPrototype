package com.mygdx.game.overworldObjects;

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
    protected Vector2 positionCenter;
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
    protected void initialize(Vector2 position, int height, int width, int levelId, Array<LevelBeacon> connectedBeacons){
        this.position = position.cpy();
        this.hitBox = new Rectangle(position.x, position.y, width, height);
        this.height = height;
        this.width = width;
        this.levelId = levelId;
        //compute position of the center of the rectangle
        positionCenter = new Vector2(position.x + width / 2, position.y + height / 2);
        this.connectedBeacons = connectedBeacons;
    }

    /**
     * Renders this LevelBeacon
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(0.8f, 0.1f, 0.8f, 1);
        shapeRenderer.rect(position.x, position.y, width, height);
        //render edges between beacons
        shapeRenderer.setColor(1, 1, 1, 1);
        for(LevelBeacon connectedBeacon : connectedBeacons){
            shapeRenderer.line(positionCenter, connectedBeacon.getPositionCenter());
        }
    }

    /**
     * getter for position of beacon
     * @return position
     */
    public Vector2 getPosition(){
        return position;
    }

    /**
     * getter for position of center of beacon
     * @return positionCenter
     */
    public Vector2 getPositionCenter(){
        return positionCenter;
    }

    /**
     * getter for hit box
     * @return hitBox
     */
    public Shape2D getHitBox(){
        return hitBox;
    }

    /**
     * getter for level id
     * @return levelId
     */
    public int getLevelId(){
        return levelId;
    }

    /**
     * getter for connected beacons of this level
     * @return connectedBeacons
     */
    public Array<LevelBeacon> getConnectedBeacons(){
        return connectedBeacons;
    }
}
