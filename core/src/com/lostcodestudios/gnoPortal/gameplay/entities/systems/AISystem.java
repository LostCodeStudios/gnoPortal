package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.systems.EntitySystem;
import com.lostcode.javalib.utils.Convert;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.AI;

public class AISystem extends EntitySystem {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(Entity e) {
		AI ai = e.getComponent(AI.class);
		Body target = ai.target.getComponent(Body.class);
		Body eb = e.getComponent(Body.class);
		
		if(dist(eb,target) < 200 && target.getLinearVelocity().x > 0)
			defense(eb, target);
		else
			move(eb,0);
	}

	public float dist(Body a, Body b){
		return a.getPosition().cpy().sub(b.getPosition()).len();
	}
	
	public Vector2 disp(Body a, Body b){
		return a.getPosition().cpy().sub(b.getPosition());
	}
	
	public Vector2 disp(Vector2 a, Vector2 b){
		return a.cpy().sub(b);
	}
	
	public boolean onScreen(Vector2 a){
		return Convert.pixelsToMeters(new Rectangle(-722/2, -462/2, 722, 462)).contains(a);
	}
	
	public boolean testy(float y){
		return onScreen(new Vector2(0,y));
	}
	
	public void move(Body b, float y){
		
		Vector2 pos = b.getPosition().cpy();
		
		Vector2 dir = disp(new Vector2(pos.x, y),pos);
		

		
	
		if(dir.len() > 4){
			Vector2 vel = new Vector2(0, Math.signum(dir.y)*PongWorld.PADDLE_SPEED);
			b.setLinearVelocity(vel);
		}
		else
			b.setLinearVelocity(Vector2.Zero.cpy());
	}
	
	
	@Override
	public boolean canProcess(Entity e) {
		return e.hasComponent(AI.class);
	}
	
	public void defense(Body pb, Body bb){
		Vector2 vb = bb.getLinearVelocity().cpy();
		Vector2 b = bb.getPosition();
		Vector2 p = pb.getPosition();
		
		//x1
		float y1 = disp(p,b).x / vb.x * vb.y + b.y;
		if(testy(y1))
			move(pb, y1);
		
		else
		{
			//calculate x2
			float scY = vb.y > 0 ? 462/2 : -462/2;
			Vector2 w = new Vector2((scY - b.y)/vb.y*vb.x, scY);
			float y2 = disp(p,w).x/vb.x*vb.y + w.y;
			if(testy(y2))
				move(pb, y2);
			else
				move(pb,0);
		}
			
		
	}

}
