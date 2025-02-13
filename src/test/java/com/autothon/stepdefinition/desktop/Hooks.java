package com.autothon.stepdefinition.desktop;

import java.net.MalformedURLException;

import com.autothon.utility.DatabaseUtils;
import org.openqa.selenium.WebDriver;

import com.autothon.base.DriverFactory;
import com.autothon.utility.PropertyManager;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	private WebDriver desktopDriver;
	private AndroidDriver mobileDriver;
	private String browser = System.getProperty("browser", "Chrome");
	private String deviceType = System.getProperty("deviceType", "mobile");

	@Before
	public void setup() throws MalformedURLException {
		PropertyManager properties = new PropertyManager();
		/*
		// Initialize database connection
		System.out.println("Initializing database connection...");
		DatabaseUtils.initialize();
		*/

		if (deviceType.equalsIgnoreCase("desktop")) {
			DriverFactory.setDesktopDriver(browser);
			desktopDriver = DriverFactory.getDesktopDriver();
			desktopDriver.get(properties.getproperty("url"));
		} else if (deviceType.equalsIgnoreCase("mobile")) {
			DriverFactory.setMobileDriver(browser);
			mobileDriver = DriverFactory.getMobileDriver();
			mobileDriver.get(properties.getproperty("url"));
		}
	}

	@After
	public void tearDown() {
		if (deviceType.equalsIgnoreCase("desktop")) {
			DriverFactory.quitDesktopDriver();
		} else if (deviceType.equalsIgnoreCase("mobile")) {
			DriverFactory.quitMobileDriver();
		}
		/*
		// Close database connection pool
		System.out.println("Closing database connection...");
		DatabaseUtils.closePool();
		*/
		ScenarioContext.getInstance().clear();
	}
}
