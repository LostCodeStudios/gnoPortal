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
		
		title = "Ninja Space Pirate";
		
		landscapeMode = false;
		
		super.create();

		
		getScreenManager().addScreen(new GameScreen(this) {
			
			@Override
			public void show() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void render(float delta) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void hide() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	protected void loadSounds() {
		// TODO Auto-generated method stub
		
	}
}
