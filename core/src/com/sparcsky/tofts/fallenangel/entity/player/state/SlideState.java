package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class SlideState extends PlayerState {


    public SlideState(Player player) {
        super(player);
        animState = new Animation<>(0.09f, atlas.findRegions("adventurer-wall-slide"), Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        player.setAnimations(animState);
        player.setWallSliding(true);
        player.setVelocityY(1);
    }

    @Override
    public void exit() {
        player.setWallSliding(false);
        player.setStateTime(0);
        player.getBody().setGravityScale(4.5f);
    }

    @Override
    public void update(float delta) {
        player.getBody().setGravityScale(4.5f);

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.getBody().setGravityScale(0.5f);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                player.changeState(StateType.JUMP);
            }
        }
    }

    @Override
    public StateType getType() {
        return StateType.SLIDE;
    }
}
