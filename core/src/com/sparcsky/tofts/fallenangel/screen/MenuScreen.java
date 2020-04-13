package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.GameWorld;
import com.sparcsky.tofts.fallenangel.ParticleSystem;
import com.sparcsky.tofts.fallenangel.WeatherSystem;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Menu;
import com.sparcsky.tofts.fallenangel.entity.Player;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxBackground;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxFactory;

import box2dLight.RayHandler;

public class MenuScreen extends BaseScreen {

    private RayHandler rayHandler;
    private GameWorld world;
    private Player player;
    private Menu menu;
    private ParallaxBackground landscapeParallax;
    private ParallaxBackground groundParallax;
    private WeatherSystem weatherSystem;

    private ParticleSystem particles;

    MenuScreen(FallenAngel game) {
        super(game);
        worldViewport = new StretchViewport(GameWorld.WIDTH, GameWorld.HEIGHT);
    }

    @Override
    public void show() {
        createParallax();
        createGameWorld();
        createParticles();
        createPlayer();
        createMenuOptions();
        setCursor();

        // ((OrthographicCamera) worldViewport.getCamera()).zoom = 5f;
    }

    private void createParticles() {
        ParticleEffect particleEffect = asset.get(Asset.DATA_PARTICLE);
        ParticleEffectPool dustEffectPool = new ParticleEffectPool(particleEffect, 50, 200);
        particles = new ParticleSystem(dustEffectPool);
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
        Viewport viewport = new StretchViewport(GameWorld.WIDTH * 20, GameWorld.HEIGHT * 20);
        menu = new Menu(asset, batch, viewport);
    }

    private void createParallax() {
        landscapeParallax = ParallaxFactory.createLandscape(asset);
        groundParallax = ParallaxFactory.createGround(asset);
    }

    private void createGameWorld() {
        TiledMap menuBg = asset.get(Asset.TMX_BACKGROUND);
        world = new GameWorld(menuBg);
        world.setViewport(worldViewport);

        rayHandler = new RayHandler(world.getWorld());
        rayHandler.setShadows(true);

        weatherSystem = new WeatherSystem(rayHandler);
    }

    private void updateWeather(float delta) {
        weatherSystem.update(delta);
        rayHandler.setCombinedMatrix((OrthographicCamera) worldViewport.getCamera());
        rayHandler.update();
    }

    @Override
    public void update(float delta) {
        menu.update(delta);

        player.update(delta);
        world.update();
        updateWeather(delta);
    }

    @Override
    public void render(float delta) {
        renderBackground(delta);
        //     menu.render(batch);
        particles.render(batch);
    }

    private void renderBackground(float delta) {
        renderParallax(delta);
        world.render();
        renderPlayer();
        renderWeather();
    }

    private void renderWeather() {
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
