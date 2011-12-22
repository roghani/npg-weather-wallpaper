package com.nickgarvey.bb.wwu;

import com.nickgarvey.bb.wwu.util.IntMap;

/**
 * Stores the list of wallpapers for each {@link WeatherState} to the device to
 * be read on load. This class uses an {@link IntMap} as the backing map, so
 * {@link WeatherState}s should be made sure to have low integer values or
 * memory usage will be unnecessarily high.
 * 
 * @author Nick
 * 
 */
public class WallpaperStore extends ObjectStore {
	
	private IntMap weatherMap;
	
	private static WallpaperStore _instance = null;
	
	private static final long KEY = "WallpaperStore".hashCode();
	
	private WallpaperStore() {

		super(KEY);

		weatherMap = (IntMap) super.objToStore;

		setWallpaperForWeather(WeatherState.CLOUDY, "file:///store/home/user/camera/IMG-20111129-00002.jpg" );
		setWallpaperForWeather(WeatherState.OVERCAST,  "file:///store/home/user/camera/IMG-20111129-00002.jpg" );		
		setWallpaperForWeather(WeatherState.RAINING,  "file:///store/home/user/camera/IMG-20111129-00005.jpg" );
		
	}
	
	public static WallpaperStore getInstance() {

		if (_instance == null) {
			_instance = new WallpaperStore();
		}

		return _instance;

	}
	
	public String getWallpaperForWeather(int weatherState) {
		return (String)weatherMap.get(weatherState);
	}

	public void setWallpaperForWeather(int weatherState, String wallPaper) {
		weatherMap.put(weatherState, wallPaper);
		super.save();
	}
	
	public void removeWallpaperForWeather(int weatherState) {
		weatherMap.remove(weatherState);
		super.save();
	}

	protected Object getInitialObj() {
		return new IntMap();
	}

}
