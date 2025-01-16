package nch.wcmstests.pages;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import config.WebDriverFactory;

public class GmeApplicationFormPage extends BasePage {

	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/DD/YYYY");
	LocalDate date = LocalDate.now();
	String formattedDate = date.format(dateFormatter);

	public GmeApplicationFormPage(WebDriver driver) {
		super(driver);
	}

	// Method to wait for an element's visibility
//	private WebElement waitForElement(By locator) {
//		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//	}

	// Method to wait for a list of elements' visibility
//	private List<WebElement> waitForElements(By locator) {
//		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//	}

	// Rotation Request form page locators
	By start_application_button_loc = By.xpath("//strong[normalize-space()='Start Application']");
//	By start_date_loc = By.xpath("//input[@name='rrcStartDate']");
	By start_date_loc = By.name("rrcStartDate");
	By end_date_loc = By.name("rrcEndDate");
//	By end_date_loc = By.xpath("//input[@name='rrcEndDate']");
	By new_to_nch_yes_loc = By.xpath("//input[@id='new-to-NCH-yes']");
	By new_to_nch_no_loc = By.xpath("//input[@id='new-to-NCH-no']");
	By request_rotation_loc = By.xpath("//select[@name='rrcRequestedRotation']");
	By sponsoring_inst_loc = By.xpath("//select[@name='rrcSponsoringInstitution']");
	By current_prog_loc = By.xpath("//select[@name='rrcCurrentProgram']");
	By previous_scr_loc = By.xpath("//form[@name='rotationRequestForm']//button[@type='button']");
	By next_scr_loc = By.xpath("//button[@ng-click='rrc.next()']");
	By request_rot_selector_loc = By.xpath("//select[@name='rrcRequestedRotation']");

	// Applicant Information page
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
	By previous_screen_loc = By.xpath(
			"//form[@name='applicantForm']//button[@type='button']//span[@class='show-for-medium'][normalize-space()='Screen']");
	By nextscreen_AF = By
			.xpath("//button[@ng-click='aic.next()']//span[@class='show-for-medium'][normalize-space()='Screen']");

	// Emergency info page
	By emergency_contact = By.xpath("//input[@name='ecicEmergencyContact']");
	By phone = By.xpath("//input[@name='ecicTelephone']");
	By nextscr_EC = By.xpath("//button[@ng-click='ecic.next()']");

	// Med school info page
	By mdttl = By.xpath("//h2[@id='medicalSchoolInformationLabel']");
	By state = By.name("msicState");
	By med_cty = By.name("msicCity");
	By med_sch = By.xpath("//select[@name='msicMedicalSchool']");
	By degree = By.xpath("//select[@name='msicDegree']");
	By start_mth = By.xpath("//select[@name='msicStartMonth']");
	By start_yr = By.xpath("//input[@name='msicStartYear']");
	By grad_mnth = By.xpath("//select[@name='msicGradMonth']");
	By grad_yr = By.xpath("//input[@name='msicGradYear']");
	By nextscr_MDC = By
			.xpath("//button[@ng-click='msic.next()']//span[@class='show-for-medium'][normalize-space()='Screen']");

	// PG info page
	By pgyttl = By.xpath("//h2[@id='postGraduateTrainingLabel']");
	By pgy = By.xpath("//select[@name='pgtcPGY']");
//	By bgmth = By.xpath("//select[@name='pgtcBeginMonth']");
	By bgmth = By.name("pgtcBeginMonth");
	By bgyr = By.xpath("//input[@name='pgtcBeginYear']");
	By end_mth = By.xpath("//select[@name='pgtcEndMonth']");
	By end_yr = By.xpath("//input[@name='pgtcEndYear']");
	By speciality = By.xpath("//select[@name='pgtcSpecialty']");
	By inst = By.xpath("//select[@name='pgtcInstitution']");
	By nextscr_pgy = By.xpath("//button[@ng-click='pgtc.next()']");

