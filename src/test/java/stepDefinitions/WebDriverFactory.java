package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	
	 public static boolean isFirefox = false; // Set this based on your browser detection logic
	
	
	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		
		switch (browser.toLowerCase()) {
		 case "chrome":
             WebDriverManager.chromedriver().setup(); // Ensure driver executable is set up
             driver = new ChromeDriver();
             break;
         case "firefox":
        	 WebDriverManager.firefoxdriver().setup(); 
        	 isFirefox = true; // Set the flag for Firefox
        	 FirefoxProfile profile = new FirefoxProfile();
             profile.setPreference("dom.webnotifications.enabled", false);
             profile.setPreference("toolkit.cosmeticAnimations.enabled", false);
             
             FirefoxOptions options = new FirefoxOptions();
             options.setProfile(profile);
             options.addArguments("--width=1920", "--height=1080");
             options.setCapability("moz:webdriverClick", true); 
//             options.addArguments("--headless");
//             options.setCapability("marionette", true); // Use GeckoDriver (Marionette)
//             options.setCapability("dom.webnotifications.enabled", false); // Disable notifications

        	 
             driver = new FirefoxDriver(options);
             break;
         case "edge":
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
             break;
         default:
             throw new IllegalArgumentException("Browser not supported: " + browser);
     }
		return driver;
	}

}
