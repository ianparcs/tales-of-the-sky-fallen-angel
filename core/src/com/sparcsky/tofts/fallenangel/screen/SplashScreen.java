package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
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
        worldViewport = new FillViewport(GameWorld.WIDTH, GameWorld.HEIGHT);
        stage = new Stage(worldViewport);
        libgdxSplash = new LibgdxSplash(asset);
        libgdxSplash.addToStage(stage);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);

        if (isKeyPressedOrTouch()) gotoNextScreen();
        if (libgdxSplash.isFinish()) gotoNextScreen();
    }

    private boolean isKeyPressedOrTouch() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched();
    }

    private void gotoNextScreen() {
        dispose();
        screenManager.setScreen(new MenuScreen(game));
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        asset.unload(Asset.IMAGE_LIBGDX_LOGO);
    }
}
