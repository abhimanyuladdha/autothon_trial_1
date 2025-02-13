# EagleEye
----------------------------------------------

• This is a Maven based Cucumber framework which supports automating UI, API, Mobile and Database.

• This framework supports page object model design pattern.

• pom.xml should have everything you need to create and run the tests. All required dependencies, plugins and profiles are added.

-----------------------
Framework Structure:
-----------------------


The package src/main/java/com.autothon.base contains the following classes:

• DriverFactory - 

The package src/main/java/com.autothon.utility contains the following classes:

• CsvUtils : This framework gives the flexibility to read the data from CSV files and this class provides the generic methods for read csv operations.

• DatabaseUtils : 

• EncryptionUtils :

• ExcelUtils : This framework gives the flexibility to read and write data from/to Excel files and this class provides the generic methods for read/write excel operations.

• ExtentReport : 

• GenericMethods : This class contains the Selenium methods generalised to be reused in the framework, avoids rewriting the methods on every use.

• HttpUtils - This class contains the Rest assured methods generalised to be reused in the framework, avoids rewriting the api calls on every use.

• JsonOperations : This class contains the methods to extract json data, validate the responses overall gives the comfort zone for validating APIs

• PropertyManager : This class makes a call to fetch testdata from property files.

• TestEventListener : 

• WaitLib : This class have the customized methods to use the selenium synchronizations, implicit, explicit and fluent waits.

The folder src/test/java/com.autothon.businesslibraries contains the following packages:

The folder src/test/java/com.autothon.pages.desktop contains the following packages:

The folder src/test/java/com.autothon.pages.mobile contains the following packages:

The folder src/test/java/com.autothon.runners contains the following packages:

The folder src/test/java/com.autothon.stepdefinition.desktop contains the following packages:

The folder src/test/java/com.autothon.stepdefinition.mobile contains the following packages:

The folder src/test/resources/ contains the following folders

• ConfigurationFiles

• FeatureFiles

• JSONPayload

• TestData

------------------------------
Steps to execute this project
------------------------------

Pre-requisites
---------------

• JAVA SDK 1.8 or higher

• Maven CLI

• Cucumber Plugin installed in IDE

• Appium server (v2.15.0) --> To be started using CMD

• Android Studio (To monitor Test case execution for Mobile Test Scenarios)

• A Virtual Device with name 'demo'

Steps
------

• Clone the project to local.

• Got an command line or any IDE that supports JAVA & Maven dependencies.

• We may need to import the Maven dependencies (Scope got set to Compile for newly added dependencies in pom.xml).

• We can run corresponding runner files from IDE after downloading the dependencies.

• Test Result will be captured in target folder, and Extent reports are captured under ExtentReports folder, in the execution date folder.