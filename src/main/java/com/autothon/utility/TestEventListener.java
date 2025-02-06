package com.autothon.utility;

import org.apache.commons.lang3.StringUtils;

import io.cucumber.core.exception.ExceptionUtils;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.HookTestStep;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;;

public class TestEventListener implements ConcurrentEventListener {

	public static boolean isreportStarted = false;

	// Register event handlers for Cucumber events
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
		publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
		publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
		publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
	}

	private void onTestRunStarted(TestRunStarted event) {
		if (!isreportStarted) {
			ExtentReport.setupExtentReport();
		}
	}

	private void onTestRunFinished(TestRunFinished event) {
		ExtentReport.flush();
	}

	private void onTestCaseStarted(TestCaseStarted event) {
		String featureUri = event.getTestCase().getUri().toString();
		String featureName = featureUri.split(".*/")[1];
		String scenarioName = event.getTestCase().getName();

		ExtentReport.createFeature(featureUri, featureName);
		ExtentReport.createScenario(scenarioName);
	}

	private void onTestStepStarted(TestStepStarted event) {
		String stepName = null;
		String keyWord = "Cucumber Hook Triggered";
		if (event.getTestStep() instanceof PickleStepTestStep) {
			PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
			stepName = step.getStep().getText();
			keyWord = step.getStep().getKeyword();
		} else {
			HookTestStep step = (HookTestStep) event.getTestStep();
			stepName = step.getHookType().name();
		}

		ExtentReport.createStep(StringUtils.join(keyWord, stepName, " "));
	}

	private void onTestStepFinished(TestStepFinished event) {
		Result result = event.getResult();
		Status status = result.getStatus();
		if (status == Status.FAILED) {
			Throwable error = result.getError();
			ExtentReport.logStepResult(com.aventstack.extentreports.Status.FAIL,
					StringUtils.join("Step Failed -->", ExceptionUtils.printStackTrace(error)));
		}
	}
}
