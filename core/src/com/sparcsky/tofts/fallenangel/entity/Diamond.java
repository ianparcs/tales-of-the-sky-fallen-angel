package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.util.TextureSplitter;

public class Diamond extends Entity {

    private Animation<TextureRegion> loadAnim;
    private TextureRegion loadFrame;
    private float stateTime = 0;

    public Diamond(Asset asset) {
        Texture texture = asset.get(Asset.loadDiamond);
        TextureRegion[] loadFrames = TextureSplitter.split(texture, 2, 2);
        loadAnim = new Animation<>(0.15f, loadFrames);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        loadFrame = loadAnim.getKeyFrame(stateTime, true);

        width = loadFrame.getRegionWidth() * 2f;
        height = loadFrame.getRegionHeight() * 1.5f;
        x = (Gdx.graphics.getWidth() / 2f) - (width / 2f);
        y = (Gdx.graphics.getHeight() / 2f) + (height / 2f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(loadFrame, x, y, width, height);
    }

}
