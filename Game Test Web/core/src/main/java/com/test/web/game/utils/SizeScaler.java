package com.test.web.game.utils;

import com.badlogic.gdx.math.Vector2;

public class SizeScaler {

    private Axis axis;
    private float originalSize;

    private float scale = 1f;
    private float newAxisSize = 0f;

    public SizeScaler(Axis axis, float originalSize) {
        this.axis = axis;
        this.originalSize = originalSize;
    }

    public void updateScaler(Axis axis, float originalSize) {
        this.axis = axis;
        this.originalSize = originalSize;
    }

    public void calculateScale(Vector2 newSize) {
        switch (axis) {
            case X:
                newAxisSize = newSize.x;
                break;
            case Y:
                newAxisSize = newSize.y;
                break;
        }
        scale = Utils.divOr0(originalSize, newAxisSize);
    }

    public Vector2 scaled(Vector2 size) {
        return Utils.divOr0(size, scale);
    }

    public Vector2 scaledInverse(Vector2 size) {
        return size.scl(scale);
    }

    public float scaled(float size) {
        return Utils.divOr0(size, scale);
    }

    public float scaledInverse(float size) {
        return size * scale;
    }

    public enum Axis {
        X, Y
    }
}
