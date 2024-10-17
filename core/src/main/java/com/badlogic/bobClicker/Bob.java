package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bob {
    float x;
    float y;
    int width;
    int height;
    Sprite bobSprite;

    /**
     * Creates a Bob object
     * 
     * @param x         X position
     * @param y         Y position
     * @param width     Stored width
     * @param height    Stored height
     * @param bobSprite Sprite
     */
    public Bob(int x, int y, int width, int height, Sprite bobSprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bobSprite = bobSprite;
    }

    /**
     * Changes x and y position
     *
     * @param x New x position
     * @param y New y position
     */
    public void update(int x, int y) {
        bobSprite.setX(x);
        bobSprite.setY(y);
    }

    /**
     * Draws the sprite\
     * 
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        bobSprite.draw(batch);
    }
}
