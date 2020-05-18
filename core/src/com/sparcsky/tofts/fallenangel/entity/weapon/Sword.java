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
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ian Jasper Parcon on 4/23/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class Sword extends Weapon {

    protected HashMap<HitType, List<Animation<TextureRegion>>> hitsAnim;

    public Sword(Asset asset) {
        super();
        TextureAtlas atlas = asset.get(Asset.ATLAS_PLAYER);

        hitsAnim = new HashMap<>();
        hitsAnim.put(HitType.LAND, createGroundAttackAnims(atlas));
        hitsAnim.put(HitType.AIR, createAirAttackAnims(atlas));
    }

    private List<Animation<TextureRegion>> createAirAttackAnims(TextureAtlas atlas) {
        Animation<TextureRegion> airAttack0 = new Animation<>(0.09f, atlas.findRegions("adventurer-air-attack1"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack1 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack2"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack2 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack3-rdy"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack3 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack3"), Animation.PlayMode.NORMAL);
        List<Animation<TextureRegion>> airAnims = new ArrayList<>();
        airAnims.add(airAttack0);
        airAnims.add(airAttack1);
        airAnims.add(airAttack2);
        airAnims.add(airAttack3);
        return airAnims;
    }

    private List<Animation<TextureRegion>> createGroundAttackAnims(TextureAtlas atlas) {
        Animation<TextureRegion> attack0 = new Animation<>(.07f, atlas.findRegions("adventurer-attack1"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack1 = new Animation<>(.07f, atlas.findRegions("adventurer-attack2"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack2 = new Animation<>(.07f, atlas.findRegions("adventurer-attack3"), Animation.PlayMode.NORMAL);
        List<Animation<TextureRegion>> groundAttacks = new ArrayList<>();
        groundAttacks.add(attack0);
        groundAttacks.add(attack1);
        groundAttacks.add(attack2);
        return groundAttacks;
    }

    @Override
    public void define(GameWorld world) {
        Player player = (Player) equipper;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(player.getBody().getPosition().x - player.getWidth() / 4.5f, player.getY() + player.getHeight() / 2f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.7f, 0.25f);

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
    public List<Animation<TextureRegion>> getAttacks() {
        Player player = (Player) equipper;
        HitType type = player.getCurrentState() == StateType.AIR_ATTACK ? HitType.AIR : HitType.LAND;
        return hitsAnim.get(type);
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {
    }

    private enum HitType {
        AIR, LAND
    }
}
