package com.nickgarvey.bb.wwu;

public class WeatherWallpaperProcesser implements Runnable {
	
	private static UserConfigStore userDataStore = 
			UserConfigStore.getInstance();

	public void run() {

		String areaCode = userDataStore.getUserAreaCode();

		String weatherStr;

		try {
			weatherStr = WeatherFetcher.getCurrentWeather(areaCode);
		} catch (WeatherFetchException e) {
			System.err.println(
					"Error fetching weather from Google API, retrying: "
							+ e.getMessage());
			try {
				weatherStr = WeatherFetcher.getCurrentWeather(areaCode);
			} catch (WeatherFetchException e1) {
				System.err.println(
						"Second error fetching weather from Google API, giving up: "
								+ e.getMessage());
				return;
			}
		}

		int currWeather = WeatherState.convertStringToWeatherState(weatherStr);

		System.out.println("WEATHER IS :" + currWeather);

		WallpaperSetter.setWallpaper(currWeather);

	}

}
