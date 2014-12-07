package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.entities.systems.EntitySystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.TargetPosition;

public class TargetSystem extends EntitySystem {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(Entity e) {
		TargetPosition pos = e.getComponent(TargetPosition.class);
		
		Vector2 tar = pos.target.cpy();
		
		Transform t = e.getComponent(Transform.class);

		float dist = tar.cpy().sub(t.getPosition()).len();
		if (dist < 1f) { //epsilon
			// reached target
			
			pos.onReachedTarget(world, e);
		}
		else {
			Velocity v = e.getComponent(Velocity.class);
			
			v.setLinearVelocity(tar.sub(t.getPosition()).nor().scl(pos.speed));
		}
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.hasComponent(TargetPosition.class);
	}

}
