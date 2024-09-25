package com.badlogic.bobClicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bob {
    int x;
    int y;
    int xSpeed;
    int width;
    int height;

    public Bob(int x, int y, int xSpeed, int width, int height) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.width = width;
        this.height = height;
    }

    public void update() {
        x += xSpeed;
        if (x < 0 + width || x > Gdx.graphics.getWidth() + width) {
            x = -xSpeed;
        }
    }

    public void draw(SpriteBatch batch, Sprite bobSprite) {
        bobSprite.draw(batch);
    }
}
