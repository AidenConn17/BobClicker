package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GeneralText {
    BitmapFont font;
    String text;
    int x, y;

    /**
     * Creates a GeneralText object - used for nonspecific text
     * 
     * @param font The BitmapFont
     * @param text Stored output
     * @param x    X position
     * @param y    Y position
     */
    public GeneralText(BitmapFont font, String text, int x, int y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the stored text
     * 
     * @param text Text to update with
     */
    public void update(String text) {
        this.text = text;
    }

    /**
     * Draws stored text at the x and y position
     * 
     * @param batch The SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }

}
