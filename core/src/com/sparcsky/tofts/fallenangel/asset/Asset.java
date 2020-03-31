package com.sparcsky.tofts.fallenangel.asset;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.sparcsky.tofts.fallenangel.screen.BaseScreen;

public class Asset {

    public static final AssetDescriptor<BitmapFont> fontBit = new AssetDescriptor<>("font/SDS_8x8.ttf", BitmapFont.class);
    public static final AssetDescriptor<Texture> loadDiamond = new AssetDescriptor<>("image/load_diamond.png", Texture.class);
    public static final AssetDescriptor<Texture> libgdxLogo = new AssetDescriptor<>("image/libgdx_logo.png", Texture.class);
    public static final AssetDescriptor<TextureAtlas> tileSetAtlas = new AssetDescriptor<>("map/tileset.atlas", TextureAtlas.class);

    private AssetManager manager;

    public Asset() {
        manager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    public void loadFont(AssetDescriptor<BitmapFont> font, int size) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mySmallFont.fontFileName = font.fileName;
        mySmallFont.fontParameters.size = (Gdx.graphics.getWidth() * size) / BaseScreen.VIRTUAL_WIDTH;
        manager.load(font.fileName, BitmapFont.class, mySmallFont);
    }

    public void load(AssetDescriptor assetDescriptor) {
        manager.load(assetDescriptor);
    }

    public void loadAll() {
        manager.finishLoading();
    }

    public boolean isLoadFinish() {
        return manager.update();
    }

    public float getProgress() {
        return manager.getProgress();
    }

    public <T> T get(AssetDescriptor<T> assetDescriptor) {
        return manager.get(assetDescriptor);
    }

    public void dispose() {
        manager.dispose();
    }

    public void unload(AssetDescriptor<Texture> loadDiamond) {
        manager.unload(loadDiamond.fileName);
    }

    public int getCount() {
        return manager.getLoadedAssets();
    }
}
