package com.autothon.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class testRunnerMobile {

	@CucumberOptions(features = "src/test/resources/FeatureFiles", glue = "com.autothon.stepdefinition", tags = "@mobileScenario", plugin = {
			"pretty", "com.autothon.utility.TestEventListener" })
	public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

		@Override
		@DataProvider(parallel = false)
		public Object[][] scenarios() {
			return super.scenarios();
		}
	}
	
	// mvn test -Dbrowser=Chrome -DdeviceType=mobile -DthreadCount=1 -Dcucumber.filter.tags="@mobileScenario"
}
