package com.badlogic.bobClicker;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
    Texture jimbobAngry1Texture;
    Texture saveTexture;
    Texture newGameTexture;
    Texture loadTexture;
    Texture titleTexture;
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
    Sprite saveSprite;
    Sprite newGameSprite;
    Sprite loadSprite;
    Sprite titleSprite;
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
    Rectangle saveRect;
    Rectangle newGameRect;
    Rectangle loadRect;
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
    int moneyPerSecond = amountOfOrphans;
    int screen = 2;
    int jimbobTemp = 0;

    // #endregion

    Preferences prefs; // For saving

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
        jimbobAngry1Texture = new Texture("jimbobAngry1.png");
        saveTexture = new Texture("saveButton.png");
        newGameTexture = new Texture("newGameButton.png");
        loadTexture = new Texture("loadButton.png");
        titleTexture = new Texture("TItle.png");
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
        saveSprite = new Sprite(saveTexture);
        newGameSprite = new Sprite(newGameTexture);
        loadSprite = new Sprite(loadTexture);
        titleSprite = new Sprite(titleTexture);
        // #endregion

        // #region Sprite values
        // Size
        rockSprite.setSize(100, 50);
        bobSprite.setSize(200, 200);
        upgRockSprite.setSize(200, 100);
        buyOrphanSprite.setSize(200, 100);
        orphanSprite.setScale(1f);
        shopSprite.setSize(200, 100);
        exitSprite.setSize(200, 100);
        jimbobSprite.setSize(200, 150);
        saveSprite.setSize(100, 50);
        newGameSprite.setScale(1f);
        loadSprite.setScale(1f);
        titleSprite.setScale(1f);

        // Position
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
        saveSprite.setX(0);
        saveSprite.setY(275);
        newGameSprite.setX(150);
        newGameSprite.setY(150);
        loadSprite.setX(Gdx.graphics.getWidth() - 350);
        loadSprite.setY(150);
        titleSprite.setCenter(Gdx.graphics.getWidth() / 2, 300);
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
        saveRect = new Rectangle(0, 275, 100, 50);
        newGameRect = new Rectangle(150, 150, 200, 100);
        loadRect = new Rectangle(Gdx.graphics.getWidth() - 350, 150, 200, 100);
        // #endregion

        prefs = Gdx.app.getPreferences("bobSave");

        // #region Save loading
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
        if (screen == 0 || screen == 1) { // Draw if not on open screen
            moneyCounter.draw(batch);
            orphanCounter.drawAmount(batch, 0, 350);
            saveSprite.draw(batch);
        } else { // Draw newGame, title, and load button
            newGameSprite.draw(batch);
            loadSprite.draw(batch);
            titleSprite.draw(batch);
        }
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
        } else if (screen == 2) {

        }

        batch.end();
    }

    private void logic() { // Performs logic not related to user input
        // Updates counters the framesPassed variable
        moneyCounter.update(money);
        costCounter.update(upgCost, rockIsMaxLevel);
        orphanCounter.update(orphanCost, amountOfOrphans);
        framesPassed++; // Used to check if a second has passed

        if (framesPassed >= Gdx.graphics.getFramesPerSecond()) {
            money += moneyPerSecond;
            bob.update(325, -40);
            orphan.update((int) (rockSprite.getX() + rockSprite.getWidth() / 4), 55);
            framesPassed = 0;
        } else if (framesPassed >= Gdx.graphics.getFramesPerSecond() / 2) {
            bob.update(325, -30);
            orphan.update((int) (rockSprite.getX() + rockSprite.getWidth() / 4), 45);
        }
        if (rockLevel == 1) {
            rockSprite.setTexture(rockTexture1);
        } else if (rockLevel == 2) {
            rockSprite.setTexture(rockTexture2);
        } else if (rockLevel == 3) {
            rockSprite.setTexture(rockTexture3);
        } else if (rockLevel == 4) {
            rockSprite.setTexture(rockTexture4);
        } else if (rockLevel == 5) {
            rockSprite.setTexture(rockTexture5);
            rockIsMaxLevel = true;
        }
    }

    private void input() {
        // Handles logic reliant on user input
        Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

        // Save detection
        if (Gdx.input.justTouched() && saveRect.contains(touch)) {
            prefs.putInteger("money", money);
            prefs.putInteger("amountOfOrphans", amountOfOrphans);
            prefs.putInteger("mps", moneyPerSecond);
            prefs.putBoolean("tutorialOn?", tutorialActive);
            prefs.putInteger("rockLevel", rockLevel);
            prefs.putBoolean("rockMax", rockIsMaxLevel);
            prefs.putInteger("rockCost", upgCost);
            prefs.putInteger("mpsCost", orphanCost);

            prefs.flush();
        }

        if (screen == 0) { // Game screen
            // Gives user money based on the level of the rock
            // when the rock is clicked
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

            //If the shop button is clicked, set screen to shop
            if (Gdx.input.justTouched() && exitShopRect.contains(touch)) {
                screen = 1;
            }

        } else if (screen == 1) {// Shop screen
            // Sets the rock to the next level when the user
            // clicks the upgrade button and can afford to
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

            // Adds an orphan when the buyOrphan button is clicked and user has enough money
            if (Gdx.input.justTouched() && buyOrphanRect.contains(touch) && money >= orphanCost) {
                moneyPerSecond += 1;
                money -= orphanCost;
                orphanCost = (int) (orphanCost * 1.2);
                amountOfOrphans++;
            }

            // Joke ha ha dw about it
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
        } else if (screen == 2) {
            if(Gdx.input.justTouched() && newGameRect.contains(touch)){
                //Fill the save file with zeroes
                prefs.putInteger("money", 0);
                prefs.putInteger("amountOfOrphans", 0);
                prefs.putInteger("mps", 0);
                prefs.putBoolean("tutorialOn?", true);
                prefs.putInteger("rockLevel", 1);
                prefs.putBoolean("rockMax", false);
                prefs.putInteger("rockCost", 10);
                prefs.putInteger("mpsCost", 100);

                prefs.flush();
                
                //Set to main screen
                screen = 0;
            }
            
            if (Gdx.input.justTouched() && loadRect.contains(touch)){
                //Load values from save file
                money = prefs.getInteger("money", 0);
                amountOfOrphans = prefs.getInteger("amountOfOrphans", 0);
                moneyPerSecond = prefs.getInteger("mps", 0);
                tutorialActive = prefs.getBoolean("tutorialOn?", true);
                rockLevel = prefs.getInteger("rockLevel", 0);
                rockIsMaxLevel = prefs.getBoolean("rockMax", false);
                upgCost = prefs.getInteger("rockCost", 10);
                orphanCost = prefs.getInteger("mpsCost", 100);
                
                //Set to main screen
                screen = 0;
            }
        }
    }
}
