package com.test.web.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ShapeDrawerUtil implements Disposable {

    private final Array<Disposable> arrDisposable = new Array<>();
    final ShapeDrawer drawer;

    public ShapeDrawerUtil(Batch batch) {
        this.drawer = new ShapeDrawer(batch, getRegion(Color.WHITE));
    }

    @Override
    public void dispose() {
        Utils.disposeAll(arrDisposable);
        arrDisposable.clear();
    }

    public void update() {
        drawer.update();
    }

    public TextureRegion getRegion(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.drawPixel(0, 0);

        Texture texture = new Texture(pixmap);
        arrDisposable.add(texture);

        pixmap.dispose();
        return new TextureRegion(texture, 0, 0, 1, 1);
    }

    public Texture getTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.drawPixel(0, 0);

        Texture texture = new Texture(pixmap);
        arrDisposable.add(texture);

        pixmap.dispose();
        return texture;
    }

}
