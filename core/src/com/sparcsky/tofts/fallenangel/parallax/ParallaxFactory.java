package com.sparcsky.tofts.fallenangel.parallax;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public class ParallaxFactory {

    public static ParallaxBackground createLandscape(Asset asset, int worldWidth, int worldHeight) {
        TextureAtlas atlas = asset.get(Asset.TMX_PARALLAX);
        ParallaxLayer sky = new ParallaxLayer(atlas.findRegion("sky"),
                new Vector2(0, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, worldHeight));

        ParallaxLayer clouds = new ParallaxLayer(atlas.findRegion("clouds"),
                new Vector2(1, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, worldHeight));

        ParallaxLayer sea = new ParallaxLayer(atlas.findRegion("sea"),
                new Vector2(2, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, worldHeight));

        return new ParallaxBackground(new ParallaxLayer[]{sky, clouds, sea},
                worldWidth, worldHeight, new Vector2(10, 0));
    }

    public static ParallaxBackground createGround(Asset asset, int worldWidth, int worldHeight) {
        TextureAtlas atlas = asset.get(Asset.TMX_PARALLAX);
        ParallaxLayer fargrounds = new ParallaxLayer(atlas.findRegion("ground"),
                new Vector2(5, 1.0f),
                new Vector2(0, 0),
                new Vector2(0, worldHeight * 3));

        return new ParallaxBackground(new ParallaxLayer[]{fargrounds},
                worldWidth, worldHeight, new Vector2(20, 0));
    }


}
