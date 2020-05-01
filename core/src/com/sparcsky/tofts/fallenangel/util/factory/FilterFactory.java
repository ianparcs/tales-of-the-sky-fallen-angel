package com.sparcsky.tofts.fallenangel.util.factory;

import com.badlogic.gdx.physics.box2d.Filter;
import com.sparcsky.tofts.fallenangel.collision.Bits;

/**
 * Created by Ian Jasper Parcon on 4/27/2020.
 * Sparcsky Games
 * ianparcs@gmail.com
 */
public class FilterFactory {

    public static Filter createFilter(short category) {
        Filter filter = new Filter();
        filter.categoryBits = category;
        return filter;
    }

    public static Filter createFilter(short category, short mask) {
        Filter filter = createFilter(category);
        filter.maskBits = mask;
        return filter;
    }

    public static Filter createFilter(short category, short mask, short group) {
        Filter filter = createFilter(category, mask);
        filter.groupIndex = group;
        return filter;
    }
}
