package stepDefinitions;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

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
	private Faker faker = new Faker();

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
		try {
			Assert.assertEquals("GME Application Form", header.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("user select start application form button")
	public void user_select_start_application_form_button() throws InterruptedException {

		page.startApplication();
		Thread.sleep(3000);
	}

	@Then("fill required fields with {string} {string} {string}")
	public void fill_required_fields_with(String string3, String string4, String string5) throws InterruptedException {
		try {

			Assert.assertEquals("Rotation Request",
					driver.findElement(By.xpath("//h2[@id='rotationRequestLabel']")).getText());
		} catch (Exception e) {
			Assert.fail("Page not navigated to Rotation Request Form" + e.getMessage());
		}

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
	public void fill_applicant_form_with_required_fields_with(String string, String string2, String string3)
			throws InterruptedException {
		try {

			Assert.assertEquals("Applicant Information",
					driver.findElement(By.xpath("//h2[@id='applicantFormLabel']")).getText());
		} catch (Exception e) {
			Assert.fail("Page not navigated to Applicant Information Form" + e.getMessage());
		}

		page.setFirstName(faker.name().firstName());
		Thread.sleep(3000);
		page.setLastName(faker.name().lastName());
		page.setEmail(faker.internet().emailAddress());
		Thread.sleep(3000);
		page.setMedicalLicense(UtilClass.getRandomIntWithLength(10));
		Thread.sleep(3000);
		page.select_Radio_Button_for_DEA();
		page.setNPI(UtilClass.getRandomIntWithLength(10));
		Thread.sleep(3000);
		page.setPGYRotation();
		page.setSSN(string3);
		page.setDOB(UtilClass.generateCurrentDate());
		Thread.sleep(3000);

	}

	@Then("select next screen on applicant form")
	public void select_next_screen_on_applicant_form() throws InterruptedException {
		page.setNextScr_on_AF();
		Thread.sleep(5000);
	}

	@Then("fill emergency contact info")
	public void fill_emergency_contact_info() throws InterruptedException {
		try {

			Assert.assertEquals("Emergency Information",
					driver.findElement(By.xpath("//h2[@id='emergencyContactLabel']")).getText());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			ExtentListener.log("Page not navigated to Emergency Contact info");
		}
		page.setECEmail(faker.internet().emailAddress());
		page.setPhone("6142342345");
		Thread.sleep(5000);
	}

	@Then("select next on emergency contact page")
	public void select_next_on_emergency_contact_page() throws InterruptedException {
		page.setECNextScr();
		Thread.sleep(5000);
	}

	@When("we fill med school info")
	public void we_fill_med_school_info() throws InterruptedException {
		page.setState("OHIO");
		page.setCity("COLUMBUS");
		page.setMedsch("OHIO STATE UNIVERSITY COLLEGE OF MEDICINE");
		page.setDegree("MD");
		page.setStrMth("August");
		page.setStrYr();
		page.setGradMnth("May");
		page.setGradyr();
		Thread.sleep(5000);
	}

	@Then("select medschl next screen")
	public void select_medschl_next_screen() throws InterruptedException {
		page.setNextMDI();
		Thread.sleep(5000);
	}

	@Then("fill pgy info")
	public void fill_pgy_info() throws InterruptedException {
		page.setPGY("2");
		page.setBmth("August");
		page.setBGyr();
		page.setEndMth("May");
		page.setEndYr();
		page.setSpec();
		page.setInst();
		Thread.sleep(5000);
	}

	@Then("select next screen on pgy")
	public void select_next_screen_on_pgy() throws InterruptedException {
		page.setNextPGY();
		Thread.sleep(5000);
	}

	@Then("accept all the info is accurate")
	public void accept_all_the_info_is_accurate() throws InterruptedException {
		page.setCheckbx();
		Thread.sleep(5000);
	}
	
	@Then("application submitted successfully")
	public void application_submitted_successfully() {
		try {

		Assert.assertEquals("Thank you",
				driver.findElement(By.xpath("//p[normalize-space()='Thank you']")).getText());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			ExtentListener.log("Application Not submitted");
		}
	}
	
	@Then("fill required fields with empty value")
	public void fill_required_fields_with_empty_value() throws Exception {
		page.setNext_scr_loc();
		Thread.sleep(5000);
	}

	@Then("verify expected error messages displayed on page")
	public void verify_expected_error_messages_displayed_on_page() {
		try {

			Assert.assertEquals("There are some errors in your form.",
					driver.findElement(By.xpath("//p[contains(text(),'There are some errors in your form.')]")).getText());
			} catch (Exception e) {
				Assert.fail(e.getMessage());
				ExtentListener.log("Application Not submitted");
			}
	}

}
