package com.test.web.game.utils.advanced;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.test.web.GDXGame;
import com.test.web.game.utils.Constants;
import com.test.web.game.utils.ShapeDrawerUtil;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.font.FontGenerator;

public abstract class AdvancedScreen extends ScreenAdapter implements AdvancedInputProcessor {

    public abstract GDXGame getGame();

    public final ScreenViewport viewportBack = new ScreenViewport();
    public final AdvancedStage stageBack     = new AdvancedStage(viewportBack);

    public final FitViewport viewportUI = new FitViewport(Constants.WIDTH_UI, Constants.HEIGHT_UI);
    public final AdvancedStage stageUI  = new AdvancedStage(viewportUI);

    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();

    protected final Image backBackgroundImage  = new Image();
    private final Image tmpBackBackgroundImage = new Image();
    protected final Image uiBackgroundImage    = new Image();

    public final Array<Disposable> disposableSet = new Array<>();

    public ShapeDrawerUtil drawerUtil = new ShapeDrawerUtil(stageUI.getBatch());

    public final FontGenerator fontGenerator_InterRegular = new FontGenerator(FontGenerator.FontPath.InterRegular);

    public AdvancedScreen() { }

    @Override
    public void resize(int width, int height) {
        viewportBack.update(width, height, true);
        viewportUI.update(width, height, true);
    }

    @Override
    public void show() {
        stageBack.addAndFillActors(backBackgroundImage, tmpBackBackgroundImage);
        stageUI.addAndFillActor(uiBackgroundImage);

        addActorsOnStageUI(stageUI);

        Utils.addProcessors(inputMultiplexer, this, stageUI, stageBack);

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        stageBack.render();
        stageUI.render();
        drawerUtil.update();
    }

    @Override
    public void dispose() {
        Utils.log("dispose AdvancedScreen: " + this.getClass().getSimpleName());
        Utils.disposeAll(stageBack, stageUI, drawerUtil/*fontGenerator_RubikDoodleShadow, fontGenerator_SansitaOne*/);
        Utils.disposeAll(disposableSet);
        inputMultiplexer.clear();
    }

//    @Override
//    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.BACK) {
////            if (getGame().getNavigationManager().isBackStackEmpty()) {
////                getGame().getNavigationManager().exit();
////            } else {
////                hideScreen(() -> getGame().getNavigationManager().back());
////            }
//        }
//        return true;
//    }

    public abstract void addActorsOnStageUI(AdvancedStage stageUI);

    public void setBackBackground(TextureRegion region) {
        backBackgroundImage.setDrawable(new TextureRegionDrawable(region));
    }

    public void setUIBackground(TextureRegion region) {
        uiBackgroundImage.setDrawable(new TextureRegionDrawable(region));
    }

    public void setBackBackgroundAnim(TextureRegion regionOld, TextureRegion regionNew, float time) {
        backBackgroundImage.setDrawable(new TextureRegionDrawable(regionOld));
        tmpBackBackgroundImage.setDrawable(new TextureRegionDrawable(regionNew));

//        backBackgroundImage.animHide(time, () -> backBackgroundImage.setDrawable(new TextureRegionDrawable(regionNew)));
//        tmpBackBackgroundImage.animShow(time, () -> {
//        backBackgroundImage.animShow();
//        tmpBackBackgroundImage.animHide();
//                });
    }
}
