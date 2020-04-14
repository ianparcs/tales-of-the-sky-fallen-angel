package com.sparcsky.tofts.fallenangel.system;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.sparcsky.tofts.fallenangel.entity.Celestial;
import com.sparcsky.tofts.fallenangel.entity.Moon;
import com.sparcsky.tofts.fallenangel.entity.Sun;
import com.sparcsky.tofts.fallenangel.util.math.Angle;

import box2dLight.ChainLight;
import box2dLight.RayHandler;

public class DayCycle {

    private float raysDistance;
    private RayHandler rayHandler;
    private Celestial moon;
    private Sun sun;

    private Color dayLightColor;
    private ChainLight chainLight;

    public DayCycle(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
        rayHandler.setShadows(true);
        rayHandler.setAmbientLight(0.80f);

        sun = new Sun(rayHandler);
        moon = new Moon(rayHandler);
        dayLightColor = sun.getColor();

        float[] vertex = new float[]
                {0, 3, 33, 3};
        chainLight = new ChainLight(rayHandler, 128, moon.getColor(), 0, 1, vertex);
        chainLight.setSoftnessLength(15);
    }

    public void update(float delta) {
        if (isDawn()) {
            cycleDawnToDay();
        } else if (isDay()) {
            cycleDayToDusk();
        }

        if (isNight()) {
            makeGlowNight();
            enableXRay(false);
        } else {
            enableXRay(true);
        }

        sun.setRayColor(dayLightColor);
        sun.setRayLightDistance(raysDistance);

        orbitCelestialObject(delta);
    }

    private void cycleDawnToDay() {
        float dayAlpha = (sun.getOrbitDegrees() - 90) / 90f;
        dayLightColor.b = MathUtils.lerp(180f, 150f, dayAlpha);
        dayLightColor.g = MathUtils.lerp(180f, 150f, dayAlpha);
        raysDistance = Math.abs(MathUtils.lerp(500, 0, dayAlpha));

        float ambient = MathUtils.lerp(0.80f, 0.30f, dayAlpha);
        rayHandler.setAmbientLight(ambient);
    }

    private void enableXRay(boolean enable) {
        chainLight.setXray(enable);
    }

    private void cycleDayToDusk() {
        float duskAlpha = (sun.getOrbitDegrees()) / 90f;
        dayLightColor.b = MathUtils.lerp(20f, 180f, duskAlpha);
        dayLightColor.g = MathUtils.lerp(200f, 180f, duskAlpha);
        raysDistance = Math.abs(MathUtils.lerp(0, 500, duskAlpha));
        float ambient = MathUtils.lerp(0.30f, 0.80f, duskAlpha);
        rayHandler.setAmbientLight(ambient);
    }

    private void orbitCelestialObject(float delta) {
        sun.orbit(delta);
        moon.orbit(delta);
    }

    public boolean isNight() {
        return sun.getOrbitDegrees() > Angle.STRAIGHT && sun.getOrbitDegrees() < Angle.FULL;
    }

    private void makeGlowNight() {
        float moonDegrees = moon.getOrbitDegrees();
        float midnightAlpha;
        float glow;

        if (moonDegrees <= Angle.STRAIGHT && moonDegrees >= Angle.RIGHT) {
            midnightAlpha = (moon.getOrbitDegrees() - 90) / 90f;
            glow = MathUtils.lerp(4, 0.5f, midnightAlpha);
        } else {
            midnightAlpha = (moon.getOrbitDegrees()) / 90f;
            glow = MathUtils.lerp(0.5f, 4, midnightAlpha);
        }
        chainLight.setDistance(glow);
    }

    private boolean isDay() {
        return sun.getOrbitDegrees() <= Angle.RIGHT;
    }

    private boolean isDawn() {
        return sun.getOrbitDegrees() <= Angle.STRAIGHT && sun.getOrbitDegrees() >= Angle.RIGHT;
    }


}
