package com.nickgarvey.bb.wwu;

import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class WeatherScreen extends MainScreen {

	final String GOOGLE_API_URL_BASE = "http://www.google.com/ig/api?weather=";

	/**
	 * Creates a new MyScreen object
	 */
	public WeatherScreen() {
		// Set the displayed title of the screen
		System.out.println("DEBUG: NG Test");

		setTitle("Weather Wallpaper");

		String weather = null;
		try {
			weather = WeatherFetcher.getCurrentWeather("14623");
		} catch (WeatherFetchException e) {
			e.printStackTrace();
		}

		add(new RichTextField("Weather: " + weather));

	}

}
