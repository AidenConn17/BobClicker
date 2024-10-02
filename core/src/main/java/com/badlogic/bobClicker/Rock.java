package com.badlogic.bobClicker;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rock {
    float x;
    float y;
    SpriteBatch batch;
    Sprite sprite;
    int level = 1;

    public Rock(float x, float y, SpriteBatch batch, Sprite sprite){
        this.x = x;
        this.y = y;

        this.batch = batch;
        this.sprite = sprite;
        this.sprite.setX(this.x);
        this.sprite.setY(this.y);
    }

    public void update(){}

    public void draw(){
        sprite.draw(batch);
    }
}
