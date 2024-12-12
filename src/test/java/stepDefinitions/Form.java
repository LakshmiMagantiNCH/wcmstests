package stepDefinitions;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import listenerClasses.ExtentReportListener;
import nch.wcmstests.pages.GmeApplicationFormPage;


public class Form {
	public ExtentTest test = ExtentReportListener.getTest();
	public GmeApplicationFormPage page;
	public WebDriver driver;
	public Properties p;
	
	private static Logger logger = LogManager.getLogger(Form.class);

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		page = new GmeApplicationFormPage(driver);
	}

	@After
	public void teardown() {
		driver.quit();
	}


	@Given("user opens {string}")
	public void user_opens(String string) throws Exception {
			FileReader file = new FileReader("./src/test/resources/application.properties");
			p = new Properties();
			p.load(file);
			driver.get(p.getProperty(string));
			test.info("loading GME Application Form Page");
			Thread.sleep(5000);
			WebElement header = driver.findElement(By.xpath("//h2[normalize-space()='GME Application Form']"));
			Assert.assertEquals("GME Application Form", header.getText());
			test.pass("Successfully landed on GME Form");
	}

	
	@Then("user select start application form button")
	public void user_select_start_application_form_button() throws InterruptedException {

		page.startApplication();
		Thread.sleep(3000);
	}

	
	@Then("fill required fields with {string} {string} {string}")
	public void fill_required_fields_with(String string3, String string4, String string5)
			throws InterruptedException {
		page.setStart_date_loc(UtilClass.generateCurrentDate());
		Thread.sleep(3000);
		page.setEnd_date_loc(UtilClass.generateRandomFutureDate());
		Thread.sleep(3000);
		page.setNew_to_nch_yes_loc();
		page.setRequest_rotation_loc(string3);
		page.setSponsoring_inst_loc(string4);
		page.setCurrent_prog_loc(string5);
		test.info("filled required form");
		Thread.sleep(3000);
	}

	@When("select next screen")
	public void select_next_screen() throws InterruptedException {
		page.setNext_scr_loc();
		Thread.sleep(3000);
	}
}
