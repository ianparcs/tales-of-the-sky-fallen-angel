package com.sparcsky.tofts.fallenangel.util;

import com.badlogic.gdx.math.Vector2;

public abstract class Physics {

    public static float PPT = 16f;
    public static float GRAVITY = -9.81f;
    public static float TIME_STEP = 1f / 60f;

    public static Vector2 calculateOrbit(float currentOrbitDegrees, float distanceFromCenterPoint, Vector2 centerPoint) {
        double radians = (float) java.lang.Math.toRadians(currentOrbitDegrees);
        double x = (Math.cos(radians) * distanceFromCenterPoint) + centerPoint.x;
        double y = (Math.sin(radians) * distanceFromCenterPoint) + centerPoint.y;
        return new Vector2((float) x, (float) y);
    }
}
