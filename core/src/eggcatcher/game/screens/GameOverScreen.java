package eggcatcher.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import eggcatcher.game.MainGame;
import eggcatcher.game.sprites.Basket;
import eggcatcher.game.Screens;

/**
 * Created by Priyadarshi Raj on 3/7/17.
 *
 * Screen shown when player's lives fall to zero
 */

public class GameOverScreen implements Screen {
    // UI
    private Stage stage;
    private Label labelScore;

    public GameOverScreen() {
        stage = new Stage(MainGame.viewport, MainGame.batch);

        Label labelTitle = new Label("GAME OVER", MainGame.labelStyles[0]);
        labelTitle.setFontScale(2f);

        labelScore = new Label("SCORE : ", MainGame.labelStyles[0]);

        Label backLabel = new Label("RETURN TO MENU", MainGame.labelStyles[0]);
        backLabel.setName("4");
        backLabel.addListener(new HoverListener());

        System.out.println();
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        table.add(labelTitle).padTop(25f).row();
        table.add().padTop(35f).row(); // empty cell for spacing
        table.add(labelScore).row();
        table.add(backLabel).padTop(110f);

        stage.addActor(table);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        labelScore.setText("SCORE : "+Basket.getScore());

        stage.act();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void resize(int width, int height) {

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
    }
}
