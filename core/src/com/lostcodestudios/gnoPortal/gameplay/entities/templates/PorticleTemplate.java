package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class PorticleTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String color = (String) args[0];
		
		
		
		return e;
	}

}
