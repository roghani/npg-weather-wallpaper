package com.nickgarvey.bb.wwu;

import net.rim.device.api.xml.jaxp.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GoogleWeatherAPIHandler extends DefaultHandler {

	private boolean inCurrentConditions;

	private static final String CURR_COND_TAG = "current_conditions";

	private static final String COND_TAG = "condition";
	
	private static final String COND_DATA_ATTR = "data";

	private String weatherString = null;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (CURR_COND_TAG.equals(qName)) {
			inCurrentConditions = true;
		} else if (inCurrentConditions && COND_TAG.equals(qName)) {
			weatherString = attributes.getValue(COND_DATA_ATTR);
		}

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (CURR_COND_TAG.equals(qName)) {
			inCurrentConditions = false;
		}

	}

	/**
	 * Returns the weather string parsed from the GoogleAPI. This method must be
	 * called <em>after</em> the {@link GoogleWeatherAPIHandler} instance is
	 * passed to {@link XMLParser#parse}.
	 * 
	 * @return The parsed weather string, <code>null</code> if no value was found.
	 */
	public String getWeatherString() {
		return weatherString;
	}

}
