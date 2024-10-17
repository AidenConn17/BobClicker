package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Orphan {
    Sprite sprite;

    /**
     * Creates an Orphan object
     * 
     * @param sprite The Sprite
     */
    public Orphan(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Changes x and y position
     * 
     * @param x New x position
     * @param y New y position
     */
    public void update(int x, int y) {
        sprite.setX(x);
        sprite.setY(y);
    }

    /**
     * Draws the sprite
     * 
     * @param batch The SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
