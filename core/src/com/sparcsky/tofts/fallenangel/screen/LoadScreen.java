package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Diamond;

public class LoadScreen extends BaseScreen {

    private GlyphLayout layout;
    private Viewport viewport;

    private String[] dots = {" ", ".", "..", "..."};
    private Diamond diamond;
    private int dotIndex;

    private OrthographicCamera camera;

    public LoadScreen(FallenAngel game) {
        super(game);
        screenColor.set(0.156f, 0.156f, 0.156f, 0.156f);
        layout = new GlyphLayout();
    }

    @Override
    public void show() {
        asset.load(Asset.loadDiamond);
        asset.loadFont(Asset.fontBit, 12);
        asset.loadAll();

        camera = new OrthographicCamera(width, height);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        mainFont = asset.get(Asset.fontBit);
        diamond = new Diamond(asset);

        setDotsTimer();

        asset.load(Asset.libgdxLogo);
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
        super.render(delta);

        String loadResource = "Loading resources";
        layout.setText(mainFont, loadResource);
        float loadResourceX = (width / 2f) - layout.width / 2f;
        float loadResourceY = diamond.getY() - layout.height * 2f;

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
        this.width = width;
        this.height = height;
        viewport.update(width, height);
        camera.position.set(width / 2f, height / 2f, 0);
    }

    @Override
    public void dispose() {
        asset.unload(Asset.loadDiamond);
    }
}
