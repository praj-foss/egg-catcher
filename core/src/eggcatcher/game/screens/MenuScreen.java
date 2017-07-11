package eggcatcher.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import eggcatcher.game.MainGame;

/**
 * Created by Priyadarshi Raj on 25/6/17.
 *
 * Screen related to main menu
 */

public class MenuScreen implements Screen {
    // UI
    private Stage stage;
    private Table[] tables = new Table[3]; // 0= Main menu, 1= How to play, 2= Credits
    private Label[] itemLabels = new Label[5];
    private HoverListener hoverListener;

    public MenuScreen() {
        stage = new Stage(MainGame.viewport, MainGame.batch);
        hoverListener = new HoverListener();

        initRoot();
        initHowToPlay();
        initCredits();

        stage.addActor(tables[0]);
        Gdx.input.setInputProcessor(stage);
    }

    private void initRoot() {
        Label labelTitle = new Label("EGG CATCHER", MainGame.labelStyles[0]);
        labelTitle.setFontScale(2f);

        itemLabels[0] = new Label("PLAY GAME", MainGame.labelStyles[0]);
        itemLabels[1] = new Label("HOW TO PLAY", MainGame.labelStyles[0]);
        itemLabels[2] = new Label("CREDITS", MainGame.labelStyles[0]);
        itemLabels[3] = new Label("EXIT GAME", MainGame.labelStyles[0]);

        tables[0] = new Table();
        tables[0].setFillParent(true);
        tables[0].top();

        tables[0].add(labelTitle).padTop(25f).row();
        tables[0].add().padTop(40f).row(); // empty cell for spacing

        for(int i = 0; i <= 3; i++) {
            itemLabels[i].setName(Integer.toString(i));
            itemLabels[i].addListener(hoverListener);

            tables[0].add(itemLabels[i]).row();
        }
    }

    private void initHowToPlay() {
        Label labelTitle = new Label("HOW TO PLAY", MainGame.labelStyles[0]);
        labelTitle.setFontScale(2f);

        Label text = new Label("", MainGame.labelStyles[0]);
        text.setFontScale(0.5f);
        text.setAlignment(Align.center);
        text.setText("CONTROL THE BASKET BY MOUSE\n\n"
                    +"CATCH ALL THE FALLING EGGS\n\n"
                    +"WHITE EGGS GIVE 5 POINTS\n"
                    +"GOLDEN EGGS GIVE 10 POINTS\n"
                    +"IF AN EGG BREAKS, YOU LOSE A LIFE\n\n"
                    +"AVOID CHICKEN DROPPINGS\n"
                    +"DROPPINGS REDUCE 5 POINTS\n"
                    +"CATCHING DROPPINGS ON ZERO SCORE\n"
                    +"WILL COST LIVES");

        Label backLabel = new Label("BACK", MainGame.labelStyles[0]);
        backLabel.setName("5");
        backLabel.addListener(hoverListener);

        tables[1] = new Table();
        tables[1].setFillParent(true);
        tables[1].top();

        tables[1].add(labelTitle).padTop(25f).row();
        tables[1].add().padTop(35f).row(); // empty cell for spacing
        tables[1].add(text).row();
        tables[1].add(backLabel).padTop(23f);
    }

    private void initCredits() {
        Label labelTitle = new Label("CREDITS", MainGame.labelStyles[0]);
        labelTitle.setFontScale(2f);

        Label text = new Label("", MainGame.labelStyles[0]);
        text.setFontScale(0.5f);
        text.setAlignment(Align.center);
        text.setText("DESIGNED AND PROGRAMMED BY\n"
                +"PRIYADARSHI RAJ\n"
                +"github.com/praj-foss\n\n\n"
                +"FONTS\n"
                +"BOXY BOLD, BY CLINT BELLANGER\n"
                +"opengameart.org/content/boxy-bold-font\n\n\n"
                +"POWERED BY\n"
                +"libGDX\n"
                +"libgdx.badlogicgames.com");

        Label backLabel = new Label("BACK", MainGame.labelStyles[0]);
        backLabel.setName("5");
        backLabel.addListener(hoverListener);

        tables[2] = new Table();
        tables[2].setFillParent(true);
        tables[2].top();

        tables[2].add(labelTitle).padTop(25f).row();
        tables[2].add().padTop(35f).row(); // empty cell for spacing
        tables[2].add(text).row();
        tables[2].add(backLabel).padTop(14f);
    }

    // Swap tables, while in the same stage
    public void changeTable(int code) {
        stage.clear();
        stage.addActor(tables[code]);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
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
