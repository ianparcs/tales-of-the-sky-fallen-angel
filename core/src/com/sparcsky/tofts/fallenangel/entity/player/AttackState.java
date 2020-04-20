package com.sparcsky.tofts.fallenangel.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class AttackState extends PlayerState {

    protected Animation<TextureRegion> attack1;
    protected Animation<TextureRegion> attack2;
    protected int nextAttack;
    protected float attackTime;
    protected float attackTimeDuration = 2;
    private List<Animation<TextureRegion>> attacks;
    private Animation<TextureRegion> currentAnim;

    public AttackState(Player player) {
        super(player);
        animState = new Animation<>(.07f, atlas.findRegions("adventurer-attack1"), Animation.PlayMode.NORMAL);
        attack1 = new Animation<>(.07f, atlas.findRegions("adventurer-attack2"), Animation.PlayMode.NORMAL);
        attack2 = new Animation<>(.07f, atlas.findRegions("adventurer-attack3"), Animation.PlayMode.NORMAL);

        attacks = new ArrayList<>();
        attacks.add(animState);
        attacks.add(attack1);
        attacks.add(attack2);
    }

    @Override
    public void enter() {
        currentAnim = attacks.get(nextAttack);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            player.setAttacking(true);
        }

        if (player.isAttacking() && player.isAnimationFinished()) {
            attackTime = 0;
            nextAttack++;
            if (nextAttack >= attacks.size()) nextAttack = 0;
            enter();
        } else {
            player.setAttacking(false);
        }

        if (attackTime > 0.55f && !player.isAttacking()) {
            player.changeState(new IdleState(player));
        }
    }
}
