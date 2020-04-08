package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.GameWorld;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Menu;
import com.sparcsky.tofts.fallenangel.entity.Player;
import com.sparcsky.tofts.fallenangel.entity.Sun;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxBackground;
import com.sparcsky.tofts.fallenangel.parallax.ParallaxFactory;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.RayHandler;

public class MenuScreen extends BaseScreen {

    private Viewport worldViewPort;
    private RayHandler light;
    private GameWorld world;
    private Player player;
    private Menu menu;
    private Sun sun;

    private ParallaxBackground landscapeParallax;
    private ParallaxBackground groundParallax;

/*    private ParticleEffect particleEffect;*/

    MenuScreen(FallenAngel game) {
        super(game);
        worldWidth = 480;
        worldHeight = 270;
        viewport = new StretchViewport(worldWidth, worldHeight);

        player = new Player(asset);
        player.setPosition(32, 16 * 3);

    /*    particleEffect = asset.get(Asset.DATA_PARTICLE);
        particleEffect.getEmitters().first().setPosition(worldWidth / 2f, worldHeight / 2f);
        particleEffect.start();*/

    }

    @Override
    public void show() {
        createWorld();
        createMenuOptions();
        createParallax();

        player.define(world.getWorld());
    }

    private void createMenuOptions() {
        StretchViewport viewport = new StretchViewport(worldWidth * 1.5f, worldHeight * 1.5f);
        menu = new Menu(asset, batch, viewport);
    }

    private void createParallax() {
        landscapeParallax = ParallaxFactory.createLandscape(asset, worldWidth, worldHeight);
        groundParallax = ParallaxFactory.createGround(asset, worldWidth * 3, worldHeight * 3);
    }

    private void createWorld() {
        float width = worldWidth / Physics.PPT;
        float height = worldHeight / Physics.PPT;

        worldViewPort = new StretchViewport(width, height);
        TiledMap menuBg = asset.get(Asset.TMX_BACKGROUND);

        world = new GameWorld(menuBg);
        world.setViewport(worldViewPort);

        light = new RayHandler(world.getWorld());
        light.setAmbientLight(1, 1, 1, 0.15f);
        light.setShadows(false);

        sun = new Sun(light, width, height);
    }

    @Override
    public void update(float delta) {
        menu.update(delta);
        sun.update(delta);

    /*    particleEffect.update(delta);*/
        player.update(delta);
        world.update();

        light.setCombinedMatrix((OrthographicCamera) world.getViewport().getCamera());
        light.update();
    }

    @Override
    public void render(float delta) {
        renderParallax(delta);
        renderPlayer();

        world.render();
        renderSun();

        menu.render(batch);

        batch.begin();
        /*particleEffect.draw(batch);*/
        batch.end();
    }

    private void renderSun() {
        sun.render(batch);
        light.render();
    }

    private void renderParallax(float delta) {
        landscapeParallax.render(batch, delta);
        groundParallax.render(batch, delta);
    }

    private void renderPlayer() {
        batch.setProjectionMatrix(worldViewPort.getCamera().combined);
        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        menu.resize(width, height, true);
        world.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        light.dispose();
        world.dispose();
        menu.dispose();
        asset.dispose();
        /*   particleEffect.dispose();*/
    }
}
