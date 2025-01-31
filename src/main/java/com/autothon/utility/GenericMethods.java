package com.autothon.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class GenericMethods {

	public void sendText(WebElement element, String text, String field) {
		try {
			element.sendKeys(text);
			//ExtentTestManager.addComment("INFO","User successfully entered the text " + text + " in the field: " + field);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to enter the text : " + text);
			Assert.assertTrue(false, "Failed to enter text in the field : " + field);
		}
	}

	public void clickOnElement(WebElement element, String elementName) {
		try {
			element.click();
			//ExtentTestManager.addComment("INFO", "User successfully clicked on the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to clicked on the element : " + elementName);
			Assert.assertTrue(false, "Failed to click on the element : " + elementName);
		}
	}

	public void submitElement(WebElement element, String elementName) {
		try {
			element.submit();
			//ExtentTestManager.addComment("INFO", "User successfully submitted the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to submit the element : " + elementName);
			Assert.assertTrue(false, "Failed to submit the element : " + elementName);
		}
	}

	public void clickUsingJavaScript(WebDriver driver, WebElement element, String elementName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			//ExtentTestManager.addComment("INFO", "User successfully clicked on the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to clicked on the element : " + elementName);
			Assert.assertTrue(false, "Failed to click on the element : " + elementName);
		}
	}

	public void scrollUsingJavaScriptToBottomOfPage(WebDriver driver) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			Assert.assertTrue(false, "Failed to scroll to the bottom");
		}
	}

	public void selectByValue(WebElement element, String option, String elementName) {
		try {
			Select sel = new Select(element);
			sel.selectByValue(option);
			//ExtentTestManager.addComment("INFO", "User successfully selected the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to select the element : " + elementName);
			Assert.assertTrue(false, "Failed to select the element : " + elementName);
		}
	}

	public void doubleClickOnElement(WebDriver driver, WebElement element, String elementName) {
		try {
			Actions action = new Actions(driver);
			action.doubleClick(element);
			//ExtentTestManager.addComment("INFO", "User successfully double clicked on the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to double click on the element : " + elementName);
			Assert.assertTrue(false, "Failed to double click on the element : " + elementName);
		}
	}

	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}

	public boolean elementIsDisplayed(WebElement element) {
		boolean value = element.isDisplayed();
		return value;
	}

	public void actionsOnAlerts(WebDriver driver, String actionName, String value) {
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
		//ExtentTestManager.addComment("INFO", "User successfully performed the action " + actionName + " on the alert");
	}

	public void switchToFrame(WebDriver driver, String frameName) {
		try {
			driver.switchTo().frame(frameName);
			//ExtentTestManager.addComment("INFO", "User successfully switched to the frame : " + frameName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to switch to the frame : " + frameName);
			Assert.assertTrue(false, "Failed to switch to the frame : " + frameName);
		}
	}

	public void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			//ExtentTestManager.addComment("INFO", "User successfully switched to default content");
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to switch to the default content");
			Assert.assertTrue(false, "Failed to switch to the default content");
		}
	}

	public boolean isPopupDisplayed(WebElement popupName) {
		if (elementIsDisplayed(popupName)) {
			return true;
		} else {
			return false;
		}
	}

	public void closePopup(WebDriver driver, WebElement popupName, String closeButtonXpath) {
		if (isPopupDisplayed(popupName)) {
			clickOnElement(driver.findElement(By.xpath(closeButtonXpath)), "Close Popup Button");
			switchToDefaultContent(driver);
			//ExtentTestManager.addComment("INFO", "User successfully closed the popup");
		} else {
			//ExtentTestManager.addComment("INFO", "No Popup Displayed");
		}
	}

	public void moveToElement(WebDriver driver, WebElement element, String elementName) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			//ExtentTestManager.addComment("INFO", "User successfully moved to the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to move to the element : " + elementName);
			Assert.assertTrue(false, "Failed to move to the element : " + elementName);
		}
	}

	public void scrollUsingJavaScriptToWebElement(WebElement element, WebDriver driver, String elementName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			//ExtentTestManager.addComment("INFO", "User successfully scrolled to the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to scroll to the element : " + elementName);
			Assert.assertTrue(false, "Failed to scroll to the element : " + elementName);
		}
	}

	public void scrollUsingJavaScriptToSetValue(WebElement element, WebDriver driver, String elementName,
			String value) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + value + "';", element);
			//ExtentTestManager.addComment("INFO", "User successfully set the value of the element : " + elementName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to set the value of the element : " + elementName);
			Assert.assertTrue(false, "Failed to scroll to the element : " + elementName);
		}
	}

	public String getTitle(WebDriver driver) {
		String title = null;
		try {
			title = driver.getTitle();
			//ExtentTestManager.addComment("INFO", "User successfully fetch the title of page as : " + title);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to fetch the title of the page");
			Assert.assertTrue(false, "User failed to fetch the title");
		}
		return title;
	}

	public String getCurrentURL(WebDriver driver) {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
			//ExtentTestManager.addComment("INFO", "User successfully fetch the URL of page as : " + currentURL);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to fetch the URL of the page");
			Assert.assertTrue(false, "User failed to fetch the URL");
		}
		return currentURL;
	}

	public Alert getAlert(WebDriver driver) {
		return driver.switchTo().alert();
	}

	public void acceptAlert(WebDriver driver) {
		try {
			getAlert(driver).accept();
			//ExtentTestManager.addComment("INFO", "User successfully clicked on Accept Alert");
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to click on Accept Alert");
			Assert.assertTrue(false, "User failed to click on Accept Alert");
		}
	}

	public void dismissAlert(WebDriver driver) {
		try {
			getAlert(driver).dismiss();
			//ExtentTestManager.addComment("INFO", "User successfully clicked on Dismiss Alert");
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to click on Dismiss Alert");
			Assert.assertTrue(false, "User failed to click on Dismiss Alert");
		}
	}

	public String getAlertText(WebDriver driver) {
		String text = null;
		try {
			text = getAlert(driver).getText();
			//ExtentTestManager.addComment("INFO", "User successfully gets the text of Alert as : " + text);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to get the text inside Alert");
			Assert.assertTrue(false, "User failed to get the text inside Alert");
		}
		return text;
	}

	public void selectByValue(WebElement element, String option, WebDriver driver, String dropDownName) {
		try {
			Select select = new Select(element);
			select.selectByValue(option);
			//ExtentTestManager.addComment("INFO","User successfully selected Dropdown Field : " + option + " in " + dropDownName);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED","User failed to select Dropdown Field : " + option + " in " + dropDownName);
			Assert.assertTrue(false, "User failed to select Dropdown Field : " + option + " in " + dropDownName);
		}
	}

	public WebDriver getHandleToWindow(String title, WebDriver driver) {
		WebDriver popup = null;
		try {
			Set<String> windowIterator = driver.getWindowHandles();
			for (String s : windowIterator) {
				String windowHandle = s;
				popup = driver.switchTo().window(windowHandle);
				if (popup.getTitle().contains(title)) {
					//ExtentTestManager.addComment("INFO", "User successfully switched to window : " + title);
					return popup;
				}
			}
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to switch to window : " + title);
			Assert.assertTrue(false, "User failed to switch to window : " + title);
		}
		return popup;
	}

	public String getPageSource(WebDriver driver) {
		String pageSource = driver.getPageSource();
		return pageSource;
	}

	public void navigateTo(WebDriver driver, String URL) {
		try {
			WaitLib.iWait(driver, 10);
			driver.navigate().to(URL);
			//ExtentTestManager.addComment("INFO", "User successfully navigated to URL : " + URL);
		} catch (Exception e) {
			//ExtentTestManager.addComment("FAILED", "User failed to navigate to URL : " + URL);
			Assert.assertTrue(false, "User failed to navigate to URL : " + URL);
		}
	}

	public void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() != 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
				//ExtentTestManager.addComment("FAILED", "The URL is broken");
			}
			// Fetching and Printing the response code obtained
			else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
				//ExtentTestManager.addComment("INFO", "User verified the URL state to be OK");
			}
		} catch (Exception e) {
		}
	}

}
