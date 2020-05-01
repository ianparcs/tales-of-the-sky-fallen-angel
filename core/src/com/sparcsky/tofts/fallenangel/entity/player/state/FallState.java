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
public class FallState extends MoveState {

    private Animation<TextureRegion> fallAnim;

    public FallState(Player player) {
        super(player);

        fallAnim = new Animation<>(0.09f, atlas.findRegions("adventurer-fall"), Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter() {
        super.enter();
        player.setAnimations(fallAnim);

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.changeState(StateType.AIR_ATTACK);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !player.isDoubleJumped()) {
            player.changeState(StateType.JUMP);
        }

    }

    @Override
    public StateType getType() {
        return StateType.FALL;
    }
}
