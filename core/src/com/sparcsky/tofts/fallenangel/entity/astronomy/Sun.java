package com.sparcsky.tofts.fallenangel.entity.astronomy;

import com.badlogic.gdx.graphics.Color;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Sun extends Celestial {

    public Sun(RayHandler rayHandler) {
        super(rayHandler, 1000, 0);
        color = Color.valueOf("#f9d71c");
        body = new PointLight(rayHandler, 50, color, 3, 0, 0);

        setRaySoftnessLength(10);
        setOrbitDegrees(180);
        setRaysAlpha(0.63f);
        setRayColor(color);
    }

    @Override
    public void setRayColor(Color color) {
        rays.setColor(250 / 255f, color.g / 255f, color.b / 255f, raysAlpha);
    }

}
