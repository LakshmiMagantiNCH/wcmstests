package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		
		switch (browser.toLowerCase()) {
		 case "chrome":
//             WebDriverManager.chromedriver().setup(); // Ensure driver executable is set up
             driver = new ChromeDriver();
             break;
         case "firefox":
//             WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
             break;
         case "edge":
//             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
             break;
         default:
             throw new IllegalArgumentException("Browser not supported: " + browser);
     }
		return driver;
	}

}
