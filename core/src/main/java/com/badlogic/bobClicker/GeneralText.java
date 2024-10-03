package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


 /*
  * General text, use for nonspecific output
  */
public class GeneralText {
    BitmapFont font;
    String text;
    int x, y;

    public GeneralText(BitmapFont font, String text, int x, int y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    // Allows for a change in text
    public void update(String text) {
        this.text = text;
    }

    // Writes the text
    public void draw(SpriteBatch batch) {
        font.setColor(Color.BLACK);
        font.draw(batch, text, x, y);
    }

}
