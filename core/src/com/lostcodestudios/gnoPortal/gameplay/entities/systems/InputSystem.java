package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.BallTemplate;

public class InputSystem extends com.lostcode.javalib.entities.systems.InputSystem {

	Vector2 leftPaddleVelocity = new Vector2();
	Vector2 crosshairPosition = new Vector2();
	public Entity Ball;
	boolean ballfired =false;
	
	public InputSystem(InputMultiplexer input, Entity ball) {
		super(input);
		this.Ball = ball;
	}

	@Override
	protected void process(Entity e) {
		if (e.getTag().equals("paddleleft")) {
			leftPaddleVelocity.scl(PongWorld.PADDLE_SPEED);
			((Body) e.getComponent(Body.class)).setLinearVelocity(leftPaddleVelocity);
		}
		
		else if (e.getTag().equals("crosshair")) {
			((Particle) e.getComponent(Particle.class)).setPosition(crosshairPosition);
		}
		
		
		super.process(e);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("paddleleft") || e.getTag().equals("crosshair");
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
		if(!ballfired){
			Vector2 pos = world.toWorldCoordinates(new Vector2(screenX, screenY));
			
			Body bb = Ball.getComponent(Body.class);
			
			pos.sub(bb.getPosition());
			pos.nor();
			
			bb.setLinearVelocity(pos.cpy().scl(BallTemplate.VELOCITY));
			ballfired = true;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 pos = world.toWorldCoordinates(new Vector2(screenX, screenY));
		
		if (world.getBounds().contains(pos)) {
			crosshairPosition = pos.cpy();
		}
		
		return true;
	}


}
