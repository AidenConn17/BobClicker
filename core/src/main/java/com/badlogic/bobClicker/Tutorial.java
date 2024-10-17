package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial {
    String text;
    BitmapFont font;
    int x, y;

    /**
     * Creates a Tutorial object
     * 
     * @param text Stored text
     * @param font The BitmapFOnt
     * @param x    X position
     * @param y    Y position
     */
    public Tutorial(String text, BitmapFont font, int x, int y) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the stored text
     * 
     * @param text New text to store
     */
    public void update(String text) {
        this.text = text;
    }

    /**
     * Updates x and y
     * 
     * @param x New x position
     * @param y New y position
     */
    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws stored text at x and y
     * 
     * @param batch The SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }
}
