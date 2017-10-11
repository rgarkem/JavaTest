package pageObjects;

import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	protected WebDriver driver;
	protected String url;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return driver.getCurrentUrl().equals(url);
			}
		});
	}
	
	public void launchPage() {
		driver.navigate().to(url);
		waitForPageLoad();
	}
}
