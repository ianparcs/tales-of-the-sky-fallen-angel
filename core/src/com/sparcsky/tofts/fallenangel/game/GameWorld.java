package com.sparcsky.tofts.fallenangel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sparcsky.tofts.fallenangel.collision.GameCollision;
import com.sparcsky.tofts.fallenangel.entity.tiled.Ground;
import com.sparcsky.tofts.fallenangel.entity.tiled.InanimateObject;
import com.sparcsky.tofts.fallenangel.entity.tiled.Wall;
import com.sparcsky.tofts.fallenangel.util.factory.ShapeFactory;
import com.sparcsky.tofts.fallenangel.util.physics.Physics;

public class GameWorld {

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

        MapObjects platform = map.getLayers().get("platform").getObjects();
        createPlatforms(platform);
    }

    private void createPlatforms(MapObjects platforms) {
        Array<Body> bodies = new Array<>();
        for (MapObject mapObject : platforms) {
            if (mapObject instanceof TextureMapObject) continue;

            Shape shape = ShapeFactory.getShapeOfMapObject(mapObject);
            InanimateObject platformObject = getPlatform(shape, mapObject.getName());
            platformObject.define(this);

            bodies.add(platformObject.getBody());
            shape.dispose();
        }
    }

    private InanimateObject getPlatform(Shape shape, String platformName) {
        InanimateObject inanimateObject = null;
        if (platformName.equals("ground")) {
            inanimateObject = new Ground(shape);
        } else if (platformName.equals("wall")) {
            inanimateObject = new Wall(shape);
        }
        return inanimateObject;
    }

    public void setCollisionListener(GameCollision gameCollision) {
        world.setContactListener(gameCollision);
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

    public Joint createJoint(JointDef jointDef) {
        return world.createJoint(jointDef);
    }

    public Body createBody(BodyDef bdef) {
        return world.createBody(bdef);
    }

    public void destroy(Body body) {
        world.destroyBody(body);
    }

    public void destroy(Joint weaponHolder) {
        world.destroyJoint(weaponHolder);
    }
}
