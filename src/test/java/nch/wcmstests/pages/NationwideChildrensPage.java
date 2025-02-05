package nch.wcmstests.pages;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.WebDriverFactory;

public class NationwideChildrensPage extends BasePage {
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

	public void search(By field, String value) {
		WebDriverFactory.scrolltoView(driver.findElement(field));
		WebDriverFactory.waitForElementToBeClickable(driver, driver.findElement(field), 10, 500);
		driver.findElement(field).sendKeys(value);
	}

	

	public WebElement findElement(By label) {
		return driver.findElement(label);
	}

}
