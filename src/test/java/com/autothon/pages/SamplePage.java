package com.autothon.pages;

import org.openqa.selenium.WebDriver;

import com.autothon.base.DriverFactory;
import com.autothon.stepdefinition.ScenarioContext;
import com.autothon.utility.ExtentReport;
import com.aventstack.extentreports.Status;

public class SamplePage {
	private final WebDriver driver;
	private ScenarioContext context;

	public SamplePage() {
		this.context = ScenarioContext.getInstance();
		this.driver = DriverFactory.getDriver();
	}

	public void launchApp() {
		context.setContext("Title", driver.getTitle());
		ExtentReport.logStepResult(Status.PASS, "Application Launched");
	}

}
