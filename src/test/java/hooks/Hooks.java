package hooks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;

import config.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private WebDriver driver;

	@Before
	public void setup(Scenario scenario) {
//		driver = new ChromeDriver();
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		page = new GmeApplicationFormPage(driver);
//		test = ExtentListener.getExtentTest();
//		ExtentListener.setDriver(driver);

		// Extract tags from the Scenario
		Collection<String> tags = scenario.getSourceTagNames();

		// List of supported browsers
		List<String> supportedBrowsers = Arrays.asList("chrome", "firefox", "edge");
		List<String> browsersToRun = new ArrayList<>();

		for (String tag : tags) {
			String browser = tag.replace("@", "").toLowerCase(); // Remove "@" from tag
			if (supportedBrowsers.contains(browser)) {
				browsersToRun.add(browser);
			}
		}

		if (browsersToRun.isEmpty()) {
			// Default to "chrome" if no browser tag is provided
			browsersToRun.add("chrome");
		}

		// Launch tests for each browser
		for (String browser : browsersToRun) {
			try {

				System.out.println("Launching tests on: " + browser);
				driver = WebDriverFactory.initializeDriver(browser); // Initialize WebDriver for the browser
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				// Mobile resolution (e.g., iPhone 12)
				//Dimension mobileResolution = new Dimension(390, 844); // Width x Height
				//driver.manage().window().setSize(mobileResolution);
				// Optionally store driver instances in a Map if multiple browsers are needed
				// concurrently
				// map.put(browser, driver);
				// Perform setup logic if required
				// For example, navigate to the application
			} catch (Exception e) {
				System.err.println("Error initializing browser: " + browser);
				e.printStackTrace();
			}
		}
	}

	@After
	public void teardown(Scenario scenario) {
		 try {
	            if (driver != null) {
	                driver.quit();
	                System.out.println("WebDriver instance closed successfully.");
	            }
	        } catch (Exception e) {
	            System.err.println("Error during WebDriver teardown.");
	            e.printStackTrace();
	        }
	}
}
