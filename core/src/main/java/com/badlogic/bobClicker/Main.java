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
    BitmapFont generalFont;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter moneyParameter;
    FreeTypeFontParameter costParameter;
    FreeTypeFontParameter amountParameter;
    FreeTypeFontParameter generalParameter;

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
    Texture batman;

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
    Tutorial tutorialText;
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
    boolean tutorialActive = true;
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
        batman = new Texture("batman.png");

        bobSprite = new Sprite(bobTexture);
        rockSprite = new Sprite(rockTexture1);
        upgRockSprite = new Sprite(upgRockTexture);
        buyOrphanSprite = new Sprite(buyOrphanTexture);
        orphanSprite = new Sprite(orphanTexture);
        shopSprite = new Sprite(shopTexture);
        exitSprite = new Sprite(exitTexture);

        rockSprite.setSize(100, 50);
        bobSprite.setSize(200, 200);
        upgRockSprite.setSize(200, 100);
        buyOrphanSprite.setSize(200, 100);
        orphanSprite.setSize(50, 100);
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
        generalParameter = new FreeTypeFontParameter();
        moneyParameter.size = 40;
        costParameter.size = 20;
        amountParameter.size = 30;
        generalParameter.size = 60;

        moneyFont = generator.generateFont(moneyParameter);
        costFont = generator.generateFont(costParameter);
        orphanFont = generator.generateFont(costParameter);
        generalFont = generator.generateFont(generalParameter);

        generator.dispose();

        moneyCounter = new MoneyCounter(moneyFont, 0, 400);
        costCounter = new CostCounter(costFont, 600, 290);
        orphanCounter = new OrphanCounter(orphanFont, 600, 140);
        bob = new Bob(150, -25, 100, 100, bobSprite, batch);
        rock = new Rock((Gdx.graphics.getWidth() / 2) + 50, 0, batch, rockSprite);
        orphan = new Orphan(orphanSprite);
        tutorialText = new Tutorial(null, costFont, 300, 200);

        upgRect = new Rectangle(600, 300, 200, 100);
        rockRect = new Rectangle((Gdx.graphics.getWidth() / 2) + 50, 0, 100, 50);
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
            if (tutorialActive) {
                if (money <= 9 && rockLevel == 1) {
                    tutorialText.update("Click this rock to make money!");
                    tutorialText.draw(batch);
                }
                if (money >= 10 && rockLevel == 1) {
                    tutorialText.update("Click the shop button \nto purchase upgrades");
                    tutorialText.update(550, 150);
                    tutorialText.draw(batch);
                }
                if(money <= 99 && rockLevel == 2){
                    tutorialText.update("Click until you have 100 money,\nthen go back to the shop");
                    tutorialText.update(250, 250);
                    tutorialText.draw(batch);
                }
                if(rockLevel == 2 && amountOfOrphans >= 1){
                    tutorialActive = false;
                }
            }
            if (amountOfOrphans != 0)
                orphanSprite.draw(batch);
            rockSprite.draw(batch);
            shopSprite.draw(batch);
        } else if (screen == 1) { // Shop screen
            if (tutorialActive) {
                if (money >= 10 && rockLevel == 1) {
                    tutorialText.update("You can afford \nto upgrade \nthe rock!");
                    tutorialText.update(400, 375);
                    tutorialText.draw(batch);
                }
                if(money >= 100 && rockLevel == 2 && amountOfOrphans == 0){
                    tutorialText.update("\"adopt\" an orphan to \nmake passive income");
                    tutorialText.update(300, 200);
                    tutorialText.draw(batch);
                }
            }
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
            bob.update(325, -40);
            orphan.update((int) (rockSprite.getX() + rockSprite.getWidth() / 4), 50);
            framesPassed = 0;
        } else if (framesPassed >= Gdx.graphics.getFramesPerSecond() / 2) {
            bob.update(325, -30);
            orphan.update((int) (rockSprite.getX() + rockSprite.getWidth() / 4), 40);
        }
    }

    private void input() {
        Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        if (screen == 0) { // Game screen
            if (Gdx.input.justTouched() && rockRect.contains(touch)) {
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

            if (money >= 100000000)
                orphanSprite.setTexture(batman);

            if (Gdx.input.justTouched() && exitShopRect.contains(touch)) {
                screen = 0;
            }
        }
    }
}
