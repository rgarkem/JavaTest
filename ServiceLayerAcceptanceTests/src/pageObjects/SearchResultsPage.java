package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * The results page showing the details of searched vehicle 
 * https://vehicleenquiry.service.gov.uk/ConfirmVehicle
 */
public class SearchResultsPage extends BasePage {

	private WebElement backLink;
	private WebElement regNo;
	private WebElement colour;
	
	public SearchResultsPage(WebDriver driver) {
		super(driver);
		url = "https://vehicleenquiry.service.gov.uk/ConfirmVehicle";
	}

	/**
	 * Fetch the vehicle reg No from the page 
	 * @return
	 */
	public String getRegNo() {
		regNo = driver.findElement(
				By.xpath("//li[@class='list-summary-item']/span[text()[contains(.,'Registration number')]]/../span[2]"));
		return regNo.getText();
	}
	
	/**
	 * Fetch the vehicle colour from the page
	 * @return
	 */
	public String getColour() {
		colour = driver.findElement(
				By.xpath("//li[@class='list-summary-item']/span[text()[contains(.,'Colour')]]/../span[2]"));
		return colour.getText();
	}
	
	/**
	 * Clicks the back button and takes user back to the search page 
	 */
	public void backToSearchPage() {
		backLink = driver.findElement(By.xpath("//a[@class='back-to-previous link-back']"));
		backLink.click();
	}
}
