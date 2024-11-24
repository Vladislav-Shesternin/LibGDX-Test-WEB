package com.test.web;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.test.web.game.manager.NavigationManager;
import com.test.web.game.manager.SpriteManager;
import com.test.web.game.manager.util.SpriteUtil;
import com.test.web.game.screens.LoaderScreen;
import com.test.web.game.screens.MenuScreen;
import com.test.web.game.utils.GameColor;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.advanced.AdvancedGame;

public class GDXGame extends AdvancedGame {

    public AssetManager      assetManager;
    public NavigationManager navigationManager;
    public SpriteManager     spriteManager;
//    private MusicManager musicManager;
//    private SoundManager soundManager;
//    private ParticleEffectManager particleEffectManager;

    public SpriteUtil.Loader assetsLoader;
    public SpriteUtil.All    assetsAll;

//    private final MusicUtil musicUtil = new MusicUtil();
//    private final SoundUtil soundUtil = new SoundUtil();

    public Color backgroundColor = GameColor.background;
    private final Array<Disposable> disposableSet = new Array<>();

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Utils.log("LibGDX GAME create");

        navigationManager = new NavigationManager(this);
        assetManager      = new AssetManager();
        spriteManager     = new SpriteManager(assetManager);

        // musicManager = new MusicManager(assetManager);
        // soundManager = new SoundManager(assetManager);

        navigationManager.navigate(LoaderScreen.class.getName(), null);
    }

    @Override
    public void render() {
        ScreenUtils.clear(backgroundColor);
        super.render();
    }

    @Override
    public void dispose() {
        try {
            Utils.log("dispose LibGDXGame");
            Utils.disposeAll(disposableSet);
            Utils.disposeAll(assetManager /*musicUtil*/);
            super.dispose();
        } catch (Exception e) {
            Utils.log("exception: " + e.getMessage());
        }
    }

    // Logic --------------------------------------------------------------------------------

    public void initSpriteUtil_Loader() {
        assetsLoader = new SpriteUtil.Loader();
    }

    public void initSpriteUtil_All() {
        assetsAll = new SpriteUtil.All();
    }

}

