package com.autothon.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class GenericMethods {

	public static void sendText(WebDriver driver,WebElement element, String text, String field) {
		try {
			clickUsingJavaScript(driver, element, field);
			element.sendKeys(text);
			ExtentReport.logStepResult(Status.INFO,"User successfully entered the text " + text + " in the field: " + field);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to enter the text : " + text);
			Assert.assertTrue(false, "Failed to enter text in the field : " + field);
		}
	}

	public static void clickOnElement(WebElement element, String elementName) {
		try {
			element.click();
			ExtentReport.logStepResult(Status.INFO,"User successfully clicked on the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to clicked on the element : " + elementName);
			Assert.assertTrue(false, "Failed to click on the element : " + elementName);
		}
	}

	public static void submitElement(WebElement element, String elementName) {
		try {
			element.submit();
			ExtentReport.logStepResult(Status.INFO,"User successfully submitted the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,  "User failed to submit the element : " + elementName);
			Assert.assertTrue(false, "Failed to submit the element : " + elementName);
		}
	}

	public static void clickUsingJavaScript(WebDriver driver, WebElement element, String elementName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			ExtentReport.logStepResult(Status.INFO,"User successfully clicked on the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to clicked on the element : " + elementName);
			Assert.assertTrue(false, "Failed to click on the element : " + elementName);
		}
	}

	public static void scrollUsingJavaScriptToBottomOfPage(WebDriver driver) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			ExtentReport.logStepResult(Status.INFO,"User successfully scrolled to the bottom of page");
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to scroll to bottom of page");
			Assert.assertTrue(false, "Failed to scroll to the bottom");
		}
	}

	public static void selectByValue(WebElement element, String option, String elementName) {
		try {
			Select sel = new Select(element);
			sel.selectByValue(option);
			ExtentReport.logStepResult(Status.INFO,"User successfully selected the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to select the element : " + elementName);
			Assert.assertTrue(false, "Failed to select the element : " + elementName);
		}
	}

	public static void doubleClickOnElement(WebDriver driver, WebElement element, String elementName) {
		try {
			Actions action = new Actions(driver);
			action.doubleClick(element);
			ExtentReport.logStepResult(Status.INFO,"User successfully double clicked on the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to double click on the element : " + elementName);
			Assert.assertTrue(false, "Failed to double click on the element : " + elementName);
		}
	}

	public static String getText(WebElement element) {
		String text = element.getText();
		ExtentReport.logStepResult(Status.INFO,"User fetches the text from the element : " +element+" as : "+text);
		return text;
	}

	public static boolean elementIsDisplayed(WebElement element) {
		boolean value = element.isDisplayed();
		return value;
	}

	public static void actionsOnAlerts(WebDriver driver, String actionName, String value) {
		switch (actionName) {
		case ("Dismiss"):
			driver.switchTo().alert().dismiss();
			break;
		case ("Accept"):
			driver.switchTo().alert().accept();
			break;
		case ("Sendkeys"):
			driver.switchTo().alert().sendKeys(value);
			driver.switchTo().alert().accept();
			break;
		}
		ExtentReport.logStepResult(Status.INFO,"User successfully performed the action " + actionName + " on the alert");
	}

	public static void switchToFrame(WebDriver driver, String frameName) {
		try {
			driver.switchTo().frame(frameName);
			ExtentReport.logStepResult(Status.INFO,"User successfully switched to the frame : " + frameName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to switch to the frame : " + frameName);
			Assert.assertTrue(false, "Failed to switch to the frame : " + frameName);
		}
	}

	public static void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			ExtentReport.logStepResult(Status.INFO,"User successfully switched to default content");
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to switch to the default content");
			Assert.assertTrue(false, "Failed to switch to the default content");
		}
	}

	public static boolean isPopupDisplayed(WebElement popupName) {
		if (elementIsDisplayed(popupName)) {
			return true;
		} else {
			return false;
		}
	}

	public static void closePopup(WebDriver driver, WebElement popupName, String closeButtonXpath) {
		if (isPopupDisplayed(popupName)) {
			clickOnElement(driver.findElement(By.xpath(closeButtonXpath)), "Close Popup Button");
			switchToDefaultContent(driver);
			ExtentReport.logStepResult(Status.INFO,"User successfully closed the popup");
		} else {
			ExtentReport.logStepResult(Status.INFO, "No Popup Displayed");
		}
	}

	public static void moveToElement(WebDriver driver, WebElement element, String elementName) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			ExtentReport.logStepResult(Status.INFO,"User successfully moved to the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to move to the element : " + elementName);
			Assert.assertTrue(false, "Failed to move to the element : " + elementName);
		}
	}

	public static void scrollUsingJavaScriptToWebElement(WebElement element, WebDriver driver, String elementName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			ExtentReport.logStepResult(Status.INFO,"User successfully scrolled to the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to scroll to the element : " + elementName);
			Assert.assertTrue(false, "Failed to scroll to the element : " + elementName);
		}
	}

	public static void scrollUsingJavaScriptToSetValue(WebElement element, WebDriver driver, String elementName,
			String value) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + value + "';", element);
			ExtentReport.logStepResult(Status.INFO,"User successfully set the value of the element : " + elementName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to set the value of the element : " + elementName);
			Assert.assertTrue(false, "Failed to scroll to the element : " + elementName);
		}
	}

	public static String getTitle(WebDriver driver) {
		String title = null;
		try {
			title = driver.getTitle();
			ExtentReport.logStepResult(Status.INFO,"User successfully fetch the title of page as : " + title);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to fetch the title of the page");
			Assert.assertTrue(false, "User failed to fetch the title");
		}
		return title;
	}

	public static String getCurrentURL(WebDriver driver) {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
			ExtentReport.logStepResult(Status.INFO,"User successfully fetch the URL of page as : " + currentURL);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to fetch the URL of the page");
			Assert.assertTrue(false, "User failed to fetch the URL");
		}
		return currentURL;
	}

	public static Alert getAlert(WebDriver driver) {
		return driver.switchTo().alert();
	}

	public static void acceptAlert(WebDriver driver) {
		try {
			getAlert(driver).accept();
			ExtentReport.logStepResult(Status.INFO, "User successfully clicked on Accept Alert");
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to click on Accept Alert");
			Assert.assertTrue(false, "User failed to click on Accept Alert");
		}
	}

	public static void dismissAlert(WebDriver driver) {
		try {
			getAlert(driver).dismiss();
			ExtentReport.logStepResult(Status.INFO, "User successfully clicked on Dismiss Alert");
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to click on Dismiss Alert");
			Assert.assertTrue(false, "User failed to click on Dismiss Alert");
		}
	}

	public static String getAlertText(WebDriver driver) {
		String text = null;
		try {
			text = getAlert(driver).getText();
			ExtentReport.logStepResult(Status.INFO, "User successfully gets the text of Alert as : " + text);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to get the text inside Alert");
			Assert.assertTrue(false, "User failed to get the text inside Alert");
		}
		return text;
	}

	public static void selectByValue(WebElement element, String option, WebDriver driver, String dropDownName) {
		try {
			Select select = new Select(element);
			select.selectByValue(option);
			ExtentReport.logStepResult(Status.INFO, "User successfully selected Dropdown Field : " + option + " in " + dropDownName);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL, "User failed to select Dropdown Field : " + option + " in " + dropDownName);
			Assert.assertTrue(false, "User failed to select Dropdown Field : " + option + " in " + dropDownName);
		}
	}

	public static WebDriver getHandleToWindow(String title, WebDriver driver) {
		WebDriver popup = null;
		try {
			Set<String> windowIterator = driver.getWindowHandles();
			for (String s : windowIterator) {
				String windowHandle = s;
				popup = driver.switchTo().window(windowHandle);
				if (popup.getTitle().contains(title)) {
					ExtentReport.logStepResult(Status.INFO, "User successfully switched to window : " + title);
					return popup;
				}
			}
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,  "User failed to switch to window : " + title);
			Assert.assertTrue(false, "User failed to switch to window : " + title);
		}
		return popup;
	}

	public static String getPageSource(WebDriver driver) {
		String pageSource = driver.getPageSource();
		return pageSource;
	}

	public static void navigateTo(WebDriver driver, String URL) {
		try {
			WaitLib.iWait(driver, 10);
			driver.navigate().to(URL);
			ExtentReport.logStepResult(Status.INFO, "User successfully navigated to URL : " + URL);
		} catch (Exception e) {
			ExtentReport.logStepResult(Status.FAIL,"User failed to navigate to URL : " + URL);
			Assert.assertTrue(false, "User failed to navigate to URL : " + URL);
		}
	}

	public static void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() != 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
				ExtentReport.logStepResult(Status.FAIL, "The URL is broken");
			}
			// Fetching and Printing the response code obtained
			else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
				ExtentReport.logStepResult(Status.INFO, "User verified the URL state to be OK");
			}
		} catch (Exception e) {
		}
	}
	
	public static void hitEnterButton(WebElement element)
	{
		element.sendKeys(Keys.ENTER);
		ExtentReport.logStepResult(Status.INFO, "User pressed Enter button");
	}

}
