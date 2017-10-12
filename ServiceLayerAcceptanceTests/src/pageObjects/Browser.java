package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Singleton representation of a browser. 
 * Ensures that the test only opens a single browser window
 */
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
	
	/**
	 * fetch the singleton WebDriver instance, for the common browser instance
	 * If one has not been instantiated yet, then a new instance is created 
	 */
	public static WebDriver getDriver() { 
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	/**
	 * Close the singleton Browser instance
	 */
	public static void Quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
