package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.GameWorld;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public abstract class Celestial {

    private static final float MIN_PULSE_RATE = 3;
    private static final float MAX_PULSE_RATE = 5;
    private final float SPEED = 34;
    private float orbitDegrees;
    protected PointLight body;
    protected PointLight rays;
    protected Color color;
    protected float raysAlpha;
    private float bodyLightDistance = MIN_PULSE_RATE;
    private float pulseTimer;
    private Vector2 orbit;
    private boolean pulse;

    public Celestial(RayHandler rayHandler, int rayNum, int distance) {
        rays = new PointLight(rayHandler, rayNum);
        setRayLightDistance(distance);
    }

    public void orbit(float delta) {
        if (orbitDegrees < 0) orbitDegrees = 360;
        orbitDegrees -= SPEED * delta;
        orbit = Physics.calculateOrbit(orbitDegrees, GameWorld.WIDTH / 1.6f, new Vector2(GameWorld.WIDTH / 2f, 3));
        rays.setPosition(orbit.x, orbit.y);
        body.setPosition(orbit.x, orbit.y);
    }

    private void pulsate(float delta) {
        pulseTimer += 0.15f * delta;
        if (pulseTimer > 0.035f) {
            pulseTimer = 0;
            if (pulse) {
                bodyLightDistance += 0.08f;
                if (bodyLightDistance >= MAX_PULSE_RATE) pulse = false;
            }
            if (!pulse) {
                bodyLightDistance -= 0.08f;
                if (bodyLightDistance <= MIN_PULSE_RATE) pulse = true;
            }
        }
        body.setDistance(bodyLightDistance);
    }


    public abstract void setRayColor(Color color);

    public void setRaySoftnessLength(int softnessLength) {
        rays.setSoftnessLength(softnessLength);
    }

    public void setRaysAlpha(float raysAlpha) {
        this.raysAlpha = raysAlpha;
    }

    public void setRayLightDistance(float raysDistance) {
        rays.setDistance(raysDistance);
    }

    public float getOrbitDegrees() {
        return orbitDegrees;
    }

    public void setOrbitDegrees(float orbitDegrees) {
        this.orbitDegrees = orbitDegrees;
    }

    public  Color getColor(){
        return color;
    }
}
