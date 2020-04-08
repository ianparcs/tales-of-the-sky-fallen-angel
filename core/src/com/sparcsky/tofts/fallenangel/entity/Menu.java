package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.util.Renderable;
import com.sparcsky.tofts.fallenangel.util.Updatable;
import com.sparcsky.tofts.fallenangel.util.factory.GuiFactory;

public class Menu implements Disposable, Renderable, Updatable {

    private Table menuWindow;
    private Table root;

    private Stage stage;
    private Skin skin;

    private Sound keyboardType;
    private Music bgMusic;

    public Menu(Asset asset, SpriteBatch batch, StretchViewport viewport) {
        this.skin = asset.get(Asset.SKIN_UI);
        this.keyboardType = asset.get(Asset.SOUND_KEYBOARD_TYPE);
        this.bgMusic = asset.get(Asset.SOUND_WOODLAND_FANTASY);

        menuWindow = new Table(skin);
        createTitle();
        createMenuOptions();

        root = new Table();
        root.setFillParent(true);
        root.add(menuWindow);

        stage = new Stage(viewport, batch);
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
    }

    private void createMenuOptions() {
        String[] optionText1 = {"Play", "Load", "Settings", "Credits", "Exit"};
        for (String optionText : optionText1) {
            TextButton option = GuiFactory.createOptions(optionText, skin);
            option.addListener(new ClickListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    keyboardType.play();
                }
            });
            menuWindow.add(option).top().padBottom(Value.percentHeight(0.2f)).row();
        }
    }

    private void createTitle() {
        TypingLabel label = GuiFactory.createMenuTitle(skin);
        label.setTypingListener(new TypingAdapter() {
            @Override
            public void event(String event) {
                bgMusic.play();
                bgMusic.setLooping(true);
            }

            @Override
            public void onChar(Character ch) {
                keyboardType.play();
            }
        });
        menuWindow.add(label).padBottom(Value.percentHeight(0.1f)).row();
        menuWindow.padBottom(Value.percentHeight(0.2f));
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
