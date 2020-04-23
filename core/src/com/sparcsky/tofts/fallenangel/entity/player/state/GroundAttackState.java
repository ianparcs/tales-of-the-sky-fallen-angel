package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class GroundAttackState extends AttackState {


    public GroundAttackState(Player player) {
        super(player);

        animState = new Animation<>(.07f, atlas.findRegions("adventurer-attack1"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack1 = new Animation<>(.07f, atlas.findRegions("adventurer-attack2"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> attack2 = new Animation<>(.07f, atlas.findRegions("adventurer-attack3"), Animation.PlayMode.NORMAL);

        attacks.add(animState);
        attacks.add(attack1);
        attacks.add(attack2);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (attackTime >= 0.5f && !player.isAttacking()) {
            player.changeState(StateType.IDLE);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.changeState(StateType.RUN);
        }
    }

    @Override
    public StateType getType() {
        return StateType.GROUND_ATTACK;
    }
}
