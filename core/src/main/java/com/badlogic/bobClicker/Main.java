package com.badlogic.bobClicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture bobTexture;
    Sprite bobSprite;
    FitViewport viewport;
    Bob bob;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bobTexture = new Texture("bob.png");
        bobSprite = new Sprite(bobTexture);
        bobSprite.setSize(1, 1);
        bob = new Bob(0, 0, 5, 1, 1);
        viewport = new FitViewport(8, 5);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bobTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {
        ScreenUtils.clear(Color.WHITE);
        viewport.apply();
        bob.update();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        bob.draw(batch, bobSprite);
        batch.end();
    }

    private void logic() {
    }

    private void input() {
    }
}
