package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.utils.FPSLimiter;

public class MyGdxGame extends Game {

	public static MyGdxGame game;

	public SpriteBatch batch;
	public SpriteBatch uiBatch;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;
	public FPSLimiter fpsLimit;
	public String currentVersion;

	/*
		cam manager needs to be here to initiate the input manager
	*/
	public CameraManager cm;

	public Screen current;

	public void create() {
		game = this;
		batch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		font.getData().scale(1.0f);
		shapeRenderer = new ShapeRenderer();
		fpsLimit = new FPSLimiter(60);
		currentVersion = "Prototype v0.0.9";
		openScreen(new MainMenuScreen());
	}

	public void openScreen(Screen screen)
	{
		current = screen;
		setScreen(screen);
	}

	public void render() {
		super.render(); //important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
