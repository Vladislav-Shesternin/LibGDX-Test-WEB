package com.test.web.game.utils.actor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import java.util.List;

public class ActorUtils {

    public interface ActorCallback {
        void onClick(Actor actor);
    }

    public static void setOnClickListener(Actor actor /*SoundUtil soundUtil*/, ActorCallback block) {
        actor.addListener(new InputListener() {
            private boolean isWithin = false;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touchDragged(event, x, y, pointer);
//                if (soundUtil != null) {
//                    soundUtil.play(soundUtil.getClick());
//                }
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                isWithin = x >= 0f && x <= actor.getWidth() && y >= 0f && y <= actor.getHeight();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isWithin) {
                    isWithin = false;
                    block.onClick(actor);
                }
            }


        });
    }

//    public static void disable(Actor actor) {
//        if (actor instanceof AButton) {
//            ((AButton) actor).disable();
//        } else {
//            actor.setTouchable(Touchable.disabled);
//        }
//    }
//
//    public static void enable(Actor actor) {
//        if (actor instanceof AButton) {
//            ((AButton) actor).enable();
//        } else {
//            actor.setTouchable(Touchable.enabled);
//        }
//    }

    public static void setFillParent(List<Actor> actors) {
        for (Actor actor : actors) {
            if (actor instanceof Widget) {
                ((Widget) actor).setFillParent(true);
            } else if (actor instanceof WidgetGroup) {
                ((WidgetGroup) actor).setFillParent(true);
            }
        }
    }

    public static void setBounds(Actor actor, Vector2 position, Vector2 size) {
        actor.setBounds(position.x, position.y, size.x, size.y);
    }

    public static void setPosition(Actor actor, Vector2 position) {
        actor.setPosition(position.x, position.y);
    }

    public static void setOrigin(Actor actor, Vector2 vector) {
        actor.setOrigin(vector.x, vector.y);
    }

    public static void animShow(Actor actor, float time, Runnable block) {
        actor.addAction(Actions.sequence(
            Actions.fadeIn(time),
            Actions.run(block)
        ));
    }

    public static void animHide(Actor actor, float time, Runnable block) {
        actor.addAction(Actions.sequence(
            Actions.fadeOut(time),
            Actions.run(block)
        ));
    }
}
