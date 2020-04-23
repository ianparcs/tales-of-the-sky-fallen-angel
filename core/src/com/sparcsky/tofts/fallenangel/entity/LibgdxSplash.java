package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public class LibgdxSplash {

    private static final float FADEOUT_TIME = 1.5f;
    private static final float DELAY = 1f;

    private static final int MAX_FADER_COUNT = 5;

    private Array<Actor> flash;
    private Table table;
    private Label label;
    private Actor bg;

    private float x;
    private float y;
    private float width;
    private float height;

    private boolean finish;

    public LibgdxSplash(Asset asset) {
        Skin skin = asset.get(Asset.SKIN_UI);

        Texture logo = asset.get(Asset.IMAGE_LIBGDX_LOGO);
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        initProperties();

        table = new Table();
        flash = new Array<>();
        bg = createBackground();
        label = createLabel(skin);

        for (int i = 0; i < MAX_FADER_COUNT; i++) {
            Actor actor = createActor(logo);
            actor.setX(-actor.getWidth() - (i * (actor.getWidth() / 2f)));

            AlphaAction logoFadeOut = Actions.fadeOut(5);

            if (i == 0) {
                actor.addAction(createMainLogoActions());
            } else {
                actor.addAction(Actions.parallel(createMoveAction(), logoFadeOut));
            }
            flash.add(actor);
        }
    }

    private Actor createActor(Texture logo) {
        Actor actor = new Image(logo);
        actor.setSize(width, height);
        actor.setY(y);
        return actor;
    }

    private void initProperties() {
        this.width = GameWorld.WIDTH / 2f;
        this.height = GameWorld.HEIGHT / 8f;
        this.x = (GameWorld.WIDTH / 2f) - (width / 2f);
        this.y = (GameWorld.HEIGHT / 2f) - (height / 2f);
    }

    private Action createMainLogoActions() {
        return Actions.sequence(createMoveAction(), new RunnableAction() {
            @Override
            public void run() {
                actor.addAction(Actions.sequence(Actions.delay(DELAY), Actions.fadeOut(FADEOUT_TIME)));
                label.addAction(Actions.sequence(Actions.delay(DELAY), Actions.fadeOut(FADEOUT_TIME)));
                bg.addAction(Actions.sequence(Actions.delay(DELAY), Actions.color(Color.BLACK, FADEOUT_TIME),
                        new RunnableAction() {
                            @Override
                            public void run() {
                                finish = true;
                            }
                        }));
            }
        });
    }

    private Label createLabel(Skin skin) {
        BitmapFont font = skin.getFont("main");
        font.getData().setScale(0.45f / 16f);
        font.setUseIntegerPositions(false);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = skin.getColor("gray");
        Label label = new Label("POWERED BY", labelStyle);
        label.setPosition(x, y + height + label.getHeight() / 2f);
        return label;
    }

    private MoveToAction createMoveAction() {
        MoveToAction moveAction = Actions.action(MoveToAction.class);
        moveAction.setInterpolation(Interpolation.swing);
        moveAction.setPosition(x, y);
        moveAction.setDuration((float) 4);
        return moveAction;
    }

    private Actor createBackground() {
        int width = (int) GameWorld.WIDTH;
        int height = (int) GameWorld.HEIGHT;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.drawRectangle(width, height, width, height);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        Texture txBackground = new Texture(pixmap);
        pixmap.dispose();
        return new Image(txBackground);
    }

    public boolean isFinish() {
        return finish;
    }

    public void addToStage(Stage stage) {
        table = new Table();
        table.addActor(bg);
        table.addActor(label);
        for (int i = 0; i < flash.size; i++) {
            table.addActor(flash.get(i));
        }
        stage.addActor(table);
    }

}
