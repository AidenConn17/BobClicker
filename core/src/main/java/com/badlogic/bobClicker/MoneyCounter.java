package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoneyCounter {
    BitmapFont font;
    int x, y;
    String money;
    
    public MoneyCounter(BitmapFont font, int x, int y){
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void update(Integer money){
        this.money = money.toString();
    }

    public void draw(SpriteBatch batch){
        font.setColor(Color.BLACK);
        font.draw(batch, "Money: " + money, x, y);
    }
}
