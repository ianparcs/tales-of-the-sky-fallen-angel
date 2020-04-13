package com.sparcsky.tofts.fallenangel.util.factory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

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
        option.getLabel().setFontScale(0.8f);
        option.setTransform(true);

        return option;
    }
}
