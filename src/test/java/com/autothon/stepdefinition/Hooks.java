package com.autothon.stepdefinition;

import org.openqa.selenium.WebDriver;

import com.autothon.base.DriverFactory;
import com.autothon.utility.PropertyManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	private WebDriver driver;
	private String browser = System.getProperty("browser", "chrome"); // add this to POM

	@Before
	public void setup() {
		PropertyManager properties = new PropertyManager();
		DriverFactory.setDriver(browser);
		driver = DriverFactory.getDriver();
		driver.get(properties.getproperty("url"));
	}

	@After
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
