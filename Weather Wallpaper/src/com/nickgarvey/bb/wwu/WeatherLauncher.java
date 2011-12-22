package com.nickgarvey.bb.wwu;

import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a graphical user
 * interface.
 */
public class WeatherLauncher extends UiApplication {
	
	private static long MILLIS_TO_RECHECK = 10 * 1000L;
	
	/**
	 * Entry point for application
	 * 
	 * @param args
	 *            Command line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create a new instance of the application and make the currently
		// running thread the application's event dispatch thread.
		
		WeatherLauncher launcher = new WeatherLauncher();
		new WeatherWallpaperProcesser().run();
		launcher.enterEventDispatcher();
		
		
	}

	/**
	 * Creates a new MyApp object
	 */
	public WeatherLauncher() {

		// Push a screen onto the UI stack for rendering.
		WeatherScreen screen = new WeatherScreen();
		pushScreen(screen);

	}
}
