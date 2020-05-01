package com.sparcsky.tofts.fallenangel.util.factory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sparcsky.tofts.fallenangel.entity.player.parts.Arm;
import com.sparcsky.tofts.fallenangel.entity.player.parts.BodyParts;
import com.sparcsky.tofts.fallenangel.entity.player.parts.Feet;
import com.sparcsky.tofts.fallenangel.entity.player.parts.Torso;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;

/**
 * Created by Ian Jasper Parcon on 4/27/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class PlayerBodyBuilder {

    private BodyEditorLoader bodyLoader;
    private GameWorld world;
    private Body body;

    public PlayerBodyBuilder(GameWorld world, BodyEditorLoader bodyLoader) {
        this.bodyLoader = bodyLoader;
        this.world = world;
    }

    public void buildBaseBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale = 4.5f;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
    }

    public BodyParts buildTorso() {
        BodyParts torso = new Torso(bodyLoader);
        torso.setParentBody(body);
        torso.setRigidName("torso");
        torso.define(world);
        return torso;
    }

    public BodyParts buildFeet() {
        BodyParts feet = new Feet(bodyLoader);
        feet.setParentBody(body);
        feet.setRigidName("feet");
        feet.define(world);
        return feet;
    }

    public BodyParts buildArm(String rigidName) {
        BodyParts arm = new Arm(bodyLoader);
        arm.setParentBody(body);
        arm.setRigidName(rigidName);
        arm.define(world);
        return arm;
    }

    public Body build() {
        return body;
    }
}
