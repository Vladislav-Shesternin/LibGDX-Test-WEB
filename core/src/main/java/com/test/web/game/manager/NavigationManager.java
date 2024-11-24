package com.test.web.game.manager;

import com.badlogic.gdx.Gdx;
import com.test.web.GDXGame;
import com.test.web.game.screens.GameScreen;
import com.test.web.game.screens.MenuScreen;
import com.test.web.game.utils.Utils;
import com.test.web.game.utils.advanced.AdvancedScreen;

import java.util.ArrayList;
import java.util.List;

public class NavigationManager {

    private final GDXGame game;
    private final List<String> backStack = new ArrayList<>();

    public NavigationManager(GDXGame game) {
        this.game = game;
    }

    public void navigate(String toScreenName, String fromScreenName) {
        Utils.runGDX(() -> {
            game.updateScreen(getScreenByName(toScreenName));
            backStack.removeIf(name -> name.equals(toScreenName));
            if (fromScreenName != null) {
                backStack.removeIf(name -> name.equals(fromScreenName));
                backStack.add(fromScreenName);
            }
        });
    }

    public void back() {
        Utils.runGDX(() -> {
            if (isBackStackEmpty()) {
                exit();
            } else {
                game.updateScreen(getScreenByName(backStack.remove(backStack.size() - 1)));
            }
        });
    }

    public void exit() {
        Utils.runGDX(() -> Gdx.app.exit());
    }

    public boolean isBackStackEmpty() {
        return backStack.isEmpty();
    }

    private AdvancedScreen getScreenByName(String name) {
        if (name.equals(MenuScreen.class.getName())) {
            return new MenuScreen(game);
        } else if (name.equals(GameScreen.class.getName())) {
            return new GameScreen(game);
        } else {
            return new MenuScreen(game);
        }
    }

}
