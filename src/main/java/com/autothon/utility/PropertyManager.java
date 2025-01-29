package com.autothon.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	Properties prop;
	static FileInputStream input;
	public static String testData = "/src/test/resources/testproperties/config.properties";
	private static File currentDirectory = new File(new File("").getAbsolutePath());

	public synchronized String getproperty(String key) {
		prop = new Properties();
		try {
			input = new FileInputStream(currentDirectory + testData);
			prop.load(input);
			//input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}