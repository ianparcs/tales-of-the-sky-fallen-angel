package com.sparcsky.tofts.fallenangel.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class ParticleSystem implements Disposable {

    private Array<ParticleEffectPool.PooledEffect> effects;
    private OrthographicCamera camera;
    private float delta;

    public ParticleSystem(ParticleEffectPool particleEffectPool) {
        effects = new Array<>();
        effects.add(particleEffectPool.obtain());
    }

    public void render(SpriteBatch batch) {
        float delta = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(batch, delta);
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
        batch.end();
    }

    private void reset() {
        for (int i = effects.size - 1; i >= 0; i--)
            effects.get(i).free(); //free all the effects back to the pool
        effects.clear(); //clear the current effects array
    }

    public void setCamera(Camera camera) {
        this.camera = (OrthographicCamera) camera;
    }

    @Override
    public void dispose() {
        reset();

    }
}
