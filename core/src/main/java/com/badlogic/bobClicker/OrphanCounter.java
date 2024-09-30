package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrphanCounter {
    BitmapFont font;
    int x, y;
    String amountOfOrphans;
    String cost;

    public OrphanCounter(BitmapFont font, int x, int y){
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void update(Integer cost, Integer amountOfOrphans){
        this.cost = cost.toString();
        this.amountOfOrphans = amountOfOrphans.toString();
    }

    public void drawCost(SpriteBatch batch){
        font.setColor(Color.BLACK);
        font.draw(batch, "Cost: " + cost, x, y);
    }

    public void drawAmount(SpriteBatch batch, int x, int y){
        font.setColor(Color.BLACK);
        font.draw(batch, "Amount of orphans: " + amountOfOrphans, x, y);
    }
}
