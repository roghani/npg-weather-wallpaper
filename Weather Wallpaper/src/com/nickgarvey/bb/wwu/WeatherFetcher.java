package com.nickgarvey.bb.wwu;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.HttpConnection;

import net.rim.device.api.io.IOUtilities;
import net.rim.device.api.io.transport.ConnectionDescriptor;
import net.rim.device.api.io.transport.ConnectionFactory;
import net.rim.device.api.xml.jaxp.XMLParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WeatherFetcher {

	final static String GOOGLE_API_URL_BASE = "http://www.google.com/ig/api?weather=";

	/**
	 * Get the current weather from Google's weather API. Null is returned if
	 * there is a problem fetching the weather.
	 * 
	 * @param areaCode The area code of the location to get the weather for.
	 * 
	 * @return The current weather as a String, or null if an error occurs.
	 */
	public static String getCurrentWeather(String areaCode) throws WeatherFetchException {
		
		String weather = null;
		
		String urlToFetch = GOOGLE_API_URL_BASE + areaCode + ";deviceSide=true";

		ConnectionFactory connFact = new ConnectionFactory();
		ConnectionDescriptor pigConnDesc = connFact.getConnection(urlToFetch);

		InputStream googInputStream = null;

		final HttpConnection googConn = 
			(HttpConnection) pigConnDesc.getConnection();

		try {

			googConn.setRequestMethod("GET");

			googInputStream = googConn.openInputStream();

			byte[] googOutput = IOUtilities.streamToBytes(googInputStream);

			weather = parseGoogOutput(googOutput);

		} catch (IOException e) {
			e.printStackTrace();
			throw new WeatherFetchException(e.getMessage());
		} finally {
			try {
				if (googInputStream != null) {
					googInputStream.close();
				}
				googConn.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new WeatherFetchException(e.getMessage());
			}
		}

		return weather;

	}

	private static String parseGoogOutput(byte[] googOutput)
			throws IOException, WeatherFetchException {

		XMLParser parser = new XMLParser();

		GoogleWeatherAPIHandler apiHandler = new GoogleWeatherAPIHandler();

		InputSource xmlSource = new InputSource(
				new ByteArrayInputStream(googOutput));

		try {
			parser.parse(xmlSource, apiHandler);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new WeatherFetchException(e.getMessage());
		}

		return apiHandler.getWeatherString();

	}

}
