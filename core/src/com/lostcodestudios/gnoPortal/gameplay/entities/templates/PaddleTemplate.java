package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class PaddleTemplate implements EntityTemplate {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String side = (String)args[0];
		
		e.init("paddle" + side, "paddles", "player");
		
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "paddle");
		e.addComponent(sprite);
		
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

		
		Body body = new Body(world, e, BodyType.DynamicBody, shape, new Vector2(posX, posY));
		e.addComponent(body);
		return e;
	}

}
