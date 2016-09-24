package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.renderAbleObjects.decorations.TeleportRangeDetector;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.utils.SpaceMath;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class Teleport extends Item
{

    private float teleportMaxRadius;
    private TeleportRangeDetector teleportRangeDetector;

    private SpaceShip player;
    private Vector2 spaceShipPosition;

    private final static float maxCooldown = 5000;

    public Teleport(int itemPos, int sideToAdd)
    {
        super();
        this.level = 3;
        sideInHud = sideToAdd;
        levelPos = itemPos;
        teleportRangeDetector = new TeleportRangeDetector();
    }
    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        initialize("teleport_200x200.png",200,posToRender);
        teleportMaxRadius = 190;
        player = ItemManager.get.getPlayer();
        spaceShipPosition = player.getPosition();
        teleportRangeDetector.initialize(false,teleportMaxRadius,spaceShipPosition,"orbit2_"+(int)(teleportMaxRadius*2)+ "x" + (int) (teleportMaxRadius * 2) + ".png");
        name = "Teleport";
        uses = 3;
        ItemManager.get.addUnitToManager(teleportRangeDetector);
        InputManager.get.register(teleportRangeDetector);
        player.setTeleportRangeDetector(teleportRangeDetector);
        teleportRangeDetector.switchActive(false);
    }

    @Override
    public void activate() {

    }

    @Override
    public void effectStart() {

    }

    @Override
    public void effectEnd() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void reset() {

    }
    @Override
    public void update(float delta){
        if(ItemManager.get.getPlayer().isInOrbit()){
            teleportRangeDetector.switchActive(false);
            effectEndSuper();
        }
        if(stateItem == StateItem.ACTIVATED){
            stateItem = StateItem.EFFECT;
            timeCooldown = maxCooldown;
            teleportRangeDetector.resetState();
            teleportRangeDetector.switchActive(true);
        }
        if(stateItem == StateItem.EFFECT ){
            teleportRangeDetector.updatePosition(player.getPosition());
            if(teleportRangeDetector.isDoneTeleporting()){
                teleportRangeDetector.switchActive(false);
                effectEndSuper();
            }
        }
        if(stateItem == StateItem.COOLDOWN){
            timeCooldown -=1000*delta;
            if(timeCooldown <= 0){
                deactivateSuper();
                timeCooldown = 0;
            }
        }
    }

    public void render(SpriteBatch sB){

        if(!isActive || tex == null){
            return;
        }

        sprite.draw(sB);

        //Debug
    }
}
