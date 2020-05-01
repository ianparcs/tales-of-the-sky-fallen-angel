package com.sparcsky.tofts.fallenangel.entity.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/19/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class JumpState extends MoveState {

    private Animation<TextureRegion> jumpAnim;
    private Animation<TextureRegion> doubleJumpAnim;

    private boolean jump;
    private boolean doubleJump;

    public JumpState(Player player) {
        super(player);

        jumpAnim = new Animation<>(0.09f, atlas.findRegions("adventurer-jump"), Animation.PlayMode.NORMAL);
        doubleJumpAnim = new Animation<>(0.09f, atlas.findRegions("adventurer-smrslt"), Animation.PlayMode.LOOP);

    }

    @Override
    public void enter() {
        if (player.getPreviousState() == StateType.FALL) {
            doubleJumpState();
            return;
        }
        player.jump();
        player.setStateTime(0);
        player.setAnimations(jumpAnim);
        jump = true;
        doubleJump = false;
    }

    @Override
    public void exit() {
        jump = false;
    }

    private void doubleJumpState() {
        player.doubleJump();
        player.setAnimations(doubleJumpAnim);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.changeState(StateType.AIR_ATTACK);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !player.isDoubleJumped()) {
            doubleJumpState();
        }

        if (player.isFalling()) {
            player.changeState(StateType.FALL);
        }
    }

    public boolean isDoubleJump() {
        return doubleJump;
    }

    public void setDoubleJump(boolean doubleJump) {
        this.doubleJump = doubleJump;
    }

    @Override
    public StateType getType() {
        return StateType.JUMP;
    }
}
