package com.mygdx.game.Items.Level1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.renderAbles.ItemButton;
import com.mygdx.game.utils.SpaceMath;
import com.mygdx.game.utils.SpacePhysiX;

/**
 * Created by Mechandrius on 25.06.2016.
 */
public class SpeedBooser extends Item
{
    public SpeedBooser(int itemPos)
    {
        super();
        this.level = 1;
        this.levelPos = itemPos;
        itemButton = new ItemButton();
    }

    @Override
    public void initialize(){
        Vector2 posToRender = SpaceMath.getPosToRender(levelPos);
        itemButton.initialize("speedBooster_200x200.png",200,posToRender);
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

    public void update(float delta)
    {

    }

    @Override
    public void render(SpriteBatch sB){
        itemButton.render(sB);
    }
}
