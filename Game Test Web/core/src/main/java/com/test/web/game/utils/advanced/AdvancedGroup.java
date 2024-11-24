package com.test.web.game.utils.advanced;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.test.web.game.utils.OneTime;
import com.test.web.game.utils.SizeScaler;
import com.test.web.game.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AdvancedGroup extends WidgetGroup implements Disposable {

    public abstract AdvancedScreen getScreen();

    protected SizeScaler sizeScaler = new SizeScaler(SizeScaler.Axis.X, 1f);

    private boolean isDisposed        = false;
    private boolean isDisposeOnRemove = true;

    private final Array<Drawer> preDrawArray = new Array<>();
    private final Array<Drawer> postDrawArray = new Array<>();
    private final Set<Disposable> disposableSet = new HashSet<>();

    private final OneTime onceInit = new OneTime();

    public abstract void addActorsOnGroup();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Drawer drawer : preDrawArray) {
        drawer.draw(parentAlpha * this.getColor().a);
    }
        super.draw(batch, parentAlpha);
        for (Drawer drawer : postDrawArray) {
        drawer.draw(parentAlpha * this.getColor().a);
    }
    }

    @Override
    public void setStage(Stage stage) {
        super.setStage(stage);
        tryInitGroup();
    }

    @Override
    public void sizeChanged() {
        super.sizeChanged();
        tryInitGroup();
    }

    private void tryInitGroup() {
        if (getWidth() > 0 && getHeight() > 0 && getStage() != null) {
            sizeScaler.calculateScale(new Vector2(getWidth(), getHeight()));
            onceInit.use(this::addActorsOnGroup);
        }
    }

    @Override
    public void dispose() {
        if (!isDisposed) {
            preDrawArray.clear();
            postDrawArray.clear();

            Utils.disposeAll(disposableSet);
            disposableSet.clear();

            disposeAndClearChildren();

            isDisposed = true;
        }
    }

    @Override
    public boolean remove() {
        if (isDisposeOnRemove) dispose();
        return super.remove();
    }

    private void disposeAndClearChildren() {
        for (Actor actor : getChildren()) {
        if (actor instanceof Disposable) {
            ((Disposable) actor).dispose();
        }
    }
        clearChildren();
    }

    public void addAlignActor(Actor actor, AlignmentHorizontal alignmentHorizontal, AlignmentVertical alignmentVertical) {
        addActor(actor);

        float newX = 0f;
        float newY = 0f;

        if (alignmentHorizontal == AlignmentHorizontal.START && alignmentVertical == AlignmentVertical.CENTER) {
            newY = (getHeight() / 2) - (actor.getHeight() / 2);
        } else if (alignmentHorizontal == AlignmentHorizontal.START && alignmentVertical == AlignmentVertical.TOP) {
            newY = getHeight() - actor.getHeight();
        } else if (alignmentHorizontal == AlignmentHorizontal.CENTER && alignmentVertical == AlignmentVertical.BOTTOM) {
            newX = (getWidth() / 2) - (actor.getWidth() / 2);
        } else if (alignmentHorizontal == AlignmentHorizontal.CENTER && alignmentVertical == AlignmentVertical.CENTER) {
            newX = (getWidth() / 2) - (actor.getWidth() / 2);
            newY = (getHeight() / 2) - (actor.getHeight() / 2);
        } else if (alignmentHorizontal == AlignmentHorizontal.CENTER && alignmentVertical == AlignmentVertical.TOP) {
            newX = (getWidth() / 2) - (actor.getWidth() / 2);
            newY = getHeight() - actor.getHeight();
        } else if (alignmentHorizontal == AlignmentHorizontal.END && alignmentVertical == AlignmentVertical.BOTTOM) {
            newX = getWidth() - actor.getWidth();
        } else if (alignmentHorizontal == AlignmentHorizontal.END && alignmentVertical == AlignmentVertical.CENTER) {
            newX = getWidth() - actor.getWidth();
            newY = (getHeight() / 2) - (actor.getHeight() / 2);
        } else if (alignmentHorizontal == AlignmentHorizontal.END && alignmentVertical == AlignmentVertical.TOP) {
            newX = getWidth() - actor.getWidth();
            newY = getHeight() - actor.getHeight();
        }
        actor.setPosition(newX, newY);
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

    protected void setBoundsScaled(Actor actor, float x, float y, float width, float height) {
        actor.setBounds(sizeScaler.scaled(x), sizeScaler.scaled(y), sizeScaler.scaled(width), sizeScaler.scaled(height));
    }

    protected void setBoundsScaled(Actor actor, Vector2 position, Vector2 size) {
        setBoundsScaled(actor, position.x, position.y, size.x, size.y);
    }

    protected void setSizeScaled(Actor actor, float width, float height) {
        actor.setSize(sizeScaler.scaled(width), sizeScaler.scaled(height));
    }

    public enum AlignmentHorizontal {
            START, CENTER, END
    }

    public enum AlignmentVertical {
            BOTTOM, CENTER, TOP
    }

    @FunctionalInterface
    public interface Drawer {
        void draw(float alpha);
    }
}
