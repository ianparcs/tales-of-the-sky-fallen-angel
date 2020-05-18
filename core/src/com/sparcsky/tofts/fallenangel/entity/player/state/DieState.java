package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 5/4/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class DieState extends PlayerState {

    private Animation<TextureRegion> dieAnim;

    public DieState(Player player) {
        super(player);
        dieAnim = new Animation<>(0.15f, atlas.findRegions("adventurer-die"), Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter() {
        player.setStateTime(0);
        player.setAnimations(dieAnim);
        player.getBody().setAwake(false);
    }

    @Override
    public void exit() {
        System.out.println("df");
    }

    @Override
    public void update(float delta) {
        System.out.println("df");
    }

    @Override
    public StateType getType() {
        return StateType.DIE;
    }
}
