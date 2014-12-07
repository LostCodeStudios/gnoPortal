package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class PaddleTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String side = (String)args[0];
		
		e.init("paddle" + side, "paddles", "player");
		return null;
	}

}
