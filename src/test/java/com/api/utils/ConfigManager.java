package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	public static ConfigManager manager;
	public static final Properties prop = new Properties();
	
	public ConfigManager() {
		InputStream inputStream = ConfigManager.class.getResourceAsStream("com.api.utils.ConfigManager");
		try {InputStream stream = ConfigManager.class.getResourceAsStream("/config.properties");
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigManager getInstance(){
		if(manager == null) {
			synchronized (ConfigManager.class) {
			manager = new ConfigManager(); 
			}
		}
		return manager;
	}
	
	public String getString(String key) {
		return System.getProperty(key, prop.getProperty(key));
		
	}
}
