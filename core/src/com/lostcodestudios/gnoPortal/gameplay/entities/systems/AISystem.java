package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.entities.systems.EntitySystem;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.AI;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.TargetPosition;

public class AISystem extends EntitySystem {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(Entity e) {
		AI ai = e.getComponent(AI.class);
		
		Vector2 cv = ((Velocity)ai.target.getComponent(
				Velocity.class)).getLinearVelocity();
		Vector2 cp = ((Transform)ai.target.getComponent(
				Transform.class)).getPosition();
		
		Vector2 pv = ((Velocity)e.getComponent(
				Velocity.class)).getLinearVelocity();
		Vector2 pp = ((Transform)e.getComponent(
				Transform.class)).getPosition();
		
		float distance = pp.x - cp.x;
		float time = distance / cv.x;
		
		if(distance < 46){
		if (cp.y + time * cv.y > pp.y + time * (((time*cv.y + cp.y) -pp.y)/Math.abs(time*cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)* PongWorld.PADDLE_SPEED
				&& cp.y - time * cv.y > pp.y + time * (((time*-cv.y + cp.y) -pp.y)/Math.abs(time*-cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)* PongWorld.PADDLE_SPEED){
		} else
		{
			if(cp.y - time * cv.y > pp.y + time * (((time*-cv.y + cp.y) -pp.y)/Math.abs(time*-cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)* PongWorld.PADDLE_SPEED)
			{
				System.out.println(time);
				if(Math.abs(pp.y - (time*cv.y + cp.y)) > 6)
					((Velocity)e.getComponent(
							Velocity.class)).setLinearVelocity(new Vector2(0, (((time*cv.y + cp.y) -pp.y)/Math.abs(time*cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)));
				else
					((Velocity)e.getComponent(
							Velocity.class)).setLinearVelocity(new Vector2(0,0));
			}
			else if(cp.y + time * cv.y > pp.y + time * (((time*cv.y + cp.y) -pp.y)/Math.abs(time*cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)* PongWorld.PADDLE_SPEED)
			{
				System.out.println(time);
				if(Math.abs(pp.y - (time*-cv.y + cp.y)) > 6)
					((Velocity)e.getComponent(
							Velocity.class)).setLinearVelocity(new Vector2(0, (((time*-cv.y + cp.y) -pp.y)/Math.abs(time*-cv.y + cp.y -pp.y)*PongWorld.PADDLE_SPEED)));
				else
					((Velocity)e.getComponent(
							Velocity.class)).setLinearVelocity(new Vector2(0,0));
			}
		}
		}
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.hasComponent(AI.class);
	}

}
