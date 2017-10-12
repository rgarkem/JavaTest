package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Search page which has the input field for searching vehicle details
 * https://vehicleenquiry.service.gov.uk
 */
public class SearchPage extends BasePage {

	private WebElement searchTextBox;
	private WebElement continueButton;
	
	public SearchPage(WebDriver driver) {
		super(driver);
		url = "https://vehicleenquiry.service.gov.uk/";
	}

	/**
	 * Enters the regNo in the input box, and clicks submit button
	 * @param regNo
	 */
	public void searchVehicle(String regNo) {
		searchTextBox = driver.findElement(By.id("Vrm"));
		searchTextBox.sendKeys(regNo);
		
		continueButton = driver.findElement(By.name("Continue"));
		continueButton.click();
	}
	
}
