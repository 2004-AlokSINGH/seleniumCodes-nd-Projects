package page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.WebDriverManager;
import configppt.ConfigureProperties;

public class LoginPage {
	WebDriver driver;

	public LoginPage() {
		this.driver = WebDriverManager.getDriver();
	}

	private By username = By.id("user");
	private By password = By.id("pass");
	private By loginBtn = By.xpath("//*[@id=\"right_col_top\"]/form/div/input");
	private By logoutBtn = By.xpath("//*[@id=\"user_bar\"]/ul/li[1]/a");
	private By loginMessage = By.xpath("//*[@id=\"right_col_top_err\"]");

	private By allWeblink = By.tagName("a");

	public void enterUsername(String user) {
		driver.findElement(username).sendKeys(user);
	}

	public void enterPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}

	public void entercredentials() {
		ConfigureProperties config = new ConfigureProperties();
		driver.findElement(By.id("user")).sendKeys(config.getProperty("validUsername"));
		driver.findElement(By.id("pass")).sendKeys(config.getProperty("validPassword"));
		;
	}

	public void clickLogin() {
		driver.findElement(loginBtn).click();

	}

	public void clickLogout() {
		driver.findElement(logoutBtn).click();
	}

	public String getMessage() {
		return driver.findElement(loginMessage).getText();
	}

	public List<WebElement> getAlllink() {
		return driver.findElements(allWeblink);
	}

	@SuppressWarnings("deprecation")
	public void printUrls() {
		System.out.println("Extracted URLs:");
		for (WebElement link : getAlllink()) {
			String url = link.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				System.out.println(url);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void validateUrls() {
		System.out.println("Validating URLs...");
		String originalTab = driver.getWindowHandle();
		for (WebElement currlink : getAlllink()) {
			String url = currlink.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				System.out.println("Checking URL: " + url);
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(currlink).keyUp(Keys.CONTROL).perform();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
				wait.until(ExpectedConditions.numberOfWindowsToBe(2));
				String newTab = null;
				for (String tab : driver.getWindowHandles()) {
					if (!tab.equals(originalTab)) {
						newTab = tab;
						driver.switchTo().window(newTab);
						break;
					}
				}
				if (newTab == null) {
					System.out.println("Error: Could not switch to the new tab for URL: " + url);
					continue;
				}
				int statusCode = getStatusCode(url);
				Assert.assertTrue(statusCode >= 200 && statusCode < 400, "Broken link found: " + url);
				driver.close();
				driver.switchTo().window(originalTab);
			}
		}

		System.out.println("All links validated successfully!");
	}

	private int getStatusCode(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return ((Long) js.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];" + "fetch(arguments[0], { method: 'HEAD' })"
						+ ".then(response => callback(response.status))" + ".catch(() => callback(500));",
				url)).intValue();
	}

}
