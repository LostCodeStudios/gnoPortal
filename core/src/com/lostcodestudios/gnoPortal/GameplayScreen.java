package com.lostcodestudios.gnoPortal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.InputScreen;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class GameplayScreen extends InputScreen {

	private PongWorld world;
	private Camera camera;
	BitmapFont consoleFont;
	SpriteBatch sb;
	Array<String> consoleData;
	
	/**
	 * Makes a GameplayScreen.
	 * @param game
	 */
	public GameplayScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(722, 462);
		world = new PongWorld(game.getInput(), camera, Vector2.Zero.cpy());
		
		consoleData = new Array<String>();
		consoleData.add("tits");
		consoleData.add("boobs");
		consoleFont = new BitmapFont(Gdx.files.internal("console.fnt"));
		sb = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		//RENDER and update CONSOLE UNDERNEATH
		
		sb.begin();
		int firstIndex = Math.max(consoleData.size-24, 0);
		for(int i = firstIndex; i < Math.min(firstIndex + 24, consoleData.size); i++)
				consoleFont.draw(sb, consoleData.get(i), 3, Gdx.graphics.getHeight() - 3 - (i-firstIndex)*17);
		
		sb.end();
		
		
		//GAUSSIAN BLURR
		
		//world stuff
		world.process();
		
		if (world.isGameOver()) {
			//StealthGameOverInfo info = (StealthGameOverInfo) world.getGameOverInfo();
			
			exit();
			//game.getScreenManager().addScreen(new GameOverScreen(game, info.score));
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void show() {
		super.show();
		
		world.resume(); 
	}

	@Override
	public void hide() {
		super.hide();
		
		world.pause();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			game.getScreenManager().addScreen(new MenuScreen(game));
			exit();
		}
		
		return true;
	}
	
	

}