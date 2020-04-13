package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.sparcsky.tofts.fallenangel.Celestial;
import com.sparcsky.tofts.fallenangel.util.Physics;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Sun extends Celestial {

    public Sun(RayHandler rayHandler) {
        super(rayHandler, 500, 500);
        color = Color.valueOf("#f9d71c");
        cycleDegrees = 180;
        body = new PointLight(rayHandler, 50, color, 3, 0, 0);
        rays.setColor(color);
        rays.setSoftnessLength(25);
        raysAlpha = 0.65f;
    }

    @Override
    protected void twilighting(float delta) {

        float halfCycle = (cycleDegrees) / 90f;
        float alpha = MathUtils.lerp(0f, 1f, halfCycle);
        float toDawn = MathUtils.lerp(190f, 215f, alpha);
        color.g = MathUtils.lerp(toDawn / 255f, 1f, alpha);
        color.b = MathUtils.lerp(0.11f, 1f, alpha);

        body.setColor(color.r, color.g, color.b, 1);
        rays.setColor(color.r, color.g, color.b, 0.5f);

        if (cycleDegrees <= Physics.FULL_CIRCLE && cycleDegrees >= Physics.REFLEX_ANGLE){
            rayHandler.setAmbientLight(0.15f);
            return;
        }

        float distance = MathUtils.lerp(0, raysLightDistance, halfCycle);
        float ambient = MathUtils.lerp(0,0.75f,halfCycle);
        rays.setDistance(distance);
        rayHandler.setAmbientLight(ambient);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) raysLightDistance += 3;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) raysLightDistance -= 3;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) printData();
    }

    private void printData() {
     /*   System.out.println("sunRayDistance = " + coreLightDistance);
        System.out.println("sunAlpha = " + sunAlpha);*/
        //System.out.println(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) / 1024f);
    }

}
