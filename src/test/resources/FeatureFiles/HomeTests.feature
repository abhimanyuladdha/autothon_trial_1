@HomeTests
Feature: HomeTests 

  @desktopScenario
  Scenario: Search Operation --> Desktop
    Given User launch the application in desktop mode 
    When User searches for "Abhimanyu" in the search box in desktop mode   
   
  @desktopScenario
  Scenario: Advertising Btn Click Operation --> Desktop
    Given User launch the application in desktop mode 
    When User clicks on Advertising button on Homepage in desktop mode  

  @mobileScenario
  Scenario: Search Operation --> Mobile
    Given User launch the application in mobile mode 
    When User searches for "Abhimanyu" in the search box in mobile mode   
   
  @mobileScenario
  Scenario: Advertising Btn Click Operation --> Mobile
    Given User launch the application in mobile mode 
    When User clicks on Advertising button on Homepage in mobile mode 
  
 