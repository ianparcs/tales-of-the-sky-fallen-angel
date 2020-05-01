package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.weapon.Weapon;

/**
 * Created by Ian Jasper Parcon on 4/18/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class GroundAttackState extends AttackState {

    public GroundAttackState(Player player) {
        super(player);
    }

    @Override
    public void enter() {
        if (player.getWeapon() != null) {
            Weapon weapon = player.getWeapon();
            weapon.setAwake(true);
            attacks = weapon.getAttacks();
        }
        super.enter();
    }


    @Override
    public void exit() {
        if (player.getWeapon() != null)
            super.exit();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (player.getWeapon() != null) {
            Weapon weapon = player.getWeapon();
            weapon.setAwake(true);
        }

        if (attackTime >= 0.1F && !player.isAttacking()) {
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
