package com.autothon.pages.desktop;

import org.openqa.selenium.WebDriver;

import com.autothon.base.DriverFactory;
import com.autothon.stepdefinition.desktop.ScenarioContext;
import com.autothon.utility.ExtentReport;
import com.autothon.utility.WaitLib;
import com.aventstack.extentreports.Status;

public class DesktopSamplePage {
	private final WebDriver driver;
	private ScenarioContext context;

	public DesktopSamplePage() {
		this.context = ScenarioContext.getInstance();
		this.driver = DriverFactory.getDesktopDriver();
	}

	public void launchApp() {
		context.setContext("Title", driver.getTitle());
		ExtentReport.logStepResult(Status.PASS, "Application Launched");
		WaitLib.pause(10);
	}

}
