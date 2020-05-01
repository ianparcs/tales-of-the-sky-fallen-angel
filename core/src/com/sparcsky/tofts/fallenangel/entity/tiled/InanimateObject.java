package com.sparcsky.tofts.fallenangel.entity.tiled;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/30/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class InanimateObject implements PhysicsObject {

    protected FixtureDef fixtureDef;
    protected Shape shape;
    protected Body body;

    public InanimateObject(Shape shape) {
        this.shape = shape;
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.friction = 1;
    }

    @Override
    public void define(GameWorld world) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bd);
    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }

    public Body getBody() {
        return body;
    }
}
