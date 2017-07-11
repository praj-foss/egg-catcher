package eggcatcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import eggcatcher.game.screens.PlayScreen;
import eggcatcher.game.sprites.Basket;
import eggcatcher.game.sprites.Chicken;
import eggcatcher.game.sprites.FallingObject;

/**
 * Created by Priyadarshi Raj on 26/6/17.
 *
 * Handles the game logic, stats and characters while playing
 */

public class World implements Disposable {
    // Play screen
    private PlayScreen playScreen;

    // Camera stuff
    private OrthographicCamera camera;
    private Viewport viewport;

    // Game stats
    private float resetVal; // seconds, after which the timer will be reset
    private float timer;
    private Vector3 cursorOldPosition;
    private Vector3 cursorPosition;
    private int scoreCap;
    private boolean isResetValid;

    // Sprites
    private Basket player;
    private Array<Chicken> chickens;
    private Pool<FallingObject> fallingObjectPool;

    public World(PlayScreen playScreen) {
        this.playScreen = playScreen;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MainGame.VIEWPORT_WIDTH, MainGame.VIEWPORT_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0f);

        cursorOldPosition = new Vector3(Gdx.input.getX(), 0f, 0f);
        cursorPosition = new Vector3(Gdx.input.getX(), 0f, 0f);

        player = new Basket();
        Basket.setScore(0);
        Basket.setLives(5);
        timer = 0;
        resetVal = 1.75f;
        scoreCap = 50;

        fallingObjectPool = new Pool<FallingObject>() {
            @Override
            protected FallingObject newObject() {
                return new FallingObject();
            }
        };

        chickens = new Array<>(true,5);
        for(int i=0; i<5; i++) {
            chickens.add(new Chicken(i, player, fallingObjectPool));
        }

        isResetValid = true;
    }

    public void reset() {
        if(isResetValid) {
            for (Chicken chicken : chickens)
                chicken.reset();
            fallingObjectPool.clear();

            Basket.setScore(0);
            Basket.setLives(5);
            timer = 0;
            resetVal = 1.75f;
            scoreCap = 50;
        }
    }

    public void update(float delta) {
        timer += delta;

        handleBasketPosition();
        handleStats();

        camera.update();

        if(timer >= resetVal) {
            timer = 0;
            chickens.get(MathUtils.random(4)).fire();
        }

        render();
    }

    public void render() {
        // Render in the order: falling object -> chicken -> basket
        MainGame.batch.begin();
        for(Chicken chicken : chickens) {
            chicken.update();
        }
        player.update();
        MainGame.batch.end();
    }

    private void handleStats() {
        playScreen.setLives(player.getLives());
        playScreen.setScore(player.getScore());

        if(scoreCap <= player.getScore()) {
            resetVal -= resetVal*0.2f;
            for(Chicken chicken : chickens) {
                chicken.resetVal -= chicken.resetVal*0.05f;
            }
            scoreCap += 50;
        }
    }

    private void handleBasketPosition() {
        cursorOldPosition.set(Gdx.input.getX(), 0f, 0f); // We'll only use the x-coordinates. So forget about the y and z coord.
        cursorPosition = camera.unproject(cursorOldPosition);

        if(cursorPosition.x > 46 && cursorPosition.x < 304)  // 46 = 32(just under 1st chicken) - 2(adjustment) + 32/2(center of basket)
            player.setCenterX(cursorPosition.x);
        else if(cursorPosition.x <= 46)
            player.setCenterX(46);
        else
            player.setCenterX(304);
    }

    public void resizeViewport(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        fallingObjectPool.clear();
        FallingObject.disposeTextures();
        Chicken.disposeTextures();
    }
}
