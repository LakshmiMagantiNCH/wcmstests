package stepDefinitions;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import listenerClasses.ExtentListener;
import nch.wcmstests.pages.GmeApplicationFormPage;


public class Form {
	public GmeApplicationFormPage page;
	public WebDriver driver;
	public Properties p;
	


	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		page = new GmeApplicationFormPage(driver);
		 ExtentListener.setDriver(driver);
	}

	@After
	public void teardown(Scenario scenario) {
		driver.quit();
	}


	@Given("user opens {string}")
	public void user_opens(String string) throws Exception {
			FileReader file = new FileReader("./src/test/resources/application.properties");
			p = new Properties();
			p.load(file);
			driver.get(p.getProperty(string));
			Thread.sleep(5000);
			WebElement header = driver.findElement(By.xpath("//h2[normalize-space()='GME Application Form']"));
			Assert.assertEquals("GME Application Form", header.getText());
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
		Thread.sleep(3000);
	}

	@When("select next screen")
	public void select_next_screen() throws InterruptedException {
		page.setNext_scr_loc();
		Thread.sleep(3000);
	}
	
	@Then("fill applicant form with required fields with {string} {string} {string}")
	public void fill_applicant_form_with_required_fields_with(String string, String string2, String string3) throws InterruptedException {
		page.setFirstName(UtilClass.generateRandomString(7));
		Thread.sleep(3000);
		page.setLastName(UtilClass.generateRandomString(7));
		page.setEmail(string);
		Thread.sleep(3000);
		page.setMedicalLicense(UtilClass.getRandomIntWithLength(10));
		Thread.sleep(3000);
		page.select_Radio_Button_for_DEA();
		page.setNPI(UtilClass.getRandomIntWithLength(10));
		Thread.sleep(3000);
		page.setPGYRotation(string2);
		page.setSSN(string3);
		page.setDOB(UtilClass.generateCurrentDate());
		Thread.sleep(3000);

	}
	
	@Then("select next screen on applicant form")
	public void select_next_screen_on_applicant_form() throws InterruptedException {
		page.setNextScr_on_AF();
		WebElement warning = driver.findElement(By.xpath("//p[contains(text(),'There are some errors in your form.')]"));
		  if (warning.isDisplayed()) {
            String errorMessage = warning.getText();
            ExtentListener.log("Error message displayed: " + errorMessage);
            Assert.fail("Test failed due to error message: " + errorMessage);
        } 
		Thread.sleep(5000);
	}
}
