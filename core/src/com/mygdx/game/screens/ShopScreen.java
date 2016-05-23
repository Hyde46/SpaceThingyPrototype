package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Vali on 23.05.2016.
 */
public class ShopScreen implements Screen {


    OrthographicCamera cam;

    public ShopScreen(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1080,1920);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        MyGdxGame.game.batch.setProjectionMatrix(cam.combined);
        //draw string indicating, that this is a shop (to be removed later)
        MyGdxGame.game.batch.begin();
        MyGdxGame.game.font.draw(MyGdxGame.game.batch, "Prototype v0.0.4", 5 , 30);
        MyGdxGame.game.font.draw(MyGdxGame.game.batch, "This is a shop!", 320, 920);
        MyGdxGame.game.batch.end();
    }

    @Override
    public void dispose()    {

    }
    @Override
    public void show()    {

    }
    @Override
    public void hide()    {

    }
    @Override
    public void resume()    {

    }
    @Override
    public void resize(int x, int y)    {

    }
    @Override
    public void pause()    {

    }





}
