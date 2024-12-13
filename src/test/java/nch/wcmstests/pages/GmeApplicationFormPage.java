package nch.wcmstests.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class GmeApplicationFormPage extends BasePage{
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/DD/YYYY");
		LocalDate date = LocalDate.now();
		String formattedDate = date.format(dateFormatter);
		
		

		public GmeApplicationFormPage(WebDriver driver) {
			super(driver);
		}

		

	   //Rotation Request form page locators
	   By start_application_button_loc = By.xpath("//strong[normalize-space()='Start Application']");
	   By start_date_loc = By.xpath("//input[@name='rrcStartDate']");
	   By end_date_loc = By.xpath("//input[@name='rrcEndDate']");
	   By new_to_nch_yes_loc = By.xpath("//input[@id='new-to-NCH-yes']");
	   By new_to_nch_no_loc = By.xpath("//input[@id='new-to-NCH-no']");
	   By request_rotation_loc = By.xpath("//select[@name='rrcRequestedRotation']");
	   By sponsoring_inst_loc = By.xpath("//select[@name='rrcSponsoringInstitution']");
	   By current_prog_loc = By.xpath("//select[@name='rrcCurrentProgram']");
	   By previous_scr_loc = By.xpath("//form[@name='rotationRequestForm']//button[@type='button']");
	   By next_scr_loc = By.xpath("//button[@ng-click='rrc.next()']");
	   By request_rot_selector_loc = By.xpath("//select[@name='rrcRequestedRotation']");
	   
	   //Applicant Information page
	   By first_name_loc = By.xpath("//input[@name='aicFirstName']");
	   By last_name_loc = By.xpath("//input[@name='aicLastName']");
	   By covid_vaccine_type_loc = By.xpath("//select[@name='aicCovidVaccineType']");
	   By covid_first_vaccine_date_loc = By.xpath("//input[@name='aicCovidFirstVaccine']");
	   By email_loc = By.xpath("//input[@name='aicEmail']");
	   By med_lic_loc = By.xpath("//input[@name='aicOhioMedicalLicenseLabel']");
	   By no_DEA_number_loc = By.xpath("//input[@id='yes-dea']");
	   By npi_number_loc = By.xpath("//input[@name='aicNPI']");
	   By pgy_rotation_loc = By.xpath("//select[@name='aicPGYAtRotation']");
	   By ssn_loc = By.xpath("//input[@name='aicSSN']");
	   By birth_date_loc = By.xpath("//input[@name='aicBirthdate']");
	   By previous_screen_loc = By.xpath("//form[@name='applicantForm']//button[@type='button']//span[@class='show-for-medium'][normalize-space()='Screen']");
	   By nextscreen_AF = By.xpath("//button[@ng-click='aic.next()']//span[@class='show-for-medium'][normalize-space()='Screen']");
	   
	   //A

	   public void startApplication()
		{
			driver.findElement(start_application_button_loc).click();
		}

	
		public void setStart_date_loc(String string) {
			driver.findElement(start_date_loc).sendKeys(string);
		}

	   public void setEnd_date_loc(String string2) {
			driver.findElement(end_date_loc).sendKeys(string2);
		}

		public void setNew_to_nch_yes_loc() {
			driver.findElement(new_to_nch_yes_loc).click();
		}

		public void setNew_to_nch_no_loc() {
			driver.findElement(new_to_nch_no_loc).click();
		}

	
		public void setRequest_rotation_loc(String string3) {
			driver.findElement(request_rot_selector_loc).click();
			driver.findElement(request_rotation_loc).sendKeys(string3);
		}

		public void setSponsoring_inst_loc(String string4) {
			driver.findElement(sponsoring_inst_loc).sendKeys(string4);
		}

		public void setCurrent_prog_loc(String string5) {
			driver.findElement(current_prog_loc).sendKeys(string5);
		}

		public void setPrevious_scr_loc() {
			driver.findElement(previous_scr_loc).click();
		}

		public void setNext_scr_loc() {
			driver.findElement(next_scr_loc).click();
		}
		
		public void setFirstName(String firstname) {
			driver.findElement(first_name_loc).sendKeys(firstname);		
			}
		
		public void setLastName(String lastname) {
			driver.findElement(last_name_loc).sendKeys(lastname);		
			}

		public void setEmail(String email) {
			driver.findElement(email_loc).sendKeys(email);		
			}
		
		public void setMedicalLicense(int lic) {
			driver.findElement(med_lic_loc).sendKeys(String.valueOf(lic));		
			}
		
		public void select_Radio_Button_for_DEA() {
			driver.findElement(no_DEA_number_loc).click();
		}
		
		public void setNPI(int npi) {
			driver.findElement(npi_number_loc).sendKeys(String.valueOf(npi));
		}
		
		public void setPGYRotation(String pgynumber) {
			driver.findElement(pgy_rotation_loc).sendKeys(pgynumber);		
			}
		
		public void setSSN(String ssn) {
			driver.findElement(ssn_loc).sendKeys(ssn);		
			}
		
		public void setDOB(String dob) {
			driver.findElement(birth_date_loc).sendKeys(dob);		
			}
		
		public void setNextScr_on_AF() {
			driver.findElement(nextscreen_AF).click();
		}
}
