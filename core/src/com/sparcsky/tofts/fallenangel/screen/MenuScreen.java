package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Menu;
import com.sparcsky.tofts.fallenangel.entity.player.StaticPlayer;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxBackground;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxFactory;
import com.sparcsky.tofts.fallenangel.system.ParticleSystem;
import com.sparcsky.tofts.fallenangel.system.WeatherCycle;

import box2dLight.RayHandler;

import static com.sparcsky.tofts.fallenangel.util.physics.Physics.PPT;

public class MenuScreen extends BaseScreen {

    private ParallaxBackground landscapeParallax;
    private ParallaxBackground groundParallax;
    private RayHandler rayHandler;
    private WeatherCycle weatherCycle;
    private GameWorld world;
    private StaticPlayer player;
    private Menu menu;

    private ParticleSystem particles;

    MenuScreen(FallenAngel game) {
        super(game);
        worldViewport = new FillViewport(GameWorld.WIDTH, GameWorld.HEIGHT);
        asset.setFontUseIntegerPositions();
    }

    @Override
    public void show() {
        createParallax();
        createGameWorld();
        createParticles();
        createPlayer();
        createMenuOptions();
        setCursor();

        TextButton btnPlay = menu.getBtnPlay();
        TextButton btnExit = menu.getBtnExit();

        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                screenManager.setScreen(new NewGameScreen(game));
            }
        });
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void createParticles() {
        ParticleEffect particleEffect = asset.get(Asset.DATA_PARTICLE);
        ParticleEffectPool dustEffectPool = new ParticleEffectPool(particleEffect, 50, 200);
        particles = new ParticleSystem(dustEffectPool);
        particles.setCamera(worldViewport.getCamera());
    }

    private void createPlayer() {
        player = new StaticPlayer(asset);
        player.setPosition((16 * 5) / PPT, (16 * 3) / PPT);
        player.setSize((48 / PPT), (32 / PPT));
        player.define(world.getWorld());
    }

    private void setCursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("image/cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, 10, 7));
        pixmap.dispose();
    }

    private void createMenuOptions() {
        Viewport viewport = new ExtendViewport(GameWorld.WIDTH * 20, GameWorld.HEIGHT * 20);
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

        weatherCycle = new WeatherCycle(rayHandler);
    }

    private void updateWeather(float delta) {
        weatherCycle.update(delta);
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
        menu.render(batch);
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
        rayHandler.setCombinedMatrix((OrthographicCamera) worldViewport.getCamera());
        menu.resize(width, height, true);
    }

    @Override
    public void dispose() {
        particles.dispose();
        rayHandler.dispose();
        world.dispose();
        menu.dispose();
    }
}
