package com.test.web.game.utils.font;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontParameter extends FreeTypeFontParameter {

    public FontParameter() {
        setLinear();
    }

    public FontParameter setLinear() {
        minFilter = Texture.TextureFilter.Linear;
        magFilter = Texture.TextureFilter.Linear;
        return this;
    }

    public FontParameter setSize(int size) {
        this.size = size;
        return this;
    }

    public FontParameter setCharacters(CharType characters) {
        this.characters = characters.getChars();
        return this;
    }

    public FontParameter setCharacters(String chars) {
        this.characters = chars;
        return this;
    }

    public FontParameter setBorderWidthAndColor(float width, Color color) {
        this.borderWidth = width;
        this.borderColor = color;
        return this;
    }

    public enum CharType {
        SYMBOLS("\"!`?'•.,;:()[]{}<>|/@\\\\^$€—%-+=#_&~*’…«»❤°\""),
        NUMBERS("1234567890"),
        LATIN("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
        CYRILLIC("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЄЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэєюяІЇії"),

        LATIN_CYRILLIC(LATIN.chars + CYRILLIC.chars),
        ALL(SYMBOLS.chars + NUMBERS.chars + LATIN.chars + CYRILLIC.chars);

        private final String chars;

        CharType(String chars) {
            this.chars = chars;
        }

        public String getChars() {
            return chars;
        }
    }
}
