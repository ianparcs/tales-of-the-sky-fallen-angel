package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.util.Physics;
import com.sparcsky.tofts.fallenangel.util.factory.Box2DBuilder;

public class GameWorld {

 /*   public static final float WIDTH = 480;
    public static final float HEIGHT = WIDTH * 9f / 16f;*/
    public static final float WIDTH = 32;
    public static final float HEIGHT = WIDTH * 9f / 16f;

    private Box2DDebugRenderer debugRenderer;
    private TiledMapRenderer tiledMap;
    private Viewport viewport;
    private World world;

    public GameWorld(TiledMap map) {
        tiledMap = new OrthogonalTiledMapRenderer(map, 1f / Physics.PPT);
        world = new World(new Vector2(0, Physics.GRAVITY), true);

        debugRenderer = new Box2DDebugRenderer();
        Box2DBuilder.buildShapes(map, world);
    }

    public void update() {
        tiledMap.setView(getCam());
        world.step(Physics.TIME_STEP, 6, 2);

        boolean debug = !Gdx.input.isKeyPressed(Input.Keys.Q);
        if (debug) debugRenderer.render(world, getCam().combined);
    }

    public void render() {
        tiledMap.render();
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    private OrthographicCamera getCam() {
        return (OrthographicCamera) viewport.getCamera();
    }

    public void dispose() {
        debugRenderer.dispose();
        world.dispose();
    }

    public World getWorld() {
        return world;
    }
}
