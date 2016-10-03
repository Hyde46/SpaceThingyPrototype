package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.IInputHandler;
import com.mygdx.game.InputManager.TouchData;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.shop.ShopItem;

/**
 * Created by Mechandrius on 19.06.2016.
 * Hyde
 */
public abstract class Item extends Decoration implements IInputHandler
{
    protected String name;
    protected int idItem;

    public enum StateItem {READY, ACTIVATED, EFFECT, COOLDOWN}

    public int level;
    public StateItem stateItem;

    protected int maxUses;
    protected int uses;

    /*
    *Determines whether the item is on pos 0(left) oder 1(right) oder 2(mitte)
     */
    // ??
    protected int sideInHud;
    protected int levelPos;
    //protected ItemManager iM;
    protected GameScreen gs;


    protected SpaceShip player;

    public Item()
    {
        stateItem = StateItem.READY;
    }

    public float timeCooldown;

    public void activateSuper() {
        stateItem = StateItem.ACTIVATED;
    }

    public void deactivateSuper() {
        stateItem = StateItem.READY;
    }

    public void effectStartSuper() {
        stateItem = StateItem.EFFECT;
    }

    public void effectEndSuper() {
        stateItem = StateItem.COOLDOWN;
    }

    public abstract void activate();
    public abstract void effectStart();
    public abstract void effectEnd();
    public abstract void deactivate();
    public abstract void initialize();

    /* when item is thrown out of inventory, etc
    -> item will reset, cancels everything */
    public abstract void reset();

    // will be overriden in subclass because some items dont need the update
    public void update(float delta) {

    }

    public void render(SpriteBatch sB){
        if(!isActive || tex == null){
            return;
        }
        sprite.draw(sB);
    }
    public void renderHitboxes(ShapeRenderer b){

    }

    public void initialize(String texturePath, int widthHeight, Vector2 posToRender){
        initializePositions(posToRender.cpy());
        initializeTexture(new Vector2(widthHeight,widthHeight), 0, texturePath);
        isUI = true;
        //touchHitbox = new Rectangle(posToRender.x, GameScreen.camFixed.viewportHeight-(posToRender.y+200),200,200);
        touchHitbox = new Rectangle(posToRender.x, Gdx.graphics.getHeight()-(posToRender.y+200),200,200);
    }

    @Override
    public void OnTouch(TouchData td) {
        if(ItemManager.get.getPlayer().isCollided()||ItemManager.get.hasLevelEnded())
            return;
        if(stateItem == StateItem.READY && uses > 0){
            activateSuper();
            uses--;
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

    public GameScreen getGS() { return gs; }
    public StateItem getState(){
        return stateItem;
    }
    public String getName() { return name; }
    public int getUses(){return uses;};
}