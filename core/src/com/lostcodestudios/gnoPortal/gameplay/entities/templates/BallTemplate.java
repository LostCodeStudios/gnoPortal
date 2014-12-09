package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class BallTemplate implements EntityTemplate {
	public static final  float VELOCITY = 20;
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, final EntityWorld world, Object... args) {
		String side = (String)args[0];
		
		
		
		e.init("ball", "balls", "ball");
		
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "ball");
		
		sprite.setLayer(4);
		
		e.addComponent(sprite);
		
		e.onDeleted.addCallback("key", new EventCallback() {

			@Override
			public void invoke(Entity e, Object... args) {
				((PongWorld)world).mainMenu();
			}
			
		});
		
		 CircleShape shape = new CircleShape();
		 shape.setRadius(Convert.pixelsToMeters(sprite.getWidth()/2f));
		 
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.restitution = 1.007f;
		fd.friction=0;
		bd.position.set(Convert.pixelsToMeters(new Vector2(-722f/2f + 64f, 0)));
		bd.type = BodyType.DynamicBody;
		bd.allowSleep = false;
		bd.fixedRotation = false;
		final Body body = new Body(world, e, bd, fd);
		e.addComponent(body);
		
		e.addComponent(new Collidable(){
			@Override
			public void onBeginContact(Entity container, Entity victim) {
				if(victim.hasComponent(Health.class)){
					Health h = victim.getComponent(Health.class);
					Transform vic = victim.getComponent(Transform.class);
					h.drain(10);
					Vector2 splodePos = new Vector2(
							(vic.getPosition().x+ body.getPosition().x)/2f,
							body.getPosition().y);
					world.createEntity("explosion", splodePos);
					
				}
				System.out.println("Collision");
				super.onEndContact(container, victim);
			}
			@Override 
			public float continueCollision(Entity container, Entity victim) {
				if(victim.hasComponent(Health.class)){
					Health h = victim.getComponent(Health.class);
					h.drain(10);
					if(h.getCurrentValue() <= 0)
						return 0;
				}
				return 1;
			}
			@Override
			public void onAdd(ComponentManager container) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRemove(ComponentManager container) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return e;
	}

}
