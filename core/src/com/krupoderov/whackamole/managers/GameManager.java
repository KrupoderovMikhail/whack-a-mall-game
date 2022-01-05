package com.krupoderov.whackamole.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.krupoderov.whackamole.gameobjects.Mole;

/**
 * @author Krupoderov Mikhail
 * @version 1.0
 */
public class GameManager {
    static Array<Mole> moles; // array of the moles
    static Texture moleTexture; // texture image for the mole

    static Texture backgroundTexture; // texture image for background
    static Sprite backgroundSprite; // sprite for background

    static Texture holeTexture; // texture image for hole
    static Array<Sprite> holeSprites; // array of hole sprites
    private static float HOLE_RESIZE_FACTOR = 1100f;

    private static float MOLE_RESIZE_FACTOR = 2500f;
    public static float MOLE_VERT_POSITION_FACTOR = 3f;
    public static float MOLE1_HORIZ_POSITION_FACTOR = 5.8f;
    public static float MOLE2_HORIZ_POSITION_FACTOR = 2.4f;
    public static float MOLE3_HORIZ_POSITION_FACTOR = 1.5f;

    public static void initialize(float width, float height) {
        backgroundTexture = new Texture(Gdx.files.internal("data/ground.jpg"));
        backgroundSprite = new Sprite(backgroundTexture); // set background sprite
        // set background sprite's dimensions and position
        backgroundSprite.setSize(width, height);
        backgroundSprite.setPosition(0, 0f);

        holeTexture = new Texture(Gdx.files.internal("data/hole.png"));
        holeSprites = new Array<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Sprite sprite = new Sprite(holeTexture);
                // resize the holes
                sprite.setSize(
                        sprite.getWidth() * (width / HOLE_RESIZE_FACTOR),
                        sprite.getHeight() * (width / HOLE_RESIZE_FACTOR));
                // position the holes so they are in the center of the ground
                sprite.setPosition(
                        width * (j + 1) / 4f - sprite.getWidth() / 2,
                        height * (i + 1) / 4.4f - sprite.getHeight());
                holeSprites.add(sprite);
            }
        }

        moles = new Array<>();
        moleTexture = new Texture(Gdx.files.internal("data/mole.png"));
        // instantiate new moles and add it to the array
        for (int i = 0; i < 3; i++) {
            moles.add(new Mole());
        }

        // set the mole's display position
        moles.get(0).position.set(
                width/MOLE1_HORIZ_POSITION_FACTOR,
                height/MOLE_VERT_POSITION_FACTOR);
        moles.get(1).position.set(
                width/MOLE2_HORIZ_POSITION_FACTOR,
                height/MOLE_VERT_POSITION_FACTOR);
        moles.get(2).position.set(
                width/MOLE3_HORIZ_POSITION_FACTOR,
                height/MOLE_VERT_POSITION_FACTOR);

        for (Mole mole : moles) {
            // instantiate sprite for the mole with the texture of it
            mole.moleSprite = new Sprite(moleTexture);

            // set the dimension for the mole
            mole.width = mole.moleSprite.getWidth() * (width/MOLE_RESIZE_FACTOR);
            mole.height = mole.moleSprite.getHeight() * (width/MOLE_RESIZE_FACTOR); //TODO: width or height???
            mole.moleSprite.setSize(mole.width, mole.height);
            mole.moleSprite.setPosition(mole.position.x, mole.position.y);
        }
    }

    public static void renderGame(SpriteBatch batch) {
        // render(draw) background
        backgroundSprite.draw(batch);
        // render each hole
        for (Sprite sprite : holeSprites) {
            sprite.draw(batch);
        }
        // render(draw) each mole
        for (Mole mole : moles) {
            mole.render(batch);
        }
    }

    public static void dispose() {
        backgroundTexture.dispose();
        holeTexture.dispose();
        // dispose of the mole texture to ensure no memory leaks
        moleTexture.dispose();
    }
}
