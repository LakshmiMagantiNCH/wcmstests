package nch.wcmstests.pages;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import config.WebDriverFactory;
import util.UtilClass;

public class NationwideChildrensPage extends BasePage {
	
	private Faker faker = new Faker();
	By find_a_doc = By.cssSelector("li[aria-label='Find A Doctor'] a[class='nav-main__toggle ']");
	By search_a_doc_label = By.cssSelector("#simple-search-box");
	By find_a_doc_label = By.cssSelector(".contentHeading");
	By search_field = By.cssSelector("#simple-search-box");
	By search = By.cssSelector("i[aria-label='simple-search-box-search icon']");
	By success = By.xpath("//a[normalize-space()='Erin L. Gross, DDS, MS, PhD']");
	By not_found_page = By.xpath("//div[contains(text(),'There are no matching results for your search.')]");
	By location = By.xpath("//a[contains(@class,'nav-main__toggle')][normalize-space()='Locations']");
	By location_page_label = By.xpath("//h1[contains(@class,'contentHeading')]");
	By search_location_field = By.xpath("//input[@placeholder='Location Name or Type']");
	By zip_code_text_field = By.xpath("//input[@id='near-address-input']");
	By go_button = By.cssSelector("button[type='submit']");
	By locations_page = By.xpath("//div[contains(text(),'There are')]");
	By location_name = By.xpath("//input[@placeholder='Location Name or Type']");
	
	//Request an appointment section
	By request_appointment = By.xpath("//ul[@class='nav-utility__menu nav-utility__menu--top menu align-right']//a[contains(@class,'nav-utility__link')][normalize-space()='Request an Appointment']");
	By request_app_page_title = By.cssSelector(".h1");
	By parent_or_guardian = By.xpath("//i[@class='multistep-form__icon icon icon--guardian']");
	By new_patient =  By.xpath("//button[@class='card--link']//div[@class='text-center vertical-margin-half']");
	By primary_care = By.xpath("//button[@id='primary-link']//i[@class='icon icon--more']");
	By new_patient_radio_button = By.cssSelector("#seenAtNCH-no");
	By continue_button = By.cssSelector("button[class='button']");
	
	//Appointment Request form fields
	By patient_first_name = By.xpath("//input[@name='firstName']");
	By patient_last_name = By.xpath("//input[@name='lastName']");
	By patient_dob = By.xpath("//input[@id='date']");
	By patient_gender = By.xpath("//input[@id='sex-Female']");
	By patient_gender_identity = By.xpath("//input[@id='gender-Female']");
	By patient_health_ins_covered = By.xpath("//input[@id='hasInsurance-Yes']");
	By patient_primary_care_doc_info = By.xpath("//input[@id='hasPCP-no']");
	By patient_nch_history = By.xpath("//input[@id='seenAtNCH-no']");
	By next_button = By.xpath("//button[@type='submit']");
	
	
	//Contact info form
	By street_info = By.cssSelector("input[name='address1']");
	By city_info = By.cssSelector("input[name='city']");
	By state_info = By.cssSelector("select[name='state']");
	By zipcode_info = By.cssSelector("input[title='Please enter a valid zip code']");
	By phone_info = By.cssSelector("input[title='Please enter a valid phone number: XXX-XXX-XXXX']");
	By phone_type_info = By.cssSelector("#phoneType-Cell");
	By text_authori = By.cssSelector("#wantTextMessages-Yes");
	By email_info = By.cssSelector("input[title='Enter a valid email address in name@email.com format']");
	By preffered_lang_info = By.cssSelector("select[name='preferredLanguage']");
	By contact_info_next_button = By.cssSelector("button[type='submit']");
	
	//Parent/Guardian info form
	By parent_first_name = By.cssSelector("input[name='firstName']");
	By parent_last_name = By.cssSelector("input[name='lastName']");
	By relation_info = By.cssSelector("#relationToChild-Mother");
	By parent_confirmation = By.cssSelector("#differentGuarantor-No");
	By parent_info_next = By.cssSelector(".icon.icon--arrow-right");
	
	By reason_for_appt = By.cssSelector("#null");
	By preffered_loc = By.cssSelector("select[name='preferredLocationId']");
	By appointment_next = By.cssSelector("button[type='submit']");
	
	//Insurance info
	By insurance_plan = By.cssSelector("input[name='plan']");
	By insurance_id = By.cssSelector("input[name='idNumber']");
	By group_no = By.cssSelector("input[name='groupNumber']");
	By guarantor_info = By.cssSelector("#guarantorIsSubscriber-Yes");
	By terms_info = By.cssSelector("#lrgwi0-accordion-label"); 
	By accept_info = By.cssSelector("#accept-yes");
	By captcha = By.cssSelector("div[class='recaptcha-checkbox-border']");
	
	By send_reuqest_button = By.cssSelector(".button.long.ng-star-inserted");
	By appoint_conf_page = By.cssSelector(".h1");
	
	//search locators
	By search_bar = By.cssSelector("#site-header-search-box");
	By search_buttn = By.xpath("//button[@class='search__button button']//i[@aria-label='search icon']");
	By search_header = By.cssSelector(".contentHeading");
	
	
	public By getLocator(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            return (By) field.get(this);
        } catch (Exception e) {
            throw new RuntimeException("Locator not found: " + fieldName, e);
        }
    }
	
	public NationwideChildrensPage(WebDriver driver) {
		super(driver);

	}

	public void navigateTo(By element) {
		WebDriverFactory.scrolltoView(driver.findElement(element));
		WebDriverFactory.waitForElementToBeClickable(driver, driver.findElement(element), 10, 500);
		driver.findElement(element).click();
	}

	public void perform(By field, String value) {
		WebDriverFactory.scrolltoView(driver.findElement(field));
		WebDriverFactory.waitForElementToBeClickable(driver, driver.findElement(field), 10, 500);
		driver.findElement(field).sendKeys(value);
	}

	

	public WebElement findElement(By label) {
		return driver.findElement(label);
	}

	public void setPatientDetails() {
		perform(patient_first_name,(faker.name().firstName()));
		perform(patient_last_name, (faker.name().lastName()));
		perform(patient_dob, (UtilClass.generateCurrentDate()));
		navigateTo(patient_gender);
		navigateTo(patient_gender_identity);
		navigateTo(patient_health_ins_covered);
		navigateTo(patient_primary_care_doc_info);
		navigateTo(patient_nch_history);
		navigateTo(next_button);
	}

	public void setPatientContactDetails() {
		perform(street_info,"6737 Pine St");
		perform(city_info,"Elyria");
		perform(state_info,"Ohio");
		perform(zipcode_info,"44036");
		perform(phone_info,"6142342345");
		navigateTo(phone_type_info);
		navigateTo(text_authori);
		perform(email_info,(faker.internet().emailAddress()));
		perform(preffered_lang_info,"English");
		navigateTo(contact_info_next_button);
		
		
	}

	public void setPatientParentorGuardianDetails() {
		perform(parent_first_name,(faker.name().firstName()));
		perform(parent_last_name,(faker.name().lastName()));
		navigateTo(relation_info);
		navigateTo(parent_confirmation);
		navigateTo(parent_info_next);
		
	}

	public void setPatientInsuranceDetails() {
		perform(insurance_plan,"6737");
		perform(insurance_id,"673777");
		perform(group_no,"6737777");
		navigateTo(guarantor_info);
		navigateTo(terms_info);
		navigateTo(accept_info);
//		navigateTo(captcha);
//		navigateTo(send_reuqest_button);
		
	}

}
