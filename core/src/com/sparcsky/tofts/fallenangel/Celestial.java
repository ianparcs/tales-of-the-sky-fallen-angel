package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public abstract class Celestial {

    private static final float MIN_PULSE_RATE = 3;
    private static final float MAX_PULSE_RATE = 5;
    protected float raysLightDistance;
    protected float cycleDegrees;
    protected PointLight body;
    protected PointLight rays;
    protected Color color;
    protected float raysAlpha;
    protected RayHandler rayHandler;
    protected float bodyLightDistance = MIN_PULSE_RATE;
    private float pulseTimer;
    private float width;
    private float height;
    private Vector2 orbit;
    private boolean pulse;

    public Celestial(RayHandler rayHandler, int rayNum, int distance) {
        rays = new PointLight(rayHandler, rayNum);
        raysLightDistance = distance;

        width = GameWorld.WIDTH;
        height = GameWorld.HEIGHT;
        this.rayHandler = rayHandler;
    }

    private void orbit(float delta) {
        if (cycleDegrees < 0) cycleDegrees = 360;
        cycleDegrees -= 15f * delta;
        orbit = Physics.calculateOrbit(cycleDegrees, width / 1.6f, new Vector2(width / 2f, 0));
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
    }

    protected abstract void twilighting(float delta);

    protected void update(float delta) {
        orbit(delta);
        twilighting(delta);
        pulsate(delta);

        plotPosition();
    }

    private void plotPosition() {
        rays.setPosition(orbit.x, orbit.y);
        rays.setDistance(raysLightDistance);
        body.setPosition(orbit.x, orbit.y);
        body.setDistance(bodyLightDistance);
    }
}
