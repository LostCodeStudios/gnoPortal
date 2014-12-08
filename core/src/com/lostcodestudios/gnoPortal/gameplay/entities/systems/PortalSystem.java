package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.systems.GroupSystem;
import com.lostcode.javalib.entities.systems.TypeSystem;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class PortalSystem extends TypeSystem {

	public static float ATTRACTION = 10000;
	public PortalSystem() {
		super("portal");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(Entity e) {
		// TODO Auto-generated method stub
		String antiColor;
		if(e.getTag().equals("orange"))
			antiColor = "blue";
		else
			antiColor = "orange";
		
		if( !world.isGameOver() && ( world.tryGetEntity(antiColor, "player", "")) != null){
			Entity ball = ((PongWorld)world).Ball;
			//apply gravitational force to ball
			
			Body bb = ball.getComponent(Body.class);
			
			Vector2 force = (((Transform)e.getComponent(Transform.class)).getPosition().cpy().sub(bb.getPosition()));
			force.scl((float) (ATTRACTION/Math.pow(force.len(),3))); //F = m1m2/||r||^3
			bb.getBody().applyForceToCenter(force, true);
		}
			

	}

}
