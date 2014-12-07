package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class WallTemplate implements EntityTemplate{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init((String)args[0], "walls", "wall");
		
		Rectangle r = (Rectangle)args[1];
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "pixel");
		
		sprite.setWidth(r.width);
		sprite.setHeight(r.height);
		
		sprite.setOrigin(new Vector2(r.width/2f, r.height/2f));
		
		e.addComponent(sprite);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Convert.pixelsToMeters(r.width/2f), Convert.pixelsToMeters(r.height/2f));
		e.addComponent(new Body(world, e, BodyType.StaticBody, shape, Convert.pixelsToMeters(new Vector2(r.x, r.y))));
		return e;
	}

}
