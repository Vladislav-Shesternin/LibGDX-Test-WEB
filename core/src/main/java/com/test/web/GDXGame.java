package com.test.web;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.test.web.game.manager.NavigationManager;
import com.test.web.game.screens.MenuScreen;
import com.test.web.game.utils.GameColor;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.advanced.AdvancedGame;

public class GDXGame extends AdvancedGame {

    //private AssetManager assetManager;
    public NavigationManager navigationManager;
//    private SpriteManager spriteManager;
//    private MusicManager musicManager;
//    private SoundManager soundManager;
//    private ParticleEffectManager particleEffectManager;
//
//    private final SpriteUtil.Loader assetsLoader = new SpriteUtil.Loader();
//    private final SpriteUtil.All assetsAll = new SpriteUtil.All();
//
//    private final MusicUtil musicUtil = new MusicUtil();
//    private final SoundUtil soundUtil = new SoundUtil();
//
//    private final ParticleEffectUtil particleEffectUtil = new ParticleEffectUtil();

    private Color backgroundColor = GameColor.background;
   // private final Set<Disposable> disposableSet = new HashSet<>();

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Utils.log("LibGDX GAME create");

        navigationManager = new NavigationManager(this);
        //assetManager = new AssetManager();
//        spriteManager = new SpriteManager(assetManager);
//
//        musicManager = new MusicManager(assetManager);
//        soundManager = new SoundManager(assetManager);
//
//        particleEffectManager = new ParticleEffectManager(assetManager);
//
//        navigationManager.navigate(LoaderScreen.class.getName());

        navigationManager.navigate(MenuScreen.class.getName(), null);
    }

    @Override
    public void render() {
        ScreenUtils.clear(backgroundColor);
        super.render();
    }

    @Override
    public void dispose() {
//        try {
//            Utils.log("dispose LibGDXGame");
//            Utils.disposeAll(disposableSet);
//            Utils.disposeAll(assetManager /*musicUtil*/);
//            super.dispose();
//        } catch (Exception e) {
//            Utils.log("exception: " + e.getMessage());
//        }
    }

    // Getters for lateinit variables
//    public AssetManager getAssetManager() {
//        return assetManager;
//    }

//    public NavigationManager getNavigationManager() {
//        return navigationManager;
//    }
//
//    public SpriteManager getSpriteManager() {
//        return spriteManager;
//    }
//
//    public MusicManager getMusicManager() {
//        return musicManager;
//    }
//
//    public SoundManager getSoundManager() {
//        return soundManager;
//    }
//
//    public ParticleEffectManager getParticleEffectManager() {
//        return particleEffectManager;
//    }
//
//    public GameColor getBackgroundColor() {
//        return backgroundColor;
//    }
//
//    public void setBackgroundColor(GameColor backgroundColor) {
//        this.backgroundColor = backgroundColor;
//    }
}

