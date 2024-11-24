package com.test.web.game.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class SpriteManager {

    private final AssetManager assetManager;

    public final List<AtlasData>   loadableAtlasList    = new ArrayList<>();
    public final List<TextureData> loadableTexturesList = new ArrayList<>();

    public SpriteManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void loadAtlas() {
        for (AtlasData atlasData : loadableAtlasList) {
            assetManager.load(atlasData.path, TextureAtlas.class);
        }
    }

    public void initAtlas() {
        for (AtlasData atlasData : loadableAtlasList) {
            atlasData.atlas = assetManager.get(atlasData.path, TextureAtlas.class);
        }
        loadableAtlasList.clear();
    }

    public void loadTexture() {
        for (TextureData textureData : loadableTexturesList) {
            assetManager.load(textureData.path, Texture.class);
        }
    }

    public void initTexture() {
        for (TextureData textureData : loadableTexturesList) {
            textureData.texture = assetManager.get(textureData.path, Texture.class);
        }
        loadableTexturesList.clear();
    }

    public void initAtlasAndTexture() {
        initAtlas();
        initTexture();
    }

    public enum EnumAtlas {
        LOADER(new AtlasData("atlas/loader.atlas")),
        ALL   (new AtlasData("atlas/all.atlas"));

        public final AtlasData data;

        EnumAtlas(AtlasData data) {
            this.data = data;
        }
    }

    public enum EnumTexture {
        Loader(new TextureData("textures/loader/loader.png"));

        //ALL_BACKGROUND_BLURED(new TextureData("textures/all/background_blured.png"));

        public final TextureData data;

        EnumTexture(TextureData data) {
            this.data = data;
        }
    }

    public static class AtlasData {
        public final String path;
        public TextureAtlas atlas;

        public AtlasData(String path) {
            this.path = path;
        }
    }

    public static class TextureData {
        public final String path;
        public Texture texture;

        public TextureData(String path) {
            this.path = path;
        }
    }
}
