package com.sparcsky.tofts.fallenangel.entity.weapon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.sparcsky.tofts.fallenangel.entity.monster.Monster;
import com.sparcsky.tofts.fallenangel.entity.player.Equipper;
import com.sparcsky.tofts.fallenangel.entity.player.Player;
import com.sparcsky.tofts.fallenangel.game.GameWorld;
import com.sparcsky.tofts.fallenangel.util.physics.PhysicsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Jasper Parcon on 4/23/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public abstract class Weapon implements PhysicsObject {

    protected List<Animation<TextureRegion>> attacks;
    protected Equipper equipper;
    protected Body body;

    public Weapon() {
        attacks = new ArrayList<>();
    }

    public abstract void define(GameWorld world);

    public void setEquipper(Equipper equipper) {
        this.equipper = equipper;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void onCollision(PhysicsObject physicsObject, Contact contact) {
        if (physicsObject instanceof Monster) {
            contact.setEnabled(false);
            body.setAwake(true);
            Player player = (Player) equipper;
            if (player.isAnimationFinished() && player.isAttacking()) {
                System.out.println("damage");
            }
        }
    }

    public abstract List<Animation<TextureRegion>> getAttacks();

    public void setAwake(boolean b) {
        body.setAwake(b);
    }
}
