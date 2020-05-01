package com.sparcsky.tofts.fallenangel.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected abstract void update(float delta);

    protected abstract void render(SpriteBatch batch);

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

}
