package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.TriggerZone;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Sensor;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class PortalTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity buildEntity(final Entity e, EntityWorld world, Object... args) {
		//args
		// 0: group
		// 1: position
		// 2: color
		
		final String color = (String) args[2];
		final String antiColor;
		e.init(color, (String) args[0], "portal");
		final String  group = (String) args[0];
		if(color.equals("blue"))
			antiColor = "orange";
		else
			antiColor = "blue";
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "portal" + color);
		
		sprite.setLayer(3);
		
		e.addComponent(sprite);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Convert.pixelsToMeters(sprite.getWidth() / 2), Convert.pixelsToMeters(sprite.getHeight() / 2));
		
		Body body = new Body(world, e, BodyType.StaticBody, (Vector2) args[1]);
		e.addComponent(body);
		
		TriggerZone sensor = new TriggerZone(e, shape) {

			@Override
			public void onDetected(Entity elol, EntityWorld world) {
				super.onDetected(elol, world);
				
				Entity other = world.tryGetEntity(antiColor, group, "");
				if(other != null && elol.getTag().equals("ball"))
				{
					
					//CAREFUL IF WE HAVE OTHER MOVING OBJECTS BEING DETECTED
					//THEY WILL BE PORTED
					Transform bb = elol.getComponent(Transform.class);
					Transform ob = other.getComponent(Transform.class);
					bb.setPosition(ob.getPosition());
					
					other.delete();
					e.delete();
					
					//POSSIBLY PLAY A TELEPORT PARTICLE EFFECT
				}
			}
			
		};
	
		e.addComponent(sensor);
		
		return e;
	}

}
