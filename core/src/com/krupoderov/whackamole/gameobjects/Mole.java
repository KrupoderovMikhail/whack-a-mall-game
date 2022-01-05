package com.krupoderov.whackamole.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Krupoderov Mikhail
 * @version 1.0
 */
public class Mole {
    public Sprite moleSprite; // sprite to display the mole
    public Vector2 position = new Vector2(); // the mole's position
    public float height, width; // the mole's dimensions
    public float scaleFactor; // scalling factor for the mole

    public State state = State.GOINGUP; // variable describing mole's current state
    public float currentHeight = 0.0f; // current height of the mole above ground
    public float speed = 2f; // speed of the mole as it goes up and down

    public void update() {
        switch (state) {
            // here increase the height till it reaches max, once it reaches, change the state
            case GOINGUP:
                currentHeight += speed;
                if (currentHeight >= height) {
                    currentHeight = height;
                    state = State.GOINGDOWN;
                }
                break;
            // here decrease the height till it reaches min(0), once it reaches, change the state
            case GOINGDOWN:
                currentHeight -= speed;
                if (currentHeight <= 0.0) {
                    currentHeight = 0.0f;
                    state = State.GOINGUP;
                }
                break;
        }

        // draw only some portion of the mole image, depending on height
        moleSprite.setRegion(
                0,
                0,
                (int) (width / scaleFactor),
                (int) (currentHeight / scaleFactor));
        moleSprite.setSize(moleSprite.getWidth(), currentHeight);
    }

    public void render(SpriteBatch batch) {
        moleSprite.draw(batch);
    }

    public enum State {
        GOINGUP, GOINGDOWN
    }
}
