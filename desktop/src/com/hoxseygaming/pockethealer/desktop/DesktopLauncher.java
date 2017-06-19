package com.hoxseygaming.pockethealer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hoxseygaming.pockethealer.PocketHealer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PocketHealer.WIDTH;
		config.height = PocketHealer.HEIGHT;
		config.title = PocketHealer.TITLE;
		new LwjglApplication(new PocketHealer(), config);
	}
}
