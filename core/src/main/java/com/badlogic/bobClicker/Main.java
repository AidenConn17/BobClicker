package com.badlogic.bobClicker;

import java.util.Random;

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
    // #region Font declarations
    BitmapFont moneyFont;
    BitmapFont costFont;
    BitmapFont orphanFont;
    BitmapFont generalFont;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter moneyParameter;
    FreeTypeFontParameter costParameter;
    FreeTypeFontParameter amountParameter;
    FreeTypeFontParameter generalParameter;
    // #endregion

    SpriteBatch batch;

    // #region Texture declarations
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
    Texture batmanTexture;
    Texture jimbobTexture;
    // #endregion

    // #region Sprite declarations
    Sprite bobSprite;
    Sprite rockSprite;
    Sprite upgRockSprite;
    Sprite buyOrphanSprite;
    Sprite orphanSprite;
    Sprite shopSprite;
    Sprite exitSprite;
    Sprite jimbobSprite;
    // #endregion

    FitViewport viewport;

    // #region Class declarations
    Bob bob;
    Rock rock;
    MoneyCounter moneyCounter;
    CostCounter costCounter;
    OrphanCounter orphanCounter;
    GeneralText generalText;
    Tutorial tutorialText;
    Orphan orphan;
    Random random;
    String[] jimbobQuotes = { "I'm feeling successful! \nWant to buy something?",
            "The sun is high, \nbut my prices are not.",
            "Lovely morning, wouldn't you say? \nWas there something you needed?",
            "I have a lot of money \nand you don't",
            "I am Jimbob, \nthe richest man on <PLANET>" };
    // #endregion

    // #region Rectangles for click detection
    Rectangle upgRect;
    Rectangle rockRect;
    Rectangle buyOrphanRect;
    Rectangle exitShopRect;
    Rectangle jimbobRect;
    // #endregion

    // #region Primitive variables
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
    int jimbobTemp = 0;

    // #endregion
    @Override
    public void create() {
        batch = new SpriteBatch();

        // #region Texture initialization
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
        batmanTexture = new Texture("batman.png");
        jimbobTexture = new Texture("jimbob.png");
        // #endregion

        // #region Sprite initialization
        bobSprite = new Sprite(bobTexture);
        rockSprite = new Sprite(rockTexture1);
        upgRockSprite = new Sprite(upgRockTexture);
        buyOrphanSprite = new Sprite(buyOrphanTexture);
        orphanSprite = new Sprite(orphanTexture);
        shopSprite = new Sprite(shopTexture);
        exitSprite = new Sprite(exitTexture);
        jimbobSprite = new Sprite(jimbobTexture);
        // #endregion

        // #region Sprite values
        rockSprite.setSize(100, 50);
        bobSprite.setSize(200, 200);
        upgRockSprite.setSize(200, 100);
        buyOrphanSprite.setSize(200, 100);
        orphanSprite.setSize(50, 100);
        shopSprite.setSize(200, 100);
        exitSprite.setSize(200, 100);
        jimbobSprite.setSize(200, 150);

        upgRockSprite.setX(300);
        upgRockSprite.setY(300);
        buyOrphanSprite.setX(600);
        buyOrphanSprite.setY(300);
        orphanSprite.setX(0);
        orphanSprite.setY(0);
        shopSprite.setX(600);
        shopSprite.setY(0);
        exitSprite.setX(600);
        exitSprite.setY(0);
        jimbobSprite.setX(250);
        jimbobSprite.setY(0);
        // #endregion

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // #region Font/Class values and initialization
        generator = new FreeTypeFontGenerator(Gdx.files.internal("MintsodaLimeGreen13X16Regular-4n30l.ttf"));

        moneyParameter = new FreeTypeFontParameter();
        costParameter = new FreeTypeFontParameter();
        amountParameter = new FreeTypeFontParameter();
        generalParameter = new FreeTypeFontParameter();
        moneyParameter.size = 40;
        costParameter.size = 20;
        amountParameter.size = 30;
        generalParameter.size = 15;

        moneyFont = generator.generateFont(moneyParameter);
        costFont = generator.generateFont(costParameter);
        orphanFont = generator.generateFont(costParameter);
        generalFont = generator.generateFont(generalParameter);

        generator.dispose();

        moneyCounter = new MoneyCounter(moneyFont, 0, 400);
        costCounter = new CostCounter(costFont, 300, 290);
        orphanCounter = new OrphanCounter(orphanFont, 600, 290);
        tutorialText = new Tutorial(null, costFont, 300, 200);
        generalText = new GeneralText(generalFont, null, 150, 200);
        bob = new Bob(150, -25, 100, 100, bobSprite, batch);
        rock = new Rock((Gdx.graphics.getWidth() / 2) + 50, 0, batch, rockSprite);
        orphan = new Orphan(orphanSprite);
        random = new Random();
        // #endregion

        // #region Rectangle initialization
        upgRect = new Rectangle(300, 300, 200, 100);
        rockRect = new Rectangle((Gdx.graphics.getWidth() / 2) + 50, 0, 100, 50);
        buyOrphanRect = new Rectangle(600, 300, 200, 100);
        exitShopRect = new Rectangle(600, 0, 200, 100);
        jimbobRect = new Rectangle(250, 0, 200, 150);
        // #endregion
    }

    @Override
    public void render() { // Game loop, runs every frame
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

    private void draw() { // Draws sprites and text in the game window
        // Things that are done/drawn no matter the screen
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        ScreenUtils.clear(Color.WHITE);
        moneyCounter.draw(batch);
        orphanCounter.drawAmount(batch, 0, 350);
        if (screen == 0) { // Main screen

            bob.draw();

            // #region Tutorial
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
                if (money <= 99 && rockLevel == 2 && amountOfOrphans == 0) {
                    tutorialText.update("Click until you have 100 money,\nthen go back to the shop");
                    tutorialText.update(250, 250);
                    tutorialText.draw(batch);
                }
                if (rockLevel == 2 && amountOfOrphans >= 1) {
                    if (money < 5) {
                        tutorialText.update("That's all there is to it!");
                        tutorialText.update(250, 250);
                        tutorialText.draw(batch);
                    } else {
                        tutorialActive = false;
                    }
                }
            } // #endregion

            // Draws the orphan sprite if there's at least one orphan
            if (amountOfOrphans != 0)
                orphanSprite.draw(batch);
            rockSprite.draw(batch);
            shopSprite.draw(batch);
        } else if (screen == 1) { // Shop screen
            // #region Tutorial
            if (tutorialActive) {
                if (money >= 10 && rockLevel == 1) {
                    tutorialText.update("You can afford \nto upgrade \nthe rock!");
                    tutorialText.update(300, 250);
                    tutorialText.draw(batch);
                }
                if (money >= 100 && rockLevel == 2 && amountOfOrphans == 0) {
                    tutorialText.update("\"adopt\" an orphan to \nmake passive income");
                    tutorialText.update(300, 200);
                    tutorialText.draw(batch);
                }
            } // #endregion

            // Draws cost counters and buttons
            upgRockSprite.draw(batch);
            buyOrphanSprite.draw(batch);
            costCounter.draw(batch);
            orphanCounter.drawCost(batch);
            exitSprite.draw(batch);
            jimbobSprite.draw(batch);
            if (money < jimbobTemp) {
                generalText.draw(batch);
            }
        }
        batch.end();
    }

    private void logic() { // Performs logic not related to user input
        // Updates counters the framesPassed variable
        moneyCounter.update(money);
        costCounter.update(upgCost, rockIsMaxLevel);
        orphanCounter.update(orphanCost, amountOfOrphans);
        orphan.update(amountOfOrphans);
        framesPassed++; // Used to check if a second has passed

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
        // Handles logic reliant on user input
        Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        if (screen == 0) { // Game screen
            //Gives user money based on the level of the rock
            //when the rock is clicked
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
            //Sets the rock to the next level when the user
            //clicks the upgrade button and can afford to
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

            //Adds an orphan when the buyOrphan button is clicked and user has enough money
            if (Gdx.input.justTouched() && buyOrphanRect.contains(touch) && money >= orphanCost) {
                moneyPerSecond += 1;
                money -= orphanCost;
                orphanCost = (int) (orphanCost * 1.2);
                amountOfOrphans++;
            }
            
            //Joke ha ha dw about it
            if (money >= 100000000) {
                orphanSprite.setTexture(batmanTexture);
            }

            // Exits shop when the exit button is clicked
            if (Gdx.input.justTouched() && exitShopRect.contains(touch)) {
                screen = 0;
            }

            // When jimbob is clicked on, he says something for 5 seconds
            if (Gdx.input.justTouched() && jimbobRect.contains(touch)) {
                jimbobTemp = money + moneyPerSecond * 3;
                // Increment bound by 1 for every new quote added to jimbobQuotes
                generalText.update(jimbobQuotes[random.nextInt(5)]);
            }
        }
    }
}
