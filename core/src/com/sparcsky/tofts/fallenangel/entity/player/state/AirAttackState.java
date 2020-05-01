package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.weapon.Weapon;

/**
 * Created by Ian Jasper Parcon on 4/20/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class AirAttackState extends AttackState {

    private Animation<TextureRegion> airAttackEnd = new Animation<>(.15f, atlas.findRegions("adventurer-air-attack-3-end"), Animation.PlayMode.NORMAL);

    public AirAttackState(Player player) {
        super(player);
        Animation<TextureRegion> airAttack0 = new Animation<>(0.09f, atlas.findRegions("adventurer-air-attack1"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack1 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack2"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack2 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack3-rdy"), Animation.PlayMode.NORMAL);
        Animation<TextureRegion> airAttack3 = new Animation<>(.07f, atlas.findRegions("adventurer-air-attack3"), Animation.PlayMode.NORMAL);

        attacks.add(airAttack0);
        attacks.add(airAttack1);
        attacks.add(airAttack2);
        attacks.add(airAttack3);
    }

    @Override
    public void enter() {
        if (player.getWeapon() != null) {
            Weapon weapon = player.getWeapon();
            weapon.setAwake(true);

            attacks = weapon.getAttacks();
        }
        super.enter();
        player.setVelocityX(0);
    }

    @Override
    public void exit() {
        super.exit();
        nextAttack = 0;
    }

    @Override
    public void update(float delta) {
        attackTime += delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.K) || Gdx.input.isTouched()) {
            player.setAttacking(true);
        } else {
            player.setAttacking(false);
        }

        if (player.onGround() && player.getBody().isAwake()) {
            player.setVelocityX(0);
            if (player.getAnimation() != airAttackEnd && nextAttack == 3) {
                player.setStateTime(0);
                player.setAnimations(airAttackEnd);
            }
            if (player.isAnimationFinished()) player.changeState(StateType.IDLE);
            return;
        }

        if (nextAttack == 2 && player.isAnimationFinished()) {
            nextAttack = 3;
            enter();
            player.setVelocity(new Vector2(0, -40));
            return;
        }

        if (player.isAttacking()) {
            attackTime = 0;
            if (nextAttack == 3) return;
            if (player.isAnimationFinished()) {
                nextAttack++;
                if (nextAttack >= attacks.size()) nextAttack = 0;
                enter();
            } else {
                player.setAttacking(true);
            }
        }

        if (attackTime >= 0.55f && !player.isAttacking() && nextAttack != 3) {
            player.changeState(StateType.FALL);
        }
        move();
    }

    @Override
    public StateType getType() {
        return StateType.AIR_ATTACK;
    }

    private void move() {
        float jumpY = player.getBody().getLinearVelocity().y;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setVelocity(new Vector2(10, jumpY));
            player.setFlip(false);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setVelocity(new Vector2(-10, jumpY));
            player.setFlip(true);
        }
    }

}
