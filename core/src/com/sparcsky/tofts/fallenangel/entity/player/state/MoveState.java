package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class MoveState extends PlayerState {

    protected int speedX;
    private boolean flip;

    public MoveState(Player player) {
        super(player);
    }

    @Override
    public void enter() {
        speedX = 10;
    }

    @Override
    public void exit() {
        speedX = 0;
    }

    @Override
    public void update(float delta) {
        float jumpY = player.getBody().getLinearVelocity().y;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setVelocity(new Vector2(10, jumpY));
            player.setFlip(false);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setVelocity(new Vector2(-10, jumpY));
            player.setFlip(true);
        } else {
            speedX = 0;
        }
    }
}