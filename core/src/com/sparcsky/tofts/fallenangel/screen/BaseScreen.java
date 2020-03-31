package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public abstract class BaseScreen implements Screen {

    public static final int VIRTUAL_WIDTH = 1080;
    public static final int VIRTUAL_HEIGHT = 720;

    ScreenManager screenManager;
    OrthographicCamera camera;
    BitmapFont mainFont;
    Viewport viewport;
    SpriteBatch batch;
    Color screenColor;
    FallenAngel game;
    Asset asset;

    float height = Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();

    BaseScreen(FallenAngel game) {
        this.screenManager = game.screenManager;
        this.screenColor = game.screenColor;
        this.batch = game.render;
        this.asset = game.asset;
        this.game = game;

        camera = new OrthographicCamera(width, height);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        mainFont = asset.get(Asset.FONT_BIT);
    }

    public abstract void update(float delta);

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}
