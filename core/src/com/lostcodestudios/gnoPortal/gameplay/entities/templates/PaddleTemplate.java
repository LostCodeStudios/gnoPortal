package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;
import com.lostcodestudios.gnoPortal.gameplay.entities.components.AI;

public class PaddleTemplate implements EntityTemplate {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String side = (String)args[0];
		
		e.init("paddle" + side, "paddles", "player");
		
		e.onDeleted.addCallback("this", new EventCallback() {

			@Override
			public void invoke(Entity e, Object... args) {
				System.out.println("deleted paddle");
			}
			
		});
		Sprite sprite = new Sprite(world.getSpriteSheet(), "paddle");
		
		sprite.setLayer(3);
		e.addComponent(sprite);
		
		// collidable so it will let the ball play sounds!
		e.addComponent(new Collidable() {

			@Override
			public void onAdd(ComponentManager container) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRemove(ComponentManager container) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		PolygonShape shape = new PolygonShape();
		 
		shape.setAsBox(Convert.pixelsToMeters(sprite.getWidth()/2f),
				Convert.pixelsToMeters(sprite.getHeight()/2f));
		
		float posX = 0f;
		float posY = 0f;
		
		if (side.equals("left")) {
			posX = -Convert.pixelsToMeters(722f/2f) + 5f;
		}
		
		if (side.equals("right")) {
			posX = Convert.pixelsToMeters(722f/2f) - 5f;
		}

		
		Body body = new Body(world, e, BodyType.KinematicBody, shape, new Vector2(posX, posY));
		e.addComponent(body);
		
		if (side.equals("right")) {
			AI ai = new AI();
			
			ai.target = (Entity) args[1];
			
			e.addComponent(ai);
		}
		
		return e;
	}

}
