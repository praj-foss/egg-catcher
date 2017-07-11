package eggcatcher.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import eggcatcher.game.MainGame;

/**
 * Created by Priyadarshi Raj on 28/6/17.
 *
 * Definition for chickens
 */

public class Chicken extends Sprite {
    private static TextureRegion[] CHICKEN_TEXTURES = {
            MainGame.atlas.findRegion("InactiveChicken"),
            MainGame.atlas.findRegion("ActiveChicken")
    };

    private int index;
    private boolean isActive;

    private Basket basket;
    private Pool<FallingObject> fallingObjectPool;
    private Array<FallingObject> activeFallingObjects;

    // For changing chicken image
    public float timer;
    public float resetVal;

    public Chicken(int index, Basket basket, Pool<FallingObject> fallingObjectPool) {
        super(CHICKEN_TEXTURES[0]);
        this.index = index;
        this.basket = basket;
        this.fallingObjectPool = fallingObjectPool;

        this.activeFallingObjects = new Array<>();
        setPosition(32f + index*64f,7*32f);

        timer = 0f;
        resetVal = 0.3f;
    }

    public void fire() {
        if(!isActive) {
            FallingObject fallingObject = fallingObjectPool.obtain();

            int random = MathUtils.random(1,20);
            if(random == 20)
                fallingObject.init(index, 2);
            else if(random%2==0)
                fallingObject.init(index, 1);
            else
                fallingObject.init(index, 0);

            activeFallingObjects.add(fallingObject);

            isActive = true;
            setRegion(CHICKEN_TEXTURES[1]);
            timer = 0;
        }
    }

    public void update() {
        timer += Gdx.graphics.getDeltaTime();

        // Releases the fallen objects back to the pool
        FallingObject fallingObject;
        for(int i = activeFallingObjects.size-1; i >= 0; i-- ) {
            fallingObject = activeFallingObjects.get(i);

            if(!fallingObject.isFalling() && !fallingObject.isOnGround()) {
                activeFallingObjects.removeIndex(i);
                fallingObjectPool.free(fallingObject);
            }
        }

        // Handles chicken active duration
        if(isActive && timer >= resetVal) {
            setRegion(CHICKEN_TEXTURES[0]);
            isActive = false;
        }

        // Updates the falling objects
        if(activeFallingObjects.size != 0) {
            for(FallingObject obj : activeFallingObjects) {
                obj.update(basket);
            }
        }

        draw(MainGame.batch);
    }

    public void reset() {
        fallingObjectPool.freeAll(activeFallingObjects);
    }

    public static void disposeTextures() {
        for(int i = 0; i < CHICKEN_TEXTURES.length; i++)
            CHICKEN_TEXTURES[i].getTexture().dispose();
    }
}
