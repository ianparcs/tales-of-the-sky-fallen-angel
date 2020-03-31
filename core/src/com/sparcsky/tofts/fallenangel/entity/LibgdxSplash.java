package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.screen.BaseScreen;

public class LibgdxSplash extends Actor {

    private Array<Actor> flash;
    private Table table;
    private Label label;
    private Actor bg;

    private float x;
    private float y;

    private boolean finish;

    public LibgdxSplash(Asset asset) {

        Texture logo = asset.get(Asset.libgdxLogo);
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        table = new Table();

        int virtualWidth = BaseScreen.VIRTUAL_WIDTH;
        int virtualHeight = BaseScreen.VIRTUAL_HEIGHT;

        float width = logo.getWidth() / 5f;
        float height = logo.getHeight() / 5f;
        this.x = (virtualWidth / 2f) - (width / 2f);
        this.y = (virtualHeight / 2f) - (height / 2f);

        bg = createBackground(virtualWidth, virtualHeight);

        Label.LabelStyle style = new Label.LabelStyle(asset.get(Asset.fontBit), Color.BLACK);
        label = new Label("POWERED BY", style);
        label.setPosition(x, y + height + label.getHeight() / 2f);

        flash = new Array<>();
        for (int i = 0; i < 5; i++) {
            Actor actor = new Image(logo);
            actor.setSize(width, height);
            actor.setX(-actor.getWidth() - (i * actor.getWidth()));
            actor.setY(y);

            AlphaAction logoFadeOut = Actions.fadeOut(4);
            MoveToAction moveAction = createMoveAction(5);

            if (i == 0) {
                actor.addAction(Actions.sequence(moveAction, new RunnableAction() {
                    @Override
                    public void run() {
                        actor.addAction(Actions.sequence(Actions.delay(1.5f), Actions.fadeOut(2)));
                        label.addAction(Actions.sequence(Actions.delay(1.5f), Actions.fadeOut(2)));
                        bg.addAction(Actions.sequence(Actions.delay(1.5f), Actions.color(Color.BLACK, 2),
                                new RunnableAction() {
                                    @Override
                                    public void run() {
                                        finish = true;
                                    }
                                }));
                    }
                }));
            } else {
                actor.addAction(Actions.parallel(moveAction, logoFadeOut));
            }
            flash.add(actor);
        }
    }

    private MoveToAction createMoveAction(float duration) {
        MoveToAction moveAction = Actions.action(MoveToAction.class);
        moveAction.setInterpolation(Interpolation.exp10);
        moveAction.setPosition(x, y);
        moveAction.setDuration(duration);
        return moveAction;
    }

    private Actor createBackground(int width, int height) {
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
