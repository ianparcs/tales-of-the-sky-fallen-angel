package com.sparcsky.tofts.fallenangel.asset;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Asset {

    public static final AssetDescriptor<Texture> IMAGE_DIAMOND = new AssetDescriptor<>("image/load_diamond.png", Texture.class);
    public static final AssetDescriptor<Texture> IMAGE_LIBGDX_LOGO = new AssetDescriptor<>("image/libgdx_logo.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> ATLAS_PLAYER = new AssetDescriptor<>("image/player/player.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> ATLAS_UI = new AssetDescriptor<>("ui/uiskin.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Skin> SKIN_UI = new AssetDescriptor<>("ui/uiskin.json", Skin.class, new SkinLoader.SkinParameter("ui/uiskin.atlas"));

    public static final AssetDescriptor<BitmapFont> FONT_ADVENTURER = new AssetDescriptor<>("ui/font/Adventurer.fnt", BitmapFont.class);
    public static final AssetDescriptor<BitmapFont> FONT_TITLE = new AssetDescriptor<>("ui/font/Adventurer_title.fnt", BitmapFont.class);

    public static final AssetDescriptor<Music> SOUND_WOODLAND_FANTASY = new AssetDescriptor<>("sound/Woodland_Fantasy.mp3", Music.class);
    public static final AssetDescriptor<Sound> SOUND_KEYBOARD_TYPE = new AssetDescriptor<>("sound/keyboard-1.wav", Sound.class);

    public static final AssetDescriptor<TiledMap> TMX_BACKGROUND = new AssetDescriptor<>("map/menu.tmx", TiledMap.class);
    public static final AssetDescriptor<TextureAtlas> TMX_PARALLAX = new AssetDescriptor<>("map/parallax.atlas", TextureAtlas.class);

    public static final AssetDescriptor<ParticleEffect> DATA_PARTICLE = new AssetDescriptor<>("data/particles.pt", ParticleEffect.class);
    public static final AssetDescriptor<Texture> IMAGE_PARTICLE = new AssetDescriptor<>("data/load_diamond.png", Texture.class);

    private AssetManager manager;

    public Asset() {
        manager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(ParticleEffect.class, new ParticleEffectLoader(resolver));
        manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
        manager.setLoader(Texture.class, new TextureLoader(resolver));
        manager.setLoader(TiledMap.class, new TmxMapLoader(resolver));
        manager.setLoader(Sound.class, new SoundLoader(resolver));
        manager.setLoader(Music.class, new MusicLoader(resolver));
    }

    public void initLoadScreenAsset() {
        manager.load(Asset.IMAGE_DIAMOND);
        manager.load(Asset.FONT_ADVENTURER);
        loadFinish();
    }

    public void loadAllResources() {
        manager.load(Asset.SOUND_WOODLAND_FANTASY);
        manager.load(Asset.SOUND_KEYBOARD_TYPE);
        manager.load(Asset.IMAGE_LIBGDX_LOGO);
        manager.load(Asset.IMAGE_PARTICLE);
        manager.load(Asset.TMX_BACKGROUND);
        manager.load(Asset.DATA_PARTICLE);
        manager.load(Asset.ATLAS_PLAYER);
        manager.load(Asset.TMX_PARALLAX);
        manager.load(Asset.FONT_TITLE);
        manager.load(Asset.ATLAS_UI);
        manager.load(Asset.SKIN_UI);
    }

    public void setFontUseIntegerPositions() {
        BitmapFont titleFont = manager.get(Asset.FONT_TITLE);
        BitmapFont mainFont = manager.get(Asset.FONT_ADVENTURER);
        titleFont.setUseIntegerPositions(false);
        mainFont.setUseIntegerPositions(false);
    }

    public void loadFinish() {
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

}
