package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public abstract class BaseScreen implements Screen {

    public static final int VIRTUAL_WIDTH = 1080;
    public static final int VIRTUAL_HEIGHT = 720;

    ScreenManager screenManager;
    BitmapFont mainFont;
    SpriteBatch batch;
    Color screenColor;
    FallenAngel game;
    Asset asset;

    float height = Gdx.graphics.getHeight();
    float width = Gdx.graphics.getWidth();

    BaseScreen(FallenAngel game) {
        this.screenManager = game.screenManager;
        this.batch = game.render;
        this.asset = game.asset;
        this.game = game;

        screenColor = new Color();
    }

    public abstract void update(float delta);

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(screenColor.r, screenColor.g, screenColor.b, screenColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
