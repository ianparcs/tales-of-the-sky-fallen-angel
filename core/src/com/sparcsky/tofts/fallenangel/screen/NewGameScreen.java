package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sparcsky.tofts.fallenangel.collision.GameCollision;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

import static com.sparcsky.tofts.fallenangel.util.physics.Physics.PPT;

/**
 * Created by Ian Jasper Parcon on 4/16/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */

public class NewGameScreen extends BaseScreen {

    private GameWorld gameWorld;
    private Player player;
    private OrthographicCamera playerCam;

    public NewGameScreen(FallenAngel game) {
        super(game);
        playerCam = new OrthographicCamera();
        worldViewport = new FillViewport(GameWorld.WIDTH, GameWorld.HEIGHT, playerCam);
    }

    @Override
    public void show() {
        TiledMap map = asset.get(Asset.TMX_LEVEL_1);
        gameWorld = new GameWorld(map);

        player = new Player(asset);
        player.setPosition((16 * 3) / PPT, (16 * 3) / PPT);
        player.setSize((48 / PPT), (48 / PPT));

        gameWorld.setViewport(worldViewport);
        gameWorld.addEntity(player);
        gameWorld.setCollisionListener(new GameCollision());
    }

    @Override
    public void update(float delta) {
        gameWorld.update();

        playerCam.position.x = player.getX();
        playerCam.position.y = player.getY();
        playerCam.update();
        player.update(delta);
    }

    @Override
    public void render(float delta) {
        gameWorld.render();

        batch.setProjectionMatrix(worldViewport.getCamera().combined);
        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }
}
