package com.sparcsky.tofts.fallenangel.entity.tiled;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sparcsky.tofts.fallenangel.collision.Bits;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;


/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Wall extends InanimateObject {

    protected Shape shape;

    public Wall(Shape shape) {
        super(shape);
        fixtureDef.filter.categoryBits = Bits.WALL;
        fixtureDef.filter.maskBits = Bits.ARM;
    }

    @Override
    public void define(GameWorld world) {
        super.define(world);
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {

    }
}
