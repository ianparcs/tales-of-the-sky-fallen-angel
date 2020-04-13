package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DynamicEntity extends Entity {

    Animation<TextureRegion> anim;
    TextureRegion currentFrame;

    float stateTime = 0;

    @Override
    public void update(float delta) {
        stateTime += delta;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, x, y, width, height);
    }
}
