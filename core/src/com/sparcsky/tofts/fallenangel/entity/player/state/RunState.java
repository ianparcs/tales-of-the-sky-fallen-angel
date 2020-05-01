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
public class RunState extends MoveState {

    private Animation<TextureRegion> runAnim;

    public RunState(Player player) {
        super(player);
        runAnim = new Animation<>(0.09f, atlas.findRegions("adventurer-run3"), Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        player.setAnimations(runAnim);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (speedX == 0) player.changeState(StateType.IDLE);

        if (Gdx.input.isKeyPressed(Input.Keys.D) || (Gdx.input.isKeyPressed(Input.Keys.A))) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                player.changeState(StateType.JUMP);
            }
        }
    }

    @Override
    public StateType getType() {
        return StateType.RUN;
    }
}
