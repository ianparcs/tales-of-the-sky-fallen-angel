package com.sparcsky.tofts.fallenangel.entity.weapon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/25/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class BrassKnuckle extends Weapon {

    public BrassKnuckle(Asset asset) {
        super();
        TextureAtlas atlas = asset.get(Asset.ATLAS_PLAYER);

        Animation<TextureRegion> attack0 = new Animation<>(.07f, atlas.findRegions("adventurer-punch"), Animation.PlayMode.NORMAL);
        attacks.add(attack0);
    }

    @Override
    public void define(GameWorld world) {
        Player player = (Player) equipper;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(player.getBody().getPosition().x - player.getWidth() / 4.5f, player.getY() + player.getHeight() / 2f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        body = world.createBody(bodyDef);
        body.setBullet(true);
        body.setUserData(this);

        Fixture fixture =  body.createFixture(fixtureDef);
        fixture.setUserData(this);

        shape.dispose();
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject) {

    }


    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }

}
