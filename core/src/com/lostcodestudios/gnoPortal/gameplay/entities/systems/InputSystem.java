package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.utils.Convert;
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
			
			Body b = e.getComponent(Body.class);

			
			b.setLinearVelocity(leftPaddleVelocity);

			Vector2 pos = b.getPosition().cpy();
			
			float hy = Convert.pixelsToMeters(139f/2f);
			
			float top = Convert.pixelsToMeters(462f/2f - 8f);
			float bot = -top;
			
			if (pos.y + hy > top) {
//				pos.y = top - hy;
				b.setPosition(new Vector2(pos.x, top-hy));
				
			} else if (pos.y - hy < bot) {
//				pos.y = bot + hy;
				b.setPosition(new Vector2(pos.x, bot+hy));
			}
			
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
			leftPaddleVelocity.y = PongWorld.PADDLE_SPEED;
			return true;
		}
		else if (keycode == Keys.S)
		{
			leftPaddleVelocity.y = -PongWorld.PADDLE_SPEED;
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
		else {
		

			Vector2 pos = world.toWorldCoordinates(new Vector2(screenX, screenY));
			
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				// left click?
				
				
				Entity player = world.tryGetEntity("paddleleft", "paddles", "player");
				
				Transform t = player.getComponent(Transform.class);
				Vector2 playerPos = t.getPosition();
				
				world.createEntity("bullet", "blue", playerPos, pos);
			}
			if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
				// right click?
				
				
				
Entity player = world.tryGetEntity("paddleleft", "paddles", "player");
				
				Transform t = player.getComponent(Transform.class);
				Vector2 playerPos = t.getPosition();
				
				world.createEntity("bullet", "orange", playerPos, pos);
			}
			
			return false;	
		
		}
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
