package stepDefinitions;

import java.io.FileReader;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import listenerClasses.ExtentListener;
import nch.wcmstests.pages.GmeApplicationFormPage;
import util.UtilClass;

public class Form {
	public GmeApplicationFormPage page;
	public WebDriver driver;
	public ExtentTest test;
	public Properties p;
	private Faker faker = new Faker();

	public Form() {
		driver = WebDriverFactory.getDriver();
		page = new GmeApplicationFormPage(driver);
		test = ExtentListener.getExtentTest();
		ExtentListener.setDriver(driver);
	}
	
	@Given("user opens {string}")
	public void user_opens(String string) {

		try {
			FileReader file = new FileReader("./src/test/resources/application.properties");
			p = new Properties();
			p.load(file);
			driver.get(p.getProperty(string));
			WebElement header = driver.findElement(By.xpath("//h2[normalize-space()='GME Application Form']"));
			Assert.assertEquals("GME Application Form", header.getText());
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to GME Application Form";
			Assert.fail(errorMessage);
		}
	}

	@Then("user select start application form button")
	public void user_select_start_application_form_button() {
		try {
			page.startApplication();
			Assert.assertEquals("Rotation Request",
					driver.findElement(By.xpath("//h2[@id='rotationRequestLabel']")).getText());
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Rotation Request Form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("fill required fields on rotation request form")
	public void fill_required_fields_on_rotation_request_form() {
		try {
			Assert.assertEquals("Rotation Request",
					driver.findElement(By.xpath("//h2[@id='rotationRequestLabel']")).getText());
			page.setStart_date_loc(UtilClass.generateCurrentDate());
			page.setEnd_date_loc(UtilClass.generateRandomFutureDate());
			page.setNew_to_nch_yes_loc();
			page.setRequest_rotation_loc();
			page.setSponsoring_inst_loc();
			page.setCurrent_prog_loc();

		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@When("select next screen")
	public void select_next_screen() {
		try {
			page.setNext_scr_loc();
			Assert.assertEquals("Applicant Information",
					driver.findElement(By.xpath("//h2[@id='applicantFormLabel']")).getText());

		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Applicant Information Form: Fill all required fields ";
			Assert.fail(errorMessage + e.getMessage());
		}

	}

	@Then("fill applicant form with required fields with {string}")
	public void fill_applicant_form_with_required_fields_with(String string3) {
		try {

			Assert.assertEquals("Applicant Information",
					driver.findElement(By.xpath("//h2[@id='applicantFormLabel']")).getText());
			page.setFirstName(faker.name().firstName());
			page.setLastName(faker.name().lastName());
			page.setEmail(faker.internet().emailAddress());
			page.setMedicalLicense(UtilClass.getRandomIntWithLength(10));
			page.select_Radio_Button_for_DEA();
			page.setNPI(UtilClass.getRandomIntWithLength(10));
			page.setPGYRotation();
			page.setSSN(string3);
			page.setDOB(UtilClass.generateCurrentDate());
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form";
			Assert.fail(errorMessage + e.getMessage());
		}

	}

	@Then("select next screen on applicant form")
	public void select_next_screen_on_applicant_form() {
		try {
			page.setNextScr_on_AF();
			Assert.assertEquals("Emergency Information",
					driver.findElement(By.xpath("//h2[@id='emergencyContactLabel']")).getText());
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("fill emergency contact info")
	public void fill_emergency_contact_info() {
		try {
			page.setECEmail(faker.internet().emailAddress());
			page.setPhone("6142342345");
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("select next on emergency contact page")
	public void select_next_on_emergency_contact_page() {
		try {
			page.setECNextScr();
			Assert.assertEquals("Medical School Information",
					driver.findElement(By.xpath("//h2[@id='medicalSchoolInformationLabel']")).getText());
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@When("we fill med school info")
	public void we_fill_med_school_info() {
		try {

			Assert.assertEquals("Medical School Information",
					driver.findElement(By.xpath("//h2[@id='medicalSchoolInformationLabel']")).getText());

			page.setState();
			page.setCity();
			page.setMedsch();
			page.setDegree();
			page.setStrMth();
			page.setStrYr();
			page.setGradMnth();
			page.setGradyr();
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("select medschl next screen")
	public void select_medschl_next_screen() {
		try {
			page.setNextMDI();
			Assert.assertEquals("Post Graduate Training",
					driver.findElement(By.xpath("//h2[@id='postGraduateTrainingLabel']")).getText());
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("fill pgy info")
	public void fill_pgy_info() {
		try {

			page.setPGY();
			page.setBmth();
			page.setBGyr();
			page.setEndMth();
			page.setEndYr();
			page.setSpec();
			page.setInst();
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("select next screen on pgy")
	public void select_next_screen_on_pgy() {
		try {

			page.setNextPGY();
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("accept all the info is accurate")
	public void accept_all_the_info_is_accurate() {
		try {
			page.setCheckbx();
			page.submit();
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("application submitted successfully")
	public void application_submitted_successfully() {
		try {
			WebElement successmessage = driver.findElement(By.xpath("//p[normalize-space()='Thank you']"));
			WebDriverFactory.scrolltoView(successmessage,driver);
			WebDriverFactory.waitForElementToBeClickable(driver, successmessage, 10, 500);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.pollingEvery(Duration.ofMillis(500));
//			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(successmessage));
//		
			Assert.assertEquals("Thank you",
					successmessage.getText());
		} catch (Throwable e) {
			String errorMessage = "Application Form not submitted: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

	@Then("fill required fields with empty value")
	public void fill_required_fields_with_empty_value() {
		try {
			page.setNext_scr_loc();

			Assert.assertEquals("There are some errors in your form.", driver
					.findElement(By.xpath("//p[contains(text(),'There are some errors in your form.')]")).getText());
		} catch (Throwable e) {
			String errorMessage = "There are some errors in your form: ";
			Assert.fail(errorMessage + e.getMessage());
		}
	}

//	@Then("verify expected error messages displayed on page")
//	public void verify_expected_error_messages_displayed_on_page() {
//		try {
//
//			Assert.assertEquals("There are some errors in your form.", driver
//					.findElement(By.xpath("//p[contains(text(),'There are some errors in your form.')]")).getText());
//		} catch (Throwable e) {
//			String errorMessage = "There are some errors in your form: ";
//			logger.error(errorMessage);
//			Assert.fail(errorMessage + e.getMessage());
//		}
//	}

}
