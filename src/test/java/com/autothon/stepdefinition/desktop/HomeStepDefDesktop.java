package com.autothon.stepdefinition.desktop;

import com.autothon.pages.desktop.DesktopHomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeStepDefDesktop {
	DesktopHomePage desktopHomePage = new DesktopHomePage();

	@Given("User launch the application in desktop mode")
	public void launchDesktopApp() {
		desktopHomePage.launchApp();
	}
	
	@When("User searches for {string} in the search box in desktop mode")
	public void searchTextinSearchBox(String text)
	{
		desktopHomePage.enterTextInSearchBoxAndHitEnterBtn(text);
	}
	
	@When("User clicks on Advertising button on Homepage in desktop mode")
	public void clickOnAdvertisingButton()
	{
		desktopHomePage.clickAdvertisingButton();
	}
		
}
