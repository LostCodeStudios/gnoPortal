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
import com.lostcodestudios.gnoPortal.gameplay.entities.systems.AISystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.systems.InputSystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.systems.PortalSystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.systems.TargetSystem;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.BallTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.BulletTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.CrosshairTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.DesktopTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.ExplosionTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PaddleTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PortalTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.PorticleTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.TrailTemplate;
import com.lostcodestudios.gnoPortal.gameplay.entities.templates.WallTemplate;

/**
 * @author william
 *
 */
public class PongWorld extends EntityWorld {

	public static final float PADDLE_SPEED = 35f;
	public Entity Ball;

	int level;
	
	public PongWorld(InputMultiplexer input, Camera camera, Vector2 gravity, int level) {
		super(input, camera, gravity);
		// TODO Auto-generated constructor stub
		debugView.enabled = true;
		
		this.level = level;
		
		System.out.println("level" + level);
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
		this.addTemplate("desktop", new DesktopTemplate());
		this.addTemplate("bullet", new BulletTemplate());
		this.addTemplate("trail", new TrailTemplate());
		this.addTemplate("porticle", new PorticleTemplate());
		this.addTemplate("explosion", new ExplosionTemplate());
	}
	
	private AISystem aiSystem;
	private InputSystem inputSystem;
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		systems.addSystem(inputSystem = new InputSystem(input, Ball));
		systems.addSystem(new TargetSystem());
		aiSystem = new AISystem();
		systems.addSystem(aiSystem);
		systems.addSystem(new PortalSystem());
	}

	@Override
	protected void buildEntities() {
		this.createEntity("desktop");
		
		Ball = inputSystem.Ball = this.createEntity("ball", "left");
		
		this.createEntity("paddle", "left");
		this.createEntity("paddle", "right", Ball);

		this.createEntity("wall", "bottomWall", new Rectangle(0,-462/2f-8,722f,24));
		this.createEntity("wall", "top", new Rectangle(0,462/2f+8,722f,24));
		this.createEntity("wall", "leftWall", new Rectangle(-722f/2-8,0,24,462f));
		this.createEntity("wall", "rightWall", new Rectangle(722f/2+8,0,24,462f));
		this.createEntity("crosshair");

	}
	public void buildLevel(int level) {
		System.out.println("level" + level);
		
		aiSystem.level = level;
		
		switch (level) {
			//make things interesting!!
		
		case 1:
			
			this.createEntity("wall", "testWall", new Rectangle(0,0,24,462));
			
			break;
			
		case 2:
			
			this.createEntity("wall", "1", new Rectangle(0, 0, 24, 462/3));
			this.createEntity("wall", "2", new Rectangle(-64, 128, 24, 462/3));
			this.createEntity("wall", "3", new Rectangle(64, 128, 24, 462/3));
			this.createEntity("wall", "4", new Rectangle(-64, -128, 24, 462/3));
			this.createEntity("wall", "5", new Rectangle(64, -128, 24, 462/3));
			
			break;
			
		case 3:
			
			this.createEntity("wall", "1", new Rectangle(0, 64, 722/4, 24));
			this.createEntity("wall", "2", new Rectangle(0, -64, 722/4, 24));
			this.createEntity("wall", "3", new Rectangle(-200, 128, 722/4, 24));
			this.createEntity("wall", "4", new Rectangle(200, -128, 722/4, 24));
			
			break;
			
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lostcode.javalib.entities.EntityWorld#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(new Rectangle(-722f/2 * 2.33f, -462f/2 * 2.33f, 722f * 2.33f, 462f * 2.33f));
	}

	public void enableDebug(){
		debugView.enabled = true;
	}
}
