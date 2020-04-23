package com.sparcsky.tofts.fallenangel.util.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sparcsky.tofts.fallenangel.collision.Category;

/**
 * Created by Ian Jasper Parcon on 4/21/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */

public class Box2DPartsBuilder {

    public static Body build(World world, float x, float y, float width, float height) {
        BodyDef torsoBdef = new BodyDef();
        torsoBdef.position.set(x, y);
        torsoBdef.type = BodyDef.BodyType.DynamicBody;
        torsoBdef.gravityScale = 4.5f;


        Body body = world.createBody(torsoBdef);

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/bodies"));

        Fixture leftArm = loader.attachFixture(body, "left_arm", new FixtureDef(), 1);
        leftArm.setUserData("left_arm");
        leftArm.setFriction(0);

        Fixture rightArm = loader.attachFixture(body, "right_arm", new FixtureDef(), 1);
        rightArm.setUserData("right_arm");
        rightArm.setFriction(0);

        Fixture feet = loader.attachFixture(body, "feet", new FixtureDef(), 1);
        feet.setUserData("feet");

        Fixture torso = loader.attachFixture(body, "torso", new FixtureDef(), 1);
        torso.setFriction(0);
        torso.setUserData("torso");

        Filter filter = new Filter();
        filter.categoryBits =Category.ARM;
        filter.maskBits = Category.GROUND;

        rightArm.setFilterData(filter);
        leftArm.setFilterData(filter);
        feet.getFilterData().categoryBits = Category.FEET;
        feet.getFilterData().maskBits = Category.GROUND;

        return body;
    }


}
