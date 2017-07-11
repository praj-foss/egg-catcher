package eggcatcher.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import eggcatcher.game.MainGame;

/**
 * Created by Priyadarshi Raj on 28/6/17.
 *
 * Definition for falling objects
 */

public class FallingObject extends Sprite implements Pool.Poolable {
    private static final float FALL_SPEED = 320f;
    private static final TextureRegion[] FALLING_OBJECT_TEXTURES = {
            MainGame.atlas.findRegion("ChickenDropping"),
            MainGame.atlas.findRegion("WhiteEgg"),
            MainGame.atlas.findRegion("GoldenEgg"),
            MainGame.atlas.findRegion("SplatteredEgg"),
            MainGame.atlas.findRegion("SplatteredDropping")
    };

    private int type;
    private float y;

    private boolean isFallComplete;
    private boolean isAlongBasket;
    private boolean isOnGround;

    private float timer;
    private float resetVal;

    public FallingObject() {
        super(FALLING_OBJECT_TEXTURES[0]);
    }

    public void init(int index, int type) {
        this.type = type;

        setRegion(FALLING_OBJECT_TEXTURES[type]);

        y = 7 * 32f;
        setPosition(32f + index*64f, y);

        isFallComplete = false;
        isAlongBasket = false;      // assumption
        isOnGround = false;

        timer = 0f;  // for changing object image when on ground
        resetVal = 1.25f;
    }

    public void update(Basket basket) {
        if(isOnGround)
            timer += Gdx.graphics.getDeltaTime();
        if(timer >= resetVal)
            isOnGround = false;

        if(!isFallComplete && !isOnGround) {
            y -= FALL_SPEED * Gdx.graphics.getDeltaTime();
            setY(y);

            if(y <= 61) {
                if (!isAlongBasket) {
                    if (getX() > basket.getX() - 13 && getX() < basket.getX() + 13)
                        isAlongBasket = true;
                    else if (y <= 47) {
                        setY(47);

                        if(type == 0)
                            setRegion(FALLING_OBJECT_TEXTURES[4]);
                        else {
                            basket.updateLives();
                            setRegion(FALLING_OBJECT_TEXTURES[3]);
                        }

                        isFallComplete = true;
                        isOnGround = true;
                    }
                }
                else {
                    setX(basket.getX());

                    if (y <= 53) {
                        basket.updateScore(type);
                        isFallComplete = true;
                    }
                }
            }
        }

        draw(MainGame.batch);
    }

    public boolean isFalling() {
        return !isFallComplete;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    @Override
    public void reset() {
        isOnGround = false;
    }

    public static void disposeTextures() {
        for(int i = 0; i < FALLING_OBJECT_TEXTURES.length; i++)
            FALLING_OBJECT_TEXTURES[i].getTexture().dispose();
    }
}
