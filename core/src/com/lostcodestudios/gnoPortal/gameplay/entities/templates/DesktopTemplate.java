package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class DesktopTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("desktop.png")));
		
		sprite.setOrigin(new Vector2(1021, 490));
		
		e.addComponent(sprite);
		
		Particle p = new Particle(e, Vector2.Zero.cpy(), 0f);
		e.addComponent(p);
		
		return e;
	}

}
