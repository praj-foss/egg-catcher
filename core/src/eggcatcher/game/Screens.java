package eggcatcher.game;

import com.badlogic.gdx.Screen;
import eggcatcher.game.screens.GameOverScreen;
import eggcatcher.game.screens.MenuScreen;
import eggcatcher.game.screens.PlayScreen;

/**
 * Created by Priyadarshi Raj on 4/7/17.
 *
 * Stores static Screen objects
 */

public class Screens {
    public static final Screen PLAY_SCREEN = new PlayScreen();
    public static final Screen MENU_SCREEN = new MenuScreen();
    public static final Screen GAME_OVER_SCREEN = new GameOverScreen();

    private Screens() { }
}
