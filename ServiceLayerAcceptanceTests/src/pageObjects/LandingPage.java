package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * The first landing page of the DVLA site 
 * https://www.gov.uk/get-vehicle-information-from-dvla
 */
public class LandingPage extends BasePage{

	private WebElement startButton;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		url = "https://www.gov.uk/get-vehicle-information-from-dvla";
	}
	
	/**
	 * Clicks the get started button and starts the search 
	 */
	public void startSearch() {
		startButton = driver.findElement(By.id("get-started")).findElement(By.tagName("a"));
		startButton.click();
	}
}
