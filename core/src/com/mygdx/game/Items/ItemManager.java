package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputManager.InputManager;
import com.mygdx.game.Items.Level1.ItemPickerRadius;
import com.mygdx.game.Items.Level1.SpeedBooser;
import com.mygdx.game.Items.Level2.Break;
import com.mygdx.game.Items.Level2.ItemPickTarget;
import com.mygdx.game.Items.Level2.TeleportRandom;
import com.mygdx.game.Items.Level3.ArtificialPlanet;
import com.mygdx.game.Items.Level3.DestroyTarget;
import com.mygdx.game.Items.Level3.PhaseOut;
import com.mygdx.game.Items.Level3.Teleport;
import com.mygdx.game.renderAbleObjects.decorations.Decoration;
import com.mygdx.game.renderAbleObjects.units.SpaceShip;
import com.mygdx.game.renderAbleObjects.units.Unit;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MyGdxGame;
import com.mygdx.game.screens.shop.ShopItem;

/**
 * Created by Denis on 27.06.2016.
 */
public class ItemManager
{
    //some sort of safedata, with informations about which items the player has is needed

    // while 1 item is activated ... orbit leave not possible ..
    // item activation can trigger orbit leave with same touch

    // when 1 item activated other gets deactivated ... activate has to go over manager

    public static ItemManager get;

    public boolean isOneItemActive() {
        // is item 0, 1 == activated
        return true;
    }

    private Item[] items;
    private ItemNames[] itemIds;

    private int itemCounter;

    private GameScreen gs;

    public ItemManager() {
        items = new Item[2];
        itemIds = new ItemNames[2];
        items[0] = null;
        items[1] = null;
        itemIds[0] = ItemNames.NONE;
        itemIds[1] = ItemNames.NONE;
    }

    public static void initialize(GameScreen gs) {
        get = new ItemManager();
        get.gs = gs;
    }

    public boolean setItems(ItemNames itemIdLeft, ItemNames itemIdRight) {
        if (itemIdLeft == ItemNames.NONE && itemIdRight != ItemNames.NONE) {
            addItem(itemIdRight, 1, true);
            return true;
        }
        if (itemIdLeft != ItemNames.NONE && itemIdRight == ItemNames.NONE) {
            addItem(itemIdLeft, 0, true);
            return true;
        }
        if (itemIdLeft != ItemNames.NONE && itemIdRight != ItemNames.NONE) {
            addItem(itemIdLeft, 0, false);
            addItem(itemIdRight, 1, false);
            return true;
        }
        return false;
    }

    public boolean addItem(ItemManager.ItemNames itemname, int sideToAdd, boolean isOneItem) {
        if (itemname == itemIds[0] || itemname == itemIds[1]) {
            return false;
        }
        // int itemPos = getItemPos(sideToAdd);
        int itemPos = isOneItem ? 2 : sideToAdd;

        // Vorteil wenn Item ungebunden an pos/side erzeugbar .. nur 1 riesen switch benötigt
        // Item itemTest = getItemOfName(nameItem);
        // itemTest.setItemPos(0);
        // itemTest.setSideToAdd(1);

        switch (itemname) {
            case ITEM_PICKER_RANGE:
                items[sideToAdd] = new ItemPickerRadius(itemPos, sideToAdd);
                break;
            case SPEED_BOOSTER:
                items[sideToAdd] = new SpeedBooser(itemPos, sideToAdd, this);
                break;
            case ITEM_PICKER_TARGET:
                items[sideToAdd] = new ItemPickTarget(itemPos, sideToAdd);
                break;
            case BREAK:
                items[sideToAdd] = new Break(itemPos, sideToAdd, this);
                break;
            case ARTIFICIAL_PLANET:
                items[sideToAdd] = new ArtificialPlanet(itemPos, sideToAdd, this, gs);
                break;
            case DESTROY_TARGET:
                items[sideToAdd] = new DestroyTarget(itemPos, sideToAdd, gs);
                break;
            case PHASE_OUT:
                items[sideToAdd] = new PhaseOut(itemPos, sideToAdd, this);
                break;
            case TELEPORT_RANDOM:
                items[sideToAdd] = new TeleportRandom(itemPos, sideToAdd);
                break;
            case TELEPORT:
                items[sideToAdd] = new Teleport(itemPos, sideToAdd);
                break;
            default:
                break;
        }
        itemIds[sideToAdd] = itemname;

        items[sideToAdd].initialize();

        InputManager.get.register(items[sideToAdd]);

        return true;
    }
//
//    public ShopItem extractDataShop(ItemNames itemName)
//    {
//        int posItem = 0;
//        int sideToAdd = 0;
//
//        Item itemWanted = null;
//
//        switch (itemName) {
//            case ITEM_PICKER_RANGE:
//                itemWanted = new ItemPickerRadius(posItem, sideToAdd);
//                break;
//            case SPEED_BOOSTER:
//                itemWanted = new SpeedBooser(posItem, sideToAdd, this);
//                break;
//            case ITEM_PICKER_TARGET:
//                itemWanted = new ItemPickTarget(posItem, sideToAdd);
//                break;
//            case BREAK:
//                itemWanted = new Break(posItem, sideToAdd, this);
//                break;
//            case ARTIFICIAL_PLANET:
//                itemWanted = new ArtificialPlanet(posItem, sideToAdd, this, gs);
//                break;
//            case DESTROY_TARGET:
//                itemWanted = new DestroyTarget(posItem, sideToAdd, gs);
//                break;
//            case PHASE_OUT:
//                itemWanted = new PhaseOut(posItem, sideToAdd, this);
//                break;
//            case TELEPORT_RANDOM:
//                itemWanted = new TeleportRandom(posItem, sideToAdd);
//                break;
//            case TELEPORT:
//                itemWanted = new Teleport(posItem, sideToAdd);
//                break;
//            default:
//                break;
//        }
//
//
//        if(itemWanted != null)
//            return itemWanted.getDataShop();
//        else
//            return new ShopItem();
//    }

