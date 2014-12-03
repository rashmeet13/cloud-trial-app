package com.impetus.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceFactory {

	private static final String PROPERTIES_FILE_NAME = "dbconfig";
	private static Map<String, String> dbConfigMap = new HashMap<String, String>();
	
	public static String getProperty(String paramString)
	{
		return (String)dbConfigMap.get(paramString);
	}


	static
	{
		ResourceBundle localResourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
		Enumeration localEnumeration = localResourceBundle.getKeys();
		while (localEnumeration.hasMoreElements())
		{
			String str1 = (String)localEnumeration.nextElement();
			String str2 = (String)localResourceBundle.getObject(str1);
			dbConfigMap.put(str1, str2);
		}
	}
	
	private DBCPDataSourceFactory() {
		this.ds = new BasicDataSource();
		this.ds.setDriverClassName(getProperty(ConfigConstants.DERBY_DRIVER_CLASS));
		this.ds.setUrl(getProperty(ConfigConstants.DERBY_DB_URL));
		//this.ds.setUsername(applianceConfig.getString(ConfigConstants.DERBY_DB_USER));
		//this.ds.setPassword(applianceConfig.getString(ConfigConstants.DERBY_DB_PASSWORD));

	/*	// the settings below are optional -- dbcp can work with defaults
		this.ds.setMinIdle(5);
		this.ds.setMaxIdle(20);
		this.ds.setMaxOpenPreparedStatements(180);*/

	}

	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}

	private final BasicDataSource ds;


	private static DBCPDataSourceFactory datasource;


	public static DBCPDataSourceFactory getInstance() {
		if (datasource == null) {
			datasource = new DBCPDataSourceFactory();
			return datasource;
		}
		return datasource;

	}
}
