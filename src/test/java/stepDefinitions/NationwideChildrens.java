package stepDefinitions;

import java.io.FileReader;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import config.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import listenerClasses.ExtentListener;
import nch.wcmstests.pages.NationwideChildrensPage;

public class NationwideChildrens {

	public NationwideChildrensPage page;
	public WebDriver driver;
	public ExtentTest test;
	public Properties p;

	public NationwideChildrens() {
		this.driver = WebDriverFactory.getDriver();
		page = new NationwideChildrensPage(driver);
		test = ExtentListener.getExtentTest();
		ExtentListener.setDriver(driver);
	}

	@Given("the user hovers over the {string} link on main page")
	public void the_user_hovers_over_the_link_on_main_page(String string) {
		try {
			FileReader file = new FileReader("./src/test/resources/application.properties");
			p = new Properties();
			p.load(file);
			driver.get(p.getProperty(string));
			WebElement header = driver.findElement(By.xpath("//div[@class='header-main__title top-bar-title']"));
			Assert.assertEquals("Nationwide Children’s Hospital", header.getText());
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Nationwide Children’s Hospital";
			Assert.fail(errorMessage);
		}
	}

	@Then("navigate to {string} page")
	public void navigate_to_page(String string) {
		try {
			By tab = page.getLocator(string);
			page.navigateTo(tab);
		} catch (Throwable e) {
			String errorMessage = "Page not navigated";
			Assert.fail(errorMessage);
		}
	}

	@Then("user {string} with {string}")
	public void user_with(String string, String string1) {
		try {
			By search = page.getLocator(string);
			page.perform(search, string1);
		} catch (Throwable e) {
			String errorMessage = "Page not navigated";
			Assert.fail(errorMessage);
		}

	}

	@Then("perform the {string}")
	public void perform_the(String string) {
		try {
			By button = page.getLocator(string);
			page.navigateTo(button);
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Search";
			Assert.fail(errorMessage);
		}

	}

	@Then("fill patient details form")
	public void fill_patient_details_form() {
		try {
			page.setPatientDetails();
		
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Patient Details step";
			Assert.fail(errorMessage);
		}

	}

	@Then("fill patient contact info form")
	public void fill_patient_contact_info_form() {
		try {
			page.setPatientContactDetails();
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Patient Contact Details step";
			Assert.fail(errorMessage);
		}

	}

	@Then("fill patient parent or guardian form")
	public void fill_patient_parent_or_guardian_form() {
		try {
			page.setPatientParentorGuardianDetails();
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Patient Parent or Guardian Details step";
			Assert.fail(errorMessage);
		}

	}
	

	@Then("fill patient insurance details form")
	public void fill_patient_insurance_details_form() {
		try {
			page.setPatientInsuranceDetails();
		} catch (Throwable e) {
			String errorMessage = "Page not navigated to Insurance Details step";
			Assert.fail(errorMessage);
		}

	}

	@Then("verify landed on the right {string} with {string}")
	public void verify_landed_on_the_right_with(String string, String string1) {
		try {
			By label = page.getLocator(string);
			WebElement element = page.findElement(label);
			WebDriverFactory.scrolltoView(element);
			WebDriverFactory.waitForElementToBeClickable(driver, element, 10, 500);
			Assert.assertTrue(element.getText().contains(string1));
		} catch (Throwable e) {
			String errorMessage = "Page not navigated";
			Assert.fail(errorMessage);
		}
	}

}
