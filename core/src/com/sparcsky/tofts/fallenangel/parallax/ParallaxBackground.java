package com.sparcsky.tofts.fallenangel.parallax;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxBackground {

    /**
     * @param layers The  background layers
     * @param width  The screenWith
     * @param height The screenHeight
     * @param speed  A Vector2 attribute to point out the x and y speed
     */

    private ParallaxLayer[] layers;
    private OrthographicCamera camera;
    private Vector2 speed = new Vector2();

    public ParallaxBackground(ParallaxLayer[] layers, float width, float height, Vector2 speed) {
        this.layers = layers;
        this.speed.set(speed);
        camera = new OrthographicCamera(width, height);
    }

    public void render(SpriteBatch batch, float delta) {
        this.camera.position.add(speed.x * delta, speed.y * delta, 0);

        batch.setProjectionMatrix(camera.projection);
        batch.begin();
        for (ParallaxLayer layer : layers) {
            float currentX = -camera.position.x * layer.parallaxRatio.x % (layer.region.getRegionWidth() + layer.padding.x);

            if (speed.x < 0) currentX += -(layer.region.getRegionWidth() + layer.padding.x);
            do {
                float currentY = -camera.position.y * layer.parallaxRatio.y % (layer.region.getRegionHeight() + layer.padding.y);
                if (speed.y < 0) currentY += -(layer.region.getRegionHeight() + layer.padding.y);
                do {
                    batch.draw(layer.region,
                            -this.camera.viewportWidth / 2 + currentX + layer.startPosition.x,
                            -this.camera.viewportHeight / 2 + currentY + layer.startPosition.y);
                    currentY += (layer.region.getRegionHeight() + layer.padding.y);
                } while (currentY < camera.viewportHeight);
                currentX += (layer.region.getRegionWidth() + layer.padding.x);
            } while (currentX < camera.viewportWidth);
        }
        batch.end();
    }
}