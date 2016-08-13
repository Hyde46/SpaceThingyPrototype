/*
    - Render über ARenderable render Methode besser?

 */

package com.mygdx.game.overworldObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.managers.PathNavigationManager;
import com.mygdx.game.renderAbleObjects.ARenderableObject;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.HangarScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.ShopScreenBuy;

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
    private int type;   //1 = normal level, 2 = shop, 3 = hangar
    private boolean activated;
    private Color beaconColor;


    @Override
    public void renderHitboxes(ShapeRenderer d) {

    }

    /**
     * Initializes position and hitbox of the beacon
     * @param position
     * @param height
     * @param width
     */
    protected void initialize(Vector2 position, int height, int width, int levelId, Array<LevelBeacon> connectedBeacons, int type, boolean activated){
        initializePositions(position);
        this.touchHitbox = new Rectangle(position.x, position.y, width, height);
        this.height = height;
        this.width = width;
        this.levelId = levelId;
        //compute position of the center of the rectangle
        positionCenter = new Vector2(position.x + width / 2, position.y + height / 2);
        this.connectedBeacons = connectedBeacons;
        this.type = type;
        this.activated = activated;
        //color that the object shell have depends on whether it is shop, hangar or level
        if(!activated){
            initializeTexture(new Vector2(width, height), 0, "beacon_inactive.png");
        }else if(type == 2){
            initializeTexture(new Vector2(width, height), 0, "beacon_shop.png");
        }else if(type == 3){
            initializeTexture(new Vector2(width, height), 0, "beacon_hangar.png");
        }else{ //normal activated level
            initializeTexture(new Vector2(width, height), 0, "beacon.png");
        }
    }

    /**
     * renders the edges for one level beacon
     * @param shapeRenderer
     */
    public void renderEdges(ShapeRenderer shapeRenderer){
        for(LevelBeacon connectedBeacon : connectedBeacons){
            //depending on whether or not the connected beacon is already reachable (and this beacon is reachable as well) the color of the edge is different
            if(connectedBeacon.getActivated() && this.getActivated()){
                //shapeRenderer.setColor(0, 0.8f, 0, 1);  -> green
                shapeRenderer.setColor(1, 1, 1, 1);
            }else{
                shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
            }
            shapeRenderer.line(positionCenter, connectedBeacon.getPositionCenter());
        }
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
        //System.out.println("Wird getouched");
        MyGdxGame game = MyGdxGame.game;
        //only process touch data if there is no dialog shown
        if(!((MainMenuScreen) game.current).getDialogManager().getShowDialog()) {

            LevelGraph lg = ((MainMenuScreen) game.current).getLevelGraph();
            Ship sh = ((MainMenuScreen) game.current).getShip();
            PathNavigationManager pnm = ((MainMenuScreen) game.current).getPathNavigationManager();

            if (levelId == lg.getCurrentLevel().getLevelId() && sh.getInOrbit()) {
                if (type == 2) {
                    InputManager.get.clear();
                    game.openScreen(new ShopScreenBuy());
                }
                else if(type == 3){
                    InputManager.get.clear();
                    game.openScreen(new HangarScreen());
                }
                else
                {
                    InputManager.get.clear();
                    game.openScreen(new GameScreen(levelId));

                }
            } else {  //touched level is different from current level
                if (!sh.getTravelsRoute()) {      //only call navigate function, if the ship is not already on route
                    //tell PathNavigationManager to navigate to this level
                    //only send ship to beacon if it has been activated
                    if (activated) {
                        //System.out.println("Ship should navigate now!");
                        pnm.navigateToBeacon(this);
                    }
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
     * get, if this beacon leads to a shop, hangar or level
     * @return isShop
     */
    public int getType(){
        return type;
    }

    /**
     * get if this beacon has already been activated -> reachable
     * @return activated
     */
    public boolean getActivated(){
        return activated;
    }

    /**
     * getter for widht
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter for height
     * @return height
     */
    public int getHeight() {
        return height;
    }
}
