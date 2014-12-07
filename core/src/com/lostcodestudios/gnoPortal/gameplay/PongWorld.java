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
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.SpriteSheet;
import com.lostcodestudios.gnoPortal.gameplay.entities.systems.InputSystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.BallTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.CrosshairTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PaddleTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PortalTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.WallTemplate;

/**
 * @author william
 *
 */
public class PongWorld extends EntityWorld {

	public static final float PADDLE_SPEED = 25f;
	public Entity Ball;
	
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
		this.addTemplate("crosshair", new CrosshairTemplate());
		this.addTemplate("portal", new PortalTemplate());
	}
	
	private InputSystem inputSystem;
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		systems.addSystem(inputSystem = new InputSystem(input, Ball));
	}

	@Override
	protected void buildEntities() {
		this.createEntity("paddle", "left");
		this.createEntity("paddle", "right");
		Ball = inputSystem.Ball = this.createEntity("ball", "left");
		this.createEntity("wall", "testWall", new Rectangle(0,0,100,100));
		this.createEntity("wall", "bottomWall", new Rectangle(0,-462/2f,722f,24));
		this.createEntity("wall", "top", new Rectangle(0,462/2f,722f,24));
		this.createEntity("wall", "leftWall", new Rectangle(-722f/2-6,0,24,462f));
		this.createEntity("wall", "rightWall", new Rectangle(722f/2+6,0,24,462f));
		this.createEntity("crosshair");
	}
	
	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(new Rectangle(-722f/2, -462f/2, 722f, 462f));
	}

	public void enableDebug(){
		debugView.enabled = true;
	}
}
