package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.screen.LoadScreen;
import com.sparcsky.tofts.fallenangel.screen.ScreenManager;

public class FallenAngel extends Game {

    public ScreenManager screenManager;
    public SpriteBatch render;
    public Color screenColor;
    public Asset asset;

    @Override
    public void create() {
        render = new SpriteBatch();
        screenColor = new Color();

        asset = new Asset();

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new LoadScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(screenColor.r, screenColor.g, screenColor.b, screenColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        screenManager.update(delta);
        screenManager.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        screenManager.dispose();
        render.dispose();
        asset.dispose();
    }
}