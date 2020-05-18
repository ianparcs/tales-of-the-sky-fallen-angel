package com.sparcsky.tofts.fallenangel.entity.monster;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sparcsky.tofts.fallenangel.collision.Bits;
import com.sparcsky.tofts.fallenangel.entity.DynamicEntity;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/27/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Monster extends DynamicEntity implements PhysicsObject {

    protected float attackDamage;

    public Monster(){
        attackDamage = 5;
    }
    @Override
    public void define(GameWorld world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(5, 5);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale = 4.5f;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = Bits.MONSTER;
        fixtureDef.filter.groupIndex = Bits.ENTITY_COLLISION;

        Body body = world.createBody(bodyDef);
        Fixture fixture = body.createFixture(fixtureDef);

        fixture.setUserData(this);
        body.setUserData(this);

        shape.dispose();
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }

    public float getAttack() {
        return attackDamage;
    }
}
