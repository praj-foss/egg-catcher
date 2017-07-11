package eggcatcher.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eggcatcher.game.MainGame;

/**
 * Created by Priyadarshi Raj on 25/6/17.
 *
 * Entry point for desktops
 */

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Egg Catcher";
		config.width = 693;
		config.height = 632;
        config.resizable = false; // disable "maximize" button and resizing options
        //config.fullscreen = true;
		config.foregroundFPS = 60;

        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");

		new LwjglApplication(new MainGame(), config);
	}
}
