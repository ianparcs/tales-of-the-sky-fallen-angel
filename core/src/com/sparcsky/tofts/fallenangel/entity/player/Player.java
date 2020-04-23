package com.sparcsky.tofts.fallenangel.entity.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.DynamicEntity;
import com.sparcsky.tofts.fallenangel.entity.player.state.AirAttackState;
import com.sparcsky.tofts.fallenangel.entity.player.state.DoubleJumpState;
import com.sparcsky.tofts.fallenangel.entity.player.state.FallState;
import com.sparcsky.tofts.fallenangel.entity.player.state.GroundAttackState;
import com.sparcsky.tofts.fallenangel.entity.player.state.IdleState;
import com.sparcsky.tofts.fallenangel.entity.player.state.JumpState;
import com.sparcsky.tofts.fallenangel.entity.player.state.PlayerState;
import com.sparcsky.tofts.fallenangel.entity.player.state.RunState;
import com.sparcsky.tofts.fallenangel.entity.player.state.SlideState;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.util.physics.Box2DPartsBuilder;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsBody;

import java.util.HashMap;

public class Player extends DynamicEntity implements PhysicsBody {

    public boolean attack;
    private boolean doubleJump;

    private PlayerState currentState;
    private Asset asset;

    private Body body;
    private boolean wallSliding;

    private Sprite sprite;

    private HashMap<StateType, PlayerState> states;

    public Player(Asset asset) {
        this.asset = asset;
        states = new HashMap<>();
    }

    @Override
    public void define(World world) {
        body = Box2DPartsBuilder.build(world, x, y, width, height);
        body.setUserData(this);

        sprite = new Sprite();
        initState();
    }

    private void initState() {
        states.put(StateType.GROUND_ATTACK, new GroundAttackState(this));
        states.put(StateType.DOUBLE_JUMP, new DoubleJumpState(this));
        states.put(StateType.AIR_ATTACK, new AirAttackState(this));
        states.put(StateType.SLIDE, new SlideState(this));
        states.put(StateType.IDLE, new IdleState(this));
        states.put(StateType.FALL, new FallState(this));
        states.put(StateType.JUMP, new JumpState(this));
        states.put(StateType.RUN, new RunState(this));

        currentState = states.get(StateType.IDLE);
        changeState(StateType.IDLE);
    }

    public void changeState(StateType type) {
        PlayerState newState = states.get(type);
        currentState.exit();
        newState.enter();
        currentState = newState;
        System.out.println(getCurrentState());
    }

    public void jump() {
        setVelocityY(30);
    }

    @Override
    public void update(float delta) {
        x = body.getPosition().x - width / 2f;
        y = body.getPosition().y - height / 2f;

        currentState.update(delta);
        currentFrame = animation.getKeyFrame(stateTime += delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (currentFrame != null) {
            sprite.setRegion(currentFrame);
            sprite.setSize(width, height);
            sprite.setPosition(x, y);
            sprite.flip(flip, false);
            sprite.draw(batch);
        }
    }

    public void setAnimations(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }

    public void setVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public TextureAtlas getAtlas() {
        return asset.get(Asset.ATLAS_PLAYER);
    }

    public void setVelocityY(float y) {
        float velX = body.getLinearVelocity().x;
        body.setLinearVelocity(velX, y);
    }

    public void setVelocityX(float x) {
        float velY = body.getLinearVelocity().y;
        body.setLinearVelocity(x, velY);
    }

    public boolean isAttacking() {
        return attack;
    }

    public void setAttacking(boolean attack) {
        this.attack = attack;
    }

    public Body getBody() {
        return body;
    }

    public boolean isFalling() {
        return body.getLinearVelocity().y < 0;
    }

    public boolean onGround() {
        return body.getLinearVelocity().y == 0;
    }

    public void doubleJump() {
        doubleJump = true;
        setVelocityY(30);
    }

    public void setDoubleJump(boolean doubleJump) {
        this.doubleJump = doubleJump;
    }

    public boolean isDoubleJumped() {
        return doubleJump;
    }

    public boolean isWallSliding() {
        return wallSliding;
    }

    public void setWallSliding(boolean wallSliding) {
        this.wallSliding = wallSliding;
    }

    public StateType getCurrentState() {
        return currentState.getType();
    }
}
