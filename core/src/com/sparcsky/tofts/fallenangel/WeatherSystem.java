package com.sparcsky.tofts.fallenangel;

import com.sparcsky.tofts.fallenangel.entity.Sun;

import box2dLight.RayHandler;

public class WeatherSystem {

    private RayHandler rayHandler;
    private float ambientLight;
    private Celestial moon;
    private Sun sun;

    private float stateTimer;

    public WeatherSystem(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
        rayHandler.setShadows(true);

        sun = new Sun(rayHandler);
    //    moon = new Moon(rayHandler);
    }

    private void dayCycle() {

    }

    public void update(float delta) {
        stateTimer += 3 * delta;
        if (stateTimer >= 0.001f) {
            stateTimer = 0;
            sun.update(delta);
     //       moon.update(delta);
        }
    }

}
