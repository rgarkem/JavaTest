package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {

	private WebElement searchTextBox;
	private WebElement continueButton;
	
	public SearchPage(WebDriver driver) {
		super(driver);
		url = "https://vehicleenquiry.service.gov.uk/";
	}

	public void searchVehicle(String regNo) {
		searchTextBox = driver.findElement(By.id("Vrm"));
		searchTextBox.sendKeys(regNo);
		
		continueButton = driver.findElement(By.name("Continue"));
		continueButton.click();
	}
	
}
