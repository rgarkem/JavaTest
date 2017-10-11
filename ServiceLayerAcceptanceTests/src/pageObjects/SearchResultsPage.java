package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

	private WebElement backLink;
	private WebElement regNo;
	private WebElement colour;
	
	public SearchResultsPage(WebDriver driver) {
		super(driver);
		url = "https://vehicleenquiry.service.gov.uk/ConfirmVehicle";
	}

	public String getRegNo() {
		regNo = driver.findElement(
				By.xpath("//li[@class='list-summary-item']/span[text()[contains(.,'Registration number')]]/../span[2]"));
		return regNo.getText();
	}
	
	public String getColour() {
		colour = driver.findElement(
				By.xpath("//li[@class='list-summary-item']/span[text()[contains(.,'Colour')]]/../span[2]"));
		return colour.getText();
	}
	
	public void backToSearchPage() {
		backLink = driver.findElement(By.xpath("//a[@class='back-to-previous link-back']"));
		backLink.click();
	}
}
