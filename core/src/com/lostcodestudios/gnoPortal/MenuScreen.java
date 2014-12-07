package com.lostcodestudios.gnoPortal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.InputScreen;

public class MenuScreen extends InputScreen {
	BitmapFont consoleFont;
	SpriteBatch sb;
	Array<String> consoleData;
	public MenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		consoleFont = new BitmapFont(Gdx.files.internal("assets/console.fnt"));
		sb = new SpriteBatch();
		consoleData = new Array<String>();
		consoleData.add("welcome dude");
	}

	@Override
	public void render(float delta) {
		consoleFont.draw(batch, str, x, y)

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sb.dispose();
	}

}
