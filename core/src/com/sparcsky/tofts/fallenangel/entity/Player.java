package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sparcsky.tofts.fallenangel.util.Physics;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public class Player extends DynamicEntity {

    private Body body;

    public Player(Asset asset) {
        TextureAtlas atlas = asset.get(Asset.ATLAS_PLAYER);
        anim = new Animation<>(0.1f, atlas.findRegions("adventurer-run3"));
        currentFrame = anim.getKeyFrame(stateTime, true);

        width = currentFrame.getRegionWidth();
        height = currentFrame.getRegionHeight();
    }

    public void define(World world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set((x + width * 0.5f) / Physics.PPT, (y + height * 0.5f) / Physics.PPT);
        bdef.type = BodyDef.BodyType.StaticBody;

  /*      PolygonShape shape = new PolygonShape();
        shape.setAsBox((width * 0.5f) / Physics.PPT, (height * 0.5f) / Physics.PPT);*/

        CircleShape shape = new CircleShape();
        shape.setRadius((((width / Physics.PPT) * 0.5f) + (height / Physics.PPT) * 0.5f) / 2);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        body = world.createBody(bdef);
        body.setUserData(this);
        body.createFixture(fdef);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        width = width / Physics.PPT;
        height = height / Physics.PPT;
        x = body.getPosition().x - (width / 2);
        y = body.getPosition().y - (height / 2);
    }

}
