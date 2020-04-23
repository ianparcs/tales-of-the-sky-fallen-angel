package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/21/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class DoubleJumpState extends MoveState {

    private Animation<TextureRegion> doubleJump;

    public DoubleJumpState(Player player) {
        super(player);
        doubleJump = new Animation<>(0.09f, atlas.findRegions("adventurer-smrslt"), Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        player.setAnimations(doubleJump);
        player.doubleJump();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (player.getBody().getLinearVelocity().y < 0) {
            player.changeState(StateType.FALL);
        } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.changeState(StateType.AIR_ATTACK);
        }
    }

    @Override
    public StateType getType() {
        return StateType.DOUBLE_JUMP;
    }
}
