package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoneyCounter {
    BitmapFont font;
    int x, y;
    String money;

    /**
     * Creates a MoneyCounter object
     * 
     * @param font The BitmapFont
     * @param x    X position
     * @param y    Y Position
     */
    public MoneyCounter(BitmapFont font, int x, int y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    /**
     * Updates stored text
     * 
     * @param money New money value to be displayed
     */
    public void update(Integer money) {
        this.money = money.toString();
    }

    /**
     * Draws stored text at the x and y position
     * 
     * @param batch The SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        font.draw(batch, "Money: " + money, x, y);
    }
}
