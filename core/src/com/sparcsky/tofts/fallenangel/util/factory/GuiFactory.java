package com.sparcsky.tofts.fallenangel.util.factory;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class GuiFactory {

    public static TypingLabel createMenuTitle(Skin skin) {
        String text = "{SLOW}{HANG}Tales of the SKY\nFALLEN KNIGHT{EVENT=play_sound}";

        BitmapFont font = skin.getFont("title");
        font.setUseIntegerPositions(false);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        TypingLabel label = new TypingLabel(text, style);
        label.setAlignment(Align.center, Align.center);
        label.setEllipsis(true);
        return label;
    }

    public static TextButton createOptions(String text, Skin skin) {
        BitmapFont font = skin.getFont("main");
        font.setUseIntegerPositions(false);

        TextButton option = new TextButton(text, skin, "menu_option");
        option.setOrigin(Align.center);
        option.getLabel().setAlignment(Align.center);
        option.getLabel().setFontScale(0.55f);
        option.setTransform(true);

        return option;
    }
}
