package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Ian Jasper Parcon on 4/13/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class ParallaxCamera extends OrthographicCamera {

    Matrix4 parallaxView = new Matrix4();
    Matrix4 parallaxCombined = new Matrix4();
    Vector3 tmp = new Vector3();
    Vector3 tmp2 = new Vector3();

    public ParallaxCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    public Matrix4 calculateParallaxMatrix(float parallaxX, float parallaxY) {
        update();
        tmp.set(position);
        tmp.x *= parallaxX;
        tmp.y *= parallaxY;

        parallaxView.setToLookAt(tmp, tmp2.set(tmp).add(direction), up);
        parallaxCombined.set(projection);
        Matrix4.mul(parallaxCombined.val, parallaxView.val);
        return parallaxCombined;
    }
}