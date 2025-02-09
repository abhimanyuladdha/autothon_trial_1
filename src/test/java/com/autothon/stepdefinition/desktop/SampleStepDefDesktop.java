package com.autothon.stepdefinition.desktop;

import com.autothon.pages.desktop.DesktopSamplePage;

import io.cucumber.java.en.Given;

public class SampleStepDefDesktop {
	DesktopSamplePage desktopSamplePage = new DesktopSamplePage();

	@Given("User launch the application in desktop mode")
	public void launchDesktopApp() {
		desktopSamplePage.launchApp();
	}
		
}
