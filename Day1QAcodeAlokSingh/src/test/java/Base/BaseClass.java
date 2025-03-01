package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	protected WebDriver driver ;
	
	@BeforeTest
	public void openBrowser() {

	  driver = new ChromeDriver();

		driver.get("https://seleniumbase.io/simple/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		System.out.println(driver.getTitle());

	}

	@AfterTest
	
	
	public void closeBrowser() {
		
		driver.quit();

	}
}
