package com.lostcodestudios.gnoPortal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.InputScreen;

public class MenuScreen extends InputScreen {
	BitmapFont consoleFont;
	SpriteBatch sb;
	Array<String> consoleData;
	int currentInput = 11;
	StringBuilder input;
	
	public MenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		consoleFont = new BitmapFont(Gdx.files.internal("console.fnt"));
		sb = new SpriteBatch();
		
		input =new StringBuilder("");
		consoleData = new Array<String>();
		consoleData.add("#!/bin/ld");
		consoleData.add("welcome dude");
		consoleData.add("someguy@a-computer$ help");
		consoleData.add("LCS bash, version 1.3.7(1)-release. Type `help' to see this list");
		consoleData.add(" ");
		consoleData.add("A star (*) next to a name means that the command is disabled.");
		consoleData.add("  play          -a fun game            ");
		consoleData.add("  quit          -a sad choice ");
		consoleData.add(" *rm -rf /      -deletes everything");
		consoleData.add(" *while[true]   -please don't ");
		consoleData.add(" *help          -just choose a command already");
		consoleData.add("someguy@a-computer$ ");
		
	}

	@Override
	public void render(float delta) {
		sb.begin();
		int firstIndex = Math.max(consoleData.size-24, 0);
		for(int i = firstIndex; i < Math.min(firstIndex + 24, consoleData.size); i++){
			if(i == currentInput)
				consoleFont.draw(sb, consoleData.get(i) + input.toString(), 3, Gdx.graphics.getHeight() - 3 - i*17);
			else 
				consoleFont.draw(sb, consoleData.get(i), 3, Gdx.graphics.getHeight() - 3 - i*17);
		}
		sb.end();

	}
	
	@Override
	public boolean keyTyped(char character) {
		if(character != '\b')
			input.append(character);
		else if(input.length()>0)
			input.deleteCharAt(input.length()-1);
		else return false;
		return true;
	};

	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ENTER)
			processCommand(input.toString());
		else return false;
		return true;
	};
	
	private void processCommand(String command) {
		consoleData.set(currentInput, consoleData.get(currentInput)+ command );
		if(command.equals("quit"))
			Gdx.app.exit();
		else if (command.equals("play"))
			exit();
		else{
			consoleData.add("come on dude...");
			consoleData.add("$");
			consoleData.add("someguy@a-computer$ ");
		}
		
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
