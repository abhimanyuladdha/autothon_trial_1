package com.autothon.base;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<WebDriver>();
	public static final ThreadLocal<AndroidDriver> threadLocalAndroidDriver = new ThreadLocal<AndroidDriver>();
	public static String browser;

	public static WebDriver getDesktopDriver() {
		return threadLocalWebDriver.get();
	}

	public static AndroidDriver getMobileDriver() {
		return threadLocalAndroidDriver.get();
	}

	public static void hideWarnings() {
		System.setProperty("webdriver.chrome.silentOutput", "true");
	}

	public static void setDesktopDriver(String browser) {
		WebDriver driver;

		switch (browser.toLowerCase()) {
		case "chrome":
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "chromeHeadless":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			//WebDriverManager.chromedriver().setup();
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

	public static void setMobileDriver(String browser) throws MalformedURLException {
		switch(browser.toLowerCase())
		{
		case("chrome"):
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", "Android");
			desiredCapabilities.setCapability("appium:deviceName","demo");
			desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
			desiredCapabilities.setCapability("uiautomator2ServerLaunchTimeout", 120000);
			desiredCapabilities.setCapability("browserName", browser);
			URL url= URI.create("http://127.0.0.1:4723/").toURL();
			threadLocalAndroidDriver.set(new AndroidDriver(url,desiredCapabilities));
			break;
		}
	}

	public static void quitDesktopDriver() {
		WebDriver driver = threadLocalWebDriver.get();
		if (driver != null) {
			driver.quit();
			threadLocalWebDriver.remove();
		}
	}

	public static void quitMobileDriver() {
		AndroidDriver driver = threadLocalAndroidDriver.get();
		if (driver != null) {
			driver.quit();
			threadLocalAndroidDriver.remove();
		}
	}

}
