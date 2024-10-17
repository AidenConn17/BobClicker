package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CostCounter {
    BitmapFont font;
    int x, y;
    String cost;
    boolean isMaxLevel;

    /**
     * Creates a CostCounter object
     * 
     * @param font The BitmapFont
     * @param x    X position
     * @param y    Y position
     */
    public CostCounter(BitmapFont font, int x, int y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    /**
     * Allows for a change in the text to display
     * 
     * @param cost New cost of the rock
     * @param max  True is the rock is max level
     */
    public void update(Integer cost, boolean max) {
        this.cost = cost.toString();
        this.isMaxLevel = max;
    }

    /**
     * Draws stored text at the x and y position
     * 
     * @param batch The SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        if (!isMaxLevel) {
            font.draw(batch, "Cost: " + cost, x, y);
        } else {
            font.draw(batch, "The rock is max level!", x, y);
        }
    }
}
