package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.ParticleEffect;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class TrailTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "trails", "trail");
		
		Vector2 position = (Vector2)args[0];
		Color tint = (Color)args[1];
		float rot = (Float)args[2];
		
		ParticleEffect pe = new ParticleEffect(Gdx.files.internal("stream.particle"),Gdx.files.internal("img"));
		pe.setColor(tint);
		pe.setRotation(rot);
		pe.setPosition(position);
		e.addComponent(pe);
		pe.start();
		e.addComponent(new Particle(e, position, rot));
		
		return e;
	}

}
