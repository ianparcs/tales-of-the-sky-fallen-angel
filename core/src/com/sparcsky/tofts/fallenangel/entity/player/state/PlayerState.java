package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class PlayerState {

    protected TextureAtlas atlas;
    protected Player player;

    public PlayerState(Player player) {
        this.player = player;
        this.atlas = player.getAtlas();
    }

    public abstract void enter();

    public abstract void exit();

    public abstract void update(float delta);

    public abstract StateType getType();

}
