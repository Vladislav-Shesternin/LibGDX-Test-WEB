package com.test.web.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Utils {

    // Extension-like properties
    public static TextureRegion getRegion(Texture texture) {
        return new TextureRegion(texture);
    }

    public static long toMS(float value) {
        return (long) (value * 1000);
    }

    public static Vector2 getZeroScreenVector(Viewport viewport) {
        return viewport.project(new Vector2(0f, 0f));
    }

    public static Texture getTextureEmpty() {
        return new Texture(1, 1, Pixmap.Format.Alpha);
    }

    // Utility methods
    public static void disposeAll(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }

    public static void disposeAll(Iterable<? extends Disposable> disposables) {
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }

    public static long currentTimeMinus(long time) {
        return System.currentTimeMillis() - time;
    }

    public static void log(String message) {
        Gdx.app.log("TestWEB_LOG", message);
    }

    public static void addProcessors(InputMultiplexer inputMultiplexer, InputProcessor... processors) {
        for (InputProcessor processor : processors) {
            inputMultiplexer.addProcessor(processor);
        }
    }

    public static void runGDX(Runnable block) {
        Gdx.app.postRunnable(block);
    }

    public static float divOr0(float value, float divisor) {
        return divisor != 0f ? value / divisor : 0f;
    }

    public static Vector2 divOr0(Vector2 vector, float scalar) {
        vector.x = divOr0(vector.x, scalar);
        vector.y = divOr0(vector.y, scalar);
        return vector;
    }

    public static Vector2 divOr0(Vector2 vector, Vector2 scalar) {
        vector.x = divOr0(vector.x, scalar.x);
        vector.y = divOr0(vector.y, scalar.y);
        return vector;
    }

    private static final StringBuilder stringBuilder = new StringBuilder();

    public static String toBalanceFormat(Number number) {
        stringBuilder.setLength(0); // Clear the StringBuilder
        stringBuilder.append(number.toString());

        switch (stringBuilder.length()) {
            case 4:
            stringBuilder.insert(1, ' ');
            break;
            case 5:
            stringBuilder.insert(2, ' ');
            break;
            case 6:
            stringBuilder.insert(3, ' ');
            break;
            case 7:
            stringBuilder.insert(1, ' ').insert(5, ' ');
            break;
        }

        return stringBuilder.toString();
    }
}
