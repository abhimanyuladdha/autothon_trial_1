package com.autothon.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<WebDriver>();
	public static String browser;

	public static WebDriver getDriver() {
		return threadLocalWebDriver.get();
	}

	public static void hideWarnings() {
		System.setProperty("webdriver.chrome.silentOutput", "true");
	}

	public static void setDriver(String browser) {
		WebDriver driver;

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "chromeHeadless":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			break;

		default:
			driver = new ChromeDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		threadLocalWebDriver.set(driver);
	}

	public static void quitDriver() {
		WebDriver driver = threadLocalWebDriver.get();
		if (driver != null) {
			driver.quit();
			threadLocalWebDriver.remove();
		}
	}

}
