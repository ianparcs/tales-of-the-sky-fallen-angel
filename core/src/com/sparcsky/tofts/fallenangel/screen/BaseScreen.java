package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public abstract class BaseScreen implements Screen {

    int worldWidth = 1024;
    int worldHeight = 768;
    ScreenManager screenManager;
    BitmapFont mainFont;
    Viewport viewport;
    SpriteBatch batch;
    Color screenColor;
    FallenAngel game;
    Asset asset;

    BaseScreen(FallenAngel game) {
        this.screenManager = game.screenManager;
        this.screenColor = game.screenColor;
        this.batch = game.render;
        this.asset = game.asset;
        this.game = game;
    }

    public abstract void update(float delta);

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
