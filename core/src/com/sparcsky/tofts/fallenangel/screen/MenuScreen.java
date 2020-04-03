package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.Menu;

public class MenuScreen extends BaseScreen {

    private TiledMapRenderer renderer;
    private TiledMap menuBg;
    private Menu menu;
    private Stage stage;

    public MenuScreen(FallenAngel game) {
        super(game);
        viewport = new StretchViewport(VIRTUAL_WIDTH / 2f, VIRTUAL_HEIGHT / 2f);
        stage = new Stage(viewport);

        menuBg = asset.get(Asset.MENU_BACKGROUND);
        camera.setToOrtho(false, VIRTUAL_WIDTH / 16f, VIRTUAL_HEIGHT / 16f);

        renderer = new OrthogonalTiledMapRenderer(menuBg, 1 / 16f);
        renderer.setView(camera);
    }

    @Override
    public void show() {
        Skin skin = asset.get(Asset.UI);

        menu = new Menu(skin);
        menu.addToStage(stage);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        //   super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        renderer.render();
        stage.draw();
    }

    @Override
    public void dispose() {
        asset.dispose();
        stage.dispose();
    }
}
