package com.test.web.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.test.web.GDXGame;
import com.test.web.game.manager.SpriteManager;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.advanced.AdvancedScreen;
import com.test.web.game.utils.advanced.AdvancedStage;
import com.test.web.game.utils.font.FontParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class LoaderScreen extends AdvancedScreen {

    private final GDXGame game;

    private final AtomicBoolean isFinishLoading  = new AtomicBoolean(false);
    private final AtomicBoolean isFinishProgress = new AtomicBoolean(false);

    private final FontParameter fontParameter = new FontParameter();
    private final BitmapFont font60 = fontGenerator_InterRegular.generateFont(fontParameter.setCharacters(FontParameter.CharType.ALL).setSize(60));

    private final Label.LabelStyle ls60 = new Label.LabelStyle(font60, Color.WHITE);

    private final Label lblTMP      = new Label(FontParameter.CharType.ALL.getChars(), ls60);
    private final Label lblFPS      = new Label("", ls60);
    private final Label lblPercent  = new Label("", ls60);

    private Image imgLoader;
    private Image imgLogo;

    public LoaderScreen(GDXGame game) {
        this.game = game;
    }

    @Override
    public GDXGame getGame() {
        return game;
    }

    @Override
    public void show() {
        stageUI.addActor(lblTMP);
        loadLoaderAssets();

        imgLoader = new Image(game.assetsLoader.LOADER);
        imgLogo = new Image(game.assetsLoader.logo);

        setUIBackground(drawerUtil.getRegion(Color.valueOf("373737")));
        super.show();
        loadAssets();
        collectProgress();
    }

    @Override
    public void render(float delta) {
        lblFPS.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        super.render(delta);
        loadingAssets();
        isFinish();
    }

    @Override
    public void addActorsOnStageUI(AdvancedStage stageUI) {
        addLblFPS(stageUI);
        addImgLoader(stageUI);
        addLblPercent(stageUI);
        addImgLogo(stageUI);
    }

    // Actors ------------------------------------------------------------------------

    private void addLblFPS(AdvancedStage stageUI) {
        stageUI.addActor(lblFPS);
        lblFPS.setBounds(430f, 1758f, 220f, 73f);
        lblFPS.setAlignment(Align.center);
    }

    private void addImgLoader(AdvancedStage stageUI) {
        stageUI.addActor(imgLoader);
        imgLoader.setBounds(247f, 667f, 585f, 585f);

        imgLoader.setOrigin(Align.center);
        imgLoader.addAction(Actions.forever(
            Actions.parallel(
                Actions.rotateBy(-360f, 2.5f),
                Actions.sequence(
                    Actions.scaleTo(0.6f, 0.6f, 1.25f, Interpolation.sine),
                    Actions.scaleTo(1f, 1f, 1.25f, Interpolation.sine)
                )
            )
        ));
    }

    private void addLblPercent(AdvancedStage stageUI) {
        stageUI.addActor(lblPercent);
        lblPercent.setBounds(464f, 923f, 152f, 73f);
        lblPercent.setAlignment(Align.center);
    }

    private void addImgLogo(AdvancedStage stageUI) {
        stageUI.addActor(imgLogo);
        imgLogo.setBounds(250f, 210f, 579f, 91f);
    }


    // Logic ------------------------------------------------------------------------

    private void loadLoaderAssets() {
        SpriteManager spriteManager = game.spriteManager;

        spriteManager.loadableAtlasList.add(SpriteManager.EnumAtlas.LOADER.data);
        spriteManager.loadAtlas();

        spriteManager.loadableTexturesList.add(SpriteManager.EnumTexture.Loader.data);
        spriteManager.loadTexture();

        game.assetManager.finishLoading();
        spriteManager.initAtlasAndTexture();
        game.initSpriteUtil_Loader();
    }

    private void loadAssets() {
        SpriteManager spriteManager = game.spriteManager;
        spriteManager.loadableAtlasList.add(SpriteManager.EnumAtlas.ALL.data);
        spriteManager.loadAtlas();
//        spriteManager.setLoadableTexturesList(SpriteManager.EnumTexture.entries());
//        spriteManager.loadTexture();

//        MusicManager musicManager = game.getMusicManager();
//        musicManager.setLoadableMusicList(MusicManager.EnumMusic.entries());
//        musicManager.load();
//
//        SoundManager soundManager = game.getSoundManager();
//        soundManager.setLoadableSoundList(SoundManager.EnumSound.entries());
//        soundManager.load();
//
//        ParticleEffectManager particleEffectManager = game.getParticleEffectManager();
//        particleEffectManager.setLoadableParticleEffectList(ParticleEffectManager.EnumParticleEffect.entries());
//        particleEffectManager.load();
    }

    private void initAssets() {
        game.spriteManager.initAtlasAndTexture();
//        game.getMusicManager().init();
//        game.getSoundManager().init();
//        game.getParticleEffectManager().init();
    }

    private void loadingAssets() {
        if (!isFinishLoading.get()) {
            if (game.assetManager.update(16)) {
                isFinishLoading.set(true);
                initAssets();
            }

            updateProgress(game.assetManager.getProgress());
        }
    }

    private void collectProgress() {
        AtomicReference<Float> percent = new AtomicReference<>(0f);

        listeners.add(progress -> {
            percent.set(progress * 100f);

            lblPercent.setText(percent + "%");
            if (percent.get() % 50 == 0) Utils.log("progress = " + percent + "%");
            if (percent.get() == 100) {
                game.initSpriteUtil_All();
                isFinishProgress.set(true);
            }
        });
    }

    private void isFinish() {
        if (isFinishProgress.get()) {
            isFinishProgress.set(false);

            game.navigationManager.navigate(MenuScreen.class.getName(), null);
        }
    }

    // Progress --------------------------------------------------------------------------------------

    private interface ProgressListener {
        void update(float progress);
    }

    private final List<ProgressListener> listeners = new ArrayList<>();

    private void updateProgress(float progress) {
        for (ProgressListener listener : listeners) {
            listener.update(progress);
        }
    }

}
