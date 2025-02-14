package com.autothon.pages.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.autothon.base.DriverFactory;
import com.autothon.stepdefinition.desktop.ScenarioContext;
import com.autothon.utility.ExtentReport;
import com.autothon.utility.GenericMethods;
import com.autothon.utility.WaitLib;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class MobileHomePage {
	private final AndroidDriver driver;
	private ScenarioContext context;

	public MobileHomePage() {
		this.context = ScenarioContext.getInstance();
		this.driver = DriverFactory.getMobileDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@name='q']")
	WebElement textbox_Search;
	
	@FindBy(xpath = "//a[contains(text(),'Advertising')]")
	WebElement btn_advertising;

	public void launchApp() {
		context.setContext("Title", driver.getTitle());
		ExtentReport.logStepResult(Status.PASS, "Application Launched");
		WaitLib.pause(10);
	}
	
	public void enterTextInSearchBoxAndHitEnterBtn(String searchText)
	{
		WaitLib.eWait(textbox_Search, driver, 30, "Search Box");
		GenericMethods.sendText(driver,textbox_Search, searchText, "Search Box");
		GenericMethods.hitEnterButton(textbox_Search);
		WaitLib.pause(5);
	}
	
	public void clickAdvertisingButton()
	{
		WaitLib.pause(10);
		GenericMethods.scrollUsingJavaScriptToWebElement(btn_advertising, driver, "Advertising Button");
		GenericMethods.clickUsingJavaScript(driver, btn_advertising, "Advertising Button");
		WaitLib.pause(5);
	}

}
