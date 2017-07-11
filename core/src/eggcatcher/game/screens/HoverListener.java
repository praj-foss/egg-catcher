package eggcatcher.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import eggcatcher.game.MainGame;
import eggcatcher.game.Screens;

/**
 * Created by Priyadarshi Raj on 4/7/17.
 *
 * Detects mouse hover and click events
 */

public class HoverListener extends ClickListener {
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        ((Label) event.getListenerActor()).setStyle(MainGame.labelStyles[1]);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        ((Label) event.getListenerActor()).setStyle(MainGame.labelStyles[0]);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);

        int code = Integer.parseInt(event.getListenerActor().getName());

        switch(code) {
            case 0: MainGame.changeScreen(Screens.PLAY_SCREEN); break;
            case 1: ((MenuScreen) Screens.MENU_SCREEN).changeTable(1); break;
            case 2: ((MenuScreen) Screens.MENU_SCREEN).changeTable(2); break;
            case 3: Gdx.app.exit(); break;

            default: // BACK label clicked
                if(((Game) Gdx.app.getApplicationListener()).getScreen() != Screens.MENU_SCREEN)
                    MainGame.changeScreen(Screens.MENU_SCREEN);
                ((MenuScreen) Screens.MENU_SCREEN).changeTable(0);
        }
    }
}
