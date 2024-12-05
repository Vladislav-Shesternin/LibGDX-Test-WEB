package com.test.web.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.test.web.GDXGame;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.actor.ActorUtils;
import com.test.web.game.utils.advanced.AdvancedScreen;
import com.test.web.game.utils.advanced.AdvancedStage;
import com.test.web.game.utils.font.FontParameter;

public class GameScreen extends AdvancedScreen {

    private final FontParameter fontParameter = new FontParameter();
    private final BitmapFont font60 = fontGenerator_InterRegular.generateFont(fontParameter.setCharacters(FontParameter.CharType.ALL).setSize(60));

    private final Label.LabelStyle ls60 = new Label.LabelStyle(font60, Color.WHITE);

    private final GDXGame game;

    private final Label lblTMP  = new Label(FontParameter.CharType.ALL.getChars(), ls60);;
    private final Label lblText = new Label("Screen Game\n" + "Click to Back", ls60);;
    private final Label lblFPS  = new Label("", ls60);
    private final Image imgHeader;

    public GameScreen(GDXGame game) {
        this.game = game;
        imgHeader = new Image(game.assetsAll.header);
    }

    @Override
    public GDXGame getGame() {
        return game;
    }

    @Override
    public void show() {
        stageUI.addActor(lblTMP);
        setBackBackground(drawerUtil.getRegion(Color.valueOf("111111")));
        setUIBackground(drawerUtil.getRegion(Color.valueOf("3143AE00")));
        super.show();

        ActorUtils.setOnClickListener(uiBackgroundImage, actor -> {
            Utils.log("Go to Menu");
            game.navigationManager.back();
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        lblFPS.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void addActorsOnStageUI(AdvancedStage stageUI) {
        addLblText(stageUI);
        addLblFPS(stageUI);
    }

    @Override
    public void addActorsOnStageBack(AdvancedStage stageBack) {
        addImgHeader(stageBack);
    }

    // Actors Back------------------------------------------------------------------------

    private void addImgHeader(AdvancedStage stageBack) {
        stageBack.addActor(imgHeader);

        float w = sizeScaler_Ui_Back.scaled(1080f);
        float h = sizeScaler_Ui_Back.scaled(152f);
        float x = (viewportBack.getWorldWidth() / 2) - (w / 2);
        float y = (viewportBack.getWorldHeight() - h);

        imgHeader.setBounds(x, y, w, h);
//        imgHeader.setBounds(0f, 0f, 1080f, 152f);
        imgHeader.debug();
    }

    // Actors UI------------------------------------------------------------------------

    private void addLblText(AdvancedStage stageUI) {
        stageUI.addActor(lblText);
        lblText.setBounds(275f, 923f, 529f, 73f);
        lblText.setAlignment(Align.center);
    }

    private void addLblFPS(AdvancedStage stageUI) {
        stageUI.addActor(lblFPS);
        lblFPS.setBounds(430f, 1758f, 220f, 73f);
        lblFPS.setAlignment(Align.center);
    }

}
