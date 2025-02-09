package com.autothon.pages.mobile;

import com.autothon.base.DriverFactory;
import com.autothon.stepdefinition.desktop.ScenarioContext;
import com.autothon.utility.ExtentReport;
import com.autothon.utility.WaitLib;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class MobileSamplePage {
	private final AndroidDriver driver;
	private ScenarioContext context;

	public MobileSamplePage() {
		this.context = ScenarioContext.getInstance();
		this.driver = DriverFactory.getMobileDriver();
	}

	public void launchApp() {
		context.setContext("Title", driver.getTitle());
		ExtentReport.logStepResult(Status.PASS, "Application Launched");
		WaitLib.pause(10);
	}

}
