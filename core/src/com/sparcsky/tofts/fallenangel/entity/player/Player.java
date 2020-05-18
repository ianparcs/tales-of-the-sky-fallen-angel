package com.sparcsky.tofts.fallenangel.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.sparcsky.tofts.fallenangel.asset.Asset;
import com.sparcsky.tofts.fallenangel.entity.DynamicEntity;
import com.sparcsky.tofts.fallenangel.entity.player.state.AirAttackState;
import com.sparcsky.tofts.fallenangel.entity.player.state.DieState;
import com.sparcsky.tofts.fallenangel.entity.player.state.FallState;
import com.sparcsky.tofts.fallenangel.entity.player.state.GroundAttackState;
import com.sparcsky.tofts.fallenangel.entity.player.state.HurtState;
import com.sparcsky.tofts.fallenangel.entity.player.state.IdleState;
import com.sparcsky.tofts.fallenangel.entity.player.state.JumpState;
import com.sparcsky.tofts.fallenangel.entity.player.state.PlayerState;
import com.sparcsky.tofts.fallenangel.entity.player.state.RunState;
import com.sparcsky.tofts.fallenangel.entity.player.state.SlideState;
import com.sparcsky.tofts.fallenangel.entity.player.state.StateType;
import com.sparcsky.tofts.fallenangel.entity.weapon.Weapon;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.factory.PlayerBodyBuilder;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

import java.util.HashMap;

public class Player extends DynamicEntity implements PhysicsObject, Equipper {

    private boolean attack;
    private Sprite sprite;
    private Weapon weapon;
    private Asset asset;
    private Body body;

    private HashMap<StateType, PlayerState> states;
    private PlayerState currentState;
    private PlayerState previousState;
    private GameWorld world;

    private RevoluteJoint weaponHolder;
    private PlayerBodyBuilder bodyBuilder;
    public float health;

    public Player(Asset asset) {
        this.asset = asset;
        states = new HashMap<>();
        sprite = new Sprite();
        health = 100;
    }

    @Override
    public void define(GameWorld world) {
        this.world = world;

        bodyBuilder.buildBaseBody(world.getWorld(), x, y);
        bodyBuilder.buildArm("right_arm");
        bodyBuilder.buildArm("left_arm");
        bodyBuilder.buildTorso();
        bodyBuilder.buildFeet();

        body = bodyBuilder.build();
        body.setUserData(this);


        setX(body.getPosition().x - width / 2f);
        setY(body.getPosition().y - height / 2f);

        initState();
    }

    @Override
    public void beginCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {

    }

    @Override
    public void endCollision(PhysicsObject physicsObject) {

    }

    private void initState() {
        states.put(StateType.GROUND_ATTACK, new GroundAttackState(this));
        states.put(StateType.AIR_ATTACK, new AirAttackState(this));
        states.put(StateType.SLIDE, new SlideState(this));
        states.put(StateType.IDLE, new IdleState(this));
        states.put(StateType.HURT, new HurtState(this));
        states.put(StateType.FALL, new FallState(this));
        states.put(StateType.JUMP, new JumpState(this));
        states.put(StateType.DIE, new DieState(this));
        states.put(StateType.RUN, new RunState(this));

        currentState = states.get(StateType.IDLE);
        changeState(StateType.IDLE);
    }

    public void changeState(StateType type) {
        previousState = currentState;
        PlayerState newState = states.get(type);
        currentState.exit();
        newState.enter();
        currentState = newState;
    }

    public void jump() {
        setVelocityY(25);
    }

    @Override
    public void update(float delta) {
        currentState.update(delta);
        currentFrame = animation.getKeyFrame(stateTime += delta);

        x = body.getPosition().x - width / 2f;
        y = body.getPosition().y - height / 2f;
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

    @Override
    public void equip(Weapon weapon) {
        unEquipWeapon();

        setWeapon(weapon);
        weapon.setEquipper(this);
        weapon.define(world);

        weaponHolder = (RevoluteJoint) world.createJoint(createWeaponHolder());

        weaponFlip();
    }

    private void unEquipWeapon() {
        if (weapon == null || weaponHolder == null) return;
        world.destroy(weaponHolder);
        world.destroy(weapon.getBody());
    }

    private void weaponFlip() {
        if (flip) {
            weaponHolder.setLimits(0, 360 * MathUtils.degreesToRadians);
            weapon.getBody().setTransform(weapon.getBody().getPosition(), 360 * MathUtils.degreesToRadians);

        } else {
            weaponHolder.setLimits(0, 180 * MathUtils.degreesToRadians);
            weapon.getBody().setTransform(weapon.getBody().getPosition(), 180 * MathUtils.degreesToRadians);
        }
    }

    @Override
    public void setFlip(boolean flip) {
        super.setFlip(flip);
        weaponFlip();
    }

    private RevoluteJointDef createWeaponHolder() {
        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.enableLimit = true;
        jointDef.enableMotor = true;
        jointDef.motorSpeed = 30;
        jointDef.maxMotorTorque = 50;
        jointDef.initialize(body, weapon.getBody(), body.getPosition());
        return jointDef;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    private void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    public void slowDampingFall(float value) {
        body.setLinearDamping(value);
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
        IdleState idleState = (IdleState) states.get(StateType.IDLE);
        return idleState.onGround();
    }

    public void setOnGround(boolean onGround) {
        IdleState idleState = (IdleState) states.get(StateType.IDLE);
        idleState.setOnGround(onGround);
    }

    public void doubleJump() {
        JumpState jumpState = (JumpState) states.get(StateType.JUMP);
        jumpState.setDoubleJump(true);
        setVelocityY(20);
    }

    public StateType getCurrentState() {
        return currentState.getType();
    }

    public StateType getPreviousState() {
        return previousState.getType();
    }

    public void setBodyBuilder(PlayerBodyBuilder bodyBuilder) {
        this.bodyBuilder = bodyBuilder;
    }

    public boolean isDoubleJumped() {
        JumpState jumpState = (JumpState) states.get(StateType.JUMP);
        return jumpState.isDoubleJump();
    }

    public boolean isDead() {
        return health <= 0;
    }
}
