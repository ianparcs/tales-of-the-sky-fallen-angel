package com.sparcsky.tofts.fallenangel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sparcsky.tofts.fallenangel.FallenAngel;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1920;
        config.height = 1080;
        config.forceExit = false;
        config.title = "BSP";
        new LwjglApplication(new FallenAngel(), config);
    }
}
