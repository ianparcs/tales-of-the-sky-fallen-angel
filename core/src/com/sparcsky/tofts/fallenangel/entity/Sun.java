package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Sun extends Celestial {

    public Sun(RayHandler rayHandler) {
        super(rayHandler, 500, 0);
        color = Color.valueOf("#f9d71c");
        body = new PointLight(rayHandler, 50, color, 3, 0, 0);

        setRaySoftnessLength(15);
        setOrbitDegrees(180);
        setRaysAlpha(0.6f);
        setRayColor(color);
    }

    @Override
    public void setRayColor(Color color) {
        rays.setColor(250 / 255f, color.g / 255f, color.b / 255f, raysAlpha);
    }

}
