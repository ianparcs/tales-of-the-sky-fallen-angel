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
import com.sparcsky.tofts.fallenangel.entity.monster.Monster;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

/**
 * Created by Ian Jasper Parcon on 4/23/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Sword extends Weapon {

    public Sword(Asset asset) {
        super();

        TextureAtlas atlas = asset.get(Asset.ATLAS_PLAYER);

        Animation<TextureRegion> attack0 = new Animation<>(.07f, atlas.findRegions("adventurer-attack1"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack1 = new Animation<>(.07f, atlas.findRegions("adventurer-attack2"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack2 = new Animation<>(.07f, atlas.findRegions("adventurer-attack3"), Animation.PlayMode.NORMAL);

        attacks.add(attack0);
        attacks.add(attack1);
        attacks.add(attack2);
    }

    @Override
    public void define(GameWorld world) {
        Player player = (Player) equipper;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(player.getBody().getPosition().x - player.getWidth() / 4.5f , player.getY() + player.getHeight() / 2f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;

        body = world.createBody(bodyDef);
        body.setBullet(true);
        body.setUserData(this);

        Fixture fixture = body.createFixture(fixtureDef);
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
