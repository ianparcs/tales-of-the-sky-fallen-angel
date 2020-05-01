package com.sparcsky.tofts.fallenangel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sparcsky.tofts.fallenangel.game.FallenAngel;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
        config.width = 1024;
        config.height = 720;
        config.forceExit = false;
        config.y = 0;
   //  config.fullscreen = true;
        config.title = "Tales of the Sky: The Fallen Knight";
        config.vSyncEnabled = true;
        new LwjglApplication(new FallenAngel(), config);
    }
}
