package com.autothon.stepdefinition.mobile;

import com.autothon.pages.mobile.MobileHomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeStepDefMobile {
	MobileHomePage mobileHomePage = new MobileHomePage();
	
	@Given("User launch the application in mobile mode")
	public void launchMobileApp() {
		mobileHomePage.launchApp();
	}
	
	@When("User searches for {string} in the search box in mobile mode")
	public void searchTextinSearchBox(String text)
	{
		mobileHomePage.enterTextInSearchBoxAndHitEnterBtn(text);
	}
	
	@When("User clicks on Advertising button on Homepage in mobile mode")
	public void clickOnAdvertisingButton()
	{
		mobileHomePage.clickAdvertisingButton();
	}
		
}
