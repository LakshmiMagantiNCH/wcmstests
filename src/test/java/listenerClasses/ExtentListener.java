package listenerClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentListener implements Plugin, EventListener {
	private static ExtentSparkReporter sparkReporter;
	private ExtentReports extentReports;
	private ExtentTest currentScenario;
	private static WebDriver driver;
	private final Map<String, ExtentTest> featureTests = new HashMap<>();
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	private static final ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();

	public static void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public static ExtentTest getExtentTest() {
		return stepTest.get();
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		// Subscribe to Cucumber events
		publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
		publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
		publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
		publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
		publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
	}

	private void onTestRunStarted(TestRunStarted event) {
		if (extentReports == null) { // Ensure ExtentReport is initialized only once
			synchronized (ExtentListener.class) {
				if (extentReports == null) {
					// Initialize ExtentReports
					String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					String reportFileName = "TestReport_" + timestamp + ".html";
					sparkReporter = new ExtentSparkReporter(
							System.getProperty("user.dir") + "\\extent-reports\\" + reportFileName);

					sparkReporter.config().setOfflineMode(true);
					sparkReporter.config().setDocumentTitle("Automation Tests"); // Title of the report
					sparkReporter.config().setReportName("TestReport"); // name of the report
					sparkReporter.config().setEncoding("UTF-8");
					sparkReporter.config().setTimeStampFormat("EEE, MMMM dd,YYYY, hh:mm a '('zzz')'");
					sparkReporter.config().setTheme(Theme.STANDARD);

					extentReports = new ExtentReports();
					extentReports.attachReporter(sparkReporter);
					extentReports.setSystemInfo("Environment", "QA");
					extentReports.setSystemInfo("Browser name", "Chrome");

				}
			}
		}

	}

	private void onTestStepStarted(TestStepStarted event) {
		// Log the step in progress (optional)
		if (currentScenario == null)
			return;

	}

	private void onTestCaseStarted(TestCaseStarted event) {
		String featureName = event.getTestCase().getUri().toString();

		featureName = featureName.substring(featureName.lastIndexOf("/") + 1).replace(".feature", "");

		// Create a test node for the feature if not already created
		if (!featureTests.containsKey(featureName)) {
//			   featureTests.putIfAbsent(featureName, extentReports.createTest(featureName));
			currentScenario = extentReports.createTest(featureName);
			featureTests.put(featureName, currentScenario);
		}

		// Create a test node for the scenario
		currentScenario = featureTests.get(featureName).createNode("<b style='color:blue; font-size:16px;'>"+event.getTestCase().getName()+"</b>");

	}

	private void onTestStepFinished(TestStepFinished event) {
		if (currentScenario == null) {
			return;
		}

		if (event.getTestStep() instanceof PickleStepTestStep) {
			PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
			String stepText = step.getStep().getText();
			String screenshotPath = captureScreenshot();

			ExtentTest stepNode = currentScenario.createNode(stepText);
			stepTest.set(stepNode);
			switch (event.getResult().getStatus()) {
			case PASSED:
				stepNode.pass(event.getTestStep().getCodeLocation());
				if (screenshotPath != null) {
					stepNode.pass(stepText, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				}
				break;
			case FAILED:
				stepNode.fail(stepText + "\n" + event.getResult().getError());
				stepNode.fail(event.getTestStep().getCodeLocation());
				if (screenshotPath != null) {
					stepNode.fail(stepText, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				}
				break;
			case SKIPPED:
				stepNode.skip(event.getTestStep().getCodeLocation());
				break;
			case PENDING:
				stepNode.warning(stepText + " - Pending implementation");
				stepNode.warning(event.getTestStep().getCodeLocation());
				break;
			default:
				stepNode.warning(stepText + " - Unknown status");
				stepNode.warning(event.getTestStep().getCodeLocation());
				break;
			}
		} else if (event.getTestStep() instanceof HookTestStep) {
			HookTestStep hookStep = (HookTestStep) event.getTestStep();
			String hookType = hookStep.getHookType().name();

			switch (event.getResult().getStatus()) {
			case PASSED:
				currentScenario.info(hookType + " hook passed");
				break;
			case FAILED:
				currentScenario.fail(hookType + " hook failed: " + event.getResult().getError());
				break;
			case SKIPPED:
				currentScenario.skip(hookType + " hook skipped");
				break;
			default:
				currentScenario.warning(hookType + " hook result unknown");
				break;
			}
		}
	}

	public static String captureScreenshot() {
		driver = threadLocalDriver.get();
		if (driver == null) {
			System.err.println("Driver is not initialized!");
			return null; // No driver available
		}

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\"
					+ System.currentTimeMillis() + ".png";
			File destination = new File(screenshotPath);
			Files.copy(screenshot.toPath(), destination.toPath());
			return screenshotPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void onTestCaseFinished(TestCaseFinished event) {
		// Log scenario status
		if (currentScenario != null) {
			if (event.getResult().getStatus() == Status.FAILED) {
				currentScenario.fail("Scenario failed");
			} else if (event.getResult().getStatus() == Status.PASSED) {
				currentScenario.pass("Scenario passed");
			} else {
				currentScenario.skip("Scenario skipped");
			}
		}
	}

	private void onTestRunFinished(TestRunFinished event) {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

	// Utility method to log from step definitions
	public static void log(String message) {
		ExtentTest currentStepTest = stepTest.get();
		if (currentStepTest != null) {
			currentStepTest.info(message);
		} else {
			System.out.println("Test step is null. Logging failed: " + message);
		}
	}
}
