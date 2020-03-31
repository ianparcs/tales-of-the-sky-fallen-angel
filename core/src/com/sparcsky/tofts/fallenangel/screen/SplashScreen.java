package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.LibgdxSplash;

public class SplashScreen extends BaseScreen {

    private Stage stage;
    private LibgdxSplash libgdxSplash;

    SplashScreen(FallenAngel game) {
        super(game);
        screenColor.set(0f, 0f, 0f, 0f);
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera(width, height);
        stage = new Stage(new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera));

        libgdxSplash = new LibgdxSplash(asset);
        libgdxSplash.addToStage(stage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);

        if (libgdxSplash.isFinish()) {
            dispose();
            screenManager.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        asset.unload(Asset.LIBGDX_LOGO);
    }
}
