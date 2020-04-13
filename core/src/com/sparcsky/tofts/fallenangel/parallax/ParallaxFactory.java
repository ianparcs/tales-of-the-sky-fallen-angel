package com.sparcsky.tofts.fallenangel.parallax;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.GameWorld;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public class ParallaxFactory {
    private static final float scaledUpWidth = (GameWorld.WIDTH * 16) * 2;
    private static final float scaledUpHeight = (GameWorld.HEIGHT * 16) * 2;

    public static ParallaxBackground createLandscape(Asset asset) {
        TextureAtlas atlas = asset.get(Asset.TMX_PARALLAX);
        ParallaxLayer sky = new ParallaxLayer(atlas.findRegion("sky"),
                new Vector2(0, 1.0f),
                new Vector2(0, 0),
                new Vector2(0,scaledUpHeight));

        ParallaxLayer clouds = new ParallaxLayer(atlas.findRegion("clouds"),
                new Vector2(1, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, scaledUpHeight));

        ParallaxLayer sea = new ParallaxLayer(atlas.findRegion("sea"),
                new Vector2(2, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, scaledUpHeight / 2f));

        return new ParallaxBackground(new ParallaxLayer[]{sky, clouds, sea},
                scaledUpWidth / 2f, scaledUpHeight  /2 , new Vector2(20, 0));
    }

    public static ParallaxBackground createGround(Asset asset) {
        TextureAtlas atlas = asset.get(Asset.TMX_PARALLAX);
        ParallaxLayer fargrounds = new ParallaxLayer(atlas.findRegion("ground"),
                new Vector2(5, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, scaledUpHeight));

        return new ParallaxBackground(new ParallaxLayer[]{fargrounds},
                scaledUpWidth, scaledUpHeight, new Vector2(20, 0));
    }


}
