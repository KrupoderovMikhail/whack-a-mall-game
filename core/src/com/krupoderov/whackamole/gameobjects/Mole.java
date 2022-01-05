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

    public void render(SpriteBatch batch) {
        moleSprite.draw(batch);
    }
}
