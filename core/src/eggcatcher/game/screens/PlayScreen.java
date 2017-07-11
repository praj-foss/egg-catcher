package eggcatcher.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import eggcatcher.game.MainGame;
import eggcatcher.game.Screens;
import eggcatcher.game.World;

/**
 * Created by Priyadarshi Raj on 25/6/17.
 *
 * Screen related to gameplay
 */

public class PlayScreen implements Screen {
    // Game world
    private World world;

    // UI
    private Stage stage;
    private Label labelScore, labelLives;

    public PlayScreen() {
        world = new World(this);

        stage = new Stage(MainGame.viewport, MainGame.batch);

        labelScore = new Label("", MainGame.labelStyles[0]);
        labelLives = new Label("", MainGame.labelStyles[0]);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(new Label("LIVES", MainGame.labelStyles[0])).expandX().padTop(3f);
        table.add(new Label("SCORE", MainGame.labelStyles[0])).expandX().padTop(3f);
        table.row();
        table.add(labelLives).expandX().padTop(3f);
        table.add(labelScore).expandX().padTop(3f);

        stage.addActor(table);
    }

    public void setScore(int score) {
        labelScore.setText(Integer.toString(score));
    }

    public void setLives(int lives) {
        labelLives.setText(Integer.toString(lives));
    }

    @Override
    public void show() {
        world.reset();
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        stage.draw(); // Stage calls batch.begin() and batch.end() on its own
    }

    @Override
    public void resize(int width, int height) {
        MainGame.viewport.update(width, height);
        world.resizeViewport(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
