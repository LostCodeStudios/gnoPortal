/**
 * 
 */
package com.lostcodestudios.gnoPortal.gameplay;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;

/**
 * @author william
 *
 */
public class PongWorld extends EntityWorld {

	public PongWorld(InputMultiplexer input, Camera camera, Vector2 gravity) {
		super(input, camera, gravity);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#buildSpriteSheet()
	 */
	@Override
	protected void buildSpriteSheet() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
