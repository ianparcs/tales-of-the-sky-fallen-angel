package com.sparcsky.tofts.fallenangel.entity.player.parts;

import com.badlogic.gdx.physics.box2d.Body;
import com.sparcsky.tofts.fallenangel.util.physics.BodyEditorLoader;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/28/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class BodyParts implements PhysicsObject {

    protected BodyEditorLoader bodyLoader;
    protected String rigidName;
    protected Body parentBody;

    public BodyParts(BodyEditorLoader bodyLoader) {
        this.bodyLoader = bodyLoader;
    }

    public void setRigidName(String rigidName) {
        this.rigidName = rigidName;
    }

    public void setParentBody(Body parentBody) {
        this.parentBody = parentBody;
    }

}
