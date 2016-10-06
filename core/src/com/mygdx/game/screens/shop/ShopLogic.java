package com.mygdx.game.screens.shop;

import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.dataPersistence.DataPers;
import java.util.ArrayList;

/**
 * Created by ilost on 18.09.2016.
 */

public class ShopLogic
{
    private int levelShop;
    private int levelId;
    private boolean isBuyMode;

    public int getLevelShop() {
        return levelShop;
    }
    public void setLevelShop(int levelShop) {
        this.levelShop = levelShop;
    }
    public int getLevelId() {
        return levelId;
    }
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    public boolean isBuyMode() {
        return isBuyMode;
    }
    public void setBuyMode(boolean buyMode) {
        isBuyMode = buyMode;
    }

    private ArrayList<Integer> idsItemPlayer;
    private ArrayList<Integer> idsItemShop;
    private int creditsPlayer;

    public ArrayList<Integer> getIdsItemPlayer() {
        return idsItemPlayer;
    }
    public void setIdsItemPlayer(ArrayList<Integer> idsItemPlayer) {
        this.idsItemPlayer = idsItemPlayer;
    }
    public ArrayList<Integer> getIdsItemShop() {
        return idsItemShop;
    }
    public void setIdsItemShop(ArrayList<Integer> idsItemShop) {
        this.idsItemShop = idsItemShop;
    }
    public int getCreditsPlayer() {
        return creditsPlayer;
    }
    public void setCreditsPlayer(int creditsPlayer) {
        this.creditsPlayer = creditsPlayer;
    }

    public ShopLogic(int levelShop,int levelId)
    {
        this.levelShop = levelShop;
        this.levelId = levelId;
    }

    public void loadDataShop()
    {
        idsItemPlayer = DataPers.dataP().idsItemsPlayer;
        idsItemShop = DataPers.dataS().idsItemsAvailable[levelShop];
        creditsPlayer = DataPers.dataP().credits;
    }

    public void saveDataShop()
    {
        DataPers.dataP().credits = creditsPlayer;
        DataPers.dataP().idsItemsPlayer = idsItemPlayer;
        DataPers.dataS().idsItemsAvailable[levelShop] = idsItemShop;
        DataPers.saveP();
        DataPers.saveS();
    }

    public void buyItem(int idItem)
    {
        ShopItem item = ManagerShopItem.getShopItem(idItem);
        int price = ItemManager.getItemPrice(idItem);

        if(creditsPlayer >= price)
        if(idsItemShop.contains(new Integer(idItem)))
        {
            creditsPlayer -= price;
            idsItemPlayer.add(idItem);
            idsItemShop.remove(new Integer(idItem));
        }
        else
        {
            // no money
        }
    }

    public void sellItem(int idItem)
    {
        if(idsItemPlayer.contains(new Integer(idItem)));
        {
            ShopItem item = ManagerShopItem.getShopItem(idItem);

            creditsPlayer += (int)((double) ItemManager.getItemPrice(idItem)/2.0);
            idsItemPlayer.remove(new Integer(idItem));
            idsItemShop.add(idItem);
            if(DataPers.dataH().getSlot1() == idItem){
                DataPers.dataH().setSlot1(-1);
            }
            if(DataPers.dataH().getSlot2() == idItem){
                DataPers.dataH().setSlot2(-1);
            }
            DataPers.saveH();
        }
    }
}