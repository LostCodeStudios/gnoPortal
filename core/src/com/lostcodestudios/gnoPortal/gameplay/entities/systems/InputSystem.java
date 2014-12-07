package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class InputSystem extends com.lostcode.javalib.entities.systems.InputSystem {

	Vector2 leftPaddleVelocity = new Vector2();
	
	public InputSystem(InputMultiplexer input) {
		super(input);
	}

	@Override
	protected void process(Entity e) {
		leftPaddleVelocity.scl(PongWorld.PADDLE_SPEED);
		((Body) e.getComponent(Body.class)).setLinearVelocity(leftPaddleVelocity);
		
		super.process(e);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("paddleleft");
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.F1){
			((PongWorld)world).enableDebug();
			return true;
		}
		
		if (keycode == Keys.W)
		{
			leftPaddleVelocity.y = 1;
			return true;
		}
		else if (keycode == Keys.S)
		{
			leftPaddleVelocity.y = -1;
			return true;
		}
		

		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.W || keycode == Keys.S)
		{
			leftPaddleVelocity.y = 0;
			return true;
		}
		
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
