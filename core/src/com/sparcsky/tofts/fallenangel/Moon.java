package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.graphics.Color;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Moon extends Celestial {

    public Moon(RayHandler rayHandler) {
        super(rayHandler, 100,0);
        color = Color.valueOf("#0055a5");

        body = new PointLight(rayHandler, 3, color, 0, GameWorld.WIDTH * 15, 0);
        cycleDegrees = 360;
        raysAlpha = 0.55f;
    }

    @Override
    protected void twilighting(float delta) {

    }
}
