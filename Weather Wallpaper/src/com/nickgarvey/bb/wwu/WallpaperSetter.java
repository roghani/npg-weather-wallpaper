package com.nickgarvey.bb.wwu;

import java.io.IOException;

import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.blackberry.api.homescreen.HomeScreen;

public class WallpaperSetter {
	
	private static WallpaperStore store = WallpaperStore.getInstance();

	/**
	 * Sets the device wallpaper based on the weather state.
	 * 
	 * @param weatherState
	 *            The weather state used to determine which wallpaper to set.
	 */
	public static void setWallpaper(int weatherState) {
		
		String wallpaperFilePath = store.getWallpaperForWeather(weatherState);

		if (fileExists(wallpaperFilePath)) {
			HomeScreen.setBackgroundImage(wallpaperFilePath);
		}
		
	}
	
	private static boolean fileExists(String filename) {
		
		try {
			
			if (filename == null)
				return false;
			
			Connection conn = Connector.open(filename);

			if (conn instanceof FileConnection) {
				return ((FileConnection) conn).exists();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

}
