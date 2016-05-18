package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;

	public void create() {
		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		font.getData().scale(1.0f);
		shapeRenderer = new ShapeRenderer();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
