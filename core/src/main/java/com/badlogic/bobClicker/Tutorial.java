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


    // Allows for a change in text
    public void update(String text){
        this.text = text;
    }

    // Allows for a change in position
    public void update(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Writes text at the stored position
    public void draw(SpriteBatch batch){
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }
}
