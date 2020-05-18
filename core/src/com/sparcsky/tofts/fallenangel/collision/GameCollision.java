package com.sparcsky.tofts.fallenangel.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;


/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class GameCollision implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        PhysicsObject userDataA = (PhysicsObject) contact.getFixtureA().getUserData();
        PhysicsObject userDataB = (PhysicsObject) contact.getFixtureB().getUserData();

        if (userDataA != null && userDataB != null) {
            userDataA.beginCollision(userDataB, contact);
            userDataB.beginCollision(userDataA, contact);
        }
    }

    @Override
    public void endContact(Contact contact) {
        PhysicsObject userDataA = (PhysicsObject) contact.getFixtureA().getUserData();
        PhysicsObject userDataB = (PhysicsObject) contact.getFixtureB().getUserData();

        if (userDataA != null && userDataB != null) {
            userDataA.endCollision(userDataB);
            userDataB.endCollision(userDataA);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        PhysicsObject userDataA = (PhysicsObject) contact.getFixtureA().getUserData();
        PhysicsObject userDataB = (PhysicsObject) contact.getFixtureB().getUserData();
        if (userDataA != null && userDataB != null) {
            userDataA.onCollision(userDataB, contact);
            userDataB.onCollision(userDataA, contact);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
