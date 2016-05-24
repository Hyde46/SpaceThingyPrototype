/*
    - Render über ARenderable render Methode besser?

 */

package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.Color;
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
    protected int height, width;
    protected Vector2 position;
    protected Vector2 positionCenter;
    //array that holds the levels that are connected to this on (to implement graph structure)
    private Array<LevelBeacon> connectedBeacons;
    private boolean isShop;
    private boolean activated;
    private Color beaconColor;

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
    protected void initialize(Vector2 position, int height, int width, int levelId, Array<LevelBeacon> connectedBeacons, boolean isShop, boolean activated){
        this.position = position.cpy();
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.height = height;
        this.width = width;
        this.levelId = levelId;
        //compute position of the center of the rectangle
        positionCenter = new Vector2(position.x + width / 2, position.y + height / 2);
        this.connectedBeacons = connectedBeacons;
        this.isShop = isShop;
        //color that the object shell have depends on whether it is shop or level
        if(isShop){
            beaconColor = new Color(0.3f, 0.6f, 0.8f, 1);
        }else if(activated){ //normal activated level
            beaconColor = new Color(0.8f, 0.1f, 0.8f, 1);
        }else{  //not activated level
            beaconColor = new Color(0.1f, 0.1f, 0.1f, 0.6f);
        }
        this.activated = activated;
    }

    /**
     * Renders this LevelBeacon
     * @param shapeRenderer
     */
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(beaconColor);
        shapeRenderer.rect(position.x, position.y, width, height);
        //render edges between beacons -> change type from filled to line
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(LevelBeacon connectedBeacon : connectedBeacons){
            //depending on whether or not the connected beacon is already reachable (and this beacon is reachable as well) the color of the edge is different
            if(connectedBeacon.getActivated() && this.getActivated()){
                shapeRenderer.setColor(0, 0.8f, 0, 1);
            }else{
                shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            }
            shapeRenderer.line(positionCenter, connectedBeacon.getPositionCenter());
        }
        shapeRenderer.end();
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
                    game.setScreen(new GameScreen());
                }
        }
        else
        {  //touched level is different from current level
            if(!sh.getTravelsRoute())
            {      //only call navigate function, if the ship is not already on route
                //tell PathNavigationManager to navigate to this level
                //only send ship to beacon if it has been activated
                if(activated){
                    pnm.navigateToBeacon(this);
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

    /**
     * get, if this beacon leads to a shop or not
     * @return isShop
     */
    public boolean getIsShop(){
        return isShop;
    }

    /**
     * get if this beacon has already been activated -> reachable
     * @return activated
     */
    public boolean getActivated(){
        return activated;
    }
}
