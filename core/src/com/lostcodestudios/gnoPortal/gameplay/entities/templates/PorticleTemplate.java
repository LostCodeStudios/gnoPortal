package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.ParticleEffect;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.processes.DelayProcess;
import com.lostcode.javalib.entities.processes.DeletionProcess;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class PorticleTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(final Entity e, final EntityWorld world, Object... args) {
		e.init("", "", "porticle");
		

		String color = (String) args[0];
		Entity portal = (Entity) args[1];
		Body pbody = portal.getComponent(Body.class);
		
		
		ParticleEffect pe = new ParticleEffect(
				Gdx.files.internal(color + ".particle"),
				Gdx.files.internal("img"));
		pe.start();
		pe.setLayer(3);
		e.addComponent(pe);
		
		final Vector2 position = pbody.getPosition().cpy();
		e.addComponent(new Particle(e,position, 0));
		portal.onDeleted.addCallback("particle", new EventCallback(){
			@Override
			public void invoke(Entity port, Object... args) {
				e.delete();
			}
		});
		
		
		return e;
	}

}
