package com.lostcodestudios.gnoPortal.gameplay.entities.systems;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.systems.EntitySystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.AI;

public class AISystem extends EntitySystem {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(Entity e) {
		AI ai = e.getComponent(AI.class);
		
		// TODO AI porcessing
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.hasComponent(AI.class);
	}

}
