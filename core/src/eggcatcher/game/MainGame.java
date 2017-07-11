package eggcatcher.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Priyadarshi Raj on 25/6/17.
 *
 * Main game class
 */

public class MainGame extends Game {
	// Viewport and constants
	public static final float VIEWPORT_WIDTH = 352f;
	public static final float VIEWPORT_HEIGHT = 320f;
	public static final Viewport viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());

	// Textures
	public static TextureAtlas atlas;
	public static TextureRegion background;

	// Fonts
	private BitmapFont font;
	public static final Label.LabelStyle[] labelStyles = new Label.LabelStyle[2];

	// Most important
	public static SpriteBatch batch;
	
	@Override
	public void create() {
        atlas = new TextureAtlas("egg-catcher.atlas");
        background = atlas.findRegion("Map");
		font = new BitmapFont(Gdx.files.internal("BoxyBold.fnt"));

		// Active and inactive label styles
		labelStyles[0] = new Label.LabelStyle(font, Color.FIREBRICK);	// Normal
		labelStyles[1] = new Label.LabelStyle(font, Color.SCARLET);		// When hovered over

		batch = new SpriteBatch();

		changeScreen(Screens.MENU_SCREEN);
	}

	public static void changeScreen(Screen screen) {
		((Game) Gdx.app.getApplicationListener()).setScreen(screen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Disabling blending for background improves performance
		batch.disableBlending();
		batch.begin();
		batch.draw(background, 0f, 0f);
		batch.end();

		batch.enableBlending();

		super.render();
	}
	
	@Override
	public void dispose() {
		font.dispose();
		background.getTexture().dispose();
		atlas.dispose();
		batch.dispose();
	}
}
