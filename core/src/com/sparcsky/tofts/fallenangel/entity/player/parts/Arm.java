package com.sparcsky.tofts.fallenangel.entity.player.parts;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sparcsky.tofts.fallenangel.collision.Bits;
import com.sparcsky.tofts.fallenangel.entity.monster.Monster;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.entity.tiled.Wall;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.factory.FilterFactory;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/28/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Arm extends BodyParts {

    public Arm(BodyEditorLoader bodyLoader) {
        super(bodyLoader);
    }

    @Override
    public void define(GameWorld world) {
        Filter filter = FilterFactory.createFilter(Bits.ARM, Bits.WALL, Bits.ENTITY_COLLISION);
        Fixture armFixture = bodyLoader.attachFixture(parentBody, rigidName, new FixtureDef(), 1);
        armFixture.setFilterData(filter);
        armFixture.setUserData(this);
        armFixture.setDensity(0.5f);
        armFixture.setFriction(0);
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {
        if(physicsObject instanceof Wall){
            Player player = (Player) parentBody.getUserData();
            player.changeState(StateType.SLIDE);
        }
    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }
}
