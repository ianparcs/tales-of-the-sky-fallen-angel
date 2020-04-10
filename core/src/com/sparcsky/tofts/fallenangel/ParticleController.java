package com.sparcsky.tofts.fallenangel;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.sparcsky.tofts.fallenangel.util.Graphics;
import com.sparcsky.tofts.fallenangel.util.Updatable;

public class ParticleController implements Updatable, Graphics, Disposable {

    private Array<ParticleEffectPool.PooledEffect> effects;
    private OrthographicCamera camera;
    private float delta;

    public ParticleController(ParticleEffectPool particleEffectPool) {
        effects = new Array<>();
        effects.add(particleEffectPool.obtain());
    }

    @Override
    public void update(float delta) {
        this.delta = delta;
    }

    @Override
    public void render(SpriteBatch batch) {
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
