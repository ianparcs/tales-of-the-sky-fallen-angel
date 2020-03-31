package com.sparcsky.tofts.fallenangel.screen;

import com.badlogic.gdx.Game;

import java.util.Stack;


public class ScreenManager {

    private Stack<BaseScreen> stack;
    private Game game;

    public ScreenManager(Game game) {
        this.game = game;
        stack = new Stack<>();
    }

    public void add(BaseScreen screen) {
        if (!stack.contains(screen)) {
            stack.add(screen);
        }
    }

    public void update(float deltaTime) {
        stack.peek().update(deltaTime);
    }

    public void render(float delta) {
        stack.peek().render(delta);
    }

    public void setScreen(BaseScreen screen) {
        add(screen);
        game.setScreen(stack.peek());
    }

    public void dispose() {
        if (!stack.isEmpty()) {
            stack.peek().dispose();
        }
    }

}
