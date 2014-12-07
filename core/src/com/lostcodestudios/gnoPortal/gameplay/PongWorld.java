/**
 * 
 */
package com.lostcodestudios.gnoPortal.gameplay;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.SpriteSheet;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.BallTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PaddleTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.WallTemplate;

/**
 * @author william
 *
 */
public class PongWorld extends EntityWorld {

	public PongWorld(InputMultiplexer input, Camera camera, Vector2 gravity) {
		super(input, camera, gravity);
		// TODO Auto-generated constructor stub
		
		debugView.enabled = true;
	}

	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#buildSpriteSheet()
	 */
	@Override
	protected void buildSpriteSheet() {
		try {
			this.spriteSheet = SpriteSheet.fromXML(Gdx.files.internal("spritesheet.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void buildTemplates() {
		this.addTemplate("paddle", new PaddleTemplate());
		this.addTemplate("ball", new BallTemplate());
		this.addTemplate("wall",new WallTemplate());
	}
	
	@Override
	protected void buildEntities() {
		this.createEntity("paddle", "left");
		this.createEntity("paddle", "right");
		this.createEntity("ball", "left");
		this.createEntity("wall", "testWall", new Rectangle(0,0,100,100));
	}
	
	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(new Rectangle(-722f/2, -462f/2, 722f/2, 462f/2));
	}

	public void enableDebug(){
		debugView.enabled = true;
	}
}
