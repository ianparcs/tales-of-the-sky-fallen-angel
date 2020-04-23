package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class IdleState extends PlayerState {

    public IdleState(Player player) {
        super(player);
        animState = new Animation<>(0.15f, atlas.findRegions("adventurer-idle"), Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        player.setAnimations(animState);
        player.setVelocity(new Vector2(0, player.getBody().getLinearVelocity().y));
        player.setDoubleJump(false);
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.changeState(StateType.RUN);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.changeState(StateType.JUMP);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.K) || Gdx.input.isTouched()) {
            player.changeState(StateType.GROUND_ATTACK);
        }
    }

    @Override
    public StateType getType() {
        return StateType.IDLE;
    }

}
