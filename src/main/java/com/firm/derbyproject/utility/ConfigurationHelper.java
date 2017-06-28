package com.firm.derbyproject.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurationHelper {
	
	private static final Logger logger = Logger.getLogger(ConfigurationHelper.class);
	
	public static Properties loadProperties(){
		Properties properties = new Properties();
		//Read file from configuration file
		InputStream input = ConfigurationHelper.class.getResourceAsStream("/configuration.properties");
		try {
			if (input==null){
				throw new IOException();
			}
			properties.load(input);
		} catch (IOException e) {
			logger.error("it's impossible to read configuration file");
		}
		return properties;
	}

}
