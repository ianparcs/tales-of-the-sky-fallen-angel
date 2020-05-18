package com.sparcsky.tofts.fallenangel.entity.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.DynamicEntity;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.Physics;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/20/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class StaticPlayer extends DynamicEntity implements PhysicsObject {

    private Body body;

    public StaticPlayer(Asset asset) {
        TextureAtlas atlas = asset.get(Asset.ATLAS_PLAYER);
        animation = new Animation<>(0.09f, atlas.findRegions("adventurer-run3"), Animation.PlayMode.LOOP);

        currentFrame = animation.getKeyFrame(stateTime);
        width = getRegionWidth() / Physics.PPT;
        height = getRegionHeight() / Physics.PPT;
    }

    @Override
    public void define(GameWorld world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(((width * 0.5f)) + x, ((height * 0.5f)) + y);
        bdef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width * 0.5f) / 2f, (height * 0.5f) / 1.2f);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.friction = 0;

        body = world.createBody(bdef);
        body.setUserData(this);
        body.createFixture(fdef);
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
}
