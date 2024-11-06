package stepDefinitions;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import nch.wcmstests.pages.GmeApplicationFormPage;

public class Form {
	
	public GmeApplicationFormPage page;
	public WebDriver driver;
	public Properties p;

	@Before
	public void setup()
	{
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
	public void user_opens(String string) throws IOException {
		FileReader file = new FileReader("./src/test/resources/application.properties");
		p = new Properties();
		p.load(file);
	   driver.get(p.getProperty(string));
	}

	@Then("user select start application form button")
	public void user_select_start_application_form_button() throws InterruptedException {
	   
	    page.startApplication();
	    Thread.sleep(3000);
	}

	@Then("fill required fields with {string} {string} {string} {string} {string}")
	public void fill_required_fields_with(String string, String string2, String string3, String string4, String string5) {
	    page.setStart_date_loc(string);
	    page.setEnd_date_loc(string2);
	    page.setNew_to_nch_yes_loc();
	    page.setRequest_rotation_loc(string3);
	    page.setSponsoring_inst_loc(string4);
	    page.setCurrent_prog_loc(string5);
	}

	@When("select next screen")
	public void select_next_screen() {
	    page.setNext_scr_loc();
	}
}
