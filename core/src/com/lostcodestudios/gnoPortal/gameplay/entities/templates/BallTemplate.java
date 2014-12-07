package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class BallTemplate implements EntityTemplate {
	private static final Vector2 BODY_POSITION = new Vector2(0, -5);
	private static final float BODY_RADIUS = 4f;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String side = (String)args[0];
		
		e.init("ball", "balls", "ball");
		
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "ball");
		e.addComponent(sprite);
		
		 CircleShape shape = new CircleShape();
		 shape.setRadius(Convert.pixelsToMeters(sprite.getWidth()/2f));
		 
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.restitution = 1;
		fd.friction=0;
		bd.position.set(BODY_POSITION.cpy());
		bd.type = BodyType.DynamicBody;
		bd.allowSleep = false;
		
		
		
		Body body = new Body(world, e, bd, fd);
		e.addComponent(body);
		return e;
	}

}
