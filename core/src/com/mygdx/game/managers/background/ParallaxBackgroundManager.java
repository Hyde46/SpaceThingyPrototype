package com.mygdx.game.managers.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.renderAbleObjects.decorations.BackGround;

/**
 * Created by Denis on 24.05.2016.
 */
public class ParallaxBackgroundManager {

    private Array<BackGround> layers;
    private float[] layerDamp;

    public ParallaxBackgroundManager(){
        layers = new Array<BackGround>();

    }

    public void setLayers(int numlayers){
        layerDamp = new float[numlayers+1];
        BackGround[] layers = new BackGround[numlayers];

        for(int i = 0; i < numlayers; ++i){
            layers[i] = new BackGround();
            layers[i].initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_stars"+i+".png");
            layerDamp[i] = (i+1)*0.1f;
            this.layers.add(layers[i]);
        }
        BackGround hex = new BackGround();
        this.layers.add(hex);
        layerDamp[numlayers] = 0.001f;
        hex.initialize(new Vector2(0,0),new Vector2(1080,1920),3,"bg_hex.png");
    }

    public void render(SpriteBatch g){
        for(BackGround bg : layers){
            bg.render(g);
        }
    }

    public void noticeTranslation(Vector2 translation){
        layers.get(0).getSprite().translate(translation.x*layerDamp[0],translation.y*layerDamp[0]);
        layers.get(1).getSprite().translate(translation.x*layerDamp[1],translation.y*layerDamp[1]);
        layers.get(2).getSprite().translate(translation.x*layerDamp[2],translation.y*layerDamp[2]);
    }
}