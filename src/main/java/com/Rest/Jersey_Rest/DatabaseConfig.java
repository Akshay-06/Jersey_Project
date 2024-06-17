package com.Rest.Jersey_Rest;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

	private Properties properties;

	public DatabaseConfig() {
		properties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find db.properties");
				return;
			}
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection connectToDatabase() {
		Connection con;
		try {
			Class.forName(getProperty("db.driver"));
			con = DriverManager.getConnection(getProperty("db.url"), getProperty("db.uname"), getProperty("db.pass"));
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
