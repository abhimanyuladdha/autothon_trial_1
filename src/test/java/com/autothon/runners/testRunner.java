package com.autothon.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class testRunner {

	@CucumberOptions(features = "src/test/resources/FeatureFiles", 
			glue = "com.autothon.stepdefinition",
			tags = "@tag1",
			plugin = { "pretty", "html:target/cucumber-reports" }
	)
	public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

		@Override
		@DataProvider(parallel = true)
		public Object[][] scenarios(){
			return super.scenarios();
		}
	}
}
