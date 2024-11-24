package com.test.web.game.utils.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.test.web.game.utils.Utils;

public class FontGenerator extends FreeTypeFontGenerator {

    private final Array<Disposable> disposableSet = new Array<>();

    public FontGenerator(FontPath fontPath) {
        super(Gdx.files.internal(fontPath.path));
    }

    @Override
    public BitmapFont generateFont(FreeTypeFontParameter parameter) {
        BitmapFont font = super.generateFont(parameter);
        disposableSet.add(font);
        return font;
    }

    @Override
    public void dispose() {
        super.dispose();
        Utils.disposeAll(disposableSet);
        disposableSet.clear();
    }

    public enum FontPath {
        InterRegular("Inter-Regular.ttf");

        final String path;

        FontPath(String path) {
            this.path = path;
        }
    }
}
