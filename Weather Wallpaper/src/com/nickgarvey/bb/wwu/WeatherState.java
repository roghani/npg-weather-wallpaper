package com.nickgarvey.bb.wwu;

import java.util.Hashtable;

import net.rim.device.api.util.Arrays;

/**
 * TODO: Refactor this using {@link Hashtable} and looping constructs.
 * @author Nick
 *
 */
public class WeatherState {

	private static final String[] CLOUD_STRS = { "partly cloudy", "cloudy",
			"mostly cloudy" };

	private static final String[] CLEAR_STRS = { "clear" };

	private static final String[] OVERCAST_STRS = { "overcast",
			"chance of rain", "chance of storm", "chance of snow",
			"chance of tstorm", "mist", "dust", "fog", "smoke", "haze" };

	private static final String[] SUNNY_STRS = { "sunny", "partly sunny",
			"mostly sunny" };

	private static final String[] RAIN_STRS = { "scattered showers", "showers",
			"rain and snow", "freezing drizzle", "rain", "light rain", "sleet" };

	private static final String[] STORM_STRS = { "scattered showers",
			"showers", "rain and snow", "freezing drizzle", "rain",
			"light rain", "sleet" };

	private static final String[] SNOW_STRS = { "light snow", "snow", "icy",
			"flurries", "snow showers", "hail" };

	private static int currentWeather = -1;

	public final static int UNKNOWN = 0;
	public final static int CLOUDY = 1;
	public final static int CLEAR = 2;
	public final static int OVERCAST = 3;
	public final static int SUNNY = 4;
	public final static int RAINING = 5;
	public final static int STORM = 6;
	public final static int SNOW = 7;
	

	/**
	 * Converts a String to a {@link WeatherState}.
	 */
	public static int convertStringToWeatherState(String stateStr) {

		String stateStrLower = stateStr.toLowerCase();

		if (Arrays.contains(CLOUD_STRS, stateStrLower)) {
			currentWeather = WeatherState.CLOUDY;
		}
		else if (Arrays.contains(CLEAR_STRS, stateStrLower)) {
			currentWeather = WeatherState.CLEAR;
		}
		else if (Arrays.contains(OVERCAST_STRS, stateStrLower)) {
			currentWeather = WeatherState.OVERCAST;
		}
		else if (Arrays.contains(SUNNY_STRS, stateStrLower)) {
			currentWeather = WeatherState.SUNNY;
		}
		else if (Arrays.contains(CLOUD_STRS, stateStrLower)) {
			currentWeather = WeatherState.CLOUDY;
		}
		else if (Arrays.contains(RAIN_STRS, stateStrLower)) {
			currentWeather = WeatherState.RAINING;
		}
		else if (Arrays.contains(STORM_STRS, stateStrLower)) {
			currentWeather = WeatherState.STORM;
		}
		else if (Arrays.contains(SNOW_STRS, stateStrLower)) {
			currentWeather = WeatherState.SNOW;
		}
		else {
			currentWeather = WeatherState.UNKNOWN;
		}
	
		return currentWeather;
		
	}

}
