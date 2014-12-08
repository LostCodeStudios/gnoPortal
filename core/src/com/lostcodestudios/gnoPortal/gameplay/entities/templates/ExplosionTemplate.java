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

public class ExplosionTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(final Entity e, EntityWorld world, Object... args) {
		e.init("", "", "explosion");
		Vector2 position = (Vector2) args[0];
		
		String peFile;
		if(args.length > 1)
			peFile = "portaldeath.particle";
		else
			peFile = "explosion.particle";
		ParticleEffect pe = new ParticleEffect(
				Gdx.files.internal(peFile),
				Gdx.files.internal("img"));
		pe.start();
		pe.setLayer(3);
		e.addComponent(pe);
		
		e.addComponent(new Particle(e, position.cpy(), 0));
		
		world.getProcessManager().attach(new DelayProcess(3,
				new DeletionProcess(e)));
		
		
		
		return e;
	}

}
