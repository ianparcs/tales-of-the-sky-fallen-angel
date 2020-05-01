package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.collision.GameCollision;
import com.sparcsky.tofts.fallenangel.entity.monster.Monster;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.weapon.BrassKnuckle;
import com.sparcsky.tofts.fallenangel.entity.weapon.Sword;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.factory.PlayerBodyBuilder;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;

import static com.sparcsky.tofts.fallenangel.util.physics.Physics.PPT;

/**
 * Created by Ian Jasper Parcon on 4/16/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */

public class NewGameScreen extends BaseScreen {


    private PlayerBodyBuilder playerBodyBuilder;
    private OrthographicCamera playerCam;
    private GameWorld world;
    private Player player;

    public NewGameScreen(FallenAngel game) {
        super(game);
        playerCam = new OrthographicCamera();
        worldViewport = new FillViewport(GameWorld.WIDTH, GameWorld.HEIGHT, playerCam);

    }

    @Override
    public void show() {
        initWorld();
        initPlayer();
        initEnemy();
    }

    private void initWorld() {
        TiledMap map = asset.get(Asset.TMX_LEVEL_1);
        world = new GameWorld(map);
        world.setViewport(worldViewport);
        world.setCollisionListener(new GameCollision());
    }

    private void initPlayer() {
        BrassKnuckle brassKnuckle = new BrassKnuckle(asset);

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/bodies"));
        PlayerBodyBuilder bodyBuilder = new PlayerBodyBuilder(world,loader);

        player = new Player(asset);
        player.setPosition((16 * 3) / PPT, (16 * 3) / PPT);
        player.setSize((48 / PPT), (48 / PPT));
        player.setBodyBuilder(bodyBuilder);
        player.define(world);
        player.equip(brassKnuckle);
    }

    private void initEnemy() {
        Monster monster = new Monster();
        monster.define(world);
    }

    @Override
    public void update(float delta) {
        world.update();

        playerCam.position.x = player.getX();
        playerCam.position.y = player.getY();
        playerCam.update();
        player.update(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            BrassKnuckle knuckle = new BrassKnuckle(asset);
            player.equip(knuckle);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            Sword sword = new Sword(asset);
            player.equip(sword);
        }
    }

    @Override
    public void render(float delta) {
        world.render();

        batch.setProjectionMatrix(worldViewport.getCamera().combined);
        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        world.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        world.dispose();
    }


}
