package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial {
    String text;
    BitmapFont font;
    int x, y;

    public Tutorial(String text, BitmapFont font, int x, int y){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void update(String text){
        this.text = text;
    }

    public void update(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch){
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }
}
