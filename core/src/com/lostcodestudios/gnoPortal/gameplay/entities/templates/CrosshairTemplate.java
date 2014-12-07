package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class CrosshairTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("crosshair", "", "crosshair");
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "crosshair");
		
		e.addComponent(sprite);
		
		Particle particle = new Particle(e, new Vector2(0, 0), 0f, sprite.getOrigin());
		e.addComponent(particle);
		
		return e;
	}

}
