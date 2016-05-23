/*
    - Render über ARenderable render Methode besser?

 */

package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.ShopScreen;

/**
 * Created by Vali on 18.05.2016.
 */
public class LevelBeacon extends ARenderableObject implements IInputHandler{

    private int levelId;
//    protected Shape2D hitBox;
    protected int height, width;
    protected Vector2 position;
    protected Vector2 positionCenter;
    //array that holds the levels that are connected to this on (to implement graph structure)
    private Array<LevelBeacon> connectedBeacons;
    private boolean isShop;

    /**
     * Constructor for this class
     */
    public LevelBeacon(){
    }

    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * Initializes position and hitbox of the beacon
     * @param position
     * @param height
     * @param width
     */
    protected void initialize(Vector2 position, int height, int width, int levelId, Array<LevelBeacon> connectedBeacons, boolean isShop){
        this.position = position.cpy();
 //       this.hitBox = new Rectangle(position.x, position.y, width, height);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.height = height;
        this.width = width;
        this.levelId = levelId;
        //compute position of the center of the rectangle
        positionCenter = new Vector2(position.x + width / 2, position.y + height / 2);
        this.connectedBeacons = connectedBeacons;
        this.isShop = isShop;
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
//    public Shape2D getHitBox(){
//        return touchHitbox;
//    }

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

    @Override
    public void OnTouch(TouchData td)
    {
        MyGdxGame game = MyGdxGame.game;
        LevelGraph lg = ((MainMenuScreen)MyGdxGame.game.current).getLevelGraph();
        Ship sh = ((MainMenuScreen)MyGdxGame.game.current).getShip();
        PathNavigationManager pnm = ((MainMenuScreen)MyGdxGame.game.current).getPathNavigationManager();

        if(levelId == lg.getCurrentLevel().getLevelId() && sh.getInOrbit())
        {
                if(getIsShop())
                {
                    game.setScreen(new ShopScreen());
                }
                else
                {
                    game.setScreen(new GameScreen(levelId));
                }
        }
        else
        {  //touched level is different from current level
            if(!sh.getTravelsRoute())
            {      //only call navigate function, if the ship is not already on route
                //tell PathNavigationManager to navigate to this level
                pnm.navigateToBeacon(this);
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

    /**
     * get, if this beacon leads to a shop or not
     * @return isShop
     */
    public boolean getIsShop(){
        return isShop;
    }
}
