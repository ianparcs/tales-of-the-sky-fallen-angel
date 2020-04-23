package com.sparcsky.tofts.fallenangel.collision;

import com.sparcsky.tofts.fallenangel.entity.Wall;
import com.sparcsky.tofts.fallenangel.entity.player.Player;

/**
 * Created by Ian Jasper Parcon on 4/22/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public interface ICollidable {

    void collide(Player player);
    void collide(Wall wall);
}
