package com.sparcsky.tofts.fallenangel.entity.player.parts;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sparcsky.tofts.fallenangel.collision.Bits;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.entity.tiled.Ground;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.factory.FilterFactory;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/28/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Feet extends BodyParts {

    public Feet(BodyEditorLoader bodyLoader) {
        super(bodyLoader);
    }

    @Override
    public void define(GameWorld world) {
        Filter feetFilter = FilterFactory.createFilter(Bits.FEET, Bits.GROUND, Bits.ENTITY_COLLISION);
        Fixture feetFixture = bodyLoader.attachFixture(parentBody, rigidName, new FixtureDef(), 1);
        feetFixture.setFilterData(feetFilter);
        feetFixture.setUserData(this);
        feetFixture.setDensity(1f);
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject) {
        if (physicsObject instanceof Ground) {
            Player player = (Player) parentBody.getUserData();
            StateType currentState = player.getCurrentState();

            if (currentState != StateType.AIR_ATTACK && currentState != StateType.RUN) {
                player.changeState(StateType.IDLE);
            }
        }
    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }
}
