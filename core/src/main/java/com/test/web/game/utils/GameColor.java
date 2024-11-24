package com.test.web.game.utils;

import com.badlogic.gdx.graphics.Color;

public class GameColor {

    public static final Color background = Color.valueOf("565656");

    // Приватний конструктор, щоб запобігти створенню об'єктів
    private GameColor() {
        throw new UnsupportedOperationException("Utility class");
    }
}
