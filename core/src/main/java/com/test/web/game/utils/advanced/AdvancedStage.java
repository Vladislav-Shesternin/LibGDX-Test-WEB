package com.test.web.game.utils.advanced;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class AdvancedStage extends Stage {

    public AdvancedStage(Viewport viewport) {
        super(viewport);
    }

    public void addActors(Actor... actors) {
        for (Actor actor : actors) {
            addActor(actor);
        }
    }

    public void addActors(List<Actor> actors) {
        for (Actor actor : actors) {
            addActor(actor);
        }
    }

    public void addAndFillActor(Actor actor) {
        addActor(actor);
        actor.setSize(getWidth(), getHeight());
    }

    public void addAndFillActors(List<Actor> actors) {
        for (Actor actor : actors) {
            addActor(actor);
            actor.setSize(getWidth(), getHeight());
        }
    }

    public void addAndFillActors(Actor... actors) {
        for (Actor actor : actors) {
            addActor(actor);
            actor.setSize(getWidth(), getHeight());
        }
    }

    public void render() {
        getViewport().apply();
        act();
        draw();
    }

    @Override
    public void dispose() {
        for (Actor actor : getActors()) {
            if (actor instanceof Disposable) {
                ((Disposable) actor).dispose();
            }
        }
        super.dispose();
    }
}
