package com.lostcodestudios.gnoPortal.gameplay.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;

public abstract class TargetPosition implements Component {

	public Vector2 target = Vector2.Zero.cpy();
	public float speed;
	
	public TargetPosition(Vector2 pos, float speed) {
		this.target = pos;
		this.speed = speed;
	}
	
	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}
	
	public abstract void onReachedTarget(EntityWorld world, Entity e);

}
