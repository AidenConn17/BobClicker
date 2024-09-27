package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rock {
    float x;
    float y;
    int width;
    int height;
    SpriteBatch batch;
    int level = 1;

    public Rock(float x, float y, int width, int height, SpriteBatch batch){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.batch = batch;
    }

    public void update(){} // Any updates are done in Main.input();

    public void draw(Sprite s){
        s.draw(batch);
    }
}
