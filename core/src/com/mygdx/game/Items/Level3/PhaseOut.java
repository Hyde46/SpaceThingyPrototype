package com.mygdx.game.Items.Level3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Items.Item;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class PhaseOut extends Item
{
    public PhaseOut()
    {
        super();
        this.level = 3;
    }
    @Override
    public void initialize(){

    }

    @Override
    public void render(SpriteBatch sB){
        itemButton.render(sB);
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
}
