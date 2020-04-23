package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Jasper Parcon on 4/20/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class AttackState extends PlayerState {

    protected List<Animation<TextureRegion>> attacks;
    protected float attackTime;
    protected int nextAttack;

    public AttackState(Player player) {
        super(player);
        attacks = new ArrayList<>();
    }

    @Override
    public void enter() {
        Animation<TextureRegion> currentAnim = attacks.get(nextAttack);
        player.setAnimations(currentAnim);
        player.setAttacking(true);
        player.setStateTime(0);
    }

    @Override
    public void exit() {
        nextAttack = 0;
        attackTime = 0;
        player.setStateTime(0);
        player.setAttacking(false);
    }

    @Override
    public void update(float delta) {
        attackTime += delta;
        player.attack = Gdx.input.isKeyJustPressed(Input.Keys.K) || Gdx.input.isTouched();

        if (player.isAttacking()) {
            attackTime = 0;
            if (player.isAnimationFinished()) {
                nextAttack++;
                if (nextAttack >= attacks.size()) nextAttack = 0;
                enter();
            } else {
                player.setAttacking(true);
            }
        }
    }

}
