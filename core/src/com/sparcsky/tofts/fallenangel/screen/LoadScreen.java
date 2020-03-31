package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Timer;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Diamond;

public class LoadScreen extends BaseScreen {

    private String[] dots = {" ", ".", "..", "..."};
    private GlyphLayout layout;
    private Diamond diamond;
    private int dotIndex;

    public LoadScreen(FallenAngel game) {
        super(game);
        screenColor.set(0.156f, 0.156f, 0.156f, 0.156f);
        layout = new GlyphLayout();
    }

    @Override
    public void show() {
        setDotsTimer();

        diamond = new Diamond(asset);
        asset.load(Asset.LIBGDX_LOGO);
        asset.load(Asset.PLAYER);
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
        camera.update();
        diamond.update(delta);

        if (asset.isLoadFinish()) {
            dispose();
            screenManager.setScreen(new SplashScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        String loadResource = "Loading resources";
        layout.setText(mainFont, loadResource);
        float loadResourceX = (width / 2f) - layout.width / 2f;
        float loadResourceY = diamond.getY() - layout.height * 3f;

        String loadProgress = (int) asset.getProgress() * 100 + "%";
        layout.setText(mainFont, loadProgress);
        float loadProgressX = (width / 2f) - layout.width / 2f;
        float loadProgressY = diamond.getY() - layout.height / 2f;

        String loadFullName = loadResource + dots[dotIndex];

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        diamond.draw(batch);
        mainFont.draw(batch, loadProgress, loadProgressX, loadProgressY);
        mainFont.draw(batch, loadFullName, loadResourceX, loadResourceY);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.position.set(width / 2f, height / 2f, 0);
    }

    @Override
    public void dispose() {
        asset.unload(Asset.LOAD_DIAMOND);
    }
}
