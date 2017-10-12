package pageObjects;

import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * An abstract base page, with common functionality needed across all page objects
 */
public abstract class BasePage {
	protected WebDriver driver;
	protected String url;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Wait for the page to load. 
	 * Base implementation is to validate actual URL with page's expected URL 
	 * Can be overridden to look for visibility of specific elements on the page to indicate successful load. 
	 */
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return driver.getCurrentUrl().equals(url);
			}
		});
	}
	
	/**
	 * Launch the page in a browser, using the page's expected url
	 */
	public void launchPage() {
		driver.navigate().to(url);
		waitForPageLoad();
	}
}
