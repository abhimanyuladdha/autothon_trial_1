package com.autothon.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class testRunnerDesktop {

	@CucumberOptions(features = "src/test/resources/FeatureFiles", glue = "com.autothon.stepdefinition", tags = "@desktopScenario", plugin = {
			"pretty", "com.autothon.utility.TestEventListener" })
	public static class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

		@Override
		@DataProvider(parallel = false)
		public Object[][] scenarios() {
			return super.scenarios();
		}
	}

	// mvn test -Dbrowser=Chrome -DdeviceType=desktop -DthreadCount=1 -Dcucumber.filter.tags="@desktopScenario"
}
