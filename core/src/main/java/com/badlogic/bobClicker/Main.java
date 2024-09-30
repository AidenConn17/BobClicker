package com.badlogic.bobClicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    BitmapFont moneyFont;
    BitmapFont costFont;
    BitmapFont orphanFont;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter moneyParameter;
    FreeTypeFontParameter costParameter;
    FreeTypeFontParameter amountParameter;

    SpriteBatch batch;

    Texture bobTexture;
    Texture rockTexture1;
    Texture rockTexture2;
    Texture rockTexture3;
    Texture rockTexture4;
    Texture rockTexture5;
    Texture upgRockTexture;
    Texture buyOrphanTexture;
    Texture orphanTexture;
    Texture shopTexture;
    Texture exitTexture;

    Sprite bobSprite;
    Sprite rockSprite;
    Sprite upgRockSprite;
    Sprite buyOrphanSprite;
    Sprite orphanSprite;
    Sprite shopSprite;
    Sprite exitSprite;

    FitViewport viewport;

    Bob bob;
    Rock rock;
    MoneyCounter moneyCounter;
    CostCounter costCounter;
    OrphanCounter orphanCounter;
    Orphan orphan;

    Rectangle upgRect;
    Rectangle rockRect;
    Rectangle buyOrphanRect;
    Rectangle exitShopRect;

    int rockLevel = 1;
    int money = 0;
    int upgCost = 10;
    int amountOfOrphans = 0;
    int orphanCost = 100;
    boolean rockIsMaxLevel = false;
    int framesPassed = 0;
    int moneyPerSecond = 0;
    int screen = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();

        bobTexture = new Texture("bob.png");
        rockTexture1 = new Texture("rocklvl1.png");
        rockTexture2 = new Texture("rocklvl2.png");
        rockTexture3 = new Texture("rocklvl3.png");
        rockTexture4 = new Texture("rocklvl4.png");
        rockTexture5 = new Texture("rocklvl5.png");
        upgRockTexture = new Texture("upgRockButton.png");
        buyOrphanTexture = new Texture("mpsButton.png");
        orphanTexture = new Texture("britishOrphan.png");
        shopTexture = new Texture("shopButton.png");
        exitTexture = new Texture("exitButton.png");

        bobSprite = new Sprite(bobTexture);
        rockSprite = new Sprite(rockTexture1);
        upgRockSprite = new Sprite(upgRockTexture);
        buyOrphanSprite = new Sprite(buyOrphanTexture);
        orphanSprite = new Sprite(orphanTexture);
        shopSprite = new Sprite(shopTexture);
        exitSprite = new Sprite(exitTexture);

        rockSprite.setSize(50, 50);
        bobSprite.setSize(100, 100);
        upgRockSprite.setSize(200, 100);
        buyOrphanSprite.setSize(200, 100);
        orphanSprite.setSize(100, 100);
        shopSprite.setSize(200, 100);
        exitSprite.setSize(200, 100);

        upgRockSprite.setX(600);
        upgRockSprite.setY(300);
        buyOrphanSprite.setX(600);
        buyOrphanSprite.setY(150);
        orphanSprite.setX(0);
        orphanSprite.setY(0);
        shopSprite.setX(600);
        shopSprite.setY(0);
        exitSprite.setX(600);
        exitSprite.setY(0);

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        generator = new FreeTypeFontGenerator(Gdx.files.internal("MintsodaLimeGreen13X16Regular-4n30l.ttf"));

        moneyParameter = new FreeTypeFontParameter();
        costParameter = new FreeTypeFontParameter();
        amountParameter = new FreeTypeFontParameter();
        moneyParameter.size = 40;
        costParameter.size = 20;
        amountParameter.size = 30;

        moneyFont = generator.generateFont(moneyParameter);
        costFont = generator.generateFont(costParameter);
        orphanFont = generator.generateFont(costParameter);

        generator.dispose();

        moneyCounter = new MoneyCounter(moneyFont, 0, 400);
        costCounter = new CostCounter(costFont, 600, 290);
        orphanCounter = new OrphanCounter(orphanFont, 600, 140);
        bob = new Bob(150, -25, 100, 100, bobSprite, batch);
        rock = new Rock(100, 0, 50, 50, batch, rockSprite);
        orphan = new Orphan(orphanSprite);

        upgRect = new Rectangle(600, 300, 200, 100);
        rockRect = new Rectangle(0, 0, 50, 50);
        buyOrphanRect = new Rectangle(600, 150, 200, 100);
        exitShopRect = new Rectangle(600, 0, 200, 100);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bobTexture.dispose();
        rockTexture1.dispose();
        rockTexture2.dispose();
        rockTexture3.dispose();
        rockTexture4.dispose();
        rockTexture5.dispose();
        upgRockTexture.dispose();
        buyOrphanTexture.dispose();
        orphanTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        ScreenUtils.clear(Color.WHITE);
        moneyCounter.draw(batch);
        orphanCounter.drawAmount(batch, 0, 350);
        if (screen == 0) { // Main screen
            bob.draw();
            if (amountOfOrphans != 0)
                orphanSprite.draw(batch);
            rockSprite.draw(batch);
            shopSprite.draw(batch);
        } else if (screen == 1) { // Shop screen
            upgRockSprite.draw(batch);
            buyOrphanSprite.draw(batch);
            costCounter.draw(batch);
            orphanCounter.drawCost(batch);
            exitSprite.draw(batch);
        }
        batch.end();
    }

    private void logic() {
        rock.update();
        moneyCounter.update(money);
        costCounter.update(upgCost, rockIsMaxLevel);
        orphanCounter.update(orphanCost, amountOfOrphans);
        orphan.update(amountOfOrphans);
        framesPassed++;

        if (framesPassed >= Gdx.graphics.getFramesPerSecond()) {
            money += moneyPerSecond;
            bob.update(150, -10);
            orphan.update(0, -20);
            framesPassed = 0;
        } else if (framesPassed >= Gdx.graphics.getFramesPerSecond() / 2) {
            bob.update(150, -20);
            orphan.update(0, -10);
        }
    }

    private void input() {
        Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        if (screen == 0) { // Game screen
            if (Gdx.input.justTouched() && !upgRect.contains(touch) && !buyOrphanRect.contains(touch)
                    && !exitShopRect.contains(touch)) {
                if (rockLevel == 1) {
                    money += 1;
                } else if (rockLevel == 2) {
                    money += 5;
                } else if (rockLevel == 3) {
                    money += 20;
                } else if (rockLevel == 4) {
                    money += 50;
                } else {
                    money += 200;
                }
            }
            if (Gdx.input.justTouched() && exitShopRect.contains(touch)) {
                screen = 1;
            }
        } else if (screen == 1) {// Shop screen
            if (Gdx.input.justTouched() && upgRect.contains(touch)) {
                if (rockLevel == 1 && money >= upgCost) {
                    rockSprite.setTexture(rockTexture2);
                    rockLevel++;
                    money -= upgCost;
                    upgCost = 100;
                } else if (rockLevel == 2 && money >= upgCost) {
                    rockSprite.setTexture(rockTexture3);
                    rockLevel++;
                    money -= upgCost;
                    upgCost = 1000;
                } else if (rockLevel == 3 && money >= upgCost) {
                    rockSprite.setTexture(rockTexture4);
                    rockLevel++;
                    money -= upgCost;
                    upgCost = 5000;
                } else if (rockLevel == 4 && money >= upgCost) {
                    rockSprite.setTexture(rockTexture5);
                    rockLevel++;
                    money -= upgCost;
                    rockIsMaxLevel = true;
                }
            }

            if (Gdx.input.justTouched() && buyOrphanRect.contains(touch) && money >= orphanCost) {
                moneyPerSecond += 1;
                money -= orphanCost;
                orphanCost = (int) (orphanCost * 1.2);
                amountOfOrphans++;
            }

            if (Gdx.input.justTouched() && exitShopRect.contains(touch)){
                screen = 0;
            }
        }
    }
}
