package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Orphan {
    int amount = 0;
    Sprite sprite;

    public Orphan(Sprite sprite) {
        this.sprite = sprite;
    }

    public void update(int amount) {
        this.amount = amount;
    }

    public void update(int x, int y) {
        sprite.setX(x);
        sprite.setY(y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
