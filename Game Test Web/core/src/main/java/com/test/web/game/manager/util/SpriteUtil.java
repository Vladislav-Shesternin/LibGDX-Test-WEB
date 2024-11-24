package com.test.web.game.manager.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.test.web.game.manager.SpriteManager;

public class SpriteUtil {

    public static class Loader {

        public final TextureRegion logo;

        public final Texture LOADER;

        public Loader() {
            logo = getRegion("logo");

            LOADER = SpriteManager.EnumTexture.Loader.data.texture;
        }

        private TextureRegion getRegion(String name) {
            return SpriteManager.EnumAtlas.LOADER.data.atlas.findRegion(name);
        }
    }

    public static class All {

        public final TextureRegion hamster;

        //public final Texture LOADER;

        public All() {
            hamster = getRegion("ham");

            //LOADER = SpriteManager.EnumTexture.Loader.data.texture;
        }

        private TextureRegion getRegion(String name) {
            return SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name);
        }
    }
}
