package config;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
	 public static boolean isFirefox = false; // Set this based on browser detection logic
	 
	
	public static WebDriver initializeDriver(String browser) {
		WebDriver driver;
		switch (browser.toLowerCase()) {
		 case "chrome":
             WebDriverManager.chromedriver().setup(); //Automatically downloads and sets up the ChromeDriver and Ensure driver executable is set up
             driver = new ChromeDriver();
             driver.manage().deleteAllCookies();
             break;
         case "firefox":
        	 WebDriverManager.firefoxdriver().setup(); 
        	 isFirefox = true; // Set the flag for Firefox
        	 
        	 // Configure Firefox profile
        	 FirefoxProfile profile = new FirefoxProfile();
             profile.setPreference("dom.webnotifications.enabled", false);
             profile.setPreference("toolkit.cosmeticAnimations.enabled", false);
             
             // Set Firefox options
             FirefoxOptions options = new FirefoxOptions();
             options.setProfile(profile);
             options.addArguments("--width=1920", "--height=1080");
             options.setCapability("moz:webdriverClick", true); 
//             options.addArguments("--headless");
//             options.setCapability("marionette", true); // Use GeckoDriver (Marionette)
//             options.setCapability("dom.webnotifications.enabled", false); // Disable notifications

        	 
             driver = new FirefoxDriver(options);
             driver.manage().deleteAllCookies();
             break;
         case "edge":
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
             driver.manage().deleteAllCookies();
             break;
         default:
             throw new IllegalArgumentException("Browser not supported: " + browser);
     }
		webdriver.set(driver);
		return driver;
	}
	
	public static WebDriver getDriver() {
		WebDriver driver = webdriver.get();
		if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Call initializeDriver first.");
        }
		return driver ;
	}
	
	 public static void removeDriver() {
	        webdriver.get().quit();
	        webdriver.remove();
	    }

	 public static void scrolltoView(WebElement element, WebDriver driver){
//		  driver = webdriver.get();
	        try {
	            if (driver == null) {
	                throw new IllegalArgumentException("WebDriver instance is null");
	            }
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(10))
	                    .pollingEvery(Duration.ofMillis(500))
	                    .ignoring(Exception.class);

	            wait.until(ExpectedConditions.visibilityOf(element));

	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	            
	            // Extra scroll to adjust if sticky headers exist
//	            js.executeScript("window.scrollBy(0, -100);");
	            
	            Thread.sleep(500);


	        } catch (Exception e) {
	            System.err.println("Exception in scrolltoView: " + e.getMessage());
	        }
		   
		}
	 
	 // Method to wait for an element to be clickable
	    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds, int pollingInMillis) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.pollingEvery(Duration.ofMillis(pollingInMillis));
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	    
	    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds, int pollingInMillis) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.pollingEvery(Duration.ofMillis(pollingInMillis));
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }
}
