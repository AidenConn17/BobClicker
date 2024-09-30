package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bob {
    float x;
    float y;
    int width;
    int height;
    Sprite bobSprite;
    SpriteBatch batch;

    public Bob(int x, int y, int width, int height, Sprite bobSprite, SpriteBatch batch) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bobSprite = bobSprite;
        this.batch = batch;
    }

    public void update(int x, int y) {
        bobSprite.setX(x);
        bobSprite.setY(y);
        }

    public void draw() {
        bobSprite.draw(batch);
    }
}
