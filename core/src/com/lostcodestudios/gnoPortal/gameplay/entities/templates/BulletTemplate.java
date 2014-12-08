package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.TargetPosition;

public class BulletTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
//0: color
		// 1: pos
		// 2: target pos
		// 3: group
		final String color = (String) args[0];
		final String group = (String) args[3];
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), color + "bullet");
		
		e.addComponent(sprite);
		
		Vector2 position = (Vector2) args[1];
		Vector2 target = (Vector2) args[2];
		
		Particle p = new Particle(e, position, 0f);
		
		e.addComponent(p);
		
		TargetPosition tar = new TargetPosition(target, 60f) {

			@Override
			public void onReachedTarget(EntityWorld world, Entity e) {
				e.delete();
				
				// delete old one
				Entity e1 = null;
				
				do {
					 e1 = world.tryGetEntity(color, "player", "portal");
				
					if (e1 != null)
						e1.delete();
					else
						System.out.println("oops");
				} while (e1 != null);	
				
				world.createEntity("portal", group, target, color);
			}
			
		};
		
		e.addComponent(tar);
		
return e;
	}

}
