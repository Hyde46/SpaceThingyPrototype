package com.mygdx.game.screens.shop;

/**
 * Created by ilost on 18.09.2016.
 */

public class ShopItem
{
    private String name = "";
    private String desciption = "";
    private int price = 0;
    private boolean isSellable = true;

    public ShopItem()
    {
        //TODO test
        name = "ItemTest";
        desciption = "This is a test item";
        price = 10;
        isSellable = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSellable() {
        return isSellable;
    }

    public void setSellable(boolean sellable) {
        isSellable = sellable;
    }
}