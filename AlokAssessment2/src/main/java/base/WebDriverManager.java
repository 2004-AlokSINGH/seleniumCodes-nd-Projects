package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
	private static WebDriver driver;
    private WebDriverManager() {}
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(); 
        }
        return driver;
    }
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
