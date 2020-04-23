package com.sparcsky.tofts.fallenangel.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;


/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class GameCollision implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA() != null && contact.getFixtureB() != null) {
            Object userDataA = contact.getFixtureA().getUserData();
            Object userDataB = contact.getFixtureB().getUserData();

            if (userDataA.equals("wall")) {
                if (userDataB.equals("right_arm") || userDataB.equals("left_arm")) {
                    Player player = (Player) contact.getFixtureB().getBody().getUserData();
                    player.changeState(StateType.SLIDE);
                }
            }

            if (userDataA.equals("ground") && userDataB.equals("feet")) {
                Player player = (Player) contact.getFixtureB().getBody().getUserData();
                if (player.getCurrentState() != StateType.AIR_ATTACK && player.getCurrentState() != StateType.RUN)
                    player.changeState(StateType.IDLE);
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        if (contact.getFixtureA() != null && contact.getFixtureB() != null) {
            Object userDataA = contact.getFixtureA().getUserData();
            Object userDataB = contact.getFixtureB().getUserData();

            if (userDataA.equals("ground") && userDataB.equals("feet")) {
                Player player = (Player) contact.getFixtureB().getBody().getUserData();
                if (player.getCurrentState() != StateType.JUMP)
                    player.changeState(StateType.FALL);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
