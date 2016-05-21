package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.utils.FPSLimiter;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;

	public FPSLimiter fpsLimit;

	public String currentVersion;

	public void create() {
		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		font.getData().scale(1.0f);
		shapeRenderer = new ShapeRenderer();
		fpsLimit = new FPSLimiter(60);
		currentVersion = "Prototype v0.0.7";
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
