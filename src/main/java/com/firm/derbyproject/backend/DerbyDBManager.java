package com.firm.derbyproject.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.firm.derbyproject.utility.ConfigurationHelper;

public class DerbyDBManager {
	
	private static final Logger logger = Logger.getLogger(DerbyDBManager.class);
	
	/**
	 * Retrieve connection object
	 */
	public  Connection getConnection(){
		Connection connection = null;
		try{
			Properties properties = ConfigurationHelper.loadProperties();
			Class.forName(properties.getProperty("derby_driver")).newInstance();
			String url = properties.getProperty("derby_url");
			String username = properties.getProperty("derby_username");
			String password = properties.getProperty("derby_password");
			connection = DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException e){
			logger.error("it's impossible to load the apache derby's driver");
		} catch (InstantiationException e) {
			logger.error("Instatiation error");
		} catch (IllegalAccessException e) {
			logger.error("it's impossible to access to database");
		} catch (SQLException e) {
			logger.error("it's impossible to access to database");
		}
		return connection;
	}

}
