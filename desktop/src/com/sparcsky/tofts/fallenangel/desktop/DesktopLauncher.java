package com.sparcsky.tofts.fallenangel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sparcsky.tofts.fallenangel.FallenAngel;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
        config.forceExit = false;
       // config.fullscreen = true;
        config.title = "Tales of the Sky: The Fallen Knight";

        new LwjglApplication(new FallenAngel(), config);
    }
}
