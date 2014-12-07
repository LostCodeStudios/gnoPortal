package com.lostcodestudios.gnoPortal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.states.InputScreen;
import com.lostcode.javalib.utils.Convert;
import com.lostcodestudios.gnoPortal.gameplay.PongWorld;

public class GameplayScreen extends InputScreen {

	private PongWorld world;
	private Camera camera;
	BitmapFont consoleFont;
	SpriteBatch sb;
	Array<String> consoleData;		
	

	ShaderProgram blurShader;
	FrameBuffer blurTargetA, blurTargetB;
	TextureRegion fboRegion;
	
	OrthographicCamera cam;
	
	/**
	 * Makes a GameplayScreen.
	 * @param game
	 */
	public GameplayScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(722, 462);
		world = new PongWorld(game.getInput(), camera, Vector2.Zero.cpy());
		
		consoleData = new Array<String>();
		consoleData.add("tits");
		consoleData.add("boobs");
		consoleFont = new BitmapFont(Gdx.files.internal("console.fnt"));
		
		//important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;
		
		blurShader = new ShaderProgram(VERT, FRAG);
		if (!blurShader.isCompiled()) {
			System.err.println(blurShader.getLog());
			System.exit(0);
		}
		if (blurShader.getLog().length()!=0)
			System.out.println(blurShader.getLog());
		
		//setup uniforms for our shader
		blurShader.begin();
		blurShader.setUniformf("dir", 0f, 0f);
		blurShader.setUniformf("resolution", FBO_SIZE);
		blurShader.setUniformf("radius", 1f);
		blurShader.end();
		
		blurTargetA = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		blurTargetB = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		fboRegion = new TextureRegion(blurTargetA.getColorBufferTexture());
		fboRegion.flip(false, true);
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false);
	}

	@Override
	public void render(float delta) {
		
		//update cionsike
		
		//render console
		this.renderConsole();
		
		//world stuff
		world.process();
		
		if (world.isGameOver()) {
			//StealthGameOverInfo info = (StealthGameOverInfo) world.getGameOverInfo();
			
			exit();
			//game.getScreenManager().addScreen(new GameOverScreen(game, info.score));
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void show() {
		super.show();
		
		world.resume(); 
	}

	@Override
	public void hide() {
		super.hide();
		
		world.pause();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			game.getScreenManager().addScreen(new MenuScreen(game));
			exit();
		}
		
		return true;
	}
	
	public static final int FBO_SIZE = 1024;
	 
	public static final float MAX_BLUR = 2f;
	
	void resizeBatch(int width, int height) {
		cam.setToOrtho(false, width, height);
		sb.setProjectionMatrix(cam.combined);
	}
	
	public void renderConsole(){
		Vector2 ballGradient;
			ballGradient = new Vector2(.25f, .25f);
				
		
		//Start rendering to an offscreen color buffer
		blurTargetA.begin();
		
		//Clear the offscreen buffer with an opaque background
		Gdx.gl.glClearColor(0.1845f, 0.0390625f, .140625f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//before rendering, ensure we are using the default shader
		sb.setShader(null);
		
		//resize the batch projection matrix before drawing with it
		resizeBatch(FBO_SIZE, FBO_SIZE);
		
		//now we can start drawing...
		sb.begin();
		
		//RENDER and update CONSOLE UNDERNEATH
		
		//GAUSSIAN BLURR;
		int firstIndex = Math.max(consoleData.size-24, 0);
		for(int i = firstIndex; i < Math.min(firstIndex + 24, consoleData.size); i++)
				consoleFont.draw(sb, consoleData.get(i), 8, Gdx.graphics.getHeight() - 14 - (i-firstIndex)*17);

		
		
		//finish rendering to the offscreen buffer
		sb.flush();
		
		//finish rendering to the offscreen buffer
		blurTargetA.end();
		
		//now let's start blurring the offscreen image
		sb.setShader(blurShader);
		
		//since we never called batch.end(), we should still be drawing
		//which means are blurShader should now be in use
		
		//ensure the direction is along the X-axis only
		blurShader.setUniformf("dir", 1f, 0f);
		
		//update blur amount based on touch input
		blurShader.setUniformf("radius", ballGradient.x * MAX_BLUR);
		
		//our first blur pass goes to target B
		blurTargetB.begin();
		
		//we want to render FBO target A into target B
		fboRegion.setTexture(blurTargetA.getColorBufferTexture());
		
		//draw the scene to target B with a horizontal blur effect
		sb.draw(fboRegion, 0, 0);
		
		//flush the batch before ending the FBO
		sb.flush();
		
		//finish rendering target B
		blurTargetB.end();
		
		//now we can render to the screen using the vertical blur shader
		
		//update our projection matrix with the screen size
		resizeBatch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//update the blur only along Y-axis
		blurShader.setUniformf("dir", 0f, 1f);
		
		//update the Y-axis blur radius
		blurShader.setUniformf("radius", ballGradient.y * MAX_BLUR);
		
		//draw target B to the screen with a vertical blur effect 
		fboRegion.setTexture(blurTargetB.getColorBufferTexture());
		sb.draw(fboRegion, 0, 0);
		
		//reset to default shader without blurs 
		sb.setShader(null);
		
		
		//finally, end the batch since we have reached the end of the frame
		sb.end();
	}
	
	final String VERT =  
			"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
			"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			
			"uniform mat4 u_projTrans;\n" + 
			" \n" + 
			"varying vec4 vColor;\n" +
			"varying vec2 vTexCoord;\n" +
			
			"void main() {\n" +  
			"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
			"}";
	
	final String FRAG =
			"#ifdef GL_ES\n" + 
			"#define LOWP lowp\n" + 
			"precision mediump float;\n" + 
			"#else\n" + 
			"#define LOWP \n" + 
			"#endif\n" + 
			"varying LOWP vec4 vColor;\n" + 
			"varying vec2 vTexCoord;\n" + 
			"\n" + 
			"uniform sampler2D u_texture;\n" + 
			"uniform float resolution;\n" + 
			"uniform float radius;\n" + 
			"uniform vec2 dir;\n" + 
			"\n" + 
			"void main() {\n" + 
			"	vec4 sum = vec4(0.0);\n" + 
			"	vec2 tc = vTexCoord;\n" + 
			"	float blur = radius/resolution; \n" + 
			"    \n" + 
			"    float hstep = dir.x;\n" + 
			"    float vstep = dir.y;\n" + 
			"    \n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) * 0.05;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) * 0.09;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) * 0.12;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x - 1.0*blur*hstep, tc.y - 1.0*blur*vstep)) * 0.15;\n" + 
			"	\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.16;\n" + 
			"	\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 1.0*blur*hstep, tc.y + 1.0*blur*vstep)) * 0.15;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 2.0*blur*hstep, tc.y + 2.0*blur*vstep)) * 0.12;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y + 3.0*blur*vstep)) * 0.09;\n" + 
			"	sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y + 4.0*blur*vstep)) * 0.05;\n" + 
			"\n" + 
			"	gl_FragColor = vColor * vec4(sum.rgb, 1.0);\n" + 
			"}";
	 

}