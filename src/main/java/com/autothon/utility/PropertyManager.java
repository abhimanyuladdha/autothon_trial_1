package com.autothon.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyManager {

	private static final String CONFIG_FILE_PATH  = "/src/test/resources/ConfigurationFiles/config.properties";
	private static final Properties prop = new Properties();
	private static boolean isPropertiesLoaded = false;

	
	public synchronized String getproperty(String key) {
		if(!isPropertiesLoaded) {
			loadProperties();
		}
		return prop.getProperty(key);
	}

    public synchronized String getproperty(String key, String value) {
        if(!isPropertiesLoaded) {
            loadProperties();
        }
        return prop.getProperty(key, value);
    }
	
    private void loadProperties() {
        try (FileInputStream input = new FileInputStream(Paths.get("").toAbsolutePath().toString() + CONFIG_FILE_PATH)) {
            prop.load(input);
            isPropertiesLoaded = true;
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}