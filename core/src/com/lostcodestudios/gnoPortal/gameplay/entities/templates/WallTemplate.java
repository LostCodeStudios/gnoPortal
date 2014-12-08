package com.lostcodestudios.gnoPortal.gameplay.entities.templates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.GameOverInfo;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class WallTemplate implements EntityTemplate{

	public WallTemplate(){
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(final Entity e, final EntityWorld world, Object... args) {
		e.init((String)args[0], "walls", "wall");
		
		Rectangle r = (Rectangle)args[1];
		
		final Sprite sprite = new Sprite(world.getSpriteSheet(), "pixel");
		
		sprite.setWidth(r.width);
		sprite.setHeight(r.height);
		
		sprite.setOrigin(new Vector2(r.width/2f, r.height/2f));
		
		sprite.setLayer(0);
		
		e.addComponent(sprite);
		
		if(e.getTag().equals("leftWall") || e.getTag().equals("rightWall"))
		{
			Health h = new Health(e,world, 500){
				@Override
				protected void onEmpty() {
					//End the game;
					world.setGameOverInfo(new GameOverInfo());
					
					
					
					super.onEmpty();
				}
				
				@Override
				protected void onDrain(double amount) {
				
					float hR = (float)((getCurrentValue()+10)/(getMaxValue()+10));
					sprite.setColor(new Color(Math.min(1, hR+.3f), hR, hR, 1));
					if(getCurrentValue() - amount <= 0) {
						world.setGameOverInfo(new GameOverInfo());
						
						float dir = -1;
						float rot = 0f;
						
						if (e.getTag().equals("leftWall")) {
							rot = (float) Math.PI;
							dir = 1;
						}
						
						Vector2 origin = ((Transform)e.getComponent(Transform.class)).getPosition();
						
						float x = origin.x + dir * 3;
						float y = origin.y;
						

							world.createEntity("trail", new Vector2(x,y ), Color.WHITE, rot);
			
					}
					super.onDrain(amount);
				}
			};
			e.addComponent(h);
		}
		
		e.addComponent(new Collidable(){

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
		shape.setAsBox(Convert.pixelsToMeters(r.width/2f), Convert.pixelsToMeters(r.height/2f));
		e.addComponent(new Body(world, e, BodyType.StaticBody, shape, Convert.pixelsToMeters(new Vector2(r.x, r.y))));
		return e;
	}

}
