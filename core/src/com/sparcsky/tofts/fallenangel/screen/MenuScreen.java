package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.GameWorld;
import com.sparcsky.tofts.fallenangel.ParticleController;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Menu;
import com.sparcsky.tofts.fallenangel.entity.Player;
import com.sparcsky.tofts.fallenangel.entity.Sun;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxBackground;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxFactory;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.RayHandler;

public class MenuScreen extends BaseScreen {

    private RayHandler rayHandler;
    private GameWorld world;
    private Player player;
    private Menu menu;
    private Sun sun;
    private ParallaxBackground landscapeParallax;
    private ParallaxBackground groundParallax;

    private ParticleController particles;

    MenuScreen(FallenAngel game) {
        super(game);
        worldWidth = 480;
        worldHeight = 270;
        worldViewport = new FitViewport(worldWidth, worldHeight);
    }

    @Override
    public void show() {
        createMenuOptions();
        createGameWorld();
        createParticles();
        createParallax();
        createPlayer();
        setCursor();
    }

    private void createParticles() {
        ParticleEffect particleEffect = asset.get(Asset.DATA_PARTICLE);
        ParticleEffectPool dustEffectPool = new ParticleEffectPool(particleEffect, 50, 200);
        particles = new ParticleController(dustEffectPool);
        particles.setCamera(worldViewport.getCamera());
    }

    private void createPlayer() {
        player = new Player(asset);
        player.setPosition(32, 16 * 3);
        player.define(world.getWorld());
    }

    private void setCursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("image/cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, 10, 7));
        pixmap.dispose();
    }

    private void createMenuOptions() {
        Viewport viewport = new FitViewport(worldWidth * 1.5f, worldHeight * 1.5f);
        menu = new Menu(asset, batch, viewport);
    }

    private void createParallax() {
        landscapeParallax = ParallaxFactory.createLandscape(asset, worldWidth, worldHeight);
        groundParallax = ParallaxFactory.createGround(asset, worldWidth * 2, worldHeight * 2);
    }

    private void createGameWorld() {
        float width = worldWidth / Physics.PPT;  // 64
        float height = worldHeight / Physics.PPT; // 48

        worldViewport = new FitViewport(width, height);
        TiledMap menuBg = asset.get(Asset.TMX_BACKGROUND);

        world = new GameWorld(menuBg);
        world.setViewport(worldViewport);

        rayHandler = new RayHandler(world.getWorld());
        rayHandler.setAmbientLight(0.15f);
        rayHandler.setShadows(false);

        sun = new Sun(rayHandler, width, height);
    }

    @Override
    public void update(float delta) {
        particles.update(delta);
        menu.update(delta);
        sun.update(delta);

        player.update(delta);
        world.update();

        rayHandler.setCombinedMatrix((OrthographicCamera) worldViewport.getCamera());
        rayHandler.update();
    }

    @Override
    public void render(float delta) {
        renderBackground(delta);
        menu.render(batch);
        particles.render(batch);
    }

    private void renderBackground(float delta) {
        renderParallax(delta);
        world.render();
        renderPlayer();
        renderSun();
    }

    private void renderSun() {
        sun.render(batch);
        rayHandler.render();
    }

    private void renderParallax(float delta) {
        landscapeParallax.render(batch, delta);
        groundParallax.render(batch, delta);
    }

    private void renderPlayer() {
        batch.setProjectionMatrix(worldViewport.getCamera().combined);
        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        world.getViewport().update(width, height, true);
        worldViewport.update(width, height, true);
        menu.resize(width, height, true);
    }

    @Override
    public void dispose() {
        particles.dispose();
        rayHandler.dispose();
        world.dispose();
        menu.dispose();
        asset.dispose();
    }
}
