package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.util.Graphics;
import com.sparcsky.tofts.fallenangel.util.Updatable;
import com.sparcsky.tofts.fallenangel.util.Version;
import com.sparcsky.tofts.fallenangel.util.factory.GuiFactory;

import static com.badlogic.gdx.math.Interpolation.fastSlow;

public class Menu implements Disposable, Graphics, Updatable {

    private Stage stage;
    private Skin skin;

    private Sound keyboardType;
    private Music bgMusic;

    private Table buttonContainer;

    private TextButton btnPlay;
    private TextButton btnLoad;
    private TextButton btnSettings;
    private TextButton btnCredits;
    private TextButton btnExit;

    public Menu(Asset asset, SpriteBatch batch, Viewport viewport) {
        this.skin = asset.get(Asset.SKIN_UI);
        this.keyboardType = asset.get(Asset.SOUND_KEYBOARD_TYPE);
        this.bgMusic = asset.get(Asset.SOUND_WOODLAND_FANTASY);

        buttonContainer = new Table(skin);

        BitmapFont titleFont = skin.getFont("small");
        titleFont.setUseIntegerPositions(false);

        TypingLabel title = createTitle();
        Table menuOptions = createMenuOptions();
        Label copyright = createCopyrightLabel();
        Label version = createGameVersion();

        Table menuWindow = new Table(skin);
        menuWindow.add(title).padBottom(Value.percentHeight(0.1f)).row();
        menuWindow.padBottom(Value.percentHeight(0.1f));
        menuWindow.add(menuOptions);

        Table bottom = new Table();
        bottom.add(copyright);
        bottom.add(version);

        Table root = new Table();
        root.setFillParent(true);
        root.add(menuWindow).colspan(2).center().row();
        root.add(copyright)
                .padLeft(Value.percentWidth(0.01f))
                .padBottom(Value.percentHeight(0.05f)).fill().expand();
        root.add(version)
                .padRight(Value.percentWidth(0.01f))
                .padBottom(Value.percentHeight(0.05f)).fill().expand();

        stage = new Stage(viewport, batch);
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
    }

    private Label createCopyrightLabel() {
        Label copyright = new Label("Copyright ©2020 Sparcsky", skin, "normal");
        copyright.setAlignment(Align.bottomLeft);
        return copyright;
    }

    private Label createGameVersion() {
        Label version = new Label("Version " + Version.get(), skin, "normal");
        version.setAlignment(Align.bottomRight);
        return version;
    }

    private Table createMenuOptions() {
        btnPlay = GuiFactory.createOptions("Play", skin);
        btnLoad = GuiFactory.createOptions("Load", skin);
        btnSettings = GuiFactory.createOptions("Settings", skin);
        btnCredits = GuiFactory.createOptions("Credits", skin);
        btnExit = GuiFactory.createOptions("Exit", skin);

        addButtonToContainer(btnPlay);
        addButtonToContainer(btnLoad);
        addButtonToContainer(btnSettings);
        addButtonToContainer(btnCredits);
        addButtonToContainer(btnExit);
        return buttonContainer;
    }

    private void addButtonToContainer(TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                keyboardType.play();
                button.addAction(Actions.scaleTo(1.1f, 1.1f, .15f, fastSlow));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.addAction(Actions.scaleTo(1f, 1f, .35f, fastSlow));
            }
        });
        buttonContainer.add(button)
                .width(Value.percentWidth(0.85f))
                .height(Value.percentHeight(0.80f))
                .padBottom(Value.percentHeight(0.2f))
                .row();
    }

    private TypingLabel createTitle() {
        TypingLabel label = GuiFactory.createMenuTitle(skin);
        label.setTypingListener(new TypingAdapter() {
            @Override
            public void end() {
                //      bgMusic.play();
                bgMusic.setLooping(true);
            }

            @Override
            public void onChar(Character ch) {
                keyboardType.play();
            }
        });

        return label;
    }

    public void resize(int width, int height, boolean center) {
        stage.getViewport().update(width, height, center);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        stage.draw();
    }

    @Override
    public void dispose() {
        bgMusic.dispose();
        keyboardType.dispose();
    }
}
