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
	public static final  float VELOCITY = 20;
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
		fd.restitution = 1.01f;
		fd.friction=0;
		bd.position.set(Convert.pixelsToMeters(new Vector2(-722f/2f + 60f, 0)));
		bd.type = BodyType.DynamicBody;
		bd.allowSleep = false;

		Body body = new Body(world, e, bd, fd);
		e.addComponent(body);
		return e;
	}

}
