package com.autothon.utility;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitLib {

	public static void iWait(WebDriver driver, long Sec) {
		driver.manage().timeouts().implicitlyWait(Sec, TimeUnit.SECONDS);
	}

	public static void waitUntilElementVisible(WebElement element, long Sec, WebDriver driver, String elementName) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(Sec)).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", elementName + " --> element is not visible");
			Assert.assertTrue(false, elementName + " --> element is not visible");
		}
	}

	public static void eWait(WebElement element, WebDriver driver, long Sec, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Sec));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", elementName + " --> element is not clickable");
			Assert.assertTrue(false, elementName + " --> element is not clickable");
		}
	}

	public static void waitTillVisibilityOfFrameAndSwitchToIt(WebDriver driver, int timeout, String frameName,
			String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", elementName + " --> frame is not available");
			Assert.assertTrue(false, elementName + " --> frame is not available");
		}
	}

	public static void pause(int Sec) {
		try {
			Thread.sleep(Sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
