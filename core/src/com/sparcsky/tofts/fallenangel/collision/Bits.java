package com.sparcsky.tofts.fallenangel.collision;

/**
 * Created by Ian Jasper Parcon on 4/23/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public interface Bits {

    short GROUND = 0x0001;
    short FEET = 0x0002;
    short ARM = 0x0004;
    short WALL = 0x0008;
    short SWORD = 0x0016;
    short TORSO = 0x0032;
    short MONSTER = 0x0064;

    short ENTITY_COLLISION = -0x0002;
}
