package com.solar.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.solar.game.SolarSystemTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SolarSystemTest.WIDTH;
		config.height = SolarSystemTest.HEIGHT;
		config.title = SolarSystemTest.TITLE;
		new LwjglApplication(new SolarSystemTest(), config);
	}
}
