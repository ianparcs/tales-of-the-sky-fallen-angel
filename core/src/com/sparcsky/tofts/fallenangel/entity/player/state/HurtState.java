package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.entity.player.parts.Torso;

/**
 * Created by Ian Jasper Parcon on 5/4/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class HurtState extends PlayerState {

    private Animation<TextureRegion> hurtAnim;
    private float delayTimer;

    public HurtState(Player player) {
        super(player);
        Torso s = new Torso();
        hurtAnim = new Animation<>(0.15f, atlas.findRegions("adventurer-hurt"), Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter() {
        player.setStateTime(0);
        player.setAnimations(hurtAnim);
        player.getBody().setLinearVelocity(new Vector2(-player.getBody().getLinearVelocity().x, 15));

        if (player.isDead()) {
            player.changeState(StateType.DIE);
        }
    }

    @Override
    public void exit() {
        delayTimer = 0;
    }

    @Override
    public void update(float delta) {
        delayTimer += delta;

        if (player.isAnimationFinished() && !player.isDead()) {
            if (isCoolDowned()) player.changeState(StateType.IDLE);
        }
    }

    private boolean isCoolDowned() {
        return delayTimer >= 0.5f;
    }

    @Override
    public StateType getType() {
        return StateType.HURT;
    }
}
