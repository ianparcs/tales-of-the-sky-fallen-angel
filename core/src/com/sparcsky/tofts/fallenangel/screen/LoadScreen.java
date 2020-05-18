package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Diamond;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sun.javafx.applet.Splash;

public class LoadScreen extends BaseScreen {

    private String[] dots = {" ", ".", "..", "..."};
    private GlyphLayout layout;
    private Diamond diamond;
    private BitmapFont font;
    private int dotIndex;

    public LoadScreen(FallenAngel game) {
        super(game);
        screenColor.set(0.156f, 0.156f, 0.156f, 0.156f);
        layout = new GlyphLayout();

        asset.initLoadScreenAsset();
        asset.loadAllResources();
        Box2D.init();
    }

    @Override
    public void show() {
        worldViewport = new FillViewport(GameWorld.WIDTH, GameWorld.HEIGHT);

        setDotsTimer();
        setFont();

        diamond = new Diamond(asset);
    }

    private void setFont() {
        font = asset.get(Asset.FONT_ADVENTURER);
        font.getData().setScale((font.getScaleX() / 16f) / 2f, (font.getScaleY() / 16f) / 2f);
        font.setUseIntegerPositions(false);
    }

    private void setDotsTimer() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ++dotIndex;
                if (dotIndex >= dots.length) dotIndex = 0;
            }
        }, 0, 0.15f);
    }

    @Override
    public void update(float delta) {
        diamond.update(delta);
        diamond.setX(GameWorld.WIDTH / 2f - (diamond.getWidth() / 2f));
        diamond.setY(GameWorld.HEIGHT / 2f);

        if (asset.isLoadFinish()) {
            dispose();
            screenManager.setScreen(new SplashScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        String loadResource = "Loading resources";
        layout.setText(font, loadResource);
        float loadResourceX = (diamond.getX() + diamond.getWidth() / 2f) - layout.width / 2f;
        float loadResourceY = diamond.getY() - layout.height / 2f;

        float progress = (asset.getProgress() * 100.00f);
        String loadProgress = (int) progress + "%";
        layout.setText(font, loadProgress);
        float loadProgressX = (diamond.getX() + diamond.getWidth() / 2f) - layout.width / 2f;
        float loadProgressY = diamond.getY() + layout.height / 2f;

        String loadFullName = loadResource + dots[dotIndex];

        batch.begin();
        diamond.render(batch);
        font.draw(batch, loadProgress, loadProgressX, loadProgressY);
        font.draw(batch, loadFullName, loadResourceX, loadResourceY);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        worldViewport.update(width, height, true);
        batch.setProjectionMatrix(worldViewport.getCamera().combined);
    }

    @Override
    public void dispose() {
        asset.unload(Asset.IMAGE_DIAMOND);
    }
}
