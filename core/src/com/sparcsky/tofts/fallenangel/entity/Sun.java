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

    private static final float MAX_PULSE_RATE = 3;
    private static final float MIN_PULSE_RATE = 2.5f;
    private static final float MAX_SUNLIGHT_DISTANCE = 1500;
    private float sunRayDistance = 0;
    private float sunLightDistance = -10;
    private float sunAlpha = 0.75f;
    private float circleMoveTime;
    private float pulseTimer;
    private float alphaTimer;
    private float alpha;

    private Vector2 controlPoint;
    private PointLight sunLight;
    private PointLight sunCore;

    private Vector2 startPoint;
    private Vector2 endPoint;
    private Color sunColor;

    private boolean pulse = true;

    public Sun(RayHandler rayHandler, float width, float height) {
        this.width = width;
        this.height = height;

        startPoint = new Vector2(-MIN_PULSE_RATE, height / 2);
        endPoint = new Vector2(width * 0.9f, height  * 0.9f);

        controlPoint = new Vector2();
        controlPoint.x = (endPoint.x - startPoint.x) / 2f;
        controlPoint.y = height * 1.5f;

        initDefault();
        sunLight = new PointLight(rayHandler, 500, sunColor, sunLightDistance, startPoint.x, startPoint.y);
        sunCore = new PointLight(rayHandler, 50, sunColor, MIN_PULSE_RATE, startPoint.x, startPoint.y);
    }

    private void initDefault() {
        sunColor = new Color(1, 1, 1, 1);
        sunRayDistance = MIN_PULSE_RATE;
        sunAlpha = 0.51f;
    }

    private void twilighting() {
        sunColor.g = MathUtils.lerp(1.0f, 215f / 255f, alphaTimer * 300);
        sunColor.b = MathUtils.lerp(1.0f, 28f / 255f, alphaTimer * 300);
        sunCore.setColor(sunColor.r, sunColor.g, sunColor.b, 1f);
        sunLight.setColor(sunColor.r, sunColor.g, sunColor.b, sunAlpha);
    }

    private void move() {
        Vector2 m1 = startPoint.lerp(controlPoint, alphaTimer);
        Vector2 m2 = controlPoint.lerp(endPoint, alphaTimer);
        Vector2 m3 = m1.lerp(m2, alphaTimer);
        x = m3.x;
        y = m3.y;

        sunLight.setPosition(x, y);
        sunCore.setPosition(x, y);
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
        if (sunLightDistance <= MAX_SUNLIGHT_DISTANCE) sunLightDistance++;

        pulseTimer += 0.15f * delta;
        if (pulseTimer > 0.035f) {
            pulseTimer = 0;
            if (pulse) {
                sunRayDistance += 0.08f;
                if (sunRayDistance >= MAX_PULSE_RATE) pulse = false;
            }
            if (!pulse) {
                sunRayDistance -= 0.08f;
                if (sunRayDistance <= MIN_PULSE_RATE) pulse = true;
            }
        }
    }

    @Override
    public void update(float delta) {
        float speed = 0.00005f;
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
        sunCore.setDistance(sunRayDistance);
        sunLight.setDistance(sunLightDistance);
    }

    private void handleInput() {
      /*  if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            ambientAlpha += 0.0005f;
            if (ambientAlpha >= 1.0f) ambientAlpha = 0;
        }*/
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            sunAlpha += 0.001f;
            if (sunAlpha >= 1.0f) sunAlpha = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) sunLightDistance++;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) sunLightDistance--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) printData();
    }

    private void printData() {
        /*System.out.println("ambientAlpha = " + ambientAlpha);*/
        System.out.println("sunRayDistance = " + sunLightDistance);
        System.out.println("sunAlpha = " + sunAlpha);
        //System.out.println(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) / 1024f);
    }

}
