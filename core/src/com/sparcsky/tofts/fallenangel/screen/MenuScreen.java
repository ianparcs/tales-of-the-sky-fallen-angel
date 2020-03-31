package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Game;
import com.sparcsky.tofts.fallenangel.FallenAngel;
import com.sparcsky.tofts.fallenangel.asset.Asset;

public class MenuScreen extends BaseScreen {

    public MenuScreen(Game game) {
        super((FallenAngel) game);
    }

    @Override
    public void show() {
        mainFont = asset.get(Asset.FONT_BIT);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {
        asset.dispose();
    }
}
