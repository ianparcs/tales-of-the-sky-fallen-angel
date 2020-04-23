package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class DynamicEntity extends Entity {

    protected Animation<TextureRegion> animation;
    protected TextureRegion currentFrame;
    protected float stateTime = 0;
    protected boolean flip;

    @Override
    public void update(float delta) {
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime, true);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (currentFrame != null)
            batch.draw(currentFrame, flip ? x + width : x, y, flip ? -width : width, height);
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public int getRegionWidth() {
        return currentFrame.getRegionWidth();
    }

    public int getRegionHeight() {
        return currentFrame.getRegionHeight();
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }
}