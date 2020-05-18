package com.sparcsky.tofts.fallenangel.entity.player.parts;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sparcsky.tofts.fallenangel.collision.Bits;
import com.sparcsky.tofts.fallenangel.entity.monster.Monster;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.factory.FilterFactory;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/28/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Torso extends BodyParts {

    public Torso(BodyEditorLoader bodyLoader) {
        super(bodyLoader);
    }

    @Override
    public void define(GameWorld world) {
        Filter filter = FilterFactory.createFilter(Bits.TORSO);
        Fixture torsoFixture = bodyLoader.attachFixture(parentBody, rigidName, new FixtureDef(), 1);
        torsoFixture.setFilterData(filter);
        torsoFixture.setUserData(this);
        torsoFixture.setFriction(0);
        torsoFixture.setDensity(2);
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {
        if (physicsObject instanceof Monster) {
            Player player = (Player) parentBody.getUserData();
            player.health = player.health - 10;
            player.changeState(StateType.HURT);
        }
    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {
        if (physicsObject instanceof Monster) {
            contact.setEnabled(false);
        }
    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }
}
