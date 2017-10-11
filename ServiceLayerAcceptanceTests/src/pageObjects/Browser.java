package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Browser {

	private static WebDriver driver;
	private static String chromeDriverPath = "Dependencies\\chromedriver_win32\\chromedriver.exe"; 
			
	@Before
	public void Setup() {
		Browser.getDriver();
	}
	
	@After
	public void TearDown() {
		Browser.Quit();
	}
	
	public static WebDriver getDriver() { 
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	public static void Quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
