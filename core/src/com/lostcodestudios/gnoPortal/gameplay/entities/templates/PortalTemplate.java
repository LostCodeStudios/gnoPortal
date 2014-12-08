package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.TriggerZone;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class PortalTemplate implements EntityTemplate {

	float portalC = 0;
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
					portalC++;
					
					//CAREFUL IF WE HAVE OTHER MOVING OBJECTS BEING DETECTED
					//THEY WILL BE PORTED
					Transform bb = elol.getComponent(Transform.class);
					Transform ob = other.getComponent(Transform.class);
					bb.setPosition(ob.getPosition());
					Velocity bv = elol.getComponent(Velocity.class);
					bv.setLinearVelocity(bv.getLinearVelocity().cpy().nor().scl((float) (BallTemplate.VELOCITY*Math.pow(1.2, portalC))));
					
					other.delete();
					e.delete();
					
					//POSSIBLY PLAY A TELEPORT PARTICLE EFFECT
				}
			}
			
		};
	
		e.addComponent(sensor);
		
		Vector2 position = (Vector2) args[1];
		
		float dim = 8;
		world.getBox2DWorld().QueryAABB(new QueryCallback() {

			@Override
			public boolean reportFixture(Fixture fixture) {
				Entity blocker = (Entity) fixture.getBody().getUserData();
				
				if (blocker.getType().equals("wall") || blocker.getType().equals("ball")) {
				
					e.delete(); // haha idk if this will workj
				}
				return false;
			}
			
		}, position.x - dim, position.y - dim, position.x + dim, position.y + dim);
		
		world.createEntity("porticle", color, e);
		
		return e;
	}

}
