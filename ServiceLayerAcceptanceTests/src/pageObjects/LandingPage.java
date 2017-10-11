package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage{

	private WebElement startButton;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		url = "https://www.gov.uk/get-vehicle-information-from-dvla";
	}
	
	public void startSearch() {
		startButton = driver.findElement(By.id("get-started")).findElement(By.tagName("a"));
		startButton.click();
	}
}
