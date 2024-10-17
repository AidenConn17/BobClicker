package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrphanCounter {
    BitmapFont font;
    int x, y;
    String amountOfOrphans;
    String cost;

    /**
     * Creates an OrphanCounter object
     * 
     * @param font The BitmapFont
     * @param x    X position
     * @param y    y position
     */
    public OrphanCounter(BitmapFont font, int x, int y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    /**
     * Updates stored text
     * 
     * @param cost            New cost
     * @param amountOfOrphans New orphan count
     */
    public void update(Integer cost, Integer amountOfOrphans) {
        this.cost = cost.toString();
        this.amountOfOrphans = amountOfOrphans.toString();
    }

    /**
     * Draws the cost of an orphan
     * 
     * @param batch The SpriteBatch
     */
    public void drawCost(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        font.draw(batch, "Cost: " + cost, x, y);
    }

    /**
     * Draws the amount of orphans
     * 
     * @param batch The SpriteBatch
     * @param x     X position
     * @param y     Y position
     */
    public void drawAmount(SpriteBatch batch, int x, int y) {
        font.setColor(Color.BLACK);
        font.draw(batch, "Amount of orphans: " + amountOfOrphans, x, y);
    }
}
