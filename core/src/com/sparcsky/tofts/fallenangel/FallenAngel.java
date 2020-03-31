package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.screen.LoadScreen;
import com.sparcsky.tofts.fallenangel.screen.ScreenManager;

public class FallenAngel extends Game {
    public Asset asset;
    public SpriteBatch render;
    public ScreenManager screenManager;

    @Override
    public void create() {
        render = new SpriteBatch();
        asset = new Asset();

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new LoadScreen(this));
    }

    @Override
    public void render() {
        screenManager.update(Gdx.graphics.getDeltaTime());
        screenManager.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        super.dispose();
        screenManager.dispose();
        render.dispose();
        asset.dispose();
    }
}