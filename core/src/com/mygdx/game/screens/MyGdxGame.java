package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Items.ItemManager;
import com.mygdx.game.dataPersistence.DataPers;
import com.mygdx.game.managers.camera.CameraManager;
import com.mygdx.game.utils.FPSLimiter;

public class MyGdxGame extends Game
{
	public static MyGdxGame game;

	public SpriteBatch batch;
	public SpriteBatch uiBatch;
	public BitmapFont font;
	public BitmapFont dialogFont;
	public BitmapFont debugFont;
	public ShapeRenderer shapeRenderer;
	public FPSLimiter fpsLimit;
	public String currentVersion;
	public int screenWidth;
	public int screenHeight;
	public boolean showOverlay;

	/*
		cam manager needs to be here to initiate the input manager
	*/
	public CameraManager cm;

	public Screen current;

	public void create() {
		game = this;
		screenWidth = 1080;
		screenHeight = 1920;
		batch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		//create font from truetype with help of FreeType extension
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("CubicCoreMono.ttf"));
		//create the parameter for the generator, so we can change the size of the font
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 90;
		font = generator.generateFont(parameter);
		dialogFont = generator.generateFont(parameter);
		parameter.size = 50;
		debugFont = generator.generateFont(parameter);
		generator.dispose();
		//font.getData().scale(1.0f);
		shapeRenderer = new ShapeRenderer();
		fpsLimit = new FPSLimiter(60);
		currentVersion = "Prototype v0.4.0";

		font.setColor(Color.WHITE);
		debugFont.setColor(Color.WHITE);
		dialogFont.setColor(Color.BLACK);
		showOverlay = true;

		DataPers.dataP().addToSkins(0);
		DataPers.dataP().addToSkins(1);
		DataPers.dataP().addToSkins(2);

		// TODO this resets saves every start
		DataPers.resetP();
		DataPers.resetS();

		DataPers.dataH().setSlot1(ItemManager.ItemNames.TELEPORT.ordinal());
		DataPers.dataH().setSlot2(ItemManager.ItemNames.BREAK.ordinal());
		DataPers.dataH().setCurrentSkin(6);
		DataPers.saveH();

		openScreen(new MainMenuScreen(0,true));
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
		debugFont.dispose();
	}
}