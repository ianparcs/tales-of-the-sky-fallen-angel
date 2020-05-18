package com.sparcsky.tofts.fallenangel.util.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.sparcsky.tofts.fallenangel.game.GameWorld;

/**
 * Created by Ian Jasper Parcon on 4/16/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public interface PhysicsObject {

    void define(GameWorld world);

    void beginCollision(PhysicsObject physicsObject, Contact contact);

    void onCollision(PhysicsObject physicsObject, Contact contact);

    void endCollision(PhysicsObject physicsObject);
}
