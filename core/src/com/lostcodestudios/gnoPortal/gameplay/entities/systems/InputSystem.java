package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.lostcode.javalib.entities.Entity;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class InputSystem extends com.lostcode.javalib.entities.systems.InputSystem {

	public InputSystem(InputMultiplexer input) {
		super(input);
	}

	@Override
	protected void process(Entity e) {
		// TODO Auto-generated method stub
		super.process(e);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("paddleLeft");
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.F1){
			((PongWorld)world).enableDebug();
			return true;
		}
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return super.keyUp(keycode);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return super.mouseMoved(screenX, screenY);
	}
	
	

}
