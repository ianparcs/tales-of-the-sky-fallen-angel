package com.sparcsky.tofts.fallenangel.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureSplitter {

    public static TextureRegion[] split(Texture texture, int rows, int cols) {
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / rows, texture.getHeight() / cols);
        TextureRegion[] walkFrames = new TextureRegion[rows * cols];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        return walkFrames;
    }
}
