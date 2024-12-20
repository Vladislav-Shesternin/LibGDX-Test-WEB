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

public class MenuScreen extends AdvancedScreen {

    private final FontParameter fontParameter = new FontParameter();
    private final BitmapFont font60 = fontGenerator_InterRegular.generateFont(fontParameter.setCharacters(FontParameter.CharType.ALL).setSize(60));

    private final Label.LabelStyle ls60 = new Label.LabelStyle(font60, Color.WHITE);

    private final GDXGame game;

    private final Label lblTMP   = new Label(FontParameter.CharType.ALL.getChars(), ls60);
    private final Label lblText  = new Label("Screen Menu\n" + "Click to Game", ls60);
    private final Label lblClick = new Label("Демонстрація тексту", ls60);
    private final Label lblFPS   = new Label("", ls60);

    public MenuScreen(GDXGame game) {
        this.game = game;
    }

    @Override
    public GDXGame getGame() {
        return game;
    }

    @Override
    public void show() {
        stageUI.addActor(lblTMP);
        setUIBackground(drawerUtil.getRegion(Color.valueOf("930093")));
        super.show();

        ActorUtils.setOnClickListener(uiBackgroundImage, actor -> {
            Utils.log("Go to Game");
            game.navigationManager.navigate(GameScreen.class.getName(), MenuScreen.class.getName());
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
        addLblClick(stageUI);
    }

    private void addLblText(AdvancedStage stageUI) {
        stageUI.addActor(lblText);
        lblText.setBounds(275f, 1516f, 529f, 73f);
        lblText.setAlignment(Align.center);
    }

    private void addLblFPS(AdvancedStage stageUI) {
        stageUI.addActor(lblFPS);
        lblFPS.setBounds(430f, 1758f, 220f, 73f);
        lblFPS.setAlignment(Align.center);
    }

    private void addLblClick(AdvancedStage stageUI) {
        stageUI.addActor(lblClick);
        lblClick.setBounds(327f, 1200f, 425f, 146f);
        lblClick.setAlignment(Align.center);
    }
}
