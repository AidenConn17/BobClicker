package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CostCounter {
    BitmapFont font;
    int x, y;
    String cost;
    boolean isMaxLevel;

    public CostCounter(BitmapFont font, int x, int y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void update(Integer cost, boolean max) {
        this.cost = cost.toString();
        this.isMaxLevel = max;
    }

    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        if (!isMaxLevel) {
            font.draw(batch, "Cost: " + cost, x, y);
        } else {
            font.draw(batch, "The rock is max level!", x, y);
        }
    }
}