    public boolean removeItemSlot(int sideToRemove) {
        return true;
    }

    public void render(SpriteBatch batch) {
        //Debug
//        if (items[0] != null) {
//            MyGdxGame.game.debugFont.draw(batch, "Item 1: " + items[0].getName(), 350, 1900);
//            MyGdxGame.game.debugFont.draw(batch, "Status: " + items[0].getState(), 350, 1850);
//        } else {
//            MyGdxGame.game.debugFont.draw(batch, "Item 1: NONE", 350, 1900);
//        }
//        if (items[1] != null) {
//            MyGdxGame.game.debugFont.draw(batch, "Item 2: " + items[1].getName(), 750, 1900);
//            MyGdxGame.game.debugFont.draw(batch, "Status: " + items[1].getState(), 750, 1850);
//        } else {
//            MyGdxGame.game.debugFont.draw(batch, "Item 2: NONE", 700, 1900);
//        }
        if (items[0] != null) {
            MyGdxGame.game.debugFont.draw(batch,"Cooldown: "+(int)(items[0].timeCooldown/1000), (items[0].getSprite().getX()), items[0].getSprite().getY()-25);
            MyGdxGame.game.debugFont.draw(batch,"Uses: "+items[0].getUses(), items[0].getSprite().getX(), items[0].getSprite().getY()-60);
        }
        if (items[1] != null) {
            MyGdxGame.game.debugFont.draw(batch,"Cooldown: "+(int)(items[1].timeCooldown/1000), (items[1].getSprite().getX()), items[1].getSprite().getY()-25);
            MyGdxGame.game.debugFont.draw(batch,"Uses: "+items[1].getUses(), items[1].getSprite().getX(), items[1].getSprite().getY()-60);
        }

        //button render
        if (items[0] != null) {
            items[0].render(batch);
        }
        if (items[1] != null) {
            items[1].render(batch);
        }
    }

    public void update(float delta) {
        if (items[0] != null) {
            items[0].update(delta);
        }
        if (items[1] != null) {
            items[1].update(delta);
        }
    }

    public SpaceShip getPlayer() {
        return gs.getPlayerShip();
    }
    public boolean hasLevelEnded() {
        return gs.isLevelFinished();
    }
    public void addUnitToManager(Unit u){
        gs.addUnitToManager(u);
    }
    public void addDecoToManager(Decoration u){
        gs.addDecoToManager(u);
    }

    public void dispose(){
        if(items[0] != null){
            items[0].dispose();
        }
        if(items[1] != null){
            items[1].dispose();
        }
    }

    public enum ItemNames {NONE,ITEM_PICKER_TARGET,ITEM_PICKER_RANGE,SPEED_BOOSTER,TRACEJTORY_CHANGER45,TRAJECTORY_CHANGER90,TRAJECTORY_INDICATOR,BREAK,DESTROY_RADIUS,
        FORWARD_SHOOT,MANUAL_CONTROL,SHIELD_CONTROLABLE,SHIELD_ROTATION,STABILIZER,TELEPORT_RANDOM,ARTIFICIAL_PLANET,DESTROY_TARGET,PHASE_OUT,SHIELD_FULL,TELEPORT};

    public static ItemNames getItemNameOfId(int idItem)
    {
        // :)
        ItemNames[] names = ItemNames.values();
        if(idItem >= names.length || idItem < 0) return ItemNames.NONE;
        return ItemNames.values()[idItem];
    }

//    public static ItemNames getItemNameOfId(int ordinal){
//        switch(ordinal){
//            case 0:
//                return ItemNames.NONE;
//            case 1:
//                return ItemNames.ITEM_PICKER_TARGET;
//            case 2:
//                return ItemNames.ITEM_PICKER_RANGE;
//            case 3:
//                return ItemNames.SPEED_BOOSTER;
//            case 4:
//                return ItemNames.TRACEJTORY_CHANGER45;
//            case 5:
//                return ItemNames.TRAJECTORY_CHANGER90;
//            case 6:
//                return ItemNames.TRAJECTORY_INDICATOR;
//            case 7:
//                return ItemNames.BREAK;
//            case 8:
//                return ItemNames.DESTROY_RADIUS;
//            case 9:
//                return ItemNames.FORWARD_SHOOT;
//            case 10:
//                return ItemNames.MANUAL_CONTROL;
//            case 11:
//                return ItemNames.SHIELD_CONTROLABLE;
//            case 12:
//                return ItemNames.SHIELD_ROTATION;
//            case 13:
//                return ItemNames.STABILIZER;
//            case 14:
//                return ItemNames.TELEPORT_RANDOM;
//            case 15:
//                return ItemNames.ARTIFICIAL_PLANET;
//            case 16:
//                return ItemNames.DESTROY_TARGET;
//            case 17:
//                return ItemNames.PHASE_OUT;
//            case 18:
//                return ItemNames.SHIELD_FULL;
//            case 19:
//                return ItemNames.TELEPORT;
//            default: return ItemNames.NONE;
//        }
//    }
}

//    public Item getItemFromId(int idItem) {
//        switch (idItem) {
//            case 1:
//                return new SpeedBooser(0, 0, this);
//            case 6:
//                return new Break(0, 0, this);
//            case 7:
//                return new ArtificialPlanet(0, 0, this, gs);
//            case 8:
//                return new DestroyTarget(0, 0, this, gs);
//            case 9:
//                return new PhaseOut(0, 0, this);
//            default:
//                return null;
//        }
//    }

