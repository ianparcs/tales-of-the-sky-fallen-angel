package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/19/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class JumpState extends MoveState {


    public JumpState(Player player) {
        super(player);

        animState = new Animation<>(0.09f, atlas.findRegions("adventurer-jump"), Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter() {
        player.jump();
        player.setStateTime(0);
        player.setAnimations(animState);
    }

    @Override
    public void exit() {
    //    player.setRestitution(0f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.changeState(StateType.AIR_ATTACK);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.changeState(StateType.DOUBLE_JUMP);
        }

        if (player.isFalling()) {
            player.changeState(StateType.FALL);
        }
    }

    @Override
    public StateType getType() {
        return StateType.JUMP;
    }
}
