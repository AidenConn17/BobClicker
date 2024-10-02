package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GeneralText {
    BitmapFont font;
    String text;
    int x, y;

    public GeneralText(BitmapFont font, String text, int x, int y){
        this.font = font;
        this.text = text;
        this.x = x; 
        this.y = y;
    }

    public void update(String text){
        this.text = text;
    }

    public void draw(SpriteBatch batch){
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }
    
}
