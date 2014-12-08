package com.lostcodestudios.gnoPortal;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.states.GameScreen;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.LogManager;
import com.lostcode.javalib.utils.LogManager.LogType;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class GnoPortal extends Game {
	SpriteBatch batch;
	Texture img;
	
	EntityWorld world;
	
	@Override
	public void create () {
		Convert.init(8f);
		LogManager.init(Gdx.app, LogType.ERROR);
		
		title = "gnoPortal";
		
		landscapeMode = true;
		super.width = 462;
		super.height = 722;
		
		
		super.create();
		
		getScreenManager().addScreen(new MenuScreen(this));
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1845f, 0.0390625f, .140625f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreenManager().render();
	}

	@Override
	protected void loadSounds() {
		// TODO Auto-generated method stub
		
	}
}
