package com.autothon.stepdefinition.mobile;

import com.autothon.pages.mobile.MobileSamplePage;

import io.cucumber.java.en.Given;

public class SampleStepDefMobile {
	MobileSamplePage mobileSamplePage = new MobileSamplePage();
	
	@Given("User launch the application in mobile mode")
	public void launchMobileApp() {
		mobileSamplePage.launchApp();
	}
		
}
