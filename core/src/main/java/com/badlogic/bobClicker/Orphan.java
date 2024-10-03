package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Orphan {
    Sprite sprite;

    public Orphan(Sprite sprite) {
        this.sprite = sprite;
    }

    // Allows for movement
    public void update(int x, int y) {
        sprite.setX(x);
        sprite.setY(y);
    }

    // Draws the sprite
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
