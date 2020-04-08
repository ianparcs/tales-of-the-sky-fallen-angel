package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Sun extends Entity {

    private static final float MAX_PULSE_RATE = 100;
    private static final float MIN_PULSE_RATE = 70;
    private float ambientAlpha = 0.15f;
    private float sunRayDistance = 0;
    private float sunAlpha = 0.75f;
    private float circleMoveTime;
    private float pulseTimer;
    private float alphaTimer;
    private float alpha;

    private Vector2 controlPoint;
    private PointLight sunLight;
    private Vector2 startPoint;
    private Vector2 endPoint;
    private Color sunColor;

    private boolean pulse = true;

    public Sun(RayHandler rayHandler, float width, float height) {
        this.width = width;
        this.height = height;

        startPoint = new Vector2(-width / 3, height / 2);
        endPoint = new Vector2(width, height);

        controlPoint = new Vector2();
        controlPoint.x = (endPoint.x - startPoint.x) / 2f;
        controlPoint.y = height * 2;

        initDefault();
        sunLight = new PointLight(rayHandler, 500, sunColor, sunRayDistance, startPoint.x, startPoint.y);
    }

    private void initDefault() {
        sunColor = new Color(1, 1, 1, 1);
        sunRayDistance = 0;
        ambientAlpha = 0.15f;
        sunAlpha = 0.7f;
    }

    private void twilighting() {
        sunColor.g = MathUtils.lerp(1.0f, 0.686f, alphaTimer * 200);
        sunColor.b = MathUtils.lerp(1.0f, 0.0784f, alphaTimer * 200);
        sunLight.setColor(sunColor.r, sunColor.g, sunColor.b, sunAlpha);
    }

    private void move() {
        Vector2 m1 = startPoint.lerp(controlPoint, alphaTimer);
        Vector2 m2 = controlPoint.lerp(endPoint, alphaTimer);
        Vector2 m3 = m1.lerp(m2, alphaTimer);
        x = m3.x;
        y = m3.y;

        sunLight.setPosition(x, y);
    }

    private void move(float delta) {
        circleMoveTime += delta;
        if (circleMoveTime >= 0.1f) {
            circleMoveTime = 0;

            if (alpha >= 360) alpha = 0;
            alpha++;
            Vector2 orbit = Physics.calculateOrbit(alpha, width, new Vector2(width / 2f, 0));
            sunLight.setPosition(orbit.x, orbit.y);

        }
    }

    private void pulsate(float delta) {
        pulseTimer += 0.5 * delta;
        if (pulseTimer > 0.035f) {
            pulseTimer = 0;
            if (pulse) {
                sunRayDistance++;
                if (sunRayDistance >= MAX_PULSE_RATE) pulse = false;
            }
            if (!pulse) {
                sunRayDistance--;
                if (sunRayDistance <= MIN_PULSE_RATE) pulse = true;
            }
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            ambientAlpha += 0.0001f;
            if (ambientAlpha >= 1.0f) ambientAlpha = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            sunAlpha += 0.001f;
            if (sunAlpha >= 1.0f) sunAlpha = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) sunRayDistance++;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) sunRayDistance--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) printData();
    }

    private void printData() {
        System.out.println("ambientAlpha = " + ambientAlpha);
        System.out.println("sunRayDistance = " + sunRayDistance);
        System.out.println("sunAlpha = " + sunAlpha);
        //System.out.println(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) / 1024f);
    }


    @Override
    public void update(float delta) {
        float speed = 0.0001f;
        alphaTimer += speed * delta;
        if (alphaTimer <= 1) {
            move();
            twilighting();
            pulsate(delta);
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        sunLight.setDistance(sunRayDistance);
    }


}
