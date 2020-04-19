package com.sparcsky.tofts.fallenangel.entity.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
        if (!player.isAttacking()) {
            currentAnim = attacks.get(nextAttack);
            player.setAnimations(currentAnim);
            player.setAttacking(true);
            player.setStateTime(0);
        }
        player.setVelocity(new Vector2(0,0));
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float delta) {
        attackTime += delta;
        if(attackTime >= 0.5f){
            attackTime = 0;
            System.out.println("true");
        }
        if (player.isAnimationFinished() && player.isAttacking()) {
            nextAttack++;
            if (nextAttack >= attacks.size()) nextAttack = 0;
            player.setAttacking(false);
        }
    }
}
