package com.krupoderov.whackamole;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.krupoderov.whackamole.managers.GameManager;

public class WhackAMole extends ApplicationAdapter {
    SpriteBatch batch; // spritebatch for drawing
    OrthographicCamera camera;

    @Override
    public void create() {
        // get window dimension and set out viewport dimensions
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        // set out camera viewport to window dimensions
        camera = new OrthographicCamera(width, height);
        // center to the camera at w/2, h/2
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        // initialize the game
        GameManager.initialize(width, height);
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        // render the game objects
        batch.begin();
        GameManager.renderGame(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        // dispose the batch and the textures
        batch.dispose();
        GameManager.dispose();
    }
}
