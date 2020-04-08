package com.sparcsky.tofts.fallenangel.util.factory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import static com.badlogic.gdx.math.Interpolation.fastSlow;

public class GuiFactory {

    public static TypingLabel createMenuTitle(Skin skin) {
        String text = "{SLOW}{HANG}Tales of the Sky\nFallen Knight{EVENT=play_sound}";

        TypingLabel label = new TypingLabel(text, skin, "title");
        label.setAlignment(Align.center, Align.center);
        label.setEllipsis(true);
        return label;
    }

    public static TextButton createOptions(String text, Skin skin) {
        TextButton option = new TextButton(text, skin, "menu_option");
        option.setOrigin(Align.center);
        option.getLabel().setAlignment(Align.center);
        option.setTransform(true);
        option.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                float scale = 1.1f;
                option.addAction(Actions.scaleTo(scale, scale, .15f, fastSlow));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                float scale = 1f;
                option.addAction(Actions.scaleTo(scale, scale, .35f, fastSlow));
            }
        });
        return option;
    }
}
