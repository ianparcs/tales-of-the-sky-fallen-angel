package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class SlideState extends PlayerState {

    private Animation<TextureRegion> runAnim;

    public SlideState(Player player) {
        super(player);
        runAnim = new Animation<>(0.09f, atlas.findRegions("adventurer-wall-slide"), Animation.PlayMode.LOOP);
    }


    @Override
    public void enter() {
        player.setAnimations(runAnim);
        player.getBody().setLinearDamping(15);
    }

    @Override
    public void exit() {
        player.setStateTime(0);
        player.getBody().setLinearDamping(0);
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)) {
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
