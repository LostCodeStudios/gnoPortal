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
	String input;
	
	public MenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		consoleFont = new BitmapFont(Gdx.files.internal("console.fnt"));
		sb = new SpriteBatch();
		
		input = new String();
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
				consoleFont.draw(sb, consoleData.get(i) + input.toString(), 3, Gdx.graphics.getHeight() - 3 - (i-firstIndex)*17);
			else 
				consoleFont.draw(sb, consoleData.get(i), 3, Gdx.graphics.getHeight() - 3 - (i-firstIndex)*17);
		}
		sb.end();

	}
	
	@Override
	public boolean keyTyped(char character) {
		if(character == '\b' && input.length()>0)
			input = input.substring(0, input.length()-1);
		else if(character == '\r')
			processCommand(input.toString());
		else
			input += (character);
		return true;
	};


	
	private void processCommand(String command) {
		
		if(command.equals("quit") || command.equals("exit"))
			Gdx.app.exit();
		else if (command.equals("play")) {
			game.getScreenManager().addScreen(new GameplayScreen(game));
			
			exit();
		}
		else{
			consoleData.set(currentInput, consoleData.get(currentInput)+ command );
			if(command.equals("rm") || command.equals("rm -rf") 
					||command.equals("rm -rf /") || command.equals("while[true]") || command.equals("help"))
				consoleData.add("I'm sorry Dave. I'm afraid I can't do that.");
			else if(command.equals("dance"))
				consoleData.add("I AM NOT UR DANCE SLAVE BRO. call 8018010781 for a prize.");
			else
				consoleData.add("come on dude...");
			consoleData.add("someguy@a-computer$ ");
			input = "";
			currentInput = consoleData.size-1;
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
