package base;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	WebDriver driver;
	@Before(order = 0)
	public void setup() {
		driver = WebDriverManager.getDriver();
	}
	@After(order = 0)
    public void tearDown() {
        // Uncomment this if you want to close the browser only at the end of all tests
         WebDriverManager.closeDriver();
    }
}
