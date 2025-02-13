package com.autothon.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	private static ExtentReports extent;
	private static Map<String, ExtentTest> featureName = new HashMap<String, ExtentTest>();

	private static ThreadLocal<ExtentTest> featureThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> scenarioThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> stepThreadLocal = new ThreadLocal<>();

	public static void setupExtentReport() {
		if (extent == null) {
			Date currentDate=new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
			String dateToString=format.format(currentDate);
			String executionStartDate=dateToString.substring(0,10);
			String executionStartTime=dateToString.substring(12,19).replaceAll(" ","-");
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/ExtentReports/"+executionStartDate+"/"+"extentReport-"+executionStartTime+".html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
	}

	public static void createFeature(String featureUri, String name) {
		if (featureName.get(featureUri) == null) {
			ExtentTest feature = extent.createTest(name);
			featureName.putIfAbsent(featureUri, feature);
			featureThreadLocal.set(feature);
		} else {
			featureThreadLocal.set(featureName.get(featureUri));
		}

	}

	public static void createScenario(String scenarioName) {
		ExtentTest scenarioTest = featureThreadLocal.get().createNode(scenarioName);
		scenarioThreadLocal.set(scenarioTest);
	}

	public static void createStep(String stepName) {
		ExtentTest step = null;

		if (stepName.startsWith("Given")) {
			step = scenarioThreadLocal.get().createNode(Given.class, stepName);
		} else if (stepName.startsWith("When")) {
			step = scenarioThreadLocal.get().createNode(When.class, stepName);
		} else if (stepName.startsWith("Then")) {
			step = scenarioThreadLocal.get().createNode(Then.class, stepName);
		}
		stepThreadLocal.set(step);
	}

	public static void logStepResult(Status status, String customMessage) {
		ExtentTest step = stepThreadLocal.get();
		if (step != null) {
			step.log(status, customMessage);
		}
	}

	public static void flush() {
		if (extent != null) {
			extent.flush();
		}
	}
}