	// confirmation checkbox
	By selectchbx = By.xpath("//input[@name='rcICertify']");
	//submit button
	By submit = By.xpath("//button[normalize-space()='Submit']");
	
	
	public void startApplication() {
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

	public void setRequest_rotation_loc() {
		getRandomOption(driver.findElement(request_rotation_loc));
	}

	public void setSponsoring_inst_loc() {
		getRandomOption(driver.findElement(sponsoring_inst_loc));
	}

	public void setCurrent_prog_loc() {
		getRandomOption(driver.findElement(current_prog_loc));
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

	public void setPGYRotation() {
		getRandomOption(driver.findElement(pgy_rotation_loc));
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

	public void setECEmail(String ecemail) {
		driver.findElement(emergency_contact).sendKeys(ecemail);
	}

	public void setPhone(String ph) {
		driver.findElement(phone).sendKeys(ph);
	}

	public void setECNextScr() {
		driver.findElement(nextscr_EC).click();
	}

	public void setState() {
		getRandomOption(driver.findElement(state));
	}

	public void setCity() {
		getRandomOption(driver.findElement(med_cty));
	}

	public void setMedsch() {
		getRandomOption(driver.findElement(med_sch));
	}

	public void setDegree() {
		getRandomOption(driver.findElement(degree));
	}

	public void setStrMth() {
		getRandomOption(driver.findElement(start_mth));
	}

	public void setStrYr() {
		String pastyr = String.valueOf(Year.now().getValue() - 2);
		driver.findElement(start_yr).sendKeys(pastyr);
	}

	public void setGradMnth() {
		getRandomOption(driver.findElement(grad_mnth));
	}

	public void setGradyr() {
		String futyr = String.valueOf(Year.now().getValue() + 2);
		driver.findElement(grad_yr).sendKeys(futyr);
	}

	public void setNextMDI() {
		driver.findElement(nextscr_MDC).click();
	}

	public void setPGY() {
		getRandomOption(driver.findElement(pgy));
	}

	public void setBmth() {
		getRandomOption(driver.findElement(bgmth));
	}

	public void setBGyr() {
		String bgy = String.valueOf(Year.now().getValue() - 1);
		driver.findElement(bgyr).sendKeys(bgy);
	}

	public void setEndMth() {
		getRandomOption(driver.findElement(end_mth));
	}

	public void setEndYr() {
		driver.findElement(end_yr).sendKeys(String.valueOf(Year.now()));
	}

	public void setSpec() {
		getRandomOption(driver.findElement(speciality));
	}

	public void setInst() {
		getRandomOption(driver.findElement(inst));
	}

	public void setNextPGY() {
		driver.findElement(nextscr_pgy).click();
	}

	public void setCheckbx() {
		WebElement selectchbxelement = driver.findElement(selectchbx);
		WebDriverFactory.scrolltoView(selectchbxelement);
		WebDriverFactory.waitForElementToBeClickable(driver, selectchbxelement, 10, 500);
		driver.findElement(selectchbx).click();
	}

	public void submit() {
		WebElement submitelement = driver.findElement(submit);
		WebDriverFactory.scrolltoView(submitelement);
		WebDriverFactory.waitForElementToBeClickable(driver, submitelement, 10, 500);
		driver.findElement(submit).click();
		
	}

	
	public void getRandomOption(WebElement dropdownElement) {
		WebDriverFactory.scrolltoView(dropdownElement);
		WebElement element= WebDriverFactory.waitForElementToBeClickable(driver, dropdownElement, 10, 500);;
		try {
			 
			Select select = new Select(element);

			List<String> ignoreOptions = Arrays.asList("OTHER Please enter below", "other", "Select", "IDAHO",
					"WYOMING", "MONTANA", "DELAWARE", "ALASKA");
			// Step 3: Get all the available options
			List<WebElement> validOptions = select.getOptions().stream()
					.filter(option -> !option.getText().trim().isEmpty())
					.filter(option -> ignoreOptions.stream()
							.noneMatch(ignore -> ignore.equalsIgnoreCase(option.getText())))
					.collect(Collectors.toList());

			if (validOptions.isEmpty()) {
				throw new IllegalStateException("No valid options available after filtering 'OTHER' from dropdown.");
			} else if (validOptions.size() == 1) {
				select.selectByVisibleText(validOptions.get(0).getText());
				System.out.println("Only one valid option selected: " + validOptions.get(0).getText());
			} else {
				// Randomly select one option from the filtered list
				int randomIndex = new Random().nextInt(validOptions.size());
				WebElement selectedOption = validOptions.get(randomIndex);
				select.selectByVisibleText(selectedOption.getText());
				System.out.println("Random option selected: " + selectedOption.getText());
			}
		} catch (Exception e) {
			System.err.println("Error selecting a random option: " + e.getMessage());
			throw e; // Rethrow for test failure reporting
		}

	}

	
}
