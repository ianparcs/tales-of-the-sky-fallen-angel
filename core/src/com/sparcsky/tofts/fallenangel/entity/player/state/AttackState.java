package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.weapon.Weapon;

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
        player.setAttacking(true);
        switchAttack();
    }

    @Override
    public void exit() {
        nextAttack = 0;
        attackTime = 0;
        player.setStateTime(0);
        player.setAttacking(false);
    }

    private void switchAttack() {
        player.setStateTime(0);
        Animation<TextureRegion> currentAnim = attacks.get(nextAttack);
        player.setAnimations(currentAnim);
    }

    @Override
    public void update(float delta) {
        attackTime += delta;

        if (player.isAnimationFinished()) player.setAttacking(false);

        if (Gdx.input.isKeyJustPressed(Input.Keys.K) || Gdx.input.isTouched()) {
            player.setAttacking(true);
        }

        if (player.isAttacking()) {
            attackTime = 0;
            if (player.isAnimationFinished()) {
                nextAttack++;
                if (nextAttack >= attacks.size()) nextAttack = 0;
                switchAttack();
            }
        }

    }

}
