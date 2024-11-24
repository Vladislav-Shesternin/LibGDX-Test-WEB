package com.test.web.game.utils.advanced;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class AdvancedGame implements ApplicationListener {

    private AdvancedScreen screen;

    // ---------------------------------------------------
    // Override
    // ---------------------------------------------------

    @Override
    public void create() { }

    @Override
    public void render() {
        if (screen != null) {
            screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void resize(int width, int height) {
        if (screen != null) {
            screen.resize(width, height);
        }
    }

    @Override
    public void pause() {
        if (screen != null) {
            screen.pause();
        }
    }

    @Override
    public void resume() {
        if (screen != null) {
            screen.resume();
        }
    }

    @Override
    public void dispose() {
        if (screen != null) {
            screen.dispose();
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    public void updateScreen(AdvancedScreen newScreen) {
        if (screen != null) {
            screen.dispose();
        }

        this.screen = newScreen;
        this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.screen.show();
    }
}
