package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.util.TextureSplitter;

public class Diamond extends DynamicEntity {

    public Diamond(Asset asset) {
        Texture texture = asset.get(Asset.IMAGE_DIAMOND);
        TextureRegion[] loadFrames = TextureSplitter.split(texture, 2, 2);
        animation = new Animation<>(0.15f, loadFrames);
        currentFrame = animation.getKeyFrame(stateTime, true);

        width = (currentFrame.getRegionWidth() /  16f) / 2f;
        height = (currentFrame.getRegionHeight() / 16f) / 2f;
    }
}
