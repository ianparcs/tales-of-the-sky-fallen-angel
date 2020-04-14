package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.Color;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Moon extends Celestial {

    public Moon(RayHandler rayHandler) {
        super(rayHandler, 254, 7);
        color = Color.valueOf("#0055a5");
        body = new PointLight(rayHandler, 128, color, 5, 0, 0);

        setOrbitDegrees(360);
        setRaysAlpha(0.65f);
    }

    @Override
    public void setRayColor(Color color) {
        body.setColor(color.r, color.g, color.b, 1);
        rays.setColor(color.r, color.g, color.b, 1);
    }
}
