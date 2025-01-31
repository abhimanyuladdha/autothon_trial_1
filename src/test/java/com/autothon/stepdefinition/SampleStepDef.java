package com.autothon.stepdefinition;

import com.autothon.pages.SamplePage;

import io.cucumber.java.en.Given;

public class SampleStepDef {
	SamplePage samplePage = new SamplePage();

	@Given("User launch the application")
	public void launchApp() {
		samplePage.launchApp();
	}
		
}
