package eggcatcher.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import eggcatcher.game.MainGame;
import eggcatcher.game.Screens;

/**
 * Created by Priyadarshi Raj on 27/6/17.
 *
 * Definition for the basket
 */

public class Basket extends Sprite {
    private static int score;
    private static int lives;

    public Basket() {
        super(MainGame.atlas.findRegion("Basket"));

        setPosition(30f, 51f);
    }

    public void update() {
        draw(MainGame.batch);
    }

    public void updateScore(int type) {
        switch(type) {
            case 1: score += 5; break;    // white egg
            case 2: score += 10; break;   // golden egg
            default: score -= 5;          // dropping
        }

        if(score < 0) {
            score = 0;
            updateLives();
        }
    }

    public void updateLives() {
        lives--;

        if(lives == 0)
            MainGame.changeScreen(Screens.GAME_OVER_SCREEN);
    }

    public static int getLives() {
        return lives;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Basket.score = score;
    }

    public static void setLives(int lives) {
        Basket.lives = lives;
    }
}
